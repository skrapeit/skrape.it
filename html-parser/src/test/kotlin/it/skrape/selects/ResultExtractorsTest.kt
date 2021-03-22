package it.skrape.selects

import it.skrape.core.htmlDocument
import it.skrape.selects.html5.p
import it.skrape.wireMockExampleHtml
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.isEqualTo

class ResultExtractorsTest {

    @Test
    fun `will throw custom exception if element could not be found via element function`() {

        expectThrows<ElementNotFoundException> {
            htmlDocument(wireMockExampleHtml) {
                findAll(".nonExistent")
            }
        }
    }

    @Test
    fun `can pick elements via select functions`() {

        val expectedValue = "i'm a paragraph"

        htmlDocument(wireMockExampleHtml) {
            p {
                findFirst {
                    expectThat(text).isEqualTo(expectedValue)
                }
            }
        }
    }
}
