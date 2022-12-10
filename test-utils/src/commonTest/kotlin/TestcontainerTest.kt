import io.kotest.assertions.assertSoftly
import io.kotest.assertions.print.print
import io.kotest.core.annotation.AutoScan
import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.config.LogLevel
import io.kotest.core.descriptors.Descriptor
import io.kotest.core.extensions.Extension
import io.kotest.core.extensions.SpecExtension
import io.kotest.core.extensions.TestCaseExtension
import io.kotest.core.filter.TestFilter
import io.kotest.core.filter.TestFilterResult
import io.kotest.core.listeners.BeforeSpecListener
import io.kotest.core.listeners.PrepareSpecListener
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldStartWith
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
import kotlin.reflect.KClass

@AutoScan
class Config : AbstractProjectConfig() {

    override val logLevel: LogLevel = LogLevel.Trace

    override suspend fun beforeProject() {
        DockerProjectConfig.beforeProject()
    }

    override fun extensions(): List<Extension> = listOf(MySpecExtension)
}

object MySpecExtension : SpecExtension,TestCaseExtension, TestFilter {

    override suspend fun intercept(spec: Spec, execute: suspend (Spec) -> Unit) {
        println(spec.rootTests())
        super.intercept(spec, execute)
    }

    override suspend fun intercept(testCase: TestCase, execute: suspend (TestCase) -> TestResult): TestResult {
        println("Intercept $testCase")
        val res = execute(testCase)
        println("Res: $res")
        return res
    }

    override fun filter(descriptor: Descriptor): TestFilterResult {
        println("Filter: $descriptor")
        return TestFilterResult.Include
    }


}

//@Execution(ExecutionMode.SAME_THREAD)
//@DisabledOnOs(OS.WINDOWS)
class TestcontainerTest : FunSpec() {

    val wiremockInstance: Testcontainer.Wiremock by lazy { DockerProjectConfig.wiremock }

    suspend fun callWiremock(baseUri: String? = null, path: String): HttpResponse {
        val uri = baseUri ?: wiremockInstance.httpUrl
        return wiremockInstance.ktorClient.get {
            url {
                takeFrom(uri)
                path(path)
            }
            println(url.buildString())
        }
    }

    init {

        test("wiremock as testcontainer returns corresponding urls")
            .config(
                enabledOrReasonIf = DockerProjectConfig.enabledOrReasonIf,
                coroutineTestScope = true,
                testCoroutineDispatcher = true
            ) {
                println("Ran")
                assertSoftly(wiremockInstance) {
                    httpUrl.shouldStartWith("http://localhost:")
                    httpsUrl.shouldStartWith("https://localhost:")
                }
            }

        test("wiremock is running and admin page is available under http port").config(enabledOrReasonIf = DockerProjectConfig.enabledOrReasonIf) {
            callWiremock(path = "/__admin/").status.shouldBe(HttpStatusCode.OK)
        }

        test("wiremock is running and admin page is available under https port").config(enabledOrReasonIf = DockerProjectConfig.enabledOrReasonIf) {
            callWiremock(baseUri = wiremockInstance.httpsUrl, path = "/__admin/").status.shouldBe(HttpStatusCode.OK)
        }

        test("can add file for stub").config(enabledOrReasonIf = DockerProjectConfig.enabledOrReasonIf) {
            wiremockInstance.setupStub()
            val call = callWiremock(path = "/__files/example.html")
            assertSoftly(call) {
                status.shouldBe(HttpStatusCode.OK)
                contentType().shouldBe(ContentType.parse("text/html"))
            }
            call.bodyAsText().shouldContain("<title>i'm the title</title>")
        }

        test("can override stubs").config(enabledOrReasonIf = DockerProjectConfig.enabledOrReasonIf) {
            wiremockInstance.ktorClient.post {
                url("${wiremockInstance.httpUrl}/__admin/mappings")
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


            wiremockInstance.ktorClient.post {
                url("${wiremockInstance.httpUrl}/__admin/mappings")
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

        test("can override convenient stubs").config(enabledOrReasonIf = DockerProjectConfig.enabledOrReasonIf) {
            wiremockInstance.setupStub(path = "/xyz", statusCode = 418)
            callWiremock(path = "/xyz").status.shouldBe(HttpStatusCode.fromValue(418))

            wiremockInstance.setupStub(path = "/abc", statusCode = 201)
            callWiremock(path = "/abc").status.shouldBe(HttpStatusCode.fromValue(201))
        }
    }
    override suspend fun beforeSpec(spec: Spec) {
        println("Before spec called")
    }

    override suspend fun beforeAny(testCase: TestCase) {
        println("Before any")
    }
}