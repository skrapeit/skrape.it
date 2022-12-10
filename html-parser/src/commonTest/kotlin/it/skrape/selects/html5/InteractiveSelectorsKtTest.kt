package it.skrape.selects.html5

import aStandardTag
import aValidDocument
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class InteractiveSelectorsKtTest: FunSpec({

    test("can parse details-tag") {
        val selector = aValidDocument(aStandardTag("details")).details {
            findFirst {
                text.shouldBe("i'm a details")
            }
            toCssSelector
        }

        selector.shouldBe("details")
    }

    test("can parse dialog-tag") {
        val selector = aValidDocument(aStandardTag("dialog")).dialog {
            findFirst {
                text.shouldBe("i'm a dialog")
            }
            toCssSelector
        }

        selector.shouldBe("dialog")
    }

    test("can parse menu-tag") {
        val selector = aValidDocument(aStandardTag("menu")).menu {
            findFirst {
                text.shouldBe("i'm a menu")
            }
            toCssSelector
        }

        selector.shouldBe("menu")
    }

    test("can parse menuitem-tag") {
        val selector = aValidDocument(aStandardTag("menuitem")).menuitem {
            findFirst {
                text.shouldBe("i'm a menuitem")
            }
            toCssSelector
        }

        selector.shouldBe("menuitem")
    }

    test("can parse summary-tag") {
        val selector = aValidDocument(aStandardTag("summary")).summary {
            findFirst {
                text.shouldBe("i'm a summary")
            }
            toCssSelector
        }

        selector.shouldBe("summary")
    }
})