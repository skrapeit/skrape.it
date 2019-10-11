package it.skrape.core.fetcher

import assertk.assertThat
import assertk.assertions.isEqualTo
import it.skrape.core.Method
import it.skrape.core.Request
import it.skrape.core.WireMockSetup
import it.skrape.core.setupPostStub
import it.skrape.core.setupRedirect
import it.skrape.core.setupStub
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.net.SocketTimeoutException

internal class HttpFetcherTest : WireMockSetup() {

    @Test
    internal fun `will fetch localhost 8080 with defaults if no params`() {
        // given
        wireMockServer.setupStub()

        // when
        val fetched = HttpFetcher(Request()).fetch()

        // then
        assertThat(fetched.statusCode).isEqualTo(200)
        assertThat(fetched.contentType).isEqualTo("text/html;charset=utf-8")
        assertThat(fetched.document.title()).isEqualTo("i'm the title")
    }

    @Test
    internal fun `can fetch url and use HTTP verb GET by default`() {
        // given
        wireMockServer.setupStub(path = "/example")
        val options = Request().apply {
            url = "https://localhost:8089/example"
        }

        // when
        val fetched = HttpFetcher(options).fetch()

        // then
        assertThat(fetched.statusCode).isEqualTo(200)
        assertThat(fetched.contentType).isEqualTo("text/html;charset=utf-8")
        assertThat(fetched.document.title()).isEqualTo("i'm the title")
    }

    @Test
    internal fun `will not throw exception on non existing url`() {
        // given
        val options = Request().apply {
            url = "http://localhost:8080/not-existing"
        }

        // when
        val fetched = HttpFetcher(options).fetch()

        // then
        assertThat(fetched.statusCode).isEqualTo(404)
    }

    @Test
    internal fun `will not follow redirects if configured`() {
        // given
        wireMockServer.setupRedirect()
        val options = Request().apply {
            followRedirects = false
        }

        // when
        val fetched = HttpFetcher(options).fetch()

        // then
        assertThat(fetched.statusCode).isEqualTo(302)
    }

    @Test
    internal fun `will follow redirect by default`() {
        // given
        wireMockServer.setupRedirect()

        // when
        val fetched = HttpFetcher(Request()).fetch()

        // then
        assertThat(fetched.statusCode).isEqualTo(404)
    }

    @Test
    internal fun `can fetch url and use HTTP verb POST`() {
        // given
        wireMockServer.setupPostStub()
        val options = Request().apply {
            method = Method.POST
        }

        // when
        val fetched = HttpFetcher(options).fetch()

        // then
        assertThat(fetched.statusCode).isEqualTo(200)
        assertThat(fetched.contentType).isEqualTo("application/json;charset=utf-8")
        assertThat(fetched.responseBody).isEqualTo("""{"data":"some value"}""")
    }

    @Test
    internal fun `will throw exception on timeout`() {
        wireMockServer.setupStub(delay = 6000)

        assertThrows(SocketTimeoutException::class.java
        ) {
            HttpFetcher(Request()).fetch()
        }
    }
}
