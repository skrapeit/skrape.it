package it.skrape.selects

import it.skrape.core.htmlDocument
import it.skrape.selects.html5.p
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.isEqualTo

class ResultExtractorsTest {

    val expectedValue = "i'm a paragraph"

    @Language("HTML")
    val htmlSnippet = """
        <p>$expectedValue</p>
        <p>foo</p>
    """.trimIndent()

    @Test
    fun `will throw custom exception if element could not be found via element function`() {

        expectThrows<ElementNotFoundException> {
            htmlDocument(htmlSnippet) {
                findAll(".nonExistent")
            }
        }
    }

    @Test
    fun `can pick elements via select functions`() {

        htmlDocument(htmlSnippet) {
            p {
                findFirst {
                    expectThat(text).isEqualTo(expectedValue)
                }
            }
        }
    }
}
