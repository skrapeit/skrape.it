package it.skrape.fetcher

import Testcontainer
import com.gargoylesoftware.htmlunit.util.NameValuePair
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import setupCookiesStub
import setupRedirect
import setupStub
import strikt.api.expect
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.contains
import strikt.assertions.isEqualTo
import java.net.SocketTimeoutException
import java.util.*

private val wiremock = Testcontainer.wiremock

@Execution(ExecutionMode.SAME_THREAD)
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
    fun `will throw exception on HTTP verb POST`() {
        val request = baseRequest.copy(method = Method.POST)

        expectThrows<UnsupportedRequestOptionException> {
            BrowserFetcher.fetch(request)
        }
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
    fun `can handle uri scheme`() {
        val aValideHtml = "<html><h1>headline</h1></html>"
        val base64encoded = Base64.getEncoder().encodeToString(aValideHtml.toByteArray())
        val uriScheme = "data:text/html;charset=utf-8;base64,$base64encoded"

        val fetched = BrowserFetcher.fetch(Request(url = uriScheme))

        expectThat(fetched.responseBody).isEqualTo("""
            |<?xml version="1.0" encoding="UTF-8"?>
            |<html>
            |  <head/>
            |  <body>
            |    <h1>
            |      headline
            |    </h1>
            |  </body>
            |</html>
            |""".trimMargin().replace("\n", "\r\n")
        )

    }

    @Test
    fun `will not throw if response body is not html`() {
        wiremock.setupStub(fileName = "data.json", contentType = "application/json; charset=UTF-8")

        val response = BrowserFetcher.fetch(baseRequest)
        expectThat(response.responseBody).isEqualTo("{\"data\":\"some value\"}")
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