package it.skrape.selects

import it.skrape.WireMockSetup
import it.skrape.core.Scraper
import it.skrape.core.fetcher.HttpFetcher
import it.skrape.core.htmlDocument
import it.skrape.setupStub
import it.skrape.exceptions.ElementNotFoundException
import it.skrape.expect
import it.skrape.extract
import it.skrape.matchers.toBe
import it.skrape.matchers.toContain
import it.skrape.selects.html5.p
import it.skrape.skrape
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ResultExtractorsTest : WireMockSetup() {

    @Test
    fun `will throw custom exception if element could not be found via element function`() {

        Assertions.assertThrows(ElementNotFoundException::class.java) {
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
