package it.skrape.selects.html5

import assertk.assertThat
import assertk.assertions.isEqualTo
import it.skrape.aStandardTag
import it.skrape.aValidResult
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class WebComponentsElementPickersKtTest {

    @Test
    fun `can parse content-tag`() {
        val result = aValidResult(aStandardTag("content"))
        result.content {
            assertThat(text()).isEqualTo("i'm a content")
        }
    }

    @Test
    fun `can parse shadow-tag`() {
        val result = aValidResult(aStandardTag("shadow"))
        result.shadow {
            assertThat(text()).isEqualTo("i'm a shadow")
        }
    }

    @Test
    fun `can parse slot-tag`() {
        val result = aValidResult(aStandardTag("slot"))
        result.slot {
            assertThat(text()).isEqualTo("i'm a slot")
        }
    }

    @Test
    fun `can parse template-tag`() {
        val result = aValidResult(aStandardTag("template"))
        result.template {
            assertThat(text()).isEqualTo("i'm a template")
        }
    }
}