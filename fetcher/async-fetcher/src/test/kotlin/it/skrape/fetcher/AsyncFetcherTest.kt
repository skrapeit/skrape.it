package it.skrape.fetcher

import Testcontainer
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import setupCookiesStub
import setupDeleteStub
import setupHeadStub
import setupPatchStub
import setupPostStub
import setupPutStub
import setupRedirect
import setupStub
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.contains
import strikt.assertions.isEqualTo
import java.net.SocketTimeoutException

private val wiremock = Testcontainer.wiremock
private val httpBin = Testcontainer.httpBin

class AsyncFetcherTest {

    private val baseRequest by lazy { Request(url = "${wiremock.httpUrl}/") }

    @Test
    fun `can fetch https url and use HTTP verb GET by default`() {
        wiremock.setupStub(path = "/example")
        val request = baseRequest.copy(
            url = "${wiremock.httpsUrl}/example",
            sslRelaxed = true
        )

        val fetched = runBlocking { AsyncFetcher.fetch(request) }

        expectThat(fetched.status { code }).isEqualTo(200)
        expectThat(fetched.contentType).isEqualTo("text/html;charset=UTF-8")
        expectThat(fetched.responseBody).contains("i'm the title")

    }

    @Test
    fun `will not throw exception on non existing url`() {
        val request = baseRequest.copy(url = "${wiremock.httpUrl}/not-existing", timeout = 9999999)

        val fetched = runBlocking { AsyncFetcher.fetch(request) }

        expectThat(fetched.status { code }).isEqualTo(404)
    }

    @Test
    fun `will not follow redirects if configured`() {
        wiremock.setupRedirect()
        val request = baseRequest.copy(followRedirects = false)

        runBlocking {
            val fetched = AsyncFetcher.fetch(request)

            expectThat(fetched.status { code }).isEqualTo(302)
        }

    }

    @Test
    fun `will follow redirect by default`() {
        wiremock.setupRedirect()

        runBlocking {
            val fetched = AsyncFetcher.fetch(baseRequest)

            expectThat(fetched.status { code }).isEqualTo(404)
        }
    }

    @Test
    fun `can fetch cookies`() {
        wiremock.setupCookiesStub(path = "/cookies")
        val request = baseRequest.copy(
            url = "${wiremock.httpsUrl}/cookies",
            sslRelaxed = true
        )
        val fetched = runBlocking { AsyncFetcher.fetch(request) }

        expectThat(fetched.cookies).isEqualTo(
            listOf(
                Cookie("basic", "value", Expires.Session, null, Domain("localhost", false)),
                Cookie(
                    "advanced",
                    "advancedValue",
                    Expires.Session,
                    null,
                    Domain("localhost", true),
                    "/cookies",
                    SameSite.STRICT,
                    true,
                    httpOnly = true
                ),
                Cookie(
                    "expireTest",
                    "value",
                    Expires.Date("Wed, 21 Oct 2015 07:28:00 GMT"),
                    2592000,
                    Domain("localhost", false)
                )
            )
        )
    }

    @Test
    fun `can fetch url and use HTTP verb POST`() {
        wiremock.setupPostStub()
        val request = baseRequest.copy(method = Method.POST)

        val fetched = runBlocking { AsyncFetcher.fetch(request) }

        expectThat(fetched.status { code }).isEqualTo(200)
        expectThat(fetched.contentType).isEqualTo("application/json;charset=UTF-8")
        expectThat(fetched.responseBody).isEqualTo("""{"data":"some value"}""")
    }

    @Test
    fun `can POST body`() {

        val request = Request(
            url = "$httpBin/post",
            method = Method.POST
        ).apply {
            body {
                json {
                    "foo" to "bar"
                }
            }
        }

        val fetched = runBlocking { AsyncFetcher.fetch(request) }
        expectThat(fetched.responseBody).contains(""""data": "{\"foo\":\"bar\"}"""")
    }

    @Test
    fun `can fetch url and use HTTP verb PUT`() {
        wiremock.setupPutStub()
        val request = baseRequest.copy(method = Method.PUT)

        val fetched = runBlocking { AsyncFetcher.fetch(request) }

        expectThat(fetched.status { code }).isEqualTo(201)
        expectThat(fetched.responseBody).isEqualTo("i'm a PUT stub")
    }

    @Test
    fun `can fetch url and use HTTP verb DELETE`() {
        wiremock.setupDeleteStub()
        val request = baseRequest.copy(method = Method.DELETE)

        val fetched = runBlocking { AsyncFetcher.fetch(request) }

        expectThat(fetched.status { code }).isEqualTo(201)
        expectThat(fetched.responseBody).isEqualTo("i'm a DELETE stub")
    }

    @Test
    fun `can fetch url and use HTTP verb PATCH`() {
        wiremock.setupPatchStub()
        val request = baseRequest.copy(method = Method.PATCH)

        val fetched = runBlocking { AsyncFetcher.fetch(request) }

        expectThat(fetched.status { code }).isEqualTo(201)
        expectThat(fetched.responseBody).isEqualTo("i'm a PATCH stub")
    }

    @Test
    fun `can fetch url and use HTTP verb HEAD`() {
        wiremock.setupHeadStub()
        val request = baseRequest.copy(method = Method.HEAD)

        val fetched = runBlocking { AsyncFetcher.fetch(request) }

        expectThat(fetched.status { code }).isEqualTo(201)
        expectThat(fetched.httpHeader("result")).isEqualTo("i'm a HEAD stub")
    }

    @Test
    fun `will throw exception on timeout`() {
        wiremock.setupStub(path = "/delayed", delay = 6000)

        expectThrows<SocketTimeoutException> {
            AsyncFetcher.fetch(baseRequest.copy(url = "${wiremock.httpUrl}/delayed"))
        }
    }
}
