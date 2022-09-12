package it.skrape.fetcher

import Testcontainer
import com.gargoylesoftware.htmlunit.util.NameValuePair
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledOnOs
import org.junit.jupiter.api.condition.OS
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import setupCookiesStub
import setupPostStub
import setupRedirect
import setupStub
import strikt.api.expect
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.contains
import strikt.assertions.isEqualTo
import java.net.SocketTimeoutException

private val wiremock = Testcontainer.wiremock
private val httpBin = Testcontainer.httpBin

@Execution(ExecutionMode.SAME_THREAD)
@DisabledOnOs(OS.WINDOWS)
class BrowserFetcherTest {

    private val baseRequest by lazy { Request(url = wiremock.httpUrl) }

    @Test
    fun `can render from string`() {
        val renderedHtml = BrowserFetcher.render(getMarkupFromFile("es6.html"))

        expectThat(renderedHtml).contains("""
             |    <p>
             |      <span>
             |        dynamically added
             |      </span>
             |    </p>
        """.trimMargin().replace("\n", "\r\n"))
    }

    @Test
    fun `will fetch localhost 8080 with defaults if no params`() {
        wiremock.setupStub()

        val fetched = BrowserFetcher.fetch(baseRequest)

        expect {
            that(fetched.status { code }).isEqualTo(200)
            that(fetched.responseBody).contains("i'm the title")
        }
    }

    @Test
    fun `can fetch url and use HTTP verb GET by default`() {
        wiremock.setupStub(path = "/example")

        val request = baseRequest.copy(
            url = "${wiremock.httpsUrl}/example",
            sslRelaxed = true
        )

        val fetched = BrowserFetcher.fetch(request)

        expect {
            that(fetched.status { code }).isEqualTo(200)
            that(fetched.responseBody).contains("i'm the title")
        }
    }

    @Test
    fun `will not throw exception on non existing url`() {
        val request = baseRequest.copy(url = "${wiremock.httpUrl}/not-existing")

        val fetched = BrowserFetcher.fetch(request)

        expectThat(fetched.status { code }).isEqualTo(404)
    }

    @Test
    fun `will not follow redirects if configured`() {
        wiremock.setupRedirect()
        val request = baseRequest.copy(followRedirects = false)

        val result = BrowserFetcher.fetch(request)

        expectThat(result.status { code }).isEqualTo(302)
    }

    @Test
    fun `will follow redirect by default`() {
        wiremock.setupRedirect()

        val fetched = BrowserFetcher.fetch(baseRequest)

        expectThat(fetched.status { code }).isEqualTo(404)
    }

    @Test
    fun `can fetch cookies`() {
        wiremock.setupCookiesStub(path = "/cookies")

        val request = baseRequest.copy(
            url = "${wiremock.httpsUrl}/cookies",
            sslRelaxed = true
        )
        val fetched = BrowserFetcher.fetch(request)

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
    fun `can handle HTTP verb POST`() {
        wiremock.setupPostStub()
        val request = baseRequest.copy(method = Method.POST)

        val fetched = BrowserFetcher.fetch(request)

        expectThat(fetched.responseBody).contains("""{"data":"some value"}""")
        expectThat(fetched.contentType).isEqualTo("application/json")
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

        val fetched = BrowserFetcher.fetch(request)
        expectThat(fetched.responseBody).contains(""""data": "{\"foo\":\"bar\"}"""")
    }

    @Test
    fun `can parse js rendered elements`() {
        wiremock.setupStub(fileName = "js.html")

        val fetched = BrowserFetcher.fetch(baseRequest)

        expectThat(fetched.responseBody).contains("I have been dynamically added via Javascript")
    }

    @Test
    fun `can parse js rendered elements from https page`() {
        wiremock.setupStub(fileName = "js.html")
        val request = baseRequest.copy(
            url = wiremock.httpsUrl,
            sslRelaxed = true
        )

        val fetched = BrowserFetcher.fetch(request)

        expectThat(fetched.responseBody).contains("I have been dynamically added via Javascript")
    }

    @Test
    fun `can parse es6 rendered elements from https page`() {
        wiremock.setupStub(fileName = "es6.html")

        val fetched = BrowserFetcher.fetch(baseRequest)
        expectThat(fetched.responseBody).contains("dynamically added")
    }

    @Test
    fun `will throw exception on timeout`() {
        wiremock.setupStub(delay = 6000)

        expectThrows<SocketTimeoutException> {
            BrowserFetcher.fetch(baseRequest)
        }
    }

    @Test
    fun `will extract headers to map`() {

        val htmlUnitHeaders = listOf(
            NameValuePair("first-name", "first-value"),
            NameValuePair("second-name", "second-value")
        )
        val result = htmlUnitHeaders.toMap()
        expectThat(result).isEqualTo(mapOf("first-name" to "first-value", "second-name" to "second-value"))
    }

    @Test
    fun `will create raw cookie syntax representation of map`() {

        val cookiesAsMap = mapOf(
            "first-name" to "first-value",
            "second-name" to "second-value"
        )
        val result = cookiesAsMap.asRawCookieSyntax()
        expectThat(result).isEqualTo("first-name=first-value;second-name=second-value;")
    }

    private fun getMarkupFromFile(file: String) = javaClass.getResource("/__files/$file").readText()
}