package it.skrape.selects

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import it.skrape.core.htmlDocument
import it.skrape.selects.html5.p
import kotlin.test.Test
import kotlin.js.JsName

class ResultExtractorsTest {

    val expectedValue = "i'm a paragraph"

    val htmlSnippet = """
        <p>$expectedValue</p>
        <p>foo</p>
    """.trimIndent()

    @JsName("WillThrowCustomExceptionIfElementCouldNotBeFoundViaElementFunction")
	@Test
	fun `will throw custom exception if element could not be found via element function`() {

        shouldThrow<ElementNotFoundException> {
            htmlDocument(htmlSnippet) {
                findAll(".nonExistent")
            }
        }
    }

    @JsName("CanPickElementsViaSelectFunctions")
	@Test
	fun `can pick elements via select functions`() {

        htmlDocument(htmlSnippet) {
            p {
                findFirst {
                    text.shouldBe(expectedValue)
                }
            }
        }
    }
}
