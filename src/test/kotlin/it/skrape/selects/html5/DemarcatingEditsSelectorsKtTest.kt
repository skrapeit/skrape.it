package it.skrape.selects.html5

import it.skrape.aStandardTag
import it.skrape.aValidDocument
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class DemarcatingEditsSelectorsKtTest {

    @Test
    fun `can parse del-tag`() {
        val selector = aValidDocument(aStandardTag("del")).del {
            findFirst {
                expectThat(text).isEqualTo("i'm a del")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("del")
    }

    @Test
    fun `can parse ins-tag`() {
        val selector = aValidDocument(aStandardTag("ins")).ins {
            findFirst {
                expectThat(text).isEqualTo("i'm a ins")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("ins")
    }
}