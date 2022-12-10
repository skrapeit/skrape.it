package it.skrape.fetcher

import Testcontainer
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldBeOneOf
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.types.shouldBeInstanceOf
import io.ktor.client.network.sockets.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.utils.io.*
import io.ktor.utils.io.errors.*
import setupCookiesStub
import setupDeleteStub
import setupHeadStub
import setupPatchStub
import setupPostStub
import setupPutStub
import setupRedirect
import setupStub


//@DisabledOnOs(OS.WINDOWS)
class AsyncFetcherTest : FunSpec({

    lateinit var wiremock: Testcontainer.Wiremock
    lateinit var httpBin: String

    val baseRequest by lazy { KtorRequestBuilder().apply { url(wiremock.httpUrl) } }

    beforeTest {
        wiremock = Testcontainer.getWiremock()
        httpBin = Testcontainer.getHttpBin()
    }

    test("can fetch https url and use HTTP verb GET by default") {
        wiremock.setupStub(path = "/example")
        val request = baseRequest.copy(
            url = "${wiremock.httpsUrl}/example",
            sslRelaxed = true
        )

        val fetched = Scraper(request).scrape()

        fetched.status { code }.shouldBe(200)
        fetched.contentType.shouldBe("text/html; charset=UTF-8")
        fetched.responseBody.shouldContain("i'm the title")

    }

    test("will not throw exception on non existing url") {
        val request = baseRequest.copy(url = "${wiremock.httpUrl}/not-existing", timeout = 9999999)

        val fetched = Scraper(request).scrape()

        fetched.status { code }.shouldBe(404)
    }

    test("will not follow redirects if configured") {
        wiremock.setupRedirect()
        val request = baseRequest.copy(followRedirects = false)

        val fetched = Scraper(request).scrape()

        fetched.status { code }.shouldBe(302)

    }

    test("will follow redirect by default") {
        wiremock.setupRedirect()

        val fetched = Scraper(baseRequest).scrape()

        fetched.status { code }.shouldBe(404)
    }

    test("can fetch cookies") {
        wiremock.setupCookiesStub(path = "/cookies")
        val request = baseRequest.copy(
            url = "${wiremock.httpsUrl}/cookies",
            sslRelaxed = true
        )
        val fetched = Scraper(request).scrape()

        fetched.cookies.shouldBe(
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

    test("can fetch url and use HTTP verb POST") {
        wiremock.setupPostStub()
        val request = baseRequest.copy(method = HttpMethod.Post)

        val fetched = Scraper(request).scrape()

        fetched.status { code }.shouldBe(200)
        fetched.contentType.shouldBe("application/json; charset=UTF-8")
        fetched.responseBody.shouldBe("""{"data":"some value"}""")
    }

    test("can POST body") {

        val request = KtorRequestBuilder().apply {
            url("$httpBin/post")
            method = HttpMethod.Post
            body {
                json {
                    "foo" to "bar"
                }
            }
        }

        val fetched = Scraper(request).scrape()
        fetched.responseBody.shouldContain(""""data": "{\"foo\":\"bar\"}"""")
    }

    test("can fetch url and use HTTP verb PUT") {
        wiremock.setupPutStub()
        val request = baseRequest.copy(method = HttpMethod.Put)

        val fetched = Scraper(request).scrape()

        fetched.status { code }.shouldBe(201)
        fetched.responseBody.shouldBe("i'm a PUT stub")
    }

    test("can fetch url and use HTTP verb DELETE") {
        wiremock.setupDeleteStub()
        val request = baseRequest.copy(method = HttpMethod.Delete)

        val fetched = Scraper(request).scrape()

        fetched.status { code }.shouldBe(201)
        fetched.responseBody.shouldBe("i'm a DELETE stub")
    }

    test("can fetch url and use HTTP verb PATCH") {
        wiremock.setupPatchStub()
        val request = baseRequest.copy(method = HttpMethod.Patch)

        val fetched = Scraper(request).scrape()

        fetched.status { code }.shouldBe(201)
        fetched.responseBody.shouldBe("i'm a PATCH stub")
    }

    test("can fetch url and use HTTP verb HEAD") {
        wiremock.setupHeadStub()
        val request = baseRequest.copy(method = HttpMethod.Head)

        val fetched = Scraper(request).scrape()

        fetched.status { code }.shouldBe(201)
        /* Removed this check since HTTP HEAD responses should have their body ignored
        *  See https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/HEAD
        */
        //fetched.httpHeader("result").shouldBe("i'm a HEAD stub")
    }

    test("will throw exception on timeout") {
        wiremock.setupStub(path = "/delayed", delay = 6000)
        try {
            Scraper(baseRequest.copy(url = "${wiremock.httpUrl}/delayed", timeout = 10)).scrape()
        } catch (ex: Exception) {
            //JS wraps the actual exception in a CancellaionException
            val tEx = if (ex is CancellationException) ex.cause else ex
            //This expectation is weird since the timeout can be either of 3 exceptions which are all valid
            tEx.shouldBeInstanceOf<IOException>()
            println(tEx::class)
            tEx::class.shouldBeOneOf(
                SocketTimeoutException::class,
                ConnectTimeoutException::class,
                HttpRequestTimeoutException::class
            )
        }
    }
})
