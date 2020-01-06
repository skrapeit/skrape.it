package it.skrape.core.fetcher

import it.skrape.*
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.net.SocketTimeoutException

internal class HttpFetcherTest : WireMockSetup() {

    @Test
    internal fun `will fetch localhost 8080 with defaults if no params`() {
        // given
        wireMockServer.setupStub()

        // when
        val fetched = HttpFetcher(Request()).fetch()

        // then
        expectThat(fetched.statusCode).isEqualTo(200)
        expectThat(fetched.contentType).isEqualTo("text/html;charset=utf-8")
        expectThat(fetched.document.title()).isEqualTo("i'm the title")
    }

    @Disabled("need to find a way to avoid SSLHandshakeException on JDK11")
    @Test
    internal fun `can fetch https url and use HTTP verb GET by default`() {
        // given
        wireMockServer.setupStub(path = "/example")
        val options = Request().apply {
            url = "https://localhost:8089/example"
        }

        // when
        val fetched = HttpFetcher(options).fetch()

        // then
        expectThat(fetched.statusCode).isEqualTo(200)
        expectThat(fetched.contentType).isEqualTo("text/html;charset=utf-8")
        expectThat(fetched.document.title()).isEqualTo("i'm the title")
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
        expectThat(fetched.statusCode).isEqualTo(404)
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
        expectThat(fetched.statusCode).isEqualTo(302)
    }

    @Test
    internal fun `will follow redirect by default`() {
        // given
        wireMockServer.setupRedirect()

        // when
        val fetched = HttpFetcher(Request()).fetch()

        // then
        expectThat(fetched.statusCode).isEqualTo(404)
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
        expectThat(fetched.statusCode).isEqualTo(200)
        expectThat(fetched.contentType).isEqualTo("application/json;charset=utf-8")
        expectThat(fetched.responseBody).isEqualTo("""{"data":"some value"}""")
    }

    @Test
    internal fun `can fetch url and use HTTP verb PUT`() {
        // given
        wireMockServer.setupPutStub()
        val options = Request().apply {
            method = Method.PUT
        }

        // when
        val fetched = HttpFetcher(options).fetch()

        // then
        expectThat(fetched.statusCode).isEqualTo(201)
        expectThat(fetched.responseBody).isEqualTo("i'm a PUT stub")
    }

    @Test
    internal fun `can fetch url and use HTTP verb DELETE`() {
        // given
        wireMockServer.setupDeleteStub()
        val options = Request().apply {
            method = Method.DELETE
        }

        // when
        val fetched = HttpFetcher(options).fetch()

        // then
        expectThat(fetched.statusCode).isEqualTo(201)
        expectThat(fetched.responseBody).isEqualTo("i'm a DELETE stub")
    }

    @Test
    internal fun `can fetch url and use HTTP verb PATCH`() {
        // given
        wireMockServer.setupPatchStub()
        val options = Request().apply {
            method = Method.PATCH
        }

        // when
        val fetched = HttpFetcher(options).fetch()

        // then
        expectThat(fetched.statusCode).isEqualTo(201)
        expectThat(fetched.responseBody).isEqualTo("i'm a PATCH stub")
    }

    @Test
    internal fun `can fetch url and use HTTP verb HEAD`() {
        // given
        wireMockServer.setupHeadStub()
        val options = Request().apply {
            method = Method.HEAD
        }

        // when
        val fetched = HttpFetcher(options).fetch()

        // then
        expectThat(fetched.statusCode).isEqualTo(201)
        expectThat(fetched.httpHeader("result")).isEqualTo("i'm a HEAD stub")
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
