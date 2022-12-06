package it.skrape.fetcher

import Testcontainer
import io.kotest.inspectors.forOne
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.types.shouldBeInstanceOf
import io.ktor.client.network.sockets.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.reflect.*
import io.ktor.utils.io.*
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.test.TestScope
import setupCookiesStub
import setupDeleteStub
import setupHeadStub
import setupPatchStub
import setupPostStub
import setupPutStub
import setupRedirect
import setupStub
import kotlin.js.JsName
import kotlin.test.Test


//@DisabledOnOs(OS.WINDOWS)
class AsyncFetcherTest {

    lateinit var wiremock: Testcontainer.Wiremock
    lateinit var httpBin: String

    private suspend fun initWiremock() {
        if (!this::wiremock.isInitialized) wiremock = Testcontainer.getWiremock()
        //return wiremock
    }

    private suspend fun initHttpBin() {
        if (!this::httpBin.isInitialized) httpBin = Testcontainer.getHttpBin()
        //return httpBin
    }

    //Helper to ensure wiremock and httpbin are initialized
    private fun runTest(block: suspend TestScope.() -> Unit) = runTestWith(::initWiremock, ::initHttpBin, block = block)

    private fun runTestWith(vararg init: suspend () -> Unit, block: suspend TestScope.() -> Unit) =
        kotlinx.coroutines.test.runTest {
            for (func in init) {
                func()
            }
            block()
        }

    private val baseRequest by lazy { KtorRequestBuilder().apply { url(wiremock.httpUrl) } }

    @Test
    @JsName("canfetchhttpsurlanduseHTTPverbGETbydefault")
    fun `can fetch https url and use HTTP verb GET by default`() = runTest {
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

    @Test
    @JsName("willnotthrowexceptiononnonexistingurl")
    fun `will not throw exception on non existing url`() = runTest {
        val request = baseRequest.copy(url = "${wiremock.httpUrl}/not-existing", timeout = 9999999)

        val fetched = Scraper(request).scrape()

        fetched.status { code }.shouldBe(404)
    }

    @Test
    @JsName("willnotfollowredirectsifconfigured")
    fun `will not follow redirects if configured`() = runTest {
        wiremock.setupRedirect()
        val request = baseRequest.copy(followRedirects = false)

        val fetched = Scraper(request).scrape()

        fetched.status { code }.shouldBe(302)

    }

    @Test
    @JsName("willfollowredirectbydefault")
    fun `will follow redirect by default`() = runTest {
        wiremock.setupRedirect()

        val fetched = Scraper(baseRequest).scrape()

        fetched.status { code }.shouldBe(404)
    }

    @Test
    @JsName("canfetchcookies")
    fun `can fetch cookies`() = runTest {
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

    @Test
    @JsName("canfetchurlanduseHTTPverbPOST")
    fun `can fetch url and use HTTP verb POST`() = runTest {
        wiremock.setupPostStub()
        val request = baseRequest.copy(method = HttpMethod.Post)

        val fetched = Scraper(request).scrape()

        fetched.status { code }.shouldBe(200)
        fetched.contentType.shouldBe("application/json; charset=UTF-8")
        fetched.responseBody.shouldBe("""{"data":"some value"}""")
    }

    @Test
    @JsName("canPOSTbody")
    fun `can POST body`() = runTest {

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

    @Test
    @JsName("canfetchurlanduseHTTPverbPUT")
    fun `can fetch url and use HTTP verb PUT`() = runTest {
        wiremock.setupPutStub()
        val request = baseRequest.copy(method = HttpMethod.Put)

        val fetched = Scraper(request).scrape()

        fetched.status { code }.shouldBe(201)
        fetched.responseBody.shouldBe("i'm a PUT stub")
    }

    @Test
    @JsName("canfetchurlanduseHTTPverbDELETE")
    fun `can fetch url and use HTTP verb DELETE`() = runTest {
        wiremock.setupDeleteStub()
        val request = baseRequest.copy(method = HttpMethod.Delete)

        val fetched = Scraper(request).scrape()

        fetched.status { code }.shouldBe(201)
        fetched.responseBody.shouldBe("i'm a DELETE stub")
    }

    @Test
    @JsName("canfetchurlanduseHTTPverbPATCH")
    fun `can fetch url and use HTTP verb PATCH`() = runTest {
        wiremock.setupPatchStub()
        val request = baseRequest.copy(method = HttpMethod.Patch)

        val fetched = Scraper(request).scrape()

        fetched.status { code }.shouldBe(201)
        fetched.responseBody.shouldBe("i'm a PATCH stub")
    }

    @Test
    @JsName("canfetchurlanduseHTTPverbHEAD")
    fun `can fetch url and use HTTP verb HEAD`() = runTest {
        wiremock.setupHeadStub()
        val request = baseRequest.copy(method = HttpMethod.Head)

        val fetched = Scraper(request).scrape()

        fetched.status { code }.shouldBe(201)
        /* Removed this check since HTTP HEAD responses should have their body ignored
        *  See https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/HEAD
        */
        //fetched.httpHeader("result").shouldBe("i'm a HEAD stub")
    }

    @Test
    @JsName("willthrowexceptionontimeout")
    fun `will throw exception on timeout`() = runTest {
        wiremock.setupStub(path = "/delayed", delay = 6000)
        try {
            Scraper(baseRequest.copy(url = "${wiremock.httpUrl}/delayed", timeout = 10)).scrape()
        }  catch (ex: Exception) {
            //JS wraps the actual exception in a CancellaionException
            val tEx = if (ex is CancellationException) ex.cause else ex
            //This expectation is weird since the timeout can be either of 3 exceptions which are all valid
            tEx.shouldBeInstanceOf<IOException>()
            val possibleErrors = listOf(
                SocketTimeoutException::class,
                ConnectTimeoutException::class,
                HttpRequestTimeoutException::class
            )
            possibleErrors.forOne {
                tEx.instanceOf(it)
            }
        }
    }
}
