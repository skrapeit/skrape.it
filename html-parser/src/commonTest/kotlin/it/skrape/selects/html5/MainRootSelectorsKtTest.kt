package it.skrape.selects.html5

import aValidDocument
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MainRootSelectorsKtTest: FunSpec({

    test("can pick html-tag") {
        val selector = aValidDocument().html("") {
            findFirst {
                attribute("lang").shouldBe("en")
            }
            toCssSelector
        }

        selector.shouldBe("html")
    }
})
