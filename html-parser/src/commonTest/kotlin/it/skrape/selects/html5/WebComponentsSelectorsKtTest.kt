package it.skrape.selects.html5

import aStandardTag
import aValidDocument
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import kotlin.js.JsName
import kotlin.test.Test

class WebComponentsSelectorsKtTest {

    @JsName("CanParseContentTag")
	@Test
	fun `can parse content-tag`() {
        val selector = aValidDocument(aStandardTag("content")).content {
            findFirst {
                expect(text).toEqual("i'm a content")
            }
            toCssSelector
        }

        expect(selector).toEqual("content")
    }

    @JsName("CanParseShadowTag")
	@Test
	fun `can parse shadow-tag`() {
        val selector = aValidDocument(aStandardTag("shadow")).shadow {
            findFirst {
                expect(text).toEqual("i'm a shadow")
            }
            toCssSelector
        }

        expect(selector).toEqual("shadow")
    }

    @JsName("CanParseSlotTag")
	@Test
	fun `can parse slot-tag`() {
        val selector = aValidDocument(aStandardTag("slot")).slot {
            findFirst {
                expect(text).toEqual("i'm a slot")
            }
            toCssSelector
        }

        expect(selector).toEqual("slot")
    }

    @JsName("CanParseTemplateTag")
	@Test
	fun `can parse template-tag`() {
        val selector = aValidDocument(aStandardTag("template")).template {
            findFirst {
                //println("${this.element.text()}")
                expect(html).toEqual("i'm a template")
            }
            toCssSelector
        }
        expect(selector).toEqual("template")
    }
}