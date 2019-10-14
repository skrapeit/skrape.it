package it.skrape.selects.html5

import it.skrape.aStandardTag
import it.skrape.aValidDocument
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class WebComponentsElementPickersKtTest {

    @Test
    fun `can parse content-tag`() {
        val result = aValidDocument(aStandardTag("content"))
        result.content {
            expectThat(text()).isEqualTo("i'm a content")
        }
    }

    @Test
    fun `can parse shadow-tag`() {
        val result = aValidDocument(aStandardTag("shadow"))
        result.shadow {
            expectThat(text()).isEqualTo("i'm a shadow")
        }
    }

    @Test
    fun `can parse slot-tag`() {
        val result = aValidDocument(aStandardTag("slot"))
        result.slot {
            expectThat(text()).isEqualTo("i'm a slot")
        }
    }

    @Test
    fun `can parse template-tag`() {
        val result = aValidDocument(aStandardTag("template"))
        result.template {
            expectThat(text()).isEqualTo("i'm a template")
        }
    }
}