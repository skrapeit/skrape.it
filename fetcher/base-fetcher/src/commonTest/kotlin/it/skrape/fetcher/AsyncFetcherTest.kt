package it.skrape.fetcher

import io.ktor.client.request.*
import kotlinx.coroutines.test.runTest
import setupCookiesStub
import setupDeleteStub
import setupHeadStub
import setupPatchStub
import setupPostStub
import setupPutStub
import setupRedirect
import setupStub
import kotlin.js.JsName
import kotlin.test.BeforeTest
import kotlin.test.Test


//@DisabledOnOs(OS.WINDOWS)
class AsyncFetcherTest {

    lateinit var wiremock: Testcontainer.Wiremock
    @BeforeTest
    private suspend fun getWiremock(): Testcontainer.Wiremock {
        if(!this::wiremock.isInitialized) wiremock = Testcontainer.getWiremock()
        return wiremock
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

        expectThat(fetched.status { code }).isEqualTo(200)
        expectThat(fetched.contentType).isEqualTo("text/html;charset=UTF-8")
        expectThat(fetched.responseBody).contains("i'm the title")

    }

    @Test
	@JsName("willnotthrowexceptiononnonexistingurl")
	fun `will not throw exception on non existing url`() {
        val request = baseRequest.copy(url = "${wiremock.httpUrl}/not-existing", timeout = 9999999)

        val fetched = Scraper(request).scrape()

        expectThat(fetched.status { code }).isEqualTo(404)
    }

    @Test
	@JsName("willnotfollowredirectsifconfigured")
	fun `will not follow redirects if configured`() = runTest {
        wiremock.setupRedirect()
        val request = baseRequest.copy(followRedirects = false)

        runBlocking {
            val fetched = Scraper(request).scrape()

            expectThat(fetched.status { code }).isEqualTo(302)
        }

    }

    @Test
	@JsName("willfollowredirectbydefault")
	fun `will follow redirect by default`() = runTest {
        wiremock.setupRedirect()

        runBlocking {
            val fetched = Scraper(baseRequest).scrape()

            expectThat(fetched.status { code }).isEqualTo(404)
        }
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
	@JsName("canfetchurlanduseHTTPverbPOST")
	fun `can fetch url and use HTTP verb POST`() = runTest {
        wiremock.setupPostStub()
        val request = baseRequest.copy(method = HttpMethod.Post)

        val fetched = Scraper(request).scrape()

        expectThat(fetched.status { code }).isEqualTo(200)
        expectThat(fetched.contentType).isEqualTo("application/json;charset=UTF-8")
        expectThat(fetched.responseBody).isEqualTo("""{"data":"some value"}""")
    }

    @Test
	@JsName("canPOSTbody")
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
	@JsName("canfetchurlanduseHTTPverbPUT")
	fun `can fetch url and use HTTP verb PUT`() = runTest {
        wiremock.setupPutStub()
        val request = baseRequest.copy(method = Method.PUT)

        val fetched = runBlocking { AsyncFetcher.fetch(request) }

        expectThat(fetched.status { code }).isEqualTo(201)
        expectThat(fetched.responseBody).isEqualTo("i'm a PUT stub")
    }

    @Test
	@JsName("canfetchurlanduseHTTPverbDELETE")
	fun `can fetch url and use HTTP verb DELETE`() = runTest {
        wiremock.setupDeleteStub()
        val request = baseRequest.copy(method = Method.DELETE)

        val fetched = runBlocking { AsyncFetcher.fetch(request) }

        expectThat(fetched.status { code }).isEqualTo(201)
        expectThat(fetched.responseBody).isEqualTo("i'm a DELETE stub")
    }

    @Test
	@JsName("canfetchurlanduseHTTPverbPATCH")
	fun `can fetch url and use HTTP verb PATCH`() = runTest {
        wiremock.setupPatchStub()
        val request = baseRequest.copy(method = Method.PATCH)

        val fetched = runBlocking { AsyncFetcher.fetch(request) }

        expectThat(fetched.status { code }).isEqualTo(201)
        expectThat(fetched.responseBody).isEqualTo("i'm a PATCH stub")
    }

    @Test
	@JsName("canfetchurlanduseHTTPverbHEAD")
	fun `can fetch url and use HTTP verb HEAD`() = runTest {
        wiremock.setupHeadStub()
        val request = baseRequest.copy(method = Method.HEAD)

        val fetched = runBlocking { AsyncFetcher.fetch(request) }

        expectThat(fetched.status { code }).isEqualTo(201)
        expectThat(fetched.httpHeader("result")).isEqualTo("i'm a HEAD stub")
    }

    @Test
	@JsName("willthrowexceptionontimeout")
	fun `will throw exception on timeout`() = runTest {
        wiremock.setupStub(path = "/delayed", delay = 6000)

        expectThrows<SocketTimeoutException> {
            AsyncFetcher.fetch(baseRequest.copy(url = "${wiremock.httpUrl}/delayed"))
        }
    }
}
