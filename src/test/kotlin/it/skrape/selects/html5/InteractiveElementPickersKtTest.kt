package it.skrape.selects.html5

import assertk.assertThat
import assertk.assertions.isEqualTo
import it.skrape.aStandardTag
import it.skrape.aValidResult
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class InteractiveElementPickersKtTest {

    @Test
    fun `can parse details-tag`() {
        val result = aValidResult(aStandardTag("details"))
        result.details {
            assertThat(text()).isEqualTo("i'm a details")
        }
    }

    @Test
    fun `can parse dialog-tag`() {
        val result = aValidResult(aStandardTag("dialog"))
        result.dialog {
            assertThat(text()).isEqualTo("i'm a dialog")
        }
    }

    @Test
    fun `can parse menu-tag`() {
        val result = aValidResult(aStandardTag("menu"))
        result.menu {
            assertThat(text()).isEqualTo("i'm a menu")
        }
    }

    @Test
    fun `can parse menuitem-tag`() {
        val result = aValidResult(aStandardTag("menuitem"))
        result.menuitem {
            assertThat(text()).isEqualTo("i'm a menuitem")
        }
    }

    @Test
    fun `can parse summary-tag`() {
        val result = aValidResult(aStandardTag("summary"))
        result.summary {
            assertThat(text()).isEqualTo("i'm a summary")
        }
    }
}