package it.skrape.core

import it.skrape.WireMockSetup
import it.skrape.core.fetcher.Request
import it.skrape.setupStub
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class ScraperTest : WireMockSetup() {

    @Test
    internal fun `can scrape directly with default options`() {
        wireMockServer.setupStub(contentType = "test/type")
        val result = Scraper().scrape()

        expectThat(result.statusCode).isEqualTo(200)
        expectThat(result.document.title()).isEqualTo("i'm the title")
    }

    @Test
    internal fun `can scrape html via custom http request`() {
        wireMockServer.setupStub(path = "/example")
        val result = Scraper(request = Request(url = "http://localhost:8080/example")).scrape()

        expectThat(result.statusCode).isEqualTo(200)
        expectThat(result.document.title()).isEqualTo("i'm the title")
    }
}
