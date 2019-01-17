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

            result {
                assertThat(document.title()).isEqualTo("i'm the title")
            }
        }

    }

    @Test
    fun `will not follow redirects if configured`() {
        // given
        wireMockServer.setupRedirect()

        skrape {
            followRedirects = false

            result {
                assertThat(response.statusCode()).isEqualTo(302)
            }
        }
    }

    @Test
    fun `will follow redirect by default`() {
        // given
        wireMockServer.setupRedirect()

        skrape {
            result {
                assertThat(response.statusCode()).isEqualTo(404)
            }
        }
    }

    @Test
    fun `can fetch url and use HTTP verb POST`() {
        // given
        wireMockServer.setupPostStub()

        skrape {
            method = Method.POST

            result {
                assertThat(response.statusCode()).isEqualTo(200)
                assertThat(response.contentType()).isEqualTo("application/json; charset=UTF-8")
            }
        }
    }

    @Test
    fun `will throw exception on timeout`() {
        wireMockServer.setupStub(delay = 3000)

        Assertions.assertThrows(SocketTimeoutException::class.java
        ) {
            skrape {
                timeout = 2000
            }
        }
    }
}