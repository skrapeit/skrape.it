package it.skrape.fetcher.request

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import kotlin.js.JsName
import kotlin.test.Test

internal class FormKtTest {

    private fun form(init: Form.() -> Unit): Form = Form().also(init)

    @Test
    @JsName("CanCreateStringOnePairForm")
	fun `can create string one pair form`() {
        val form = form {
            "a" to "1"
        }
        expect("$form").toEqual("a=1")
    }

    @Test
    @JsName("CanCreateNullableStringOneFieldForm")
	fun `can create nullable string one field form`() {
        val nullString: String? = null
        val form = form {
            "a" to nullString
        }
        expect("$form").toEqual("a=null")
    }

    @Test
    @JsName("CanCreateTwoFieldsForm")
	fun `can create two fields form`() {
        val form = form {
            "a" to "1"
            "b" to 2
        }
        expect("$form").toEqual("a=1&b=2")
    }

    @Test
    @JsName("CanCreateNullableNumberForm")
	fun `can create nullable number form`() {
        val nullNumber: Number? = null
        val form = form {
            "a" to nullNumber
        }
        expect("$form").toEqual("a=null")
    }

    @Test
    @JsName("CanCreateFormWithBoolean")
	fun `can create form with boolean`() {
        val form = form {
            "a" to true
        }
        expect("$form").toEqual("a=true")
    }

    @Test
    @JsName("CanCreateFormWithNullableBoolean")
	fun `can create form with nullable boolean`() {
        val nullBoolean: Boolean? = null
        val form = form {
            "a" to nullBoolean
        }
        expect("$form").toEqual("a=null")
    }

}