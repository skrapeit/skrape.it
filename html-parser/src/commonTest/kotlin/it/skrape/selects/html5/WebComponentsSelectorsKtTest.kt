package it.skrape.selects.html5

import aStandardTag
import aValidDocument
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class WebComponentsSelectorsKtTest: FunSpec({

    test("can parse content-tag") {
        val selector = aValidDocument(aStandardTag("content")).content {
            findFirst {
                text.shouldBe("i'm a content")
            }
            toCssSelector
        }

        selector.shouldBe("content")
    }

    test("can parse shadow-tag") {
        val selector = aValidDocument(aStandardTag("shadow")).shadow {
            findFirst {
                text.shouldBe("i'm a shadow")
            }
            toCssSelector
        }

        selector.shouldBe("shadow")
    }

    test("can parse slot-tag") {
        val selector = aValidDocument(aStandardTag("slot")).slot {
            findFirst {
                text.shouldBe("i'm a slot")
            }
            toCssSelector
        }

        selector.shouldBe("slot")
    }

    test("can parse template-tag") {
        val selector = aValidDocument(aStandardTag("template")).template {
            findFirst {
                //println("${this.element.text()}")
                html.shouldBe("i'm a template")
            }
            toCssSelector
        }
        selector.shouldBe("template")
    }
})