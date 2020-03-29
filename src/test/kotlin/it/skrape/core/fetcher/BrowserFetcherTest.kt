package it.skrape.core.fetcher

import com.gargoylesoftware.htmlunit.util.NameValuePair
import it.skrape.WireMockSetup
import it.skrape.core.document
import it.skrape.core.htmlDocument
import it.skrape.setupRedirect
import it.skrape.setupStub
import it.skrape.exceptions.UnsupportedRequestOptionException
import it.skrape.selects.eachText
import it.skrape.selects.html5.h1
import it.skrape.selects.html5.p
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import strikt.api.expect
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.isEqualTo
import java.net.SocketTimeoutException
import java.util.*

internal class BrowserFetcherTest : WireMockSetup() {

    @Test
    internal fun `will fetch localhost 8080 with defaults if no params`() {
        wireMockServer.setupStub()

        val fetched = BrowserFetcher(Request()).fetch()

        expect {
            that(fetched.status { code }).isEqualTo(200)
            that(fetched.document.titleText).isEqualTo("i'm the title")
        }
    }

    @Test
    internal fun `can fetch url and use HTTP verb GET by default`() {
        wireMockServer.setupStub(path = "/example")

        val request = Request(
                url = "https://localhost:8089/example",
                sslRelaxed = true
        )

        val fetched = BrowserFetcher(request).fetch()

        expect {
            that(fetched.status { code }).isEqualTo(200)
            that(fetched.document.titleText).isEqualTo("i'm the title")
        }
    }

    @Test
    internal fun `will not throw exception on non existing url`() {
        val request = Request(url = "http://localhost:8080/not-existing")

        val fetched = BrowserFetcher(request).fetch()

        expectThat(fetched.status { code }).isEqualTo(404)
    }

    @Test
    internal fun `will not follow redirects if configured`() {
        wireMockServer.setupRedirect()
        val request = Request(followRedirects = false)

        val result = BrowserFetcher(request).fetch()

        expectThat(result.status { code }).isEqualTo(302)
    }

    @Test
    internal fun `will follow redirect by default`() {
        wireMockServer.setupRedirect()

        val fetched = BrowserFetcher(Request()).fetch()

        expectThat(fetched.status { code }).isEqualTo(404)
    }

    @Test
    internal fun `will throw exception on HTTP verb POST`() {
        val options = Request().apply {
            method = Method.POST
        }
        assertThrows(UnsupportedRequestOptionException::class.java) {
            BrowserFetcher(options).fetch()
        }
    }

    @Test
    internal fun `can parse js rendered elements`() {
        wireMockServer.setupStub(fileName = "js.html")

        val fetched = BrowserFetcher(Request()).fetch()

        fetched.document.selection("div.dynamic") {
            findFirst {
                expectThat(text).isEqualTo("I have been dynamically added via Javascript")
            }
        }
    }

    @Test
    internal fun `can parse js rendered elements from https page`() {
        wireMockServer.setupStub(fileName = "js.html")
        val request = Request(
                url = "https://localhost:8089",
                sslRelaxed = true
        )

        val fetched = BrowserFetcher(request).fetch()

        fetched.document.selection("div.dynamic") {
            findFirst {
                expectThat(text).isEqualTo("I have been dynamically added via Javascript")
            }
        }
    }

    @Test
    internal fun `can parse es6 rendered elements from https page`() {
        wireMockServer.setupStub(fileName = "es6.html")

        val fetched = BrowserFetcher(Request()).fetch()
        val paragraphsText = fetched.document.findAll("p").eachText
        val paragraphsText2 = fetched.htmlDocument { p { findAll { eachText }} }

        expectThat(paragraphsText).contains("dynamically added")
        expectThat(paragraphsText2).contains("dynamically added")
    }

    @Test
    internal fun `can handle uri scheme`() {
        val aValideHtml = "<html><h1>headline</h1></html>"
        val base64encoded = Base64.getEncoder().encodeToString(aValideHtml.toByteArray())
        val uriScheme = "data:text/html;charset=utf-8;base64,$base64encoded"

        val fetched = BrowserFetcher(Request(url = uriScheme)).fetch()
        val headline = fetched.document.h1 { findFirst { text } }

        expectThat(headline).isEqualTo("headline")

    }

    @Test
    internal fun `will not throw if response body is not html`() {
        wireMockServer.setupStub(fileName = "data.json", contentType = "application/json; charset=UTF-8")

        val response = BrowserFetcher(Request()).fetch()
        expectThat(response.responseBody).isEqualTo("{\"data\":\"some value\"}")
    }

    @Test
    internal fun `will throw exception on timeout`() {
        wireMockServer.setupStub(delay = 6000)

        assertThrows(SocketTimeoutException::class.java) {
            BrowserFetcher(Request()).fetch()
        }
    }

    @Test
    internal fun `will extract headers to map`() {

        val htmlUnitHeaders = listOf(
                NameValuePair("first-name", "first-value"),
                NameValuePair("second-name", "second-value")
        )
        val result = htmlUnitHeaders.toMap()
        expectThat(result).isEqualTo(mapOf("first-name" to "first-value", "second-name" to "second-value"))
    }

    @Test
    internal fun `will create raw cookie syntax representation of map`() {

        val cookiesAsMap = mapOf(
                "first-name" to "first-value",
                "second-name" to "second-value"
        )
        val result = cookiesAsMap.asRawCookieSyntax()
        expectThat(result).isEqualTo("first-name=first-value;second-name=second-value;")
    }


}