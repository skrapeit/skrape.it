import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.request.*
import io.ktor.http.*
import it.skrape.fetcher.*
import it.skrape.matchers.*

object LegacyTests: FunSpec({

    extension(DockerExtension)
    extension(TestcontainerExtension)

    test("dsl can assert content-type in highly readable way using legacy api").config(enabledOrReasonIf = DockerExtension.isAvailable) {
        wiremock.setupStub(path = "/example")

        skrape(HttpFetcher) {
            request {
                url("${wiremock.httpUrl}/example")
            }

            response {
                contentType toContain ContentTypes.TEXT_HTML
                contentType toBe ContentTypes.TEXT_HTML_UTF8
                contentType toBeNot ContentTypes.APPLICATION_XHTML
                contentType toBeNot ContentTypes.APPLICATION_GZIP
                contentType toBeNot ContentTypes.APPLICATION_JSON
                contentType toBeNot ContentTypes.APPLICATION_TAR
                contentType toBeNot ContentTypes.APPLICATION_XML
                contentType toBeNot ContentTypes.APPLICATION_XUL
                contentType toBeNot ContentTypes.APPLICATION_ZIP

                contentType.shouldBe("text/html; charset=UTF-8")
            }
        }
    }

    test("dsl can fetch url and use HTTP verb POST").config(enabledOrReasonIf = DockerExtension.isAvailable) {
        wiremock.setupPostStub()

        skrape(HttpFetcher) {
            request {
                url("${wiremock.httpUrl}/")
                method = HttpMethod.Post
            }
            response {

                //request.method.shouldBe(Method.POST)

                status {
                    code toBe 200
                    message toBe "OK"
                }

                responseStatus toBe HttpStatus.`2xx_Successful`
                responseStatus toBe HttpStatus.`200_OK`
                responseStatus toBeNot HttpStatus.`1xx_Informational_response`
                responseStatus toBeNot HttpStatus.`3xx_Redirection`
                responseStatus toBeNot HttpStatus.`4xx_Client_error`
                responseStatus toBeNot HttpStatus.`5xx_Server_error`

                contentType toBe ContentTypes.APPLICATION_JSON_UTF8
                contentType toBe "application/json; charset=UTF-8"
            }
        }
    }
})