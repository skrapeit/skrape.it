package it.skrape.selects

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.expect
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

        expect {
            htmlDocument(htmlSnippet) {
                findAll(".nonExistent")
            }
        }.toThrow<ElementNotFoundException>()
    }

    @JsName("CanPickElementsViaSelectFunctions")
	@Test
	fun `can pick elements via select functions`() {

        htmlDocument(htmlSnippet) {
            p {
                findFirst {
                    expect(text).toEqual(expectedValue)
                }
            }
        }
    }
}
