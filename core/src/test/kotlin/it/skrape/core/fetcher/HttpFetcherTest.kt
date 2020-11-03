package it.skrape.core.fetcher

import it.skrape.*
import it.skrape.core.document
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.isEqualTo
import java.net.SocketTimeoutException

class HttpFetcherTest : WireMockSetup() {

    @Test
    fun `will fetch localhost 8080 with defaults if no params`() {
        wireMockServer.setupStub()

        val fetched = HttpFetcher.fetch(Request())

        expectThat(fetched.status { code }).isEqualTo(200)
        expectThat(fetched.contentType).isEqualTo("text/html;charset=utf-8")
        expectThat(fetched.document.titleText).isEqualTo("i'm the title")
    }

    @Test
    fun `can fetch https url and use HTTP verb GET by default`() {
        wireMockServer.setupStub(path = "/example")
        val request = Request(
                url = "https://localhost:8089/example",
                sslRelaxed = true
        )

        val fetched = HttpFetcher.fetch(request)

        expectThat(fetched.status { code }).isEqualTo(200)
        expectThat(fetched.contentType).isEqualTo("text/html;charset=utf-8")
        expectThat(fetched.document.titleText).isEqualTo("i'm the title")
    }

    @Test
    fun `will not throw exception on non existing url`() {
        val request = Request(url = "http://localhost:8080/not-existing")

        val fetched = HttpFetcher.fetch(request)

        expectThat(fetched.status { code }).isEqualTo(404)
    }

    @Test
    fun `will not follow redirects if configured`() {
        wireMockServer.setupRedirect()
        val request = Request(followRedirects = false)

        val fetched = HttpFetcher.fetch(request)

        expectThat(fetched.status { code }).isEqualTo(302)
    }

    @Test
    fun `will follow redirect by default`() {
        wireMockServer.setupRedirect()

        val fetched = HttpFetcher.fetch(Request())

        expectThat(fetched.status { code }).isEqualTo(404)
    }

    @Test
    fun `can fetch cookies`(){
        wireMockServer.setupCookiesStub(path = "/cookies")
        val request = Request(url = "https://localhost:8089/cookies", sslRelaxed = true)
        val fetched = HttpFetcher.fetch(request)

        expectThat(fetched.cookies).isEqualTo(listOf(
            Cookie("basic", "value", Expires.Session, null, Domain("localhost", false)),
            Cookie("advanced", "advancedValue", Expires.Session, null, Domain("localhost", true), "/cookies", SameSite.STRICT, true, httpOnly = true),
            Cookie("expireTest", "value", Expires.Date("Wed, 21 Oct 2015 07:28:00 GMT"), 2592000, Domain("localhost", false))
        ))
    }

    @Test
    fun `can fetch url and use HTTP verb POST`() {
        wireMockServer.setupPostStub()
        val request = Request(method = Method.POST)

        val fetched = HttpFetcher.fetch(request)

        expectThat(fetched.status { code }).isEqualTo(200)
        expectThat(fetched.contentType).isEqualTo("application/json;charset=utf-8")
        expectThat(fetched.responseBody).isEqualTo("""{"data":"some value"}""")
    }

    @Test
    fun `can fetch url and use HTTP verb PUT`() {
        wireMockServer.setupPutStub()
        val request = Request(method = Method.PUT)

        val fetched = HttpFetcher.fetch(request)

        expectThat(fetched.status { code }).isEqualTo(201)
        expectThat(fetched.responseBody).isEqualTo("i'm a PUT stub")
    }

    @Test
    fun `can fetch url and use HTTP verb DELETE`() {
        wireMockServer.setupDeleteStub()
        val request = Request(method = Method.DELETE)

        val fetched = HttpFetcher.fetch(request)

        expectThat(fetched.status { code }).isEqualTo(201)
        expectThat(fetched.responseBody).isEqualTo("i'm a DELETE stub")
    }

    @Test
    fun `can fetch url and use HTTP verb PATCH`() {
        wireMockServer.setupPatchStub()
        val request = Request(method = Method.PATCH)

        val fetched = HttpFetcher.fetch(request)

        expectThat(fetched.status { code }).isEqualTo(201)
        expectThat(fetched.responseBody).isEqualTo("i'm a PATCH stub")
    }

    @Test
    fun `can fetch url and use HTTP verb HEAD`() {
        wireMockServer.setupHeadStub()
        val request = Request(method = Method.HEAD)

        val fetched = HttpFetcher.fetch(request)

        expectThat(fetched.status { code }).isEqualTo(201)
        expectThat(fetched.httpHeader("result")).isEqualTo("i'm a HEAD stub")
    }

    @Test
    fun `will throw exception on timeout`() {
        wireMockServer.setupStub(delay = 6000)

        expectThrows<SocketTimeoutException> {
            HttpFetcher.fetch(Request())
        }
    }
}
