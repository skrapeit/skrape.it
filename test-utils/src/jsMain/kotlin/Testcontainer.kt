import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.js.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.await
import kotlinx.serialization.json.*

typealias FileMapping = Pair<String, String>

actual object Testcontainer {

    internal val client =
        HttpClient(Js) {
            install(ContentNegotiation) {
                json()
            }
            install(HttpRedirect)
        }

    actual data class Wiremock(
        actual val httpUrl: String,
        actual val httpsUrl: String,
        val httpPort: Int
    ) {
        actual val ktorClient: HttpClient = client.config {  }.also {
            //Since fetch doesn't support relaxing ssl fall back to http
            it.plugin(HttpSend).intercept { request ->
                request.url.set(scheme = "http", port = httpPort)
                execute(request)
            }
        }
    }

    private lateinit var wiremockInstance: Wiremock
    private lateinit var httpBinInstance: String

    actual suspend fun getWiremock(): Wiremock {
        if (!this::wiremockInstance.isInitialized) {
            val wiremockImage = "wiremock/wiremock:2.34.0-alpine"
            val httpPort = 8080
            val httpsPort = 8443
            val internalPorts = intArrayOf(httpPort, httpsPort)
            val commands = arrayOf("--https-port", "8443", "--enable-stub-cors", "--verbose")
            val fileMappings = arrayOf(FileMapping("/__files", "/home/wiremock/__files"))
            if (Platform.WEB.value) {
                val containerInfo = client.post("http://localhost:9876/containers/create") {
                    contentType(ContentType.Application.Json)
                    setBody(buildJsonObject {
                        put("image", wiremockImage)
                        put("ports", buildJsonArray {
                            internalPorts.forEach { add(it) }
                        })
                        put("command", buildJsonArray {
                            commands.forEach { add(it) }
                        })
                        put("mappings", buildJsonArray {
                            fileMappings.forEach {
                                add(buildJsonArray {
                                    add(it.first)
                                    add(it.second)
                                })
                            }
                        })
                    })

                }
                val jsonResponse = containerInfo.body<JsonObject>()
                val address = jsonResponse.getValue("address").jsonPrimitive.content
                val ports = jsonResponse["ports"]?.unsafeCast<JsonObject>() ?: emptyMap<String, String>()
                wiremockInstance = Wiremock(
                    httpUrl = "http://$address:${ports[httpPort.toString()]}",
                    httpsUrl = "https://$address:${ports[httpsPort.toString()]}",
                    ports[httpPort.toString()].toString().toInt()
                )
            } else if (Platform.NODE.value) {
                val rootProjectPath = js("process.env[\"ROOT_PROJECT_PATH\"]")
                var container = GenericContainer(wiremockImage)
                    .withExposedPorts(*internalPorts)
                    .withCmd(commands)
                fileMappings.forEach { (from, to) ->
                    container =
                        container.withBindMount("$rootProjectPath/test-utils/src/commonMain/resources$from", to, "ro")
                }
                val startedContainer = container.start().await()
                wiremockInstance = Wiremock(
                    httpUrl = "http://${startedContainer.getHost()}:${startedContainer.getMappedPort(httpPort)}",
                    httpsUrl = "https://${startedContainer.getHost()}:${startedContainer.getMappedPort(httpsPort)}",
                    startedContainer.getMappedPort(httpPort)
                )
            } else {
                error("Unknown js platform")
            }
        }
        return wiremockInstance
    }

    actual suspend fun getHttpBin(): String {
        if (!this::httpBinInstance.isInitialized) {
            val httpBinImage = "kennethreitz/httpbin:latest"
            val httpBinPort = 80
            if (Platform.WEB.value) {
                val containerInfo = client.post("http://localhost:9876/containers/create") {
                    contentType(ContentType.Application.Json)
                    setBody(buildJsonObject {
                        put("image", httpBinImage)
                        put("ports", buildJsonArray {
                            add(httpBinPort)
                        })
                    })

                }
                val jsonResponse = containerInfo.body<JsonObject>()
                val address = jsonResponse.getValue("address").jsonPrimitive.content
                val ports = jsonResponse["ports"]?.unsafeCast<JsonObject>() ?: emptyMap<String, String>()
                httpBinInstance = "http://$address:${ports["80"]}"
            } else if (Platform.NODE.value) {
                val container = GenericContainer(httpBinImage)
                    .withExposedPorts(httpBinPort)
                val startedContainer = container.start().await()
                httpBinInstance = "http://${startedContainer.getHost()}:${startedContainer.getMappedPort(httpBinPort)}"
            } else {
                error("Unsupported JS Platform")
            }

        }
        return httpBinInstance
    }

}