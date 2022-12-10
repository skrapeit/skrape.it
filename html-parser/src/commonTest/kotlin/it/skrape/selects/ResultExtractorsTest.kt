package it.skrape.selects

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import it.skrape.core.htmlDocument
import it.skrape.selects.html5.p

class ResultExtractorsTest: FunSpec({

    val expectedValue = "i'm a paragraph"

    val htmlSnippet = """
        <p>$expectedValue</p>
        <p>foo</p>
    """.trimIndent()

    test("will throw custom exception if element could not be found via element function") {

        shouldThrow<ElementNotFoundException> {
            htmlDocument(htmlSnippet) {
                findAll(".nonExistent")
            }
        }
    }

    test("can pick elements via select functions") {

        htmlDocument(htmlSnippet) {
            p {
                findFirst {
                    text.shouldBe(expectedValue)
                }
            }
        }
    }
})
