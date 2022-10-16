import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.js.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.json.*

actual object Testcontainer {

    private val client =
        HttpClient(Js) {
            install(ContentNegotiation) {
                json()
            }
            install(HttpRedirect)
        }

    actual data class Wiremock(
        actual val httpUrl: String,
        actual val httpsUrl: String
    ) {
        actual val ktorClient: HttpClient = client
    }

    actual val wiremock: Wiremock
        get() = TODO("Not yet implemented")

    actual val httpBin: String
        get() = TODO("Not yet implemented")

    private lateinit var wiremockInstance: Wiremock
    private val mutex = Mutex()

    actual suspend fun getWiremock(): Wiremock {
        mutex.withLock {
            if (!this::wiremockInstance.isInitialized) {
                println("Creating wiremock container")
                val containerInfo = client.post("http://localhost:9876/containers/create") {
                    contentType(ContentType.Application.Json)
                    setBody(buildJsonObject {
                        put("image", "wiremock/wiremock:2.34.0-alpine")
                        put("ports", buildJsonArray {
                            add(8080)
                            add(8443)
                        })
                        put("command", buildJsonArray {
                            add("--https-port")
                            add("8443")
                            add("--enable-stub-cors")
                            add("--print-all-network-traffic")
                        })
                        put("mapResources", buildJsonObject {
                            put("realPath", "/__files")
                            put("containerPath", "/home/wiremock/__files/")
                        })
                    })

                }
                println("Got a response")
                val jsonResponse = containerInfo.body<JsonObject>()
                println("Contains $jsonResponse")
                val address = jsonResponse.getValue("address").jsonPrimitive.content
                val ports = jsonResponse["ports"]?.unsafeCast<JsonObject>() ?: emptyMap<String, String>()
                wiremockInstance = Wiremock(
                    httpUrl = "http://$address:${ports["8080"]}",
                    httpsUrl = "https://$address:${ports["8443"]}"
                )
            }
        }
        return wiremockInstance
    }

    actual suspend fun getHttpBin(): String {
        TODO("Not yet implemented")
    }

}