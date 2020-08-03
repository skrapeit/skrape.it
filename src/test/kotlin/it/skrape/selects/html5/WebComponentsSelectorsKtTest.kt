package it.skrape.selects.html5

import it.skrape.aStandardTag
import it.skrape.aValidDocument
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class WebComponentsSelectorsKtTest {

    @Test
    fun `can parse content-tag`() {
        val selector = aValidDocument(aStandardTag("content")).content {
            findFirst {
                expectThat(text).isEqualTo("i'm a content")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("content")
    }

    @Test
    fun `can parse shadow-tag`() {
        val selector = aValidDocument(aStandardTag("shadow")).shadow {
            findFirst {
                expectThat(text).isEqualTo("i'm a shadow")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("shadow")
    }

    @Test
    fun `can parse slot-tag`() {
        val selector = aValidDocument(aStandardTag("slot")).slot {
            findFirst {
                expectThat(text).isEqualTo("i'm a slot")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("slot")
    }

    @Test
    fun `can parse template-tag`() {
        val selector = aValidDocument(aStandardTag("template")).template {
            findFirst {
                expectThat(text).isEqualTo("i'm a template")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("template")
    }
}