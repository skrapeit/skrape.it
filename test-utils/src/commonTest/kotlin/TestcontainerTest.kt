import ch.tutteli.atrium.api.fluent.en_GB.its
import ch.tutteli.atrium.api.fluent.en_GB.toContain
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.fluent.en_GB.toStartWith
import ch.tutteli.atrium.api.verbs.expect
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
        expect(getWiremock()) {
            its { httpUrl }.toStartWith("http://localhost:")
            its { httpsUrl }.toStartWith("https://localhost:")
        }
    }

    @JsName("WiremockIsRunningAndAdminPageIsAvailableUnderHttpPort")
    @Test
    fun `wiremock is running and admin page is available under http port`() = runTest {
        expect(callWiremock(path = "/__admin/").status).toEqual(HttpStatusCode.OK)
    }

    @JsName("WiremockIsRunningAndAdminPageIsAvailableUnderHttpsPort")
    @Test
    fun `wiremock is running and admin page is available under https port`() = runTest {
        expect(callWiremock(baseUri = getWiremock().httpsUrl, path = "/__admin/").status).toEqual(HttpStatusCode.OK)
    }

    @JsName("CanAddFileForStub")
    @Test
    fun `can add file for stub`() = runTest {
        getWiremock().setupStub()
        val call = callWiremock(path = "/__files/example.html")
        expect(call) {
            its(HttpResponse::status).toEqual(HttpStatusCode.OK)
            its(HttpResponse::contentType).toEqual(ContentType.parse("text/html"))
        }
        expect(call.bodyAsText()).toContain("<title>i'm the title</title>")
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
        expect(callWiremock(path = "/abc")) {
            its(HttpResponse::status).toEqual(HttpStatusCode.fromValue(418))
        }


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
        expect(callWiremock(path = "/abc").status).toEqual(HttpStatusCode.fromValue(201))
    }

    @JsName("CanOverrideConvenientStubs")
    @Test
    fun `can override convenient stubs`() = runTest {
        getWiremock().setupStub(path = "/xyz", statusCode = 418)
        expect(callWiremock(path = "/xyz").status).toEqual(HttpStatusCode.fromValue(418))

        getWiremock().setupStub(path = "/abc", statusCode = 201)
        expect(callWiremock(path = "/abc").status).toEqual(HttpStatusCode.fromValue(201))
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