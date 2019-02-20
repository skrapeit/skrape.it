package it.skrape.core

import org.assertj.core.api.KotlinAssertions.assertThat
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
        val result = Scraper(Request(url = "http://localhost:8080/example")).scrape()

        assertThat(result.statusCode).isEqualTo(200)
        assertThat(result.document.title()).isEqualTo("i'm the title")
    }
}
