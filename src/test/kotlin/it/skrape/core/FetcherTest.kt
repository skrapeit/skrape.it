package it.skrape.core

import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.net.SocketTimeoutException

internal class FetcherTest : WireMockSetup() {

    @Test
    fun `will fetch localhost 8080 with defaults if no params`() {
        // given
        wireMockServer.setupStub()

        // when
        val fetched = Fetcher(Scraper.Options()).fetch()

        // then
        assertThat(fetched.statusCode()).isEqualTo(200)
        assertThat(fetched.contentType()).isEqualTo("text/html; charset=UTF-8")
        assertThat(fetched.parse().title()).isEqualTo("i'm the title")
    }

    @Test
    fun `can fetch url and use HTTP verb GET by default`() {
        // given
        wireMockServer.setupStub(path = "/example")
        val params = Scraper.Options().apply {
            url = "https://localhost:8089/example"
        }

        // when
        val fetched = Fetcher(params).fetch()

        // then
        assertThat(fetched.statusCode()).isEqualTo(200)
        assertThat(fetched.contentType()).isEqualTo("text/html; charset=UTF-8")
        assertThat(fetched.parse().title()).isEqualTo("i'm the title")
    }

    @Test
    fun `will not throw exception on non existing url`() {
        // given
        val params = Scraper.Options().apply {
            url = "http://localhost:8080/not-existing"
        }

        // when
        val fetched = Fetcher(params).fetch()

        // then
        assertThat(fetched.statusCode()).isEqualTo(404)
    }

    @Test
    fun `will not follow redirects if configured`() {
        // given
        wireMockServer.setupRedirect()
        val params = Scraper.Options().apply {
            followRedirects = false
        }

        // when
        val fetched = Fetcher(params).fetch()

        // then
        assertThat(fetched.statusCode()).isEqualTo(302)
    }

    @Test
    fun `will follow redirect by default`() {
        // given
        wireMockServer.setupRedirect()

        // when
        val fetched = Fetcher(Scraper.Options()).fetch()

        // then
        assertThat(fetched.statusCode()).isEqualTo(404)
    }

    @Test
    fun `can fetch url and use HTTP verb POST`() {
        // given
        wireMockServer.setupPostStub()
        val params = Scraper.Options().apply {
            method = Scraper.HttpMethod.POST
        }

        // when
        val fetched = Fetcher(params).fetch()

        // then
        assertThat(fetched.statusCode()).isEqualTo(200)
        assertThat(fetched.contentType()).isEqualTo("application/json; charset=UTF-8")
        assertThat(fetched.body()).isEqualTo("""{"data":"some value"}""")
    }

    @Test
    fun `will throw exception on timeout`() {
        wireMockServer.setupStub(delay = 6000)

        assertThrows(SocketTimeoutException::class.java
        ) {
            Fetcher(Scraper.Options()).fetch()
        }
    }
}
