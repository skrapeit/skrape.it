package it.skrape.selects

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import it.skrape.core.Request
import it.skrape.core.WireMockSetup
import it.skrape.core.setupStub
import it.skrape.exceptions.*
import it.skrape.expect
import it.skrape.extract
import it.skrape.matchers.toBe
import it.skrape.matchers.toContain
import it.skrape.skrape
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class ResultExtractorsTest : WireMockSetup() {

    @Test
    internal fun `will throw custom exception if element could not be found via element function`() {

        Assertions.assertThrows(ElementNotFoundException::class.java) {
            Request().expect {
                element(".nonExistent")
            }
        }
    }

    @Test
    internal fun `will throw custom exception if element could not be found via el function`() {

        Assertions.assertThrows(ElementNotFoundException::class.java) {
            Request().expect {
                element(".nonExistent")
            }
        }
    }

    @Test
    internal fun `can pick elements via select functions`() {
        wireMockServer.setupStub()

        val expectedValue = "i'm a paragraph"

        skrape {
            extract {
                assertAll {
                    assertThat(element("p").text()).isEqualTo(expectedValue)
                    assertThat(element("p").text()).isEqualTo(expectedValue)
                    assertThat(elements("p").first().text()).isEqualTo(expectedValue)
                    assertThat(elements("p").first().text()).isEqualTo(expectedValue)
                }

            }
        }
    }

    @Test
    internal fun `can pick certain header select functions`() {
        wireMockServer.setupStub()

        skrape {
            expect {
                responseHeader("Content-Type") toBe "text/html;charset=utf-8"
                responseHeader("Content-Type") toContain "html"
                responseHeader("notExisting") toBe null
            }
        }
    }
}
