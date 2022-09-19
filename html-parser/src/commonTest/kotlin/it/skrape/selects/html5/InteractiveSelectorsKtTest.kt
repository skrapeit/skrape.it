package it.skrape.selects.html5

import aStandardTag
import aValidDocument
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test
import kotlin.js.JsName

class InteractiveSelectorsKtTest {

    @JsName("CanParseDetailsTag")
	@Test
	fun `can parse details-tag`() {
        val selector = aValidDocument(aStandardTag("details")).details {
            findFirst {
                expect(text).toEqual("i'm a details")
            }
            toCssSelector
        }

        expect(selector).toEqual("details")
    }

    @JsName("CanParseDialogTag")
	@Test
	fun `can parse dialog-tag`() {
        val selector = aValidDocument(aStandardTag("dialog")).dialog {
            findFirst {
                expect(text).toEqual("i'm a dialog")
            }
            toCssSelector
        }

        expect(selector).toEqual("dialog")
    }

    @JsName("CanParseMenuTag")
	@Test
	fun `can parse menu-tag`() {
        val selector = aValidDocument(aStandardTag("menu")).menu {
            findFirst {
                expect(text).toEqual("i'm a menu")
            }
            toCssSelector
        }

        expect(selector).toEqual("menu")
    }

    @JsName("CanParseMenuitemTag")
	@Test
	fun `can parse menuitem-tag`() {
        val selector = aValidDocument(aStandardTag("menuitem")).menuitem {
            findFirst {
                expect(text).toEqual("i'm a menuitem")
            }
            toCssSelector
        }

        expect(selector).toEqual("menuitem")
    }

    @JsName("CanParseSummaryTag")
	@Test
	fun `can parse summary-tag`() {
        val selector = aValidDocument(aStandardTag("summary")).summary {
            findFirst {
                expect(text).toEqual("i'm a summary")
            }
            toCssSelector
        }

        expect(selector).toEqual("summary")
    }
}