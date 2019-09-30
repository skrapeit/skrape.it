package it.skrape.selects

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import it.skrape.aValidResult
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
        val result = aValidResult("<html><div>divs inner text</div></html>")
        result.div {
            assertThat(text()).isEqualTo("divs inner text")
        }
    }

    @Test
    internal fun `can read div with selector from document`() {
        val result = aValidResult("<html><div class=\"existent\">divs inner text</div></html>")
        result.div(".existent") {
            assertThat(text()).isEqualTo("divs inner text")
        }
    }

    @Test
    internal fun `will throw custom exception if div could not be found via lambda`() {
        Assertions.assertThrows(DivNotFoundException::class.java) {
            aValidResult().div(".nonExistent") {}
        }
    }

    @Test
    internal fun `can read divs from document`() {
        val result = aValidResult("<html><div>first</div><div>second</div></html>")
        result.divs {
            assertThat(size).isEqualTo(2)
            assertThat(get(0).text()).isEqualTo("first")
            assertThat(get(1).text()).isEqualTo("second")
        }
    }

    @Test
    internal fun `can read divs with selector from document`() {
        val result = aValidResult("<html><div class=\"foo\">with class</div><div class=\"foo\">with class</div><div>without class</div></html>")
        result.divs(".foo") {
            assertThat(size).isEqualTo(2)
            forEach {
                assertThat(it.text()).isEqualTo("with class")
            }
        }

    }

    @Test
    internal fun `will throw custom exception if divs could not be found via lambda`() {
        Assertions.assertThrows(DivNotFoundException::class.java) {
            aValidResult().divs {}
        }
    }
}
