import com.github.tomakehurst.wiremock.client.WireMock
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.isEqualTo

val myWiremock = Testcontainer.wiremock

internal class TestcontainerTest {


    @Test
    internal fun `wiremock as testcontainer returns corresponding urls`() {
        expectThat(myWiremock) {
            get {
                httpUrl.startsWith("http://localhost:")
                httpsUrl.startsWith("https://localhost:")
            }
        }
    }

    @Test
    internal fun `wiremock is running and admin page is available under http port`() {
        expectThat(callWiremock(path = "/__admin").statusCode()).isEqualTo(200)
    }

    @Test
    internal fun `wiremock is running and admin page is available under https port`() {
        expectThat(callWiremock(baseUri = myWiremock.httpsUrl, path = "/__admin").statusCode()).isEqualTo(200)
    }

    @Test
    internal fun `can add file for stub`() {
        myWiremock.setupStub()
        with(callWiremock(path = "/__files/example.html")) {
            expectThat(statusCode()).isEqualTo(200)
            expectThat(contentType()).isEqualTo("text/html")
            expectThat(body().asString()).contains("<title>i'm the title</title>")
        }
    }

    @Test
    internal fun `can override stubs`() {
        myWiremock.client.register(
            WireMock.get(WireMock.urlEqualTo("/abc"))
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(418)
                )
        )
        expectThat(callWiremock(path = "/abc").statusCode()).isEqualTo(418)

        myWiremock.client.register(
            WireMock.get(WireMock.urlEqualTo("/abc"))
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(201)
                )
        )
        expectThat(callWiremock(path = "/abc").statusCode()).isEqualTo(201)
    }

    @Test
    internal fun `can override convenient stubs`() {
        myWiremock.setupStub(path = "/xyz", statusCode = 418)
        expectThat(callWiremock(path = "/xyz").statusCode()).isEqualTo(418)

        myWiremock.setupStub(path = "/abc", statusCode = 201)
        expectThat(callWiremock(path = "/abc").statusCode()).isEqualTo(201)
    }

    private fun callWiremock(baseUri: String = myWiremock.httpUrl, path: String) =
        given().baseUri(baseUri).relaxedHTTPSValidation()
            .`when`().get(path)
            .then()
            .extract()
}