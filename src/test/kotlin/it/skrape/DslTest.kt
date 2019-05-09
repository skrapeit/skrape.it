package it.skrape

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import it.skrape.core.Method
import it.skrape.core.Mode
import it.skrape.core.WireMockSetup
import it.skrape.core.setupPostStub
import it.skrape.core.setupRedirect
import it.skrape.core.setupStub
import it.skrape.exceptions.ElementNotFoundException
import it.skrape.matchers.toBe
import it.skrape.selects.`$`
import it.skrape.selects.body
import it.skrape.selects.el
import it.skrape.selects.element
import it.skrape.selects.elements
import it.skrape.selects.header
import it.skrape.selects.headers
import it.skrape.selects.title
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.io.File
import java.net.SocketTimeoutException

@Testcontainers
internal class DslTest : WireMockSetup() {

    companion object {
        @Container
        private val httpBin = KGenericContainer("kennethreitz/httpbin")
                .withExposedPorts(80)
                .waitingFor(Wait.forHttp("/").forStatusCode(200))
    }

    @Test
    internal fun `dsl can skrape by url`() {
        wireMockServer.setupStub(path = "/example")

        skrape {
            url = "http://localhost:8080/example"

            expect {

                assertThat(statusCode).isEqualTo(200)
                assertThat(statusMessage).isEqualTo("OK")
                assertThat(contentType).isEqualTo("text/html; charset=UTF-8")

                el("title").text() toBe "i'm the title"

                title {
                    assertThat(this).isEqualTo("i'm the title")
                }

                element("p") {
                    attr("bla") toBe ""
                    assertThat(text()).isEqualTo("i'm a paragraph")
                    assertThat(text()).isEqualTo("i'm a paragraph")
                }

                elements("p") {
                    assertThat(size).isEqualTo(2)
                }
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
                assertThat(statusCode).isEqualTo(302)
            }
        }
    }

    @Test
    internal fun `dsl can check certain header`() {
        // given
        wireMockServer.setupStub()

        skrape {
            expect {
                val header = header("Content-Type") {
                    assertThat(this).isEqualTo("text/html; charset=UTF-8")
                }
                assertThat(header).isEqualTo("text/html; charset=UTF-8")

                val nonExistingHeader = header("Non-Existing") {
                    assertThat(this).isNull()
                }
                assertThat(nonExistingHeader).isNull()
            }
        }
    }

    @Test
    internal fun `dsl can check headers`() {
        // given
        wireMockServer.setupStub()

        skrape {
            expect {
                val headers = headers {
                    assertThat(this).contains("Content-Type", "text/html; charset=UTF-8")
                }
                assertThat(headers).contains("Content-Type", "text/html; charset=UTF-8")
            }
        }
    }

    @Test
    internal fun `dsl can get body`() {
        // given
        wireMockServer.setupStub()

        skrape {
            expect {
                val body = body {
                    assertThat(this.text()).contains("i'm a paragraph")
                }
                assertThat(body.text()).contains("i'm a paragraph")
            }
        }
    }

    @Test
    internal fun `dsl will follow redirect by default`() {
        // given
        wireMockServer.setupRedirect()

        skrape {
            expect {
                assertThat(statusCode).isEqualTo(404)
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

                assertThat(request.method).isEqualTo(Method.POST)

                assertThat(statusCode).isEqualTo(200)
                assertThat(statusMessage).isEqualTo("OK")
                assertThat(contentType).isEqualTo("application/json; charset=UTF-8")
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
            assertThat(extracted.message).isEqualTo("OK")
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
            assertThat(extracted.message).isEqualTo("OK")
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
        assertThat(extracted.message).isEqualTo("OK")
    }

    @Test
    internal fun `will throw custom exception if element could not be found via lambda`() {

        Assertions.assertThrows(ElementNotFoundException::class.java) {
            skrape {
                expect {
                    element(".nonExistent") {}
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
                MyObject(
                        message = statusMessage,
                        paragraph = el("p").text(),
                        allParagraphs = `$`("p").map { it.text() }
                )
            }
        }
        assertThat(extracted.message).isEqualTo("OK")
        assertThat(extracted.paragraph).isEqualTo("i'm a paragraph")
        assertThat(extracted.allParagraphs).containsExactly("i'm a paragraph", "i'm a second paragraph")
    }

    @Test
    internal fun `can read and return html from file system with default charset (UTF-8) using the DSL`() {
        val doc = skrape(File("src/test/resources/__files/example.html")) {
            assertThat(title()).isEqualTo("i'm the title")
        }
        assertThat(doc.title()).isEqualTo("i'm the title")
    }

    @Test
    internal fun `can read and return html from file system using the DSL and non default charset`() {
        val doc = skrape(File("src/test/resources/__files/example.html"), Charsets.ISO_8859_1) {
            assertThat(title()).isEqualTo("i'm the title")
        }
        assertThat(doc.title()).isEqualTo("i'm the title")
    }

    @Test
    internal fun `can read and return html from String`() {
        val doc = skrape("<html><head><title>i'm the title</title></head></html>") {
            assertThat(title()).isEqualTo("i'm the title")
        }
        assertThat(doc.title()).isEqualTo("i'm the title")
    }

    @Test
    internal fun `can read html from file system with default charset (UTF-8) using the DSL`() {
        expect(File("src/test/resources/__files/example.html")) {
            assertThat(title()).isEqualTo("i'm the title")
        }
    }

    @Test
    internal fun `can read html from file system using the DSL and non default charset`() {
        expect(File("src/test/resources/__files/example.html"), Charsets.ISO_8859_1) {
            assertThat(title()).isEqualTo("i'm the title")
        }
    }

    @Test
    internal fun `can read html from String`() {
        expect("<html><head><title>i'm the title</title></head></html>") {
            assertThat(title()).isEqualTo("i'm the title")
        }
    }

    @Test
    internal fun `can scrape js rendered page`() {
        wireMockServer.setupStub(fileName = "js.html")

        skrape {
            mode = Mode.DOM
            expect {
                element("div.dynamic").text() toBe "I have been dynamically added via Javascript"
            }
        }
    }

    @Test
    internal fun `can send cookies with request in js rendering mode`() {

        skrape {
            mode = Mode.DOM
            url = "http://localhost:${httpBin.firstMappedPort}/cookies"
            cookies = mapOf("myCookie" to "myCookieValue")
            expect {
                assertThat(body).contains("\"myCookie\": \"myCookieValue\"")
            }
        }
    }

    @Test
    internal fun `can send cookies with request in http mode`() {

        skrape {
            mode = Mode.SOURCE
            url = "http://localhost:${httpBin.firstMappedPort}/cookies"
            cookies = mapOf("someCookie" to "someCookieValue")
            expect {
                assertThat(body).contains("\"someCookie\": \"someCookieValue\"")
            }
        }
    }
}

class MyObject(var message: String? = null, var paragraph: String = "", var allParagraphs: List<String> = emptyList())

class MyOtherObject {
    var message: String? = null
}

class KGenericContainer(imageName: String) : GenericContainer<KGenericContainer>(imageName)
