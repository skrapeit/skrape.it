package it.skrape.core

import it.skrape.*
import it.skrape.exceptions.ElementNotFoundException
import it.skrape.matchers.toBe
import it.skrape.selects.`$`
import it.skrape.selects.el
import it.skrape.selects.element
import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.net.SocketTimeoutException

internal class ScraperTest : WireMockSetup() {

    @Test
    internal fun `can scrape directly with default options`() {
        wireMockServer.setupStub(contentType = "test/type")
        val result = Scraper().scrape()

        assertThat(result.statusCode).isEqualTo(200)
        assertThat(result.document.title()).isEqualTo("i'm the title")
    }

    @Test
    internal fun `can scrape html via custom http request`() {
        wireMockServer.setupStub(path = "/example")
        val result = Scraper(Request(url = "http://localhost:8080/example")).scrape()

        assertThat(result.statusCode).isEqualTo(200)
        assertThat(result.document.title()).isEqualTo("i'm the title")
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

        Assertions.assertThrows(SocketTimeoutException::class.java
        ) {
            skrape {
                timeout = 2000
                expect {  }
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
    internal fun `dsl can fetch url and extract using it`() {
        // given
        wireMockServer.setupStub()

        val extracted: MyOtherObject = skrape {
            url = "http://localhost:8080"

            extractIt {
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
    internal fun `will throw custom exception if element could not be found via function`() {

        Assertions.assertThrows(ElementNotFoundException::class.java) {
            skrape {
                expect {
                    element(".nonExistent")
                }
            }
        }
        Assertions.assertThrows(ElementNotFoundException::class.java) {
            skrape {
                expect {
                    el(".nonExistent")
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


}

class MyObject(var message: String? = null, var paragraph: String = "", var allParagraphs: List<String> = emptyList())

class MyOtherObject {
    var message: String? = null
}
