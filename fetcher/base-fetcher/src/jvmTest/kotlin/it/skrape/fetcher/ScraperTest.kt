package it.skrape.fetcher

import Testcontainer
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import setupStub

private val wiremock = Testcontainer.wiremock

class ScraperTest {

    @Test
    @Disabled("need to mock HttpFetcher")
    fun `can scrape directly with default options`() = runTest {
        wiremock.setupStub(contentType = "test/type")
        // val result = Scraper(HttpFetcher).scrape()

        // expectThat(result.status { code }).isEqualTo(200)
        // expectThat(result.document.titleText).isEqualTo("i'm the title")
    }

    @Test
    @Disabled("need to mock HttpFetcher")
    fun `can scrape html via custom http request`() = runTest {
        wiremock.setupStub(path = "/example")
        // val result = Scraper(HttpFetcher, Request(url = "http://localhost:8080/example")).scrape()

        // expectThat(result.status { code }).isEqualTo(200)
        // expectThat(result.document.titleText).isEqualTo("i'm the title")
    }
}
