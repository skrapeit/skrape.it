package it.skrape.selects.html5

import aStandardTag
import aValidDocument
import io.kotest.matchers.shouldBe
import kotlin.js.JsName
import kotlin.test.Test

class WebComponentsSelectorsKtTest {

    @JsName("CanParseContentTag")
	@Test
	fun `can parse content-tag`() {
        val selector = aValidDocument(aStandardTag("content")).content {
            findFirst {
                text.shouldBe("i'm a content")
            }
            toCssSelector
        }

        selector.shouldBe("content")
    }

    @JsName("CanParseShadowTag")
	@Test
	fun `can parse shadow-tag`() {
        val selector = aValidDocument(aStandardTag("shadow")).shadow {
            findFirst {
                text.shouldBe("i'm a shadow")
            }
            toCssSelector
        }

        selector.shouldBe("shadow")
    }

    @JsName("CanParseSlotTag")
	@Test
	fun `can parse slot-tag`() {
        val selector = aValidDocument(aStandardTag("slot")).slot {
            findFirst {
                text.shouldBe("i'm a slot")
            }
            toCssSelector
        }

        selector.shouldBe("slot")
    }

    @JsName("CanParseTemplateTag")
	@Test
	fun `can parse template-tag`() {
        val selector = aValidDocument(aStandardTag("template")).template {
            findFirst {
                //println("${this.element.text()}")
                html.shouldBe("i'm a template")
            }
            toCssSelector
        }
        selector.shouldBe("template")
    }
}