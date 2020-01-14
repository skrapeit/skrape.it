package it.skrape

import it.skrape.core.fetcher.Method
import it.skrape.core.fetcher.Mode
import it.skrape.core.htmlDocument
import it.skrape.exceptions.ElementNotFoundException
import it.skrape.matchers.*
import it.skrape.matchers.ContentTypes.*
import it.skrape.selects.html5.*
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.testcontainers.junit.jupiter.Testcontainers
import strikt.api.expectThat
import strikt.assertions.*
import java.io.File
import java.net.SocketTimeoutException

@Testcontainers
internal class DslTest : WireMockSetup() {

    @Test
    internal fun `dsl can skrape by url`() {
        wireMockServer.setupStub(path = "/example")

        skrape {
            url = "http://localhost:8080/example"

            expect {

                statusCode toBe 200
                statusMessage toBe "OK"

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
    internal fun `dsl can assert content-type in highly readable way`() {
        wireMockServer.setupStub(path = "/example")

        skrape {
            url = "http://localhost:8080/example"

            expect {
                contentType `to contain` TEXT_HTML
                contentType `to be` TEXT_HTML_UTF8
                contentType `to be not` APPLICATION_XHTML
                contentType `to be not` APPLICATION_GZIP
                contentType `to be not` APPLICATION_JSON
                contentType `to be not` APPLICATION_TAR
                contentType `to be not` APPLICATION_XML
                contentType `to be not` APPLICATION_XUL
                contentType `to be not` APPLICATION_ZIP

                expectThat(contentType).isEqualTo("text/html;charset=utf-8")
            }
        }
    }

    @Test
    internal fun `dsl will not follow redirects if configured`() {
        wireMockServer.setupRedirect()

        skrape {
            followRedirects = false

            expect {
                statusCode toBe 302
            }
        }
    }

    @Test
    internal fun `dsl can check certain header`() {
        wireMockServer.setupStub()

        skrape {
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
    internal fun `dsl can check headers`() {
        wireMockServer.setupStub()

        skrape {
            expect {
                val headers = httpHeaders {
                    expectThat(this).hasEntry("Content-Type", "text/html;charset=utf-8")
                }
                expectThat(headers).hasEntry("Content-Type", "text/html;charset=utf-8")
            }
        }
    }

    @Test
    internal fun `dsl can get body`() {
        wireMockServer.setupStub()

        skrape {
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
    internal fun `dsl will follow redirect by default`() {
        wireMockServer.setupRedirect()

        skrape {
            expect {
                statusCode toBe 404
            }
        }
    }

    @Test
    internal fun `dsl can fetch url and use HTTP verb POST`() {
        wireMockServer.setupPostStub()

        skrape {
            method = Method.POST

            expect {

                expectThat(request.method).isEqualTo(Method.POST)

                statusCode toBe 200
                statusMessage toBe "OK"
                contentType toBe APPLICATION_JSON_UTF8
                contentType toBe "application/json;charset=utf-8"
            }
        }
    }

    @Test
    internal fun `dsl will throw exception on timeout`() {
        wireMockServer.setupStub(delay = 3000)

        assertThrows(SocketTimeoutException::class.java) {
            skrape {
                timeout = 2000
                expect {}
            }
        }
    }

    @Test
    internal fun `dsl can fetch url and extract`() {
        wireMockServer.setupStub()

        skrape {
            url = "http://localhost:8080/"

            val extracted = extract {
                MyObject(statusMessage, "", emptyList())
            }
            expectThat(extracted.message).isEqualTo("OK")
        }
    }

    @Test
    internal fun `dsl can fetch url and infer type on extract`() {
        wireMockServer.setupStub()

        skrape {
            url = "http://localhost:8080/"

            val extracted = extract {
                MyObject(statusMessage, "", emptyList())
            }
            expectThat(extracted.message).isEqualTo("OK")
        }
    }

    @Test
    internal fun `dsl can fetch url and extract using it`() {
        wireMockServer.setupStub()

        val extracted = skrape {
            url = "http://localhost:8080/"

            extractIt<MyOtherObject> {
                it.message = statusMessage
            }
        }
        expectThat(extracted.message).isEqualTo("OK")
    }

    @Test
    internal fun `will throw custom exception if element could not be found via lambda`() {

        assertThrows(ElementNotFoundException::class.java) {
            skrape {
                expect {
                    htmlDocument {
                        findFirst(".nonExistent") {}
                    }
                }
            }
        }
    }

    @Test
    internal fun `will throw custom exception if element called by dsl could not be found`() {

        assertThrows(ElementNotFoundException::class.java) {
            skrape {
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
    internal fun `dsl can fetch url and extract from skrape`() {
        wireMockServer.setupStub()

        val extracted = skrape {
            url = "http://localhost:8080/"

            extract {
                htmlDocument {
                    MyObject(
                            allParagraphs = findAll("p").eachText,
                            paragraph = findFirst("p").text
                    )
                }
            }
        }
        expectThat(extracted.paragraph).isEqualTo("i'm a paragraph")
        expectThat(extracted.allParagraphs).containsExactly("i'm a paragraph", "i'm a second paragraph")
    }

    @Test
    internal fun `can read and return html from file system with default charset (UTF-8) using the DSL`() {
        val doc = htmlDocument(File("src/test/resources/__files/example.html")) {
            expectThat(title()).isEqualTo("i'm the title")
        }
        expectThat(doc.title()).isEqualTo("i'm the title")
    }

    @Test
    internal fun `can read and return html from file system using the DSL and non default charset`() {
        val doc = htmlDocument(File("src/test/resources/__files/example.html"), Charsets.ISO_8859_1) {
            title {
                findFirst {
                    text toBe "i'm the title"
                }
            }
        }
        doc.title {
            findFirst {
                expectThat(text).isEqualTo("i'm the title")
            }
        }
    }

    @Test
    internal fun `can read and return html from String`() {
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
            }
            p {
                findAll {
                    text toContain "p-element"
                }
                findLast {
                    text toBe "last p-element"
                }
            }
        }
    }

    @Test
    internal fun `can read html from file system with default charset (UTF-8) using the DSL`() {
        htmlDocument(File("src/test/resources/__files/example.html")) {
            expectThat(title()).isEqualTo("i'm the title")
        }
    }

    @Test
    internal fun `can read html from file system using the DSL and non default charset`() {
        htmlDocument(File("src/test/resources/__files/example.html"), Charsets.ISO_8859_1) {
            expectThat(title()).isEqualTo("i'm the title")
        }
    }

    @Test
    internal fun `can scrape js rendered page`() {
        wireMockServer.setupStub(fileName = "js.html")

        skrape {
            mode = Mode.DOM
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
    internal fun `can preconfigure client`() {

        val fetcher = skrape {
            followRedirects = false
            asConfig
        }

        wireMockServer.setupRedirect()
        val body1 = fetcher.extract {
            statusCode toBe 302
            responseBody
        }

        wireMockServer.setupRedirect()
        val body2 = fetcher.extract {
            statusCode toBe 302
            responseBody
        }

        expectThat(body1).isNotEqualTo(body2)
    }
}

class MyObject(
        var message: String? = null,
        var paragraph: String = "",
        var allParagraphs: List<String> = emptyList()
)

class MyOtherObject {
    var message: String? = null
}
