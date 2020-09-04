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
            toCssSelector
        }

        expectThat(selector).isEqualTo("details")
    }

    @Test
    fun `can parse dialog-tag`() {
        val selector = aValidDocument(aStandardTag("dialog")).dialog {
            findFirst {
                expectThat(text).isEqualTo("i'm a dialog")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("dialog")
    }

    @Test
    fun `can parse menu-tag`() {
        val selector = aValidDocument(aStandardTag("menu")).menu {
            findFirst {
                expectThat(text).isEqualTo("i'm a menu")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("menu")
    }

    @Test
    fun `can parse menuitem-tag`() {
        val selector = aValidDocument(aStandardTag("menuitem")).menuitem {
            findFirst {
                expectThat(text).isEqualTo("i'm a menuitem")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("menuitem")
    }

    @Test
    fun `can parse summary-tag`() {
        val selector = aValidDocument(aStandardTag("summary")).summary {
            findFirst {
                expectThat(text).isEqualTo("i'm a summary")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("summary")
    }
}