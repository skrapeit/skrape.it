package it.skrape.selects.html5

import aSelfClosingTag
import aValidDocument
import io.kotest.matchers.shouldBe
import it.skrape.selects.attribute
import kotlin.test.Test
import kotlin.js.JsName

class ScriptingSelectorsKtTest {

    @JsName("CanParseScriptTag")
	@Test
	fun `can parse script-tag`() {
        val selector = aValidDocument(aSelfClosingTag("script")).script {
            findAll {
                attribute("custom-attr").shouldBe("script-attr")
            }
            toCssSelector
        }

        selector.shouldBe("script")
    }

    @JsName("CanParseCanvasTag")
	@Test
	fun `can parse canvas-tag`() {
        val selector = aValidDocument(aSelfClosingTag("canvas")).canvas {
            findFirst {
                attribute("custom-attr").shouldBe("canvas-attr")
            }
            toCssSelector
        }

        selector.shouldBe("canvas")
    }

    @JsName("CanParseNoscriptTag")
	@Test
	fun `can parse noscript-tag`() {
        val selector = aValidDocument(aSelfClosingTag("noscript")).noscript {
            findAll {
                attribute("custom-attr").shouldBe("noscript-attr")
            }
            toCssSelector
        }

        selector.shouldBe("noscript")
    }
}