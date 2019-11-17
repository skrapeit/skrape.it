package it.skrape.selects.html5

import it.skrape.aSelfClosingTag
import it.skrape.aValidDocument
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class ScriptingSelectorsKtTest {

    @Test
    fun `can parse script-tag`() {
        val selector = aValidDocument(aSelfClosingTag("script")).script {
            findAll {
                expectThat(attr("custom-attr")).isEqualTo("script-attr")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("script")
    }

    @Test
    fun `can parse canvas-tag`() {
        val selector = aValidDocument(aSelfClosingTag("canvas")).canvas {
            findFirst {
                expectThat(attr("custom-attr")).isEqualTo("canvas-attr")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("canvas")
    }

    @Test
    fun `can parse noscript-tag`() {
        val selector = aValidDocument(aSelfClosingTag("noscript")).noscript {
            findAll {
                expectThat(attr("custom-attr")).isEqualTo("noscript-attr")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("noscript")
    }
}