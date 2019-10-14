package it.skrape.selects.html5

import it.skrape.aStandardTag
import it.skrape.aValidDocument
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


internal class InteractiveElementPickersKtTest {

    @Test
    fun `can parse details-tag`() {
        val result = aValidDocument(aStandardTag("details"))
        result.details {
            expectThat(text()).isEqualTo("i'm a details")
        }
    }

    @Test
    fun `can parse dialog-tag`() {
        val result = aValidDocument(aStandardTag("dialog"))
        result.dialog {
            expectThat(text()).isEqualTo("i'm a dialog")
        }
    }

    @Test
    fun `can parse menu-tag`() {
        val result = aValidDocument(aStandardTag("menu"))
        result.menu {
            expectThat(text()).isEqualTo("i'm a menu")
        }
    }

    @Test
    fun `can parse menuitem-tag`() {
        val result = aValidDocument(aStandardTag("menuitem"))
        result.menuitem {
            expectThat(text()).isEqualTo("i'm a menuitem")
        }
    }

    @Test
    fun `can parse summary-tag`() {
        val result = aValidDocument(aStandardTag("summary"))
        result.summary {
            expectThat(text()).isEqualTo("i'm a summary")
        }
    }
}