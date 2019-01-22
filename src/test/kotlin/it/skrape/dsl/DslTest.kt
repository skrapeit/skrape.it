package it.skrape.dsl

import it.skrape.core.*
import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.net.SocketTimeoutException

internal class DslTest : WireMockSetup() {

    @Test
    internal fun `can skrape by url`() {
        wireMockServer.setupStub(path = "/example")

        skrape {
            url = "http://localhost:8080/example"

            response {

                document {
                    assertThat(title()).isEqualTo("i'm the title")
                }

                element("p") {
                    assertThat(text()).isEqualTo("i'm the title")
                }

                elements("p") {
                    assertThat(size).isEqualTo(2)
                }
            }
        }
    }

    @Test
    internal fun `will not follow redirects if configured`() {
        // given
        wireMockServer.setupRedirect()

        skrape {
            followRedirects = false

            response {
                assertThat(statusCode).isEqualTo(302)
            }
        }
    }

    @Test
    internal fun `will follow redirect by default`() {
        // given
        wireMockServer.setupRedirect()

        skrape {
            response {
                assertThat(statusCode).isEqualTo(404)
            }
        }
    }

    @Test
    internal fun `can fetch url and use HTTP verb POST`() {
        // given
        wireMockServer.setupPostStub()

        skrape {
            method = Method.POST

            response {
                assertThat(statusCode).isEqualTo(200)
                assertThat(statusMessage).isEqualTo("OK")
                assertThat(contentType).isEqualTo("application/json; charset=UTF-8")
            }
        }
    }

    @Test
    internal fun `will throw exception on timeout`() {
        wireMockServer.setupStub(delay = 3000)

        Assertions.assertThrows(SocketTimeoutException::class.java
        ) {
            skrape {
                timeout = 2000
            }
        }
    }
}