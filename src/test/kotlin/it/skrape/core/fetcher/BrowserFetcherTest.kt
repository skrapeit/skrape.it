package it.skrape.core.fetcher

import com.gargoylesoftware.htmlunit.util.NameValuePair
import it.skrape.WireMockSetup
import it.skrape.setupRedirect
import it.skrape.setupStub
import it.skrape.exceptions.UnsupportedRequestOptionException
import it.skrape.selects.html5.h1
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import strikt.api.expect
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.net.SocketTimeoutException
import java.util.*

internal class BrowserFetcherTest : WireMockSetup() {

    @Test
    internal fun `will fetch localhost 8080 with defaults if no params`() {
        wireMockServer.setupStub()

        val fetched = BrowserFetcher(Request()).fetch()

        expect {
            that(fetched.statusCode).isEqualTo(200)
            that(fetched.document.title()).isEqualTo("i'm the title")
        }
    }

    @Test
    internal fun `can fetch url and use HTTP verb GET by default`() {
        wireMockServer.setupStub(path = "/example")

        val options = Request().apply {
            url = "https://localhost:8089/example"
        }

        val fetched = BrowserFetcher(options).fetch()

        expect {
            that(fetched.statusCode).isEqualTo(200)
            that(fetched.document.title()).isEqualTo("i'm the title")
        }
    }

    @Test
    internal fun `will not throw exception on non existing url`() {
        val options = Request().apply {
            url = "http://localhost:8080/not-existing"
        }

        val fetched = BrowserFetcher(options).fetch()

        expectThat(fetched.statusCode).isEqualTo(404)
    }

    @Test
    internal fun `will not follow redirects if configured`() {
        wireMockServer.setupRedirect()

        val result = BrowserFetcher(Request(followRedirects = false)).fetch()

        expectThat(result.statusCode).isEqualTo(302)
    }

    @Test
    internal fun `will follow redirect by default`() {
        wireMockServer.setupRedirect()

        val fetched = BrowserFetcher(Request()).fetch()

        expectThat(fetched.statusCode).isEqualTo(404)
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

        val fetched = BrowserFetcher(Request(url = "https://localhost:8089")).fetch()

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
        val paragraphs = fetched.document.findAll("div.dynamic")

        paragraphs.forEach {
            expectThat(it.text).isEqualTo("dynamically added")
        }
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

        val sut = listOf(
                NameValuePair("first-name", "first-value"),
                NameValuePair("second-name", "second-value")
        )
        val result = sut.toMap()
        expectThat(result).isEqualTo(mapOf("first-name" to "first-value", "second-name" to "second-value"))
    }

    @Test
    internal fun `will create raw cookie syntax representation of map`() {

        val sut = mapOf(
                "first-name" to "first-value",
                "second-name" to "second-value"
        )
        val result = sut.asRawCookieSyntax()
        expectThat(result).isEqualTo("first-name=first-value;second-name=second-value;")
    }


}