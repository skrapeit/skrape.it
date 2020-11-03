package it.skrape.selects

import it.skrape.*
import it.skrape.core.Scraper
import it.skrape.core.fetcher.HttpFetcher
import it.skrape.core.htmlDocument
import it.skrape.exceptions.ElementNotFoundException
import it.skrape.matchers.toBe
import it.skrape.matchers.toContain
import it.skrape.selects.html5.p
import org.junit.jupiter.api.Test
import strikt.api.expectThrows

class ResultExtractorsTest : WireMockSetup() {

    @Test
    fun `will throw custom exception if element could not be found via element function`() {

        expectThrows<ElementNotFoundException> {
            Scraper(HttpFetcher).expect {
                htmlDocument {
                    findAll(".nonExistent")
                }
            }
        }
    }

    @Test
    fun `can pick elements via select functions`() {
        wireMockServer.setupStub()

        val expectedValue = "i'm a paragraph"

        skrape(HttpFetcher) {
            extract {
                htmlDocument {
                    p {
                        findFirst {
                            text toBe expectedValue
                        }
                    }
                }
            }
        }
    }

    @Test
    fun `can pick certain header select functions`() {
        wireMockServer.setupStub()

        skrape(HttpFetcher) {
            expect {
                httpHeader("Content-Type") toBe "text/html;charset=utf-8"
                httpHeader("Content-Type") toContain "html"
                httpHeader("notExisting") toBe null
            }
        }
    }
}
