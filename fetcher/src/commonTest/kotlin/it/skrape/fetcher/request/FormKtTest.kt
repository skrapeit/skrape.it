package it.skrape.fetcher.request

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

internal class FormKtTest : FunSpec({

    fun form(init: Form.() -> Unit): Form = Form().also(init)

    test("can create string one pair form") {
        val form = form {
            "a" to "1"
        }
        "$form".shouldBe("a=1")
    }

    test("can create nullable string one field form") {
        val nullString: String? = null
        val form = form {
            "a" to nullString
        }
        "$form".shouldBe("a=null")
    }

    test("can create two fields form") {
        val form = form {
            "a" to "1"
            "b" to 2
        }
        "$form".shouldBe("a=1&b=2")
    }

    test("can create nullable number form") {
        val nullNumber: Number? = null
        val form = form {
            "a" to nullNumber
        }
        "$form".shouldBe("a=null")
    }

    test("can create form with boolean") {
        val form = form {
            "a" to true
        }
        "$form".shouldBe("a=true")
    }

    test("can create form with nullable boolean") {
        val nullBoolean: Boolean? = null
        val form = form {
            "a" to nullBoolean
        }
        "$form".shouldBe("a=null")
    }

})