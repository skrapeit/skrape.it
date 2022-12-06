import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldStartWith
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
import kotlin.js.JsName
import kotlin.test.Test

//@Execution(ExecutionMode.SAME_THREAD)
//@DisabledOnOs(OS.WINDOWS)
internal class TestcontainerTest {

    lateinit var wiremockInstance: Testcontainer.Wiremock
    private suspend fun getWiremock(): Testcontainer.Wiremock {
        if(!this::wiremockInstance.isInitialized) wiremockInstance = Testcontainer.getWiremock()
        return wiremockInstance
    }

    @JsName("WiremockAsTestcontainerReturnsCorrespondingUrls")
    @Test
    fun `wiremock as testcontainer returns corresponding urls`() = runTest {
        assertSoftly(getWiremock()) {
            httpUrl.shouldStartWith("http://localhost:")
            httpsUrl.shouldStartWith("https://localhost:")
        }
    }

    @JsName("WiremockIsRunningAndAdminPageIsAvailableUnderHttpPort")
    @Test
    fun `wiremock is running and admin page is available under http port`() = runTest {
        callWiremock(path = "/__admin/").status.shouldBe(HttpStatusCode.OK)
    }

    @JsName("WiremockIsRunningAndAdminPageIsAvailableUnderHttpsPort")
    @Test
    fun `wiremock is running and admin page is available under https port`() = runTest {
        callWiremock(baseUri = getWiremock().httpsUrl, path = "/__admin/").status.shouldBe(HttpStatusCode.OK)
    }

    @JsName("CanAddFileForStub")
    @Test
    fun `can add file for stub`() = runTest {
        getWiremock().setupStub()
        val call = callWiremock(path = "/__files/example.html")
        assertSoftly(call) {
            status.shouldBe(HttpStatusCode.OK)
            contentType().shouldBe(ContentType.parse("text/html"))
        }
        call.bodyAsText().shouldContain("<title>i'm the title</title>")
    }

    @JsName("CanOverrideStubs")
    @Test
    fun `can override stubs`() = runTest {
        getWiremock().ktorClient.post {
            url("${getWiremock().httpUrl}/__admin/mappings")
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


        getWiremock().ktorClient.post {
            url("${getWiremock().httpUrl}/__admin/mappings")
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

    @JsName("CanOverrideConvenientStubs")
    @Test
    fun `can override convenient stubs`() = runTest {
        getWiremock().setupStub(path = "/xyz", statusCode = 418)
        callWiremock(path = "/xyz").status.shouldBe(HttpStatusCode.fromValue(418))

        getWiremock().setupStub(path = "/abc", statusCode = 201)
        callWiremock(path = "/abc").status.shouldBe(HttpStatusCode.fromValue(201))
    }

    private suspend fun callWiremock(baseUri: String? = null, path: String): HttpResponse {
        val uri = baseUri ?: getWiremock().httpUrl
        return getWiremock().ktorClient.get {
            url {
                takeFrom(uri)
                path(path)
            }
            println(url.buildString())
        }
    }

    /*given().baseUri(baseUri).relaxedHTTPSValidation()
        .`when`().get(path)
        .then()
        .extract()*/
}