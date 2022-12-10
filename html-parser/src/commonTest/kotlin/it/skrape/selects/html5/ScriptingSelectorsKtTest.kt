package it.skrape.selects.html5

import aSelfClosingTag
import aValidDocument
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import it.skrape.selects.attribute

class ScriptingSelectorsKtTest: FunSpec({

    test("can parse script-tag") {
        val selector = aValidDocument(aSelfClosingTag("script")).script {
            findAll {
                attribute("custom-attr").shouldBe("script-attr")
            }
            toCssSelector
        }

        selector.shouldBe("script")
    }

    test("can parse canvas-tag") {
        val selector = aValidDocument(aSelfClosingTag("canvas")).canvas {
            findFirst {
                attribute("custom-attr").shouldBe("canvas-attr")
            }
            toCssSelector
        }

        selector.shouldBe("canvas")
    }

    test("can parse noscript-tag") {
        val selector = aValidDocument(aSelfClosingTag("noscript")).noscript {
            findAll {
                attribute("custom-attr").shouldBe("noscript-attr")
            }
            toCssSelector
        }

        selector.shouldBe("noscript")
    }
})