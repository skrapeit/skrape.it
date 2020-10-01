package it.skrape

import it.skrape.core.fetcher.BrowserFetcher
import it.skrape.core.fetcher.HttpFetcher
import it.skrape.core.fetcher.Method
import it.skrape.core.htmlDocument
import it.skrape.exceptions.ElementNotFoundException
import it.skrape.matchers.*
import it.skrape.matchers.ContentTypes.*
import it.skrape.selects.eachHref
import it.skrape.selects.eachText
import it.skrape.selects.html5.*
import it.skrape.selects.text
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.testcontainers.junit.jupiter.Testcontainers
import strikt.api.expect
import strikt.api.expectThat
import strikt.assertions.*
import java.io.File
import java.net.SocketTimeoutException

@Testcontainers
class DslTest : WireMockSetup() {

    @Test
    fun `dsl can skrape by url`() {
        wireMockServer.setupStub(path = "/example")

        skrape(HttpFetcher) {
            request {
                url = "http://localhost:8080/example"
            }

            expect {

                status {
                    code toBe 200
                    message toBe "OK"
                }

                contentType toBe TEXT_HTML_UTF8

                htmlDocument {

                    title {
                        findFirst {
                            text toBe "i'm the title"
                        }
                    }

                    p {
                        findAll {
                            toBePresentTimes(2)
                        }
                        findFirst {
                            attribute("data-foo") toBe "bar"
                            text toBe "i'm a paragraph"
                        }

                        findLast {
                            text toBe "i'm a second paragraph"
                        }
                    }
                }
            }
        }
    }

    @Test
    fun `can call https with relaxed ssl option via DSL`() {
        wireMockServer.setupStub(path = "/example")

        skrape(HttpFetcher) {
            request {
                url = "https://localhost:8089/example"
                sslRelaxed = true
            }

            expect {
                status { code toBe 200 }
            }
        }
    }

    @Test
    fun `dsl can assert content-type in highly readable way`() {
        wireMockServer.setupStub(path = "/example")

        skrape(HttpFetcher) {
            request {
                url = "http://localhost:8080/example"
            }

            expect {
                contentType toContain TEXT_HTML
                contentType toBe TEXT_HTML_UTF8
                contentType toBeNot APPLICATION_XHTML
                contentType toBeNot APPLICATION_GZIP
                contentType toBeNot APPLICATION_JSON
                contentType toBeNot APPLICATION_TAR
                contentType toBeNot APPLICATION_XML
                contentType toBeNot APPLICATION_XUL
                contentType toBeNot APPLICATION_ZIP

                expectThat(contentType).isEqualTo("text/html;charset=utf-8")
            }
        }
    }

    @Test
    fun `dsl will not follow redirects if configured`() {
        wireMockServer.setupRedirect()

        skrape(HttpFetcher) {
            request {
                followRedirects = false
            }

            expect {
                status {
                    code toBe 302
                    message toBe "Found"
                }
            }
        }
    }

    @Test
    fun `dsl can check certain header`() {
        wireMockServer.setupStub()

        skrape(HttpFetcher) {
            expect {
                val header = httpHeader("Content-Type") {
                    expectThat(this).isEqualTo("text/html;charset=utf-8")
                }
                expectThat(header).isEqualTo("text/html;charset=utf-8")

                val nonExistingHeader = httpHeader("Non-Existing") {
                    expectThat(this).isNull()
                }
                expectThat(nonExistingHeader).isNull()
            }
        }
    }

    @Test
    fun `dsl can check headers`() {
        wireMockServer.setupStub()

        skrape(HttpFetcher) {
            expect {
                val headers = httpHeaders {
                    expectThat(this).hasEntry("Content-Type", "text/html;charset=utf-8")
                }
                expectThat(headers).hasEntry("Content-Type", "text/html;charset=utf-8")
            }
        }
    }

    @Test
    fun `dsl can get body`() {
        wireMockServer.setupStub()

        skrape(HttpFetcher) {
            expect {
                htmlDocument {
                    body {
                        findFirst {
                            text toContain "i'm a paragraph"
                        }
                    }
                }
            }
        }
    }

    @Test
    fun `dsl will follow redirect by default`() {
        wireMockServer.setupRedirect()

        skrape(HttpFetcher) {
            expect {
                status {
                    code toBe 404
                    message toBe "Not Found"
                }
            }
        }
    }

    @Test
    fun `dsl can fetch url and use HTTP verb POST`() {
        wireMockServer.setupPostStub()

        skrape(HttpFetcher) {
            request {
                method = Method.POST
            }

            expect {

                //expectThat(request.method).isEqualTo(Method.POST)

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

                contentType toBe APPLICATION_JSON_UTF8
                contentType toBe "application/json;charset=utf-8"
            }
        }
    }

    @Test
    fun `dsl will throw exception on timeout`() {
        wireMockServer.setupStub(delay = 3000)

        assertThrows(SocketTimeoutException::class.java) {
            skrape(HttpFetcher) {
                request {
                    timeout = 2000
                }

                expect {}
            }
        }
    }

    @Test
    fun `dsl can fetch url and extract to inferred type`() {
        wireMockServer.setupStub()

        skrape(HttpFetcher) {
            request {
                url = "http://localhost:8080/"
            }

            val extracted = extract {
                status {
                    MyObject(message, "", emptyList())
                }
            }
            expectThat(extracted.message).isEqualTo("OK")
        }
    }

    @Test
    fun `dsl can fetch url and extract using it`() {
        wireMockServer.setupStub()

        val extracted = skrape(HttpFetcher) {
            request {
                url = "http://localhost:8080/"
            }

            extractIt<MyOtherObject> {
                it.message = status { message }
            }
        }
        expectThat(extracted.message).isEqualTo("OK")
    }

    @Test
    fun `dsl can fetch url and extract using it in DSL-ish fashion`() {
        wireMockServer.setupStub()

        val extracted = skrape(HttpFetcher) {
            request {
                url = "http://localhost:8080/"
            }

            extractIt<MyObject> {
                it.message = status { message }
                htmlDocument {
                    it.allParagraphs = p { findAll { eachText } }
                    it.paragraph = findFirst("p").text
                    it.allLinks = findAll("[href]").eachHref
                }
            }
        }
        expect {
            that(extracted.message).isEqualTo("OK")
            that(extracted.paragraph).isEqualTo("i'm a paragraph")
            that(extracted.allParagraphs).containsExactly("i'm a paragraph", "i'm a second paragraph")
            that(extracted.allLinks).containsExactly("http://some.url", "http://some-other.url", "/relative-link")
        }
    }

    /**
     * TODO: fix Class should have a single no-arg constructor: class MyDataClass
     * for classes or data classes that have none default values
     */
    @Test
    fun `dsl can fetch url and extract to data class`() {
        wireMockServer.setupStub()

        val extracted = skrape(HttpFetcher) {
            request {
                url = "http://localhost:8080/"
            }

            extractIt<MyDataClass> {
                it.httpStatusCode = status { code }
                it.httpStatusMessage = status { message }
                htmlDocument {
                    it.allParagraphs = p { findAll { eachText } }
                    it.paragraph = p { findFirst { text } }
                    it.allLinks = a {
                        findAll { eachHref }
                    }
                }
            }
        }

        expect {
            that(extracted.httpStatusCode).isEqualTo(200)
            that(extracted.httpStatusMessage).isEqualTo("OK")
            that(extracted.paragraph).isEqualTo("i'm a paragraph")
            that(extracted.allParagraphs).containsExactly("i'm a paragraph", "i'm a second paragraph")
            that(extracted.allLinks).containsExactly("http://some.url", "http://some-other.url", "/relative-link")
        }
    }

    @Test
    fun `will throw custom exception if element could not be found via lambda`() {

        assertThrows(ElementNotFoundException::class.java) {
            skrape(HttpFetcher) {
                expect {
                    htmlDocument {
                        findFirst(".nonExistent") {}
                    }
                }
            }
        }
    }

    @Test
    fun `will return empty list if element could not be found and Doc mode is relaxed`() {

        skrape(HttpFetcher) {
            expect {
                htmlDocument {
                    relaxed = true
                    findAll(".nonExistent") {
                        toBeEmpty
                    }
                    findFirst(".nonExistent") {
                        // not throwing Exception
                    }
                }
            }
        }
    }

    @Test
    fun `will throw custom exception if element called by dsl could not be found`() {

        assertThrows(ElementNotFoundException::class.java) {
            skrape(HttpFetcher) {
                expect {
                    htmlDocument {
                        div {
                            withId = "non-existend"
                            withClass = "non-existend"
                            withAttribute = "non" to "existend"
                            withAttributeKey = "non-existend"
                            withAttributes = listOf("non" to "existend")
                            withAttributeKeys = listOf("non-existend")
                            findFirst {}
                        }
                    }
                }
            }
        }
    }

    @Test
    fun `dsl can fetch url and extract from skrape`() {
        wireMockServer.setupStub()

        val extracted = skrape(HttpFetcher) {
            request {
                url = "http://localhost:8080/"
            }

            extract {
                htmlDocument {
                    MyObject(
                            message = "",
                            allParagraphs = p { findAll { eachText } },
                            paragraph = findFirst("p").text,
                            allLinks = selection("[href]") { findAll { eachHref } }
                    )
                }
            }
        }
        expectThat(extracted.paragraph).isEqualTo("i'm a paragraph")
        expectThat(extracted.allParagraphs).containsExactly("i'm a paragraph", "i'm a second paragraph")
        expectThat(extracted.allLinks).containsExactly("http://some.url", "http://some-other.url", "/relative-link")
    }

    @Test
    fun `can read and return html from file system with default charset (UTF-8) using the DSL`() {
        val doc = htmlDocument(File("src/test/resources/__files/example.html")) {
            expectThat(titleText).isEqualTo("i'm the title")
            this
        }
        expectThat(doc.titleText).isEqualTo("i'm the title")
    }

    @Test
    fun `can read and return html from file system using the DSL and non default charset`() {
        val doc = htmlDocument(File("src/test/resources/__files/example.html"), Charsets.ISO_8859_1) {
            title {
                findFirst {
                    text toBe "i'm the title"
                }
            }
            this
        }
        doc.title {
            findFirst {
                expectThat(text).isEqualTo("i'm the title")
            }
        }
    }

    @Test
    fun `can read and return html from String`() {
        htmlDocument("""
            <html>
                <body>
                    <h1>welcome</h1>
                    <div>
                        <p>first p-element</p>
                        <p class="foo">some p-element</p>
                        <p class="foo">last p-element</p>
                    </div>
                </body>
            </html>""") {

            h1 {
                findFirst {
                    text toBe "welcome"
                }
            }
            p {
                withClass = "foo"
                findFirst {
                    text toBe "some p-element"
                    className toBe "foo"
                }
                findAll {
                    size toBe 2
                    expectThat(this.toString()).isEqualTo("[<p class=\"foo\">some p-element</p>, <p class=\"foo\">last p-element</p>]")
                }
            }
            p {
                findAll {
                    this.text toContain "p-element"
                }
                findLast {
                    text toBe "last p-element"
                }
            }
        }
    }

    @Test
    fun `can read html from file system with default charset (UTF-8) using the DSL`() {
        htmlDocument(File("src/test/resources/__files/example.html")) {
            expectThat(titleText).isEqualTo("i'm the title")
        }
    }

    @Test
    fun `can read html from file system using the DSL and non default charset`() {
        htmlDocument(File("src/test/resources/__files/example.html"), Charsets.ISO_8859_1) {
            expectThat(titleText).isEqualTo("i'm the title")
        }
    }

    @Test
    fun `can scrape js rendered page`() {
        wireMockServer.setupStub(fileName = "js.html")

        skrape(BrowserFetcher) {
            expect {
                htmlDocument {
                    div(".dynamic") {
                        findFirst {
                            text toBe "I have been dynamically added via Javascript"
                        }
                    }
                }
            }
        }
    }

    @Test
    fun `can preconfigure client`() {

        val fetcher = skrape(HttpFetcher) {
            request {
                followRedirects = false
            }

            preConfigured
        }

        wireMockServer.setupRedirect()
        val body1 = fetcher.extract {
            status {
                code toBe 302
                message toBe "Found"
            }
            responseBody
        }

        wireMockServer.setupRedirect()
        val body2 = fetcher.apply {
            request {
                followRedirects = true
            }
        }.extract {
            status {
                code toBe 404
                message toBe "Not Found"
            }
            responseStatus toBe HttpStatus.`4xx_Client_error`
            responseStatus toBe HttpStatus.`404_Not_Found`

            responseBody
        }

        expectThat(body1).isNotEqualTo(body2)
    }
}

class MyObject(
        var message: String? = null,
        var paragraph: String = "",
        var allParagraphs: List<String> = emptyList(),
        var allLinks: List<String> = emptyList()
)

data class MyDataClass(
        var httpStatusCode: Int = 0,
        var httpStatusMessage: String = "",
        var paragraph: String = "",
        var allParagraphs: List<String> = emptyList(),
        var allLinks: List<String> = emptyList()
)

class MyOtherObject {
    lateinit var message: String
}
