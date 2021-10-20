import com.github.tomakehurst.wiremock.client.WireMock
import org.testcontainers.containers.BindMode
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.utility.DockerImageName

public object Testcontainer {
    public data class Wiremock(
        val client: WireMock,
        val httpUrl: String,
        val httpsUrl: String,
    )

    public val wiremock: Wiremock by lazy {
        with(WireMockContainer().apply { start() }) {
            Wiremock(
                client = WireMock(containerIpAddress, getMappedPort(httpPort)),
                httpUrl = "http://$containerIpAddress:${getMappedPort(httpPort)}",
                httpsUrl = "https://$containerIpAddress:${getMappedPort(httpsPort)}",
            )
        }
    }

    public val httpBin: String by lazy {
        with(HttpBinContainer().apply { start() }) {
            "http://$containerIpAddress:${getMappedPort(internalPort)}"
        }
    }
}

private class WireMockContainer(
    version: String = "2.9.0-alpine",
    val httpPort: Int = 8080,
    val httpsPort: Int = 8443,
) : GenericContainer<WireMockContainer>(DockerImageName.parse("wiremock/wiremock:$version")) {
    init {
        withExposedPorts(httpPort, httpsPort)
        withCommand("--https-port $httpsPort")
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

