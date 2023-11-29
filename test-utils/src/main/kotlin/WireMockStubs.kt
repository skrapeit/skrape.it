@file:Suppress("MagicNumber")

import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.stubbing.StubMapping
import java.util.*

public fun Testcontainer.Wiremock.setupStub(
    path: String = "/",
    statusCode: Int = 200,
    contentType: String = "text/html; charset=UTF-8",
    fileName: String = "example.html",
    delay: Int = 0,
): StubMapping = client.register(
    get(urlEqualTo(path))
        .willReturn(
            aResponse().withHeader("Content-Type", contentType)
                .withStatus(statusCode)
                .withFixedDelay(delay)
                .withBodyFile(fileName),
        ),
)

public fun Testcontainer.Wiremock.setupPostStub(): StubMapping =
    client.register(
        post(urlEqualTo("/"))
            .willReturn(
                aResponse().withHeader("Content-Type", "application/json; charset=UTF-8")
                    .withStatus(200)
                    .withBodyFile("data.json"),
            ),
    )

public fun Testcontainer.Wiremock.setupRedirect(): StubMapping =
    client.register(
        get(urlEqualTo("/"))
            .willReturn(
                temporaryRedirect("/redirected")
                    .withHeader("Content-Type", "text/html").withBody(UUID.randomUUID().toString()),
            ),
    )

public fun Testcontainer.Wiremock.setupPutStub(): StubMapping =
    client.register(
        put(urlEqualTo("/"))
            .willReturn(
                aResponse()
                    .withStatus(201)
                    .withBody("i'm a PUT stub"),
            ),
    )

public fun Testcontainer.Wiremock.setupDeleteStub(): StubMapping =
    client.register(
        delete(urlEqualTo("/"))
            .willReturn(
                aResponse()
                    .withStatus(201)
                    .withBody("i'm a DELETE stub"),
            ),
    )

public fun Testcontainer.Wiremock.setupPatchStub(): StubMapping =
    client.register(
        patch(urlEqualTo("/"))
            .willReturn(
                aResponse()
                    .withStatus(201)
                    .withBody("i'm a PATCH stub"),
            ),
    )

public fun Testcontainer.Wiremock.setupHeadStub(): StubMapping =
    client.register(
        head(urlEqualTo("/"))
            .willReturn(
                aResponse()
                    .withStatus(201)
                    .withHeader("result", "i'm a HEAD stub"),
            ),
    )

public fun Testcontainer.Wiremock.setupCookiesStub(path: String = "/"): StubMapping =
    client.register(
        get(urlEqualTo(path))
            .willReturn(
                aResponse()
                    .withHeader("Set-Cookie", "basic=value")
                    .withHeader(
                        "Set-Cookie",
                        "advanced=advancedValue; Domain=localhost; Path=$path; Secure; HttpOnly; SameSite=Strict",
                    )
                    .withHeader(
                        "Set-Cookie",
                        "expireTest=value; Expires=Wed, 21 Oct 2015 07:28:00 GMT; Max-Age=2592000",
                    )
                    .withStatus(200),
            ),
    )

public fun Testcontainer.Wiremock.setupBasicAuthStub(
    username: String,
    password: String,
): StubMapping = client.register(
    get(urlEqualTo("/basic-auth")).withBasicAuth(username, password)
        .willReturn(
            aResponse()
                .withStatus(200),
        ),
)
