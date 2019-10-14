package it.skrape

import it.skrape.core.*
import it.skrape.exceptions.ElementNotFoundException
import it.skrape.matchers.*
import it.skrape.matchers.ContentTypes.*
import it.skrape.selects.*
import it.skrape.selects.html5.body
import it.skrape.selects.html5.div
import it.skrape.selects.html5.p
import it.skrape.selects.html5.title
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Testcontainers
import strikt.api.expectThat
import strikt.assertions.containsExactly
import strikt.assertions.hasEntry
import strikt.assertions.isEqualTo
import strikt.assertions.isNull
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
                        text() toBe "i'm the title"
                    }

                    p {
                        size toBe 2

                        firstOccurrence {
                            attr("data-foo") toBe "bar"
                            text() toBe "i'm a paragraph"
                        }

                        lastOccurrence {
                            text() toBe "i'm a second paragraph"
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
        // given
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
        // given
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
        // given
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
        // given
        wireMockServer.setupStub()

        skrape {
            expect {
                htmlDocument {
                    body {
                        text() toContain "i'm a paragraph"
                    }
                }
            }
        }
    }

    @Test
    internal fun `dsl will follow redirect by default`() {
        // given
        wireMockServer.setupRedirect()

        skrape {
            expect {
                statusCode toBe 404
            }
        }
    }

    @Test
    internal fun `dsl can fetch url and use HTTP verb POST`() {
        // given
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

        Assertions.assertThrows(SocketTimeoutException::class.java) {
            skrape {
                timeout = 2000
                expect {}
            }
        }
    }

    @Test
    internal fun `dsl can fetch url and extract`() {
        // given
        wireMockServer.setupStub()

        skrape {
            url = "http://localhost:8080"

            val extracted = extract {
                MyObject(statusMessage, "", emptyList())
            }
            expectThat(extracted.message).isEqualTo("OK")
        }
    }

    @Test
    internal fun `dsl can fetch url and infer type on extract`() {
        // given
        wireMockServer.setupStub()

        skrape {
            url = "http://localhost:8080"

            val extracted = extract {
                MyObject(statusMessage, "", emptyList())
            }
            expectThat(extracted.message).isEqualTo("OK")
        }
    }

    @Test
    internal fun `dsl can fetch url and extract using it`() {
        // given
        wireMockServer.setupStub()

        val extracted = skrape {
            url = "http://localhost:8080"

            extractIt<MyOtherObject> {
                it.message = statusMessage
            }
        }
        expectThat(extracted.message).isEqualTo("OK")
    }

    @Test
    internal fun `will throw custom exception if element could not be found via lambda`() {

        Assertions.assertThrows(ElementNotFoundException::class.java) {
            skrape {
                expect {
                    htmlDocument {
                        element(".nonExistent") {}
                    }
                }
            }
        }
    }

    @Test
    internal fun `dsl can fetch url and extract from skrape`() {
        // given
        wireMockServer.setupStub()

        val extracted = skrape {
            url = "http://localhost:8080"

            extract {
                htmlDocument {
                    MyObject(
                            message = statusMessage,
                            paragraph = element("p").text(),
                            allParagraphs = elements("p").map { it.text() }
                    )
                }
            }
        }
        expectThat(extracted.message).isEqualTo("OK")
        expectThat(extracted.paragraph).isEqualTo("i'm a paragraph")
        expectThat(extracted.allParagraphs).containsExactly("i'm a paragraph", "i'm a second paragraph")
    }

    @Test
    internal fun `can read and return html from file system with default charset (UTF-8) using the DSL`() {
        val doc = skrape(File("src/test/resources/__files/example.html")) {
            expectThat(title()).isEqualTo("i'm the title")
        }
        expectThat(doc.title()).isEqualTo("i'm the title")
    }

    @Test
    internal fun `can read and return html from file system using the DSL and non default charset`() {
        val doc = skrape(File("src/test/resources/__files/example.html"), Charsets.ISO_8859_1) {
            expectThat(title()).isEqualTo("i'm the title")
        }
        expectThat(doc.title()).isEqualTo("i'm the title")
    }

    @Test
    internal fun `can read and return html from String`() {
        val doc = skrape("<html><head><title>i'm the title</title></head></html>") {
            expectThat(title()).isEqualTo("i'm the title")
        }
        expectThat(doc.title()).isEqualTo("i'm the title")
    }

    @Test
    internal fun `can read html from file system with default charset (UTF-8) using the DSL`() {
        expect(File("src/test/resources/__files/example.html")) {
            expectThat(title()).isEqualTo("i'm the title")
        }
    }

    @Test
    internal fun `can read html from file system using the DSL and non default charset`() {
        expect(File("src/test/resources/__files/example.html"), Charsets.ISO_8859_1) {
            expectThat(title()).isEqualTo("i'm the title")
        }
    }

    @Test
    internal fun `can read html from String`() {
        expect("<html><head><title>i'm the title</title></head></html>") {
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
                        text() toBe "I have been dynamically added via Javascript"
                    }
                }
            }
        }
    }
}

class MyObject(var message: String? = null, var paragraph: String = "", var allParagraphs: List<String> = emptyList())

class MyOtherObject {
    var message: String? = null
}

class KGenericContainer(imageName: String) : GenericContainer<KGenericContainer>(imageName)
