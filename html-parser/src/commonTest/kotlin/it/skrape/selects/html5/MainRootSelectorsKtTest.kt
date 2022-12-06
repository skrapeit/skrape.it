package it.skrape.selects.html5

import aValidDocument
import io.kotest.matchers.shouldBe
import kotlin.test.Test
import kotlin.js.JsName

class MainRootSelectorsKtTest {

    @JsName("CanPickHtmlTag")
	@Test
	fun `can pick html-tag`() {
        val selector = aValidDocument().html("") {
            findFirst {
                attribute("lang").shouldBe("en")
            }
            toCssSelector
        }

        selector.shouldBe("html")
    }
}
