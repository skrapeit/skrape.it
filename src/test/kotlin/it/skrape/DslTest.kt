package it.skrape

import it.skrape.core.*
import it.skrape.exceptions.ElementNotFoundException
import it.skrape.matchers.toBe
import it.skrape.selects.*
import org.assertj.core.api.KotlinAssertions
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import java.net.SocketTimeoutException

internal class DslTest : WireMockSetup() {
    @Test
    internal fun `dsl can skrape by url`() {
        wireMockServer.setupStub(path = "/example")

        skrape {
            url = "http://localhost:8080/example"

            expect {

                KotlinAssertions.assertThat(statusCode).isEqualTo(200)
                KotlinAssertions.assertThat(statusMessage).isEqualTo("OK")
                KotlinAssertions.assertThat(contentType).isEqualTo("text/html; charset=UTF-8")

                el("title").text() toBe "i'm the title"

                title {
                    KotlinAssertions.assertThat(this).isEqualTo("i'm the title")
                }

                element("p") {
                    attr("bla") toBe ""
                    KotlinAssertions.assertThat(text()).isEqualTo("i'm a paragraph")
                    KotlinAssertions.assertThat(text()).isEqualTo("i'm a paragraph")
                }

                elements("p") {
                    KotlinAssertions.assertThat(size).isEqualTo(2)
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
                KotlinAssertions.assertThat(statusCode).isEqualTo(302)
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
                    KotlinAssertions.assertThat(this).isEqualTo("text/html; charset=UTF-8")
                }
                KotlinAssertions.assertThat(header).isEqualTo("text/html; charset=UTF-8")

                val nonExistingHeader = header("Non-Existing") {
                    KotlinAssertions.assertThat(this).isNull()
                }
                KotlinAssertions.assertThat(nonExistingHeader).isNull()
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
                    KotlinAssertions.assertThat(this).containsEntry("Content-Type", "text/html; charset=UTF-8")
                }
                KotlinAssertions.assertThat(headers).containsEntry("Content-Type", "text/html; charset=UTF-8")
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
                    KotlinAssertions.assertThat(this.text()).contains("i'm a paragraph")
                }
                KotlinAssertions.assertThat(body.text()).contains("i'm a paragraph")
            }
        }
    }

    @Test
    internal fun `dsl will follow redirect by default`() {
        // given
        wireMockServer.setupRedirect()

        skrape {
            expect {
                KotlinAssertions.assertThat(statusCode).isEqualTo(404)
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

                KotlinAssertions.assertThat(request.method).isEqualTo(Method.POST)

                KotlinAssertions.assertThat(statusCode).isEqualTo(200)
                KotlinAssertions.assertThat(statusMessage).isEqualTo("OK")
                KotlinAssertions.assertThat(contentType).isEqualTo("application/json; charset=UTF-8")
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
            KotlinAssertions.assertThat(extracted.message).isEqualTo("OK")
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
            KotlinAssertions.assertThat(extracted.message).isEqualTo("OK")
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
        KotlinAssertions.assertThat(extracted.message).isEqualTo("OK")
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
        KotlinAssertions.assertThat(extracted.message).isEqualTo("OK")
        KotlinAssertions.assertThat(extracted.paragraph).isEqualTo("i'm a paragraph")
        KotlinAssertions.assertThat(extracted.allParagraphs).containsExactly("i'm a paragraph", "i'm a second paragraph")
    }

    @Test
    internal fun `can read html from file system with default charset (UTF-8) using the DSL`() {
        skrape(File("src/test/resources/__files/example.html")) {
            KotlinAssertions.assertThat(title()).isEqualTo("i'm the title")
        }
    }

    @Test
    internal fun `can read html from file system using the DSL and non default charset`() {
        skrape(File("src/test/resources/__files/example.html"), Charsets.ISO_8859_1) {
            KotlinAssertions.assertThat(title()).isEqualTo("i'm the title")
        }
    }

    @Test
    internal fun `can read html from String`() {
        skrape("<html><head><title>i'm the title</title></head></html>") {
            KotlinAssertions.assertThat(title()).isEqualTo("i'm the title")
        }
    }

    @Test
    internal fun `can scrape js rendered page`() {
        wireMockServer.setupStub(fileName = "js.html")

        skrape {
            mode = Mode.BROWSER
            expect {
                element("div.dynamic").text() toBe "I have been dynamically added via Javascript"
            }
        }
    }
}

class MyObject(var message: String? = null, var paragraph: String = "", var allParagraphs: List<String> = emptyList())

class MyOtherObject {
    var message: String? = null
}