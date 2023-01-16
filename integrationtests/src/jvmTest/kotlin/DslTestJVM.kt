import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import it.skrape.core.htmlDocument
import it.skrape.fetcher.*
import it.skrape.matchers.ClientError
import it.skrape.matchers.toBe
import it.skrape.selects.eachHref
import it.skrape.selects.eachText
import it.skrape.selects.html5.a
import it.skrape.selects.html5.p
import org.jsoup.nodes.Element

class MyObject(
    var message: String? = null,
    var paragraph: String = "",
    var allParagraphs: List<String> = emptyList(),
    var allLinks: List<String> = emptyList()
)

class MyOtherObject {
    lateinit var message: String
}

class DslTestJVM : FunSpec({

    extension(DockerExtension)
    extension(TestcontainerExtension)

    test("dsl can fetch url and extract using it").config(enabledOrReasonIf = DockerExtension.isAvailable) {
        wiremock.setupStub()

        val extracted = skrape(HttpFetcher) {
            request {
                url("${wiremock.httpUrl}/")
            }

            extractIt<MyOtherObject> {
                it.message = status { description }
            }
        }
        extracted.message.shouldBe("OK")
    }


    test("dsl can fetch url and extract using it in DSL-ish fashion").config(enabledOrReasonIf = DockerExtension.isAvailable) {
        wiremock.setupStub()

        val extracted = skrape(HttpFetcher) {
            request {
                url("${wiremock.httpUrl}/")
            }

            extractIt<MyObject> {
                it.message = status { description }
                htmlDocument {
                    it.allParagraphs = p { findAll { eachText } }
                    it.paragraph = findFirst("p").text
                    it.allLinks = findAll("[href]").eachHref
                }
            }
        }
        assertSoftly(extracted) {
            message.shouldBe("OK")
            paragraph.shouldBe("i'm a paragraph")
            allParagraphs.shouldContainExactly("i'm a paragraph", "i'm a second paragraph")
            allLinks.shouldContainExactly("http://some.url", "http://some-other.url", "/relative-link")
        }
    }

    data class MyDataClass(
        var httpStatusCode: Int = 0,
        var httpStatusMessage: String = "",
        var paragraph: String = "",
        var allParagraphs: List<String> = emptyList(),
        var allLinks: List<String> = emptyList()
    )

    /**
     * TODO: fix Class should have a single no-arg constructor: class MyDataClass
     * for classes or data classes that have none default values
     */
    test("dsl can fetch url and extract to data class").config(enabledOrReasonIf = DockerExtension.isAvailable) {
        wiremock.setupStub()

        val extracted = skrape(HttpFetcher) {
            request {
                url("${wiremock.httpUrl}/")
            }

            extractIt<MyDataClass> {
                it.httpStatusCode = status { value }
                it.httpStatusMessage = status { description }
                htmlDocument {
                    it.allParagraphs = p { findAll { eachText } }
                    it.paragraph = p { findFirst { text } }
                    it.allLinks = a {
                        findAll { eachHref }
                    }
                }
            }
        }

        assertSoftly(extracted) {
            httpStatusCode.shouldBe(200)
            httpStatusMessage.shouldBe("OK")
            extracted.paragraph.shouldBe("i'm a paragraph")
            extracted.allParagraphs.shouldContainExactly("i'm a paragraph", "i'm a second paragraph")
            extracted.allLinks.shouldContainExactly("http://some.url", "http://some-other.url", "/relative-link")
        }
    }

    test("can use preconfigured client").config(enabledOrReasonIf = DockerExtension.isAvailable) {

        val fetcher: Scraper = skrape(HttpFetcher) {
            request {
                url("${wiremock.httpUrl}/")
                followRedirects = false
            }
        }

        wiremock.setupRedirect()

        // TODO: add possibility to call extract / expect outside of a coroutine scoope
        val body1 = fetcher.extractBlocking {
            status {
                value toBe 302
                description toBe "Found"
            }
            bodyAsText()
        }
        wiremock.setupRedirect()

        val body2 = fetcher.apply {
            request {
                followRedirects = true
            }
        }.extractBlocking {
            status {
                value toBe 404
                description toBe "Not Found"
            }
            status toBe HttpStatusCode.ClientError
            status toBe HttpStatusCode.NotFound

            bodyAsText()
        }

        body1.shouldNotBe(body2)

        fetcher.expectBlocking {
            status {
                value toBe 404
            }
        }

        val statusCode = fetcher.extractBlocking {
            status {
                value
            }
        }

        statusCode.shouldBe(404)

        data class MyResult(
            var statusCode: Int = 0
        )

        val myResult = fetcher.extractItBlocking<MyResult> { it.statusCode = status { value } }
        myResult.statusCode.shouldBe(404)

    }

    test("can convert DocElement to jsoup element") {
        aValidDocument().element.shouldBeInstanceOf<Element>()
    }
})