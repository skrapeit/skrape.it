package it.skrape.core

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import com.github.tomakehurst.wiremock.stubbing.StubMapping
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import java.util.*

open class WireMockSetup {
    private val options = options()
            .port(8080)
            .httpsPort(8089)

    protected val wireMockServer = WireMockServer(options)

    @BeforeEach
    fun setup() {
        wireMockServer.start()
    }

    @AfterEach
    fun teardown() {
        wireMockServer.stop()
    }
}

fun WireMockServer.setupStub(
        path: String = "/",
        statusCode: Int = 200,
        contentType: String = "text/html; charset=UTF-8",
        fileName: String = "example.html",
        delay: Int = 0
): StubMapping = this.stubFor(WireMock.get(WireMock.urlEqualTo(path))
        .willReturn(WireMock.aResponse().withHeader("Content-Type", contentType)
                .withStatus(statusCode)
                .withFixedDelay(delay)
                .withBodyFile(fileName)))

fun WireMockServer.setupPostStub(): StubMapping =
        this.stubFor(WireMock.post(WireMock.urlEqualTo("/"))
                .willReturn(WireMock.aResponse().withHeader("Content-Type", "application/json; charset=UTF-8")
                        .withStatus(200)
                        .withBodyFile("data.json")))

fun WireMockServer.setupRedirect(): StubMapping =
        this.stubFor(WireMock.get(WireMock.urlEqualTo("/"))
                .willReturn(WireMock.temporaryRedirect("/redirected")
                        .withHeader("Content-Type", "text/html").withBody(UUID.randomUUID().toString())))

fun WireMockServer.setupPutStub(): StubMapping =
        this.stubFor(WireMock.put(WireMock.urlEqualTo("/"))
                .willReturn(WireMock.aResponse()
                        .withStatus(201)
                        .withBody("i'm a PUT stub")))

fun WireMockServer.setupDeleteStub(): StubMapping =
        this.stubFor(WireMock.delete(WireMock.urlEqualTo("/"))
                .willReturn(WireMock.aResponse()
                        .withStatus(201)
                        .withBody("i'm a DELETE stub")))

fun WireMockServer.setupPatchStub(): StubMapping =
        this.stubFor(WireMock.patch(WireMock.urlEqualTo("/"))
                .willReturn(WireMock.aResponse()
                        .withStatus(201)
                        .withBody("i'm a PATCH stub")))

fun WireMockServer.setupHeadStub(): StubMapping =
        this.stubFor(WireMock.head(WireMock.urlEqualTo("/"))
                .willReturn(WireMock.aResponse()
                        .withStatus(201)
                        .withHeader("result","i'm a HEAD stub")))
