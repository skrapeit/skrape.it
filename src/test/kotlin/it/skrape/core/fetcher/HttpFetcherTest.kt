package it.skrape.core.fetcher

import it.skrape.*
import it.skrape.core.document
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.net.SocketTimeoutException

internal class HttpFetcherTest : WireMockSetup() {

    @Test
    internal fun `will fetch localhost 8080 with defaults if no params`() {
        wireMockServer.setupStub()

        val fetched = HttpFetcher(Request()).fetch()

        expectThat(fetched.status { code }).isEqualTo(200)
        expectThat(fetched.contentType).isEqualTo("text/html;charset=utf-8")
        expectThat(fetched.document.titleText).isEqualTo("i'm the title")
    }

    @Test
    internal fun `can fetch https url and use HTTP verb GET by default`() {
        wireMockServer.setupStub(path = "/example")
        val request = Request(
                url = "https://localhost:8089/example",
                sslRelaxed = true
        )

        val fetched = HttpFetcher(request).fetch()

        expectThat(fetched.status { code }).isEqualTo(200)
        expectThat(fetched.contentType).isEqualTo("text/html;charset=utf-8")
        expectThat(fetched.document.titleText).isEqualTo("i'm the title")
    }

    @Test
    internal fun `will not throw exception on non existing url`() {
        val request = Request(url = "http://localhost:8080/not-existing")

        val fetched = HttpFetcher(request).fetch()

        expectThat(fetched.status { code }).isEqualTo(404)
    }

    @Test
    internal fun `will not follow redirects if configured`() {
        wireMockServer.setupRedirect()
        val request = Request(followRedirects = false)

        val fetched = HttpFetcher(request).fetch()

        expectThat(fetched.status { code }).isEqualTo(302)
    }

    @Test
    internal fun `will follow redirect by default`() {
        wireMockServer.setupRedirect()

        val fetched = HttpFetcher(Request()).fetch()

        expectThat(fetched.status { code }).isEqualTo(404)
    }

    @Test
    internal fun `can fetch url and use HTTP verb POST`() {
        wireMockServer.setupPostStub()
        val request = Request(method = Method.POST)

        val fetched = HttpFetcher(request).fetch()

        expectThat(fetched.status { code }).isEqualTo(200)
        expectThat(fetched.contentType).isEqualTo("application/json;charset=utf-8")
        expectThat(fetched.responseBody).isEqualTo("""{"data":"some value"}""")
    }

    @Test
    internal fun `can fetch url and use HTTP verb PUT`() {
        wireMockServer.setupPutStub()
        val request = Request(method = Method.PUT)

        val fetched = HttpFetcher(request).fetch()

        expectThat(fetched.status { code }).isEqualTo(201)
        expectThat(fetched.responseBody).isEqualTo("i'm a PUT stub")
    }

    @Test
    internal fun `can fetch url and use HTTP verb DELETE`() {
        wireMockServer.setupDeleteStub()
        val request = Request(method = Method.DELETE)

        val fetched = HttpFetcher(request).fetch()

        expectThat(fetched.status { code }).isEqualTo(201)
        expectThat(fetched.responseBody).isEqualTo("i'm a DELETE stub")
    }

    @Test
    internal fun `can fetch url and use HTTP verb PATCH`() {
        wireMockServer.setupPatchStub()
        val request = Request(method = Method.PATCH)

        val fetched = HttpFetcher(request).fetch()

        expectThat(fetched.status { code }).isEqualTo(201)
        expectThat(fetched.responseBody).isEqualTo("i'm a PATCH stub")
    }

    @Test
    internal fun `can fetch url and use HTTP verb HEAD`() {
        wireMockServer.setupHeadStub()
        val request = Request(method = Method.HEAD)

        val fetched = HttpFetcher(request).fetch()

        expectThat(fetched.status { code }).isEqualTo(201)
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
