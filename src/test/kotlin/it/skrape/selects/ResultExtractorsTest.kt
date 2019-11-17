package it.skrape.selects

import it.skrape.core.fetcher.Request
import it.skrape.core.WireMockSetup
import it.skrape.core.setupStub
import it.skrape.exceptions.ElementNotFoundException
import it.skrape.expect
import it.skrape.extract
import it.skrape.matchers.toBe
import it.skrape.matchers.toContain
import it.skrape.selects.html5.p
import it.skrape.skrape
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class ResultExtractorsTest : WireMockSetup() {

    @Test
    internal fun `will throw custom exception if element could not be found via element function`() {

        Assertions.assertThrows(ElementNotFoundException::class.java) {
            Request().expect {
                htmlDocument {
                    findAll(".nonExistent")
                }
            }
        }
    }

    @Test
    internal fun `can pick elements via select functions`() {
        wireMockServer.setupStub()

        val expectedValue = "i'm a paragraph"

        skrape {
            extract {
                htmlDocument {
                    p {
                        findFirst {
                            text() toBe expectedValue
                        }
                    }
                }
            }
        }
    }

    @Test
    internal fun `can pick certain header select functions`() {
        wireMockServer.setupStub()

        skrape {
            expect {
                httpHeader("Content-Type") toBe "text/html;charset=utf-8"
                httpHeader("Content-Type") toContain "html"
                httpHeader("notExisting") toBe null
            }
        }
    }
}
