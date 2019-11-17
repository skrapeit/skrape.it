package it.skrape.core

import assertk.assertThat
import assertk.assertions.isEqualTo
import it.skrape.core.fetcher.Request
import org.junit.jupiter.api.Test

internal class ScraperTest : WireMockSetup() {

    @Test
    internal fun `can scrape directly with default options`() {
        wireMockServer.setupStub(contentType = "test/type")
        val result = Scraper().scrape()

        assertThat(result.statusCode).isEqualTo(200)
        assertThat(result.document.title()).isEqualTo("i'm the title")
    }

    @Test
    internal fun `can scrape html via custom http request`() {
        wireMockServer.setupStub(path = "/example")
        val result = Scraper(request = Request(url = "http://localhost:8080/example")).scrape()

        assertThat(result.statusCode).isEqualTo(200)
        assertThat(result.document.title()).isEqualTo("i'm the title")
    }
}
