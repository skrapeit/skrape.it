package it.skrape.core

import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Test

internal class ScraperTest : WireMockSetup() {

    @Test
    fun `can parse html from file system`() {
        val document = Scraper().read("src/test/resources/__files/example.html")
        assertThat(document.title()).isEqualTo("i'm the title")
    }

    @Test
    fun `can scrape directly with default options`() {
        wireMockServer.setupStub(contentType = "test/type")
        val result = Scraper().fetch()

        assertThat(result.response.statusCode()).isEqualTo(200)
        assertThat(result.document.title()).isEqualTo("i'm the title")
    }

    @Test
    fun `can scrape html via custom http request`() {
        wireMockServer.setupStub(path = "/example")
        val result = Scraper().options.apply {
            url = "http://localhost:8080/example"
        }.fetch()

        assertThat(result.response.statusCode()).isEqualTo(200)
        assertThat(result.document.title()).isEqualTo("i'm the title")
    }

    @Test
    fun `can parse HTML`() {
        val htmlAsString = """
            <html>
                <head>
                    <title>some title</title>
                </head>
                <body>
                    <p>some text</p>
                </body>
            </html>
        """.trimIndent()

        val result = Scraper().parse(htmlAsString)

        assertThat(result.title()).isEqualTo("some title")
        assertThat(result.selectFirst("p").text()).isEqualTo("some text")
    }
}
