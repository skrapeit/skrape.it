package it.skrape.core

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.stubbing.StubMapping
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

open class WireMockSetup {
    protected val wireMockServer = WireMockServer(8080, 8089)

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
                        .withHeader("Content-Type", "text/html")))
