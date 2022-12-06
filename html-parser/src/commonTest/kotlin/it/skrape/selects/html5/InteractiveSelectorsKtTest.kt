package it.skrape.selects.html5

import aStandardTag
import aValidDocument
import io.kotest.matchers.shouldBe
import kotlin.test.Test
import kotlin.js.JsName

class InteractiveSelectorsKtTest {

    @JsName("CanParseDetailsTag")
	@Test
	fun `can parse details-tag`() {
        val selector = aValidDocument(aStandardTag("details")).details {
            findFirst {
                text.shouldBe("i'm a details")
            }
            toCssSelector
        }

        selector.shouldBe("details")
    }

    @JsName("CanParseDialogTag")
	@Test
	fun `can parse dialog-tag`() {
        val selector = aValidDocument(aStandardTag("dialog")).dialog {
            findFirst {
                text.shouldBe("i'm a dialog")
            }
            toCssSelector
        }

        selector.shouldBe("dialog")
    }

    @JsName("CanParseMenuTag")
	@Test
	fun `can parse menu-tag`() {
        val selector = aValidDocument(aStandardTag("menu")).menu {
            findFirst {
                text.shouldBe("i'm a menu")
            }
            toCssSelector
        }

        selector.shouldBe("menu")
    }

    @JsName("CanParseMenuitemTag")
	@Test
	fun `can parse menuitem-tag`() {
        val selector = aValidDocument(aStandardTag("menuitem")).menuitem {
            findFirst {
                text.shouldBe("i'm a menuitem")
            }
            toCssSelector
        }

        selector.shouldBe("menuitem")
    }

    @JsName("CanParseSummaryTag")
	@Test
	fun `can parse summary-tag`() {
        val selector = aValidDocument(aStandardTag("summary")).summary {
            findFirst {
                text.shouldBe("i'm a summary")
            }
            toCssSelector
        }

        selector.shouldBe("summary")
    }
}