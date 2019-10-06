package it.skrape.selects.html5

import assertk.assertThat
import assertk.assertions.isEqualTo
import it.skrape.aValidResult
import it.skrape.aSelfClosingTag
import org.junit.jupiter.api.Test

internal class ScriptingElementPickersKtTest {

    @Test
    fun `can parse script-tag`() {
        val result = aValidResult(aSelfClosingTag("script"))
        result.script {
            assertThat(attr("custom-attr")).isEqualTo("script-attr")
        }
    }

    @Test
    fun `can parse canvas-tag`() {
        val result = aValidResult(aSelfClosingTag("canvas"))
        result.canvas {
            assertThat(attr("custom-attr")).isEqualTo("canvas-attr")
        }
    }

    @Test
    fun `can parse noscript-tag`() {
        val result = aValidResult(aSelfClosingTag("noscript"))
        result.noscript {
            assertThat(attr("custom-attr")).isEqualTo("noscript-attr")
        }
    }
}