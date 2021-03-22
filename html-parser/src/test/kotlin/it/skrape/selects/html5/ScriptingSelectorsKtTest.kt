package it.skrape.selects.html5

import it.skrape.aSelfClosingTag
import it.skrape.aValidDocument
import it.skrape.selects.attribute
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class ScriptingSelectorsKtTest {

    @Test
    fun `can parse script-tag`() {
        val selector = aValidDocument(aSelfClosingTag("script")).script {
            findAll {
                expectThat(attribute("custom-attr")).isEqualTo("script-attr")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("script")
    }

    @Test
    fun `can parse canvas-tag`() {
        val selector = aValidDocument(aSelfClosingTag("canvas")).canvas {
            findFirst {
                expectThat(attribute("custom-attr")).isEqualTo("canvas-attr")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("canvas")
    }

    @Test
    fun `can parse noscript-tag`() {
        val selector = aValidDocument(aSelfClosingTag("noscript")).noscript {
            findAll {
                expectThat(attribute("custom-attr")).isEqualTo("noscript-attr")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("noscript")
    }
}