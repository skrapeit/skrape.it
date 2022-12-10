import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestScope
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldStartWith
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject

class TestcontainerTest : FunSpec() {

    suspend fun TestScope.callWiremock(baseUri: String? = null, path: String): HttpResponse {
        val uri = baseUri ?: wiremock.httpUrl
        return wiremock.ktorClient.get {
            url {
                takeFrom(uri)
                path(path)
            }
            println(url.buildString())
        }
    }

    init {

        extension(DockerExtension)
        extension(TestcontainerExtension)

        test("wiremock as testcontainer returns corresponding urls")
            .config(
                enabledOrReasonIf = DockerExtension.isAvailable
            ) {
                assertSoftly(wiremock) {
                    httpUrl.shouldStartWith("http://localhost:")
                    httpsUrl.shouldStartWith("https://localhost:")
                }
            }

        test("wiremock is running and admin page is available under http port").config(enabledOrReasonIf = DockerExtension.isAvailable) {
            callWiremock(path = "/__admin/").status.shouldBe(HttpStatusCode.OK)
        }

        test("wiremock is running and admin page is available under https port").config(enabledOrReasonIf = DockerExtension.isAvailable) {
            callWiremock(baseUri = wiremock.httpsUrl, path = "/__admin/").status.shouldBe(HttpStatusCode.OK)
        }

        test("can add file for stub").config(enabledOrReasonIf = DockerExtension.isAvailable) {
            wiremock.setupStub()
            val call = callWiremock(path = "/__files/example.html")
            assertSoftly(call) {
                status.shouldBe(HttpStatusCode.OK)
                contentType().shouldBe(ContentType.parse("text/html"))
            }
            call.bodyAsText().shouldContain("<title>i'm the title</title>")
        }

        test("can override stubs").config(enabledOrReasonIf = DockerExtension.isAvailable) {
            wiremock.ktorClient.post {
                url("${wiremock.httpUrl}/__admin/mappings")
                contentType(ContentType.Application.Json)
                setBody(buildJsonObject {
                    putJsonObject("request") {
                        put("method", "GET")
                        put("url", "/abc")
                    }
                    putJsonObject("response") {
                        put("status", 418)
                    }
                })
            }

            callWiremock(path = "/abc").status.shouldBe(HttpStatusCode.fromValue(418))


            wiremock.ktorClient.post {
                url("${wiremock.httpUrl}/__admin/mappings")
                contentType(ContentType.Application.Json)
                setBody(buildJsonObject {
                    putJsonObject("request") {
                        put("method", "GET")
                        put("url", "/abc")
                    }
                    putJsonObject("response") {
                        put("status", 201)
                    }
                })
            }
            callWiremock(path = "/abc").status.shouldBe(HttpStatusCode.fromValue(201))
        }

        test("can override convenient stubs").config(enabledOrReasonIf = DockerExtension.isAvailable) {
            wiremock.setupStub(path = "/xyz", statusCode = 418)
            callWiremock(path = "/xyz").status.shouldBe(HttpStatusCode.fromValue(418))

            wiremock.setupStub(path = "/abc", statusCode = 201)
            callWiremock(path = "/abc").status.shouldBe(HttpStatusCode.fromValue(201))
        }
    }
}