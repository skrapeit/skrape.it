package it.skrape.core.fetcher

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.hasClass
import assertk.assertions.isEqualTo
import com.gargoylesoftware.htmlunit.util.NameValuePair
import it.skrape.core.Method
import it.skrape.core.Request
import it.skrape.core.WireMockSetup
import it.skrape.core.setupRedirect
import it.skrape.core.setupStub
import it.skrape.exceptions.UnsupportedRequestOptionException
import org.junit.jupiter.api.Test
import java.net.SocketTimeoutException

internal class BrowserFetcherTest : WireMockSetup() {

    @Test
    internal fun `will fetch localhost 8080 with defaults if no params`() {
        // given
        wireMockServer.setupStub()

        // when
        val fetched = BrowserFetcher(Request()).fetch()

        // then
        assertThat(fetched.statusCode).isEqualTo(200)
        assertThat(fetched.document.title()).isEqualTo("i'm the title")
    }

    @Test
    internal fun `can fetch url and use HTTP verb GET by default`() {
        // given
        wireMockServer.setupStub(path = "/example")
        val options = Request().apply {
            url = "https://localhost:8089/example"
        }

        // when
        val fetched = BrowserFetcher(options).fetch()

        // then
        assertAll {
            assertThat(fetched.statusCode).isEqualTo(200)
            assertThat(fetched.document.title()).isEqualTo("i'm the title")
        }
    }

    @Test
    internal fun `will not throw exception on non existing url`() {
        // given
        val options = Request().apply {
            url = "http://localhost:8080/not-existing"
        }

        // when
        val fetched = BrowserFetcher(options).fetch()

        // then
        assertThat(fetched.statusCode).isEqualTo(404)
    }

    @Test
    internal fun `will not follow redirects if configured`() {
        // given
        wireMockServer.setupRedirect()
        // when
        val result = BrowserFetcher(Request(followRedirects = false)).fetch()
        // then
        assertThat(result.statusCode).isEqualTo(302)
    }

    @Test
    internal fun `will follow redirect by default`() {
        // given
        wireMockServer.setupRedirect()

        // when
        val fetched = BrowserFetcher(Request()).fetch()

        // then
        assertThat(fetched.statusCode).isEqualTo(404)
    }

    @Test
    internal fun `will throw exception on HTTP verb POST`() {
        // when
        val options = Request().apply {
            method = Method.POST
        }
        // then
        assertThat { BrowserFetcher(options).fetch() }.thrownError {
            hasClass(UnsupportedRequestOptionException::class)
        }
    }

    @Test
    internal fun `can parse js rendered elements`() {
        // given
        wireMockServer.setupStub(fileName = "js.html")
        // when
        val fetched = BrowserFetcher(Request()).fetch()

        // then
        assertThat(fetched.document.select("div.dynamic").text()).isEqualTo("I have been dynamically added via Javascript")
    }

    @Test
    internal fun `can parse js rendered elements from https page`() {
        // given
        wireMockServer.setupStub(fileName = "js.html")
        // when
        val fetched = BrowserFetcher(Request(url = "https://localhost:8089")).fetch()

        // then
        assertThat(fetched.document.select("div.dynamic").text()).isEqualTo("I have been dynamically added via Javascript")
    }

    @Test
    internal fun `can parse es6 rendered elements from https page`() {
        // given
        wireMockServer.setupStub(fileName = "es6.html")
        // when
        val fetched = BrowserFetcher(Request()).fetch()
        val paragraphs = fetched.document.select("div.dynamic")

        // then
        paragraphs.forEach {
            assertThat(it.text()).isEqualTo("dynamically added")
        }
    }

    @Test
    internal fun `will throw exception on timeout`() {
        // given
        wireMockServer.setupStub(delay = 6000)
        // when
        assertThat { BrowserFetcher(Request()).fetch() }.thrownError {
            // then
            hasClass(SocketTimeoutException::class)
        }
    }

    @Test
    internal fun `will extract headers to map`() {

        val sut = listOf(
                NameValuePair("first-name", "first-value"),
                NameValuePair("second-name", "second-value")
        )
        val result = sut.toMap()
        assertThat(result).containsOnly("first-name" to "first-value", "second-name" to "second-value")
    }
}