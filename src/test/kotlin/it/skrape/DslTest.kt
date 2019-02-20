package it.skrape

import it.skrape.core.*
import it.skrape.exceptions.ElementNotFoundException
import it.skrape.matchers.toBe
import it.skrape.selects.`$`
import it.skrape.selects.el
import org.assertj.core.api.KotlinAssertions
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
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

        Assertions.assertThrows(SocketTimeoutException::class.java
        ) {
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
}

class MyObject(var message: String? = null, var paragraph: String = "", var allParagraphs: List<String> = emptyList())

class MyOtherObject {
    var message: String? = null
}