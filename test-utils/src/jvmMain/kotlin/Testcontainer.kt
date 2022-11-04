import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import org.testcontainers.containers.BindMode
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.utility.DockerImageName
import java.security.cert.X509Certificate
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

actual object Testcontainer {
    actual data class Wiremock(
        actual val httpUrl: String,
        actual val httpsUrl: String,
        actual val ktorClient: HttpClient
    )

    val wiremock: Wiremock by lazy {
        with(WireMockContainer().apply {
            //if (!isWindows) {
                start()
            //}
        }) {
            Wiremock(
                httpUrl = "http://$containerIpAddress:${getMappedPort(httpPort)}",
                httpsUrl = "https://$containerIpAddress:${getMappedPort(httpsPort)}",
                ktorClient = HttpClient(CIO) {
                    install(ContentNegotiation) {
                        json()
                    }
                    engine {
                        https {
                            trustManager = object : X509TrustManager {
                                override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
                                }

                                override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
                                }

                                override fun getAcceptedIssuers(): Array<X509Certificate> = emptyArray()

                            }
                        }
                    }
                }
            )
        }
    }

    val httpBin: String by lazy {
        with(HttpBinContainer().apply {
            //if (!isWindows) {
                start()
            //}
        }) {
            "http://$containerIpAddress:${getMappedPort(internalPort)}"
        }
    }

    private val isWindows: Boolean by lazy {
        System.getProperty("os.name").startsWith("Windows")
    }

    actual suspend fun getWiremock(): Wiremock = wiremock

    actual suspend fun getHttpBin(): String = httpBin
}

private class WireMockContainer(
    version: String = "2.9.0-alpine",
    val httpPort: Int = 8080,
    val httpsPort: Int = 8443,
) : GenericContainer<WireMockContainer>(DockerImageName.parse("wiremock/wiremock:$version")) {
    init {
        withExposedPorts(httpPort, httpsPort)
        withCommand("--https-port $httpsPort --verbose")
        withClasspathResourceMapping("./__files", "/home/wiremock/__files/", BindMode.READ_ONLY)
        waitingFor(Wait.forListeningPort())
    }
}


private class HttpBinContainer(
    version: String = "latest",
    val internalPort: Int = 80,
) : GenericContainer<HttpBinContainer>(DockerImageName.parse("kennethreitz/httpbin:$version")) {
    init {
        withExposedPorts(internalPort)
    }
}

