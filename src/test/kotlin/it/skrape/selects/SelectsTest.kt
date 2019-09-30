package it.skrape.selects

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import it.skrape.core.Request
import it.skrape.core.WireMockSetup
import it.skrape.core.setupStub
import it.skrape.exceptions.DivNotFoundException
import it.skrape.exceptions.ElementNotFoundException
import it.skrape.expect
import it.skrape.extract
import it.skrape.matchers.toBe
import it.skrape.matchers.toContain
import it.skrape.skrape
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class SelectsTest : WireMockSetup() {

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
                el(".nonExistent")
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
                    assertThat(el("p").text()).isEqualTo(expectedValue)
                    assertThat(element("p").text()).isEqualTo(expectedValue)
                    assertThat(elements("p").first().text()).isEqualTo(expectedValue)
                    assertThat(`$`("p").first().text()).isEqualTo(expectedValue)
                }

            }
        }
    }

    @Test
    internal fun `can pick certain header select functions`() {
        wireMockServer.setupStub()

        skrape {
            expect {
                header("Content-Type") toBe "text/html; charset=UTF-8"
                header("Content-Type") toContain "html"
                header("notExisting") toBe null
            }
        }
    }

    @Test
    internal fun `can read div from document`() {
        expect("<html><div>divs inner text</div></html>") {
            div {
                assertThat(text()).isEqualTo("divs inner text")
            }
        }
    }

    @Test
    internal fun `can read div with selector from document`() {
        expect("<html><div class=\"existent\">divs inner text</div></html>") {
            div(".existent") {
                assertThat(text()).isEqualTo("divs inner text")
            }
        }
    }

    @Test
    internal fun `will throw custom exception if div could not be found via lambda`() {
        Assertions.assertThrows(DivNotFoundException::class.java) {
            expect("") {
                div(".nonExistent") {}
            }

        }
    }

}