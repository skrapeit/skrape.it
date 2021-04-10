package it.skrape.fetcher.request

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class FormKtTest {

    private fun form(init: Form.() -> Unit): Form = Form().also(init)

    @Test
    fun `can create string one pair form`() {
        val form = form {
            "a" to "1"
        }
        expectThat("$form").isEqualTo("a=1")
    }

    @Test
    fun `can create nullable string one field form`() {
        val nullString: String? = null
        val form = form {
            "a" to nullString
        }
        expectThat("$form").isEqualTo("a=null")
    }

    @Test
    fun `can create two fields form`() {
        val form = form {
            "a" to "1"
            "b" to 2
        }
        expectThat("$form").isEqualTo("a=1&b=2")
    }

    @Test
    fun `can create nullable number form`() {
        val nullNumber: Number? = null
        val form = form {
            "a" to nullNumber
        }
        expectThat("$form").isEqualTo("a=null")
    }

    @Test
    fun `can create form with boolean`() {
        val form = form {
            "a" to true
        }
        expectThat("$form").isEqualTo("a=true")
    }

    @Test
    fun `can create form with nullable boolean`() {
        val nullBoolean: Boolean? = null
        val form = form {
            "a" to nullBoolean
        }
        expectThat("$form").isEqualTo("a=null")
    }

}