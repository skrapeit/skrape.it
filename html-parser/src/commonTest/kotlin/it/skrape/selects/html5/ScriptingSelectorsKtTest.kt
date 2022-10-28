package it.skrape.selects.html5

import aSelfClosingTag
import aValidDocument
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import it.skrape.selects.attribute
import kotlin.test.Test
import kotlin.js.JsName

class ScriptingSelectorsKtTest {

    @JsName("CanParseScriptTag")
	@Test
	fun `can parse script-tag`() {
        val selector = aValidDocument(aSelfClosingTag("script")).script {
            findAll {
                expect(attribute("custom-attr")).toEqual("script-attr")
            }
            toCssSelector
        }

        expect(selector).toEqual("script")
    }

    @JsName("CanParseCanvasTag")
	@Test
	fun `can parse canvas-tag`() {
        val selector = aValidDocument(aSelfClosingTag("canvas")).canvas {
            findFirst {
                expect(attribute("custom-attr")).toEqual("canvas-attr")
            }
            toCssSelector
        }

        expect(selector).toEqual("canvas")
    }

    @JsName("CanParseNoscriptTag")
	@Test
	fun `can parse noscript-tag`() {
        val selector = aValidDocument(aSelfClosingTag("noscript")).noscript {
            findAll {
                expect(attribute("custom-attr")).toEqual("noscript-attr")
            }
            toCssSelector
        }

        expect(selector).toEqual("noscript")
    }
}