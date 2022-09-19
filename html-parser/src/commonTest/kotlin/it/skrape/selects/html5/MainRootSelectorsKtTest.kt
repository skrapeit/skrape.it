package it.skrape.selects.html5

import aValidDocument
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test
import kotlin.js.JsName

class MainRootSelectorsKtTest {

    @JsName("CanPickHtmlTag")
	@Test
	fun `can pick html-tag`() {
        val selector = aValidDocument().html("") {
            findFirst {
                expect(attribute("lang")).toEqual("en")
            }
            toCssSelector
        }

        expect(selector).toEqual("html")
    }
}
