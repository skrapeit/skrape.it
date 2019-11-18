package it.skrape.selects.html5

import it.skrape.aStandardTag
import it.skrape.aValidDocument
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


internal class InteractiveSelectorsKtTest {

    @Test
    fun `can parse details-tag`() {
        val selector = aValidDocument(aStandardTag("details")).details {
            findFirst {
                expectThat(text).isEqualTo("i'm a details")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("details")
    }

    @Test
    fun `can parse dialog-tag`() {
        val selector = aValidDocument(aStandardTag("dialog")).dialog {
            findFirst {
                expectThat(text).isEqualTo("i'm a dialog")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("dialog")
    }

    @Test
    fun `can parse menu-tag`() {
        val selector = aValidDocument(aStandardTag("menu")).menu {
            findFirst {
                expectThat(text).isEqualTo("i'm a menu")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("menu")
    }

    @Test
    fun `can parse menuitem-tag`() {
        val selector = aValidDocument(aStandardTag("menuitem")).menuitem {
            findFirst {
                expectThat(text).isEqualTo("i'm a menuitem")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("menuitem")
    }

    @Test
    fun `can parse summary-tag`() {
        val selector = aValidDocument(aStandardTag("summary")).summary {
            findFirst {
                expectThat(text).isEqualTo("i'm a summary")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("summary")
    }
}