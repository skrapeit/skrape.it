package core

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.stubbing.StubMapping

fun WireMockServer.setupStub(
        path: String = "/",
        statusCode: Int = 200,
        contentType: String = "text/html; charset=UTF-8",
        fileName: String = "example.html"
): StubMapping = this.stubFor(WireMock.get(WireMock.urlEqualTo(path))
        .willReturn(WireMock.aResponse().withHeader("Content-Type", contentType)
                .withStatus(statusCode)
                .withBodyFile(fileName)))

fun WireMockServer.setupPostStub(): StubMapping =
        this.stubFor(WireMock.post(WireMock.urlEqualTo("/"))
                .willReturn(WireMock.aResponse().withHeader("Content-Type", "application/json; charset=UTF-8")
                        .withStatus(200)
                        .withBodyFile("data.json")))

fun WireMockServer.setupRedirect(): StubMapping =
        this.stubFor(WireMock.get(WireMock.urlEqualTo("/"))
                .willReturn(WireMock.temporaryRedirect("/redirected")))
