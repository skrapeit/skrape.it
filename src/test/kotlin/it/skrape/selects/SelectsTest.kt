package it.skrape.selects

import it.skrape.core.Request
import it.skrape.core.WireMockSetup
import it.skrape.core.setupStub
import it.skrape.exceptions.ElementNotFoundException
import it.skrape.expect
import it.skrape.extract
import it.skrape.skrape
import org.assertj.core.api.SoftAssertions
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
                SoftAssertions().apply {
                    assertThat(el("p").text()).isEqualTo(expectedValue)
                    assertThat(element("p").text()).isEqualTo(expectedValue)
                    assertThat(elements("p").first().text()).isEqualTo(expectedValue)
                    assertThat(`$`("p").first().text()).isEqualTo(expectedValue)
                }.assertAll()

            }
        }
    }

}