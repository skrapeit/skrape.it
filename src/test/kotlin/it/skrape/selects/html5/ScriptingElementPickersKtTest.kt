package it.skrape.selects.html5

import it.skrape.aSelfClosingTag
import it.skrape.aValidDocument
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class ScriptingElementPickersKtTest {

    @Test
    fun `can parse script-tag`() {
        val result = aValidDocument(aSelfClosingTag("script"))
        result.script {
            expectThat(attr("custom-attr")).isEqualTo("script-attr")
        }
    }

    @Test
    fun `can parse canvas-tag`() {
        val result = aValidDocument(aSelfClosingTag("canvas"))
        result.canvas {
            expectThat(attr("custom-attr")).isEqualTo("canvas-attr")
        }
    }

    @Test
    fun `can parse noscript-tag`() {
        val result = aValidDocument(aSelfClosingTag("noscript"))
        result.noscript {
            expectThat(attr("custom-attr")).isEqualTo("noscript-attr")
        }
    }
}