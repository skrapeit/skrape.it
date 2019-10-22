package it.skrape.selects.html5

import it.skrape.aStandardTag
import it.skrape.aValidDocument
import it.skrape.selects.findFirst
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class DemarcatingEditsSelectorsKtTest {

    @Test
    fun `can parse del-tag`() {
        val selector = aValidDocument(aStandardTag("del")).del {
            findFirst {
                expectThat(text()).isEqualTo("i'm a del")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("del")
    }

    @Test
    fun `can parse ins-tag`() {
        val selector = aValidDocument(aStandardTag("ins")).ins {
            findFirst {
                expectThat(text()).isEqualTo("i'm a ins")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("ins")
    }
}