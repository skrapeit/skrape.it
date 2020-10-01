package it.skrape.core

import it.skrape.WireMockSetup
import it.skrape.core.fetcher.HttpFetcher
import it.skrape.core.fetcher.Request
import it.skrape.setupStub
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class ScraperTest : WireMockSetup() {

    @Test
    fun `can scrape directly with default options`() {
        wireMockServer.setupStub(contentType = "test/type")
        val result = Scraper(HttpFetcher).scrape()

        expectThat(result.status { code }).isEqualTo(200)
        expectThat(result.document.titleText).isEqualTo("i'm the title")
    }

    @Test
    fun `can scrape html via custom http request`() {
        wireMockServer.setupStub(path = "/example")
        val result = Scraper(HttpFetcher, Request(url = "http://localhost:8080/example")).scrape()

        expectThat(result.status { code }).isEqualTo(200)
        expectThat(result.document.titleText).isEqualTo("i'm the title")
    }
}
