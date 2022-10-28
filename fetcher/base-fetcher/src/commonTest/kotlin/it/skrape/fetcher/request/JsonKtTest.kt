package it.skrape.fetcher.request

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import kotlin.js.JsName
import kotlin.test.Test

internal class JsonKtTest {

    @Test
    @JsName("CanCreateStringOneFieldJson")
	fun `can create string one field json`() {
        val json = Json().json {
            "a" to "1"
        }
        expect("$json").toEqual("""{"a":"1"}""")
    }

    @Test
    @JsName("CanCreateNullableStringOneFieldJson")
	fun `can create nullable string one field json`() {
        val nString: String? = null
        val json = Json().json {
            "a" to nString
        }
        expect("$json").toEqual("""{"a":null}""")
    }

    @Test
    @JsName("CanCreateTwoFieldsJson")
	fun `can create two fields json`() {
        val json = Json().json {
            "a" to "1"
            "b" to 2
        }
        expect("$json").toEqual("""{"a":"1","b":2}""")
    }

    @Test
    @JsName("CanCreateNullableNumberJson")
	fun `can create nullable number json`() {
        val nNumber: Number? = null
        val json = Json().json {
            "a" to nNumber
        }
        expect("$json").toEqual("""{"a":null}""")
    }

    @Test
    @JsName("CanCreateJsonWithListOfNumber")
	fun `can create json with list of Number`() {
        val json = Json().json {
            "a" to listOf(1, 2.1f, 3L)
            "b" to 2
        }
        expect("$json").toEqual("""{"a":[1,2.1,3],"b":2}""")
    }

    @Test
    @JsName("CanCreateJsonWithListOfNullableNumber")
	fun `can create json with list of nullable Number`() {
        val nNumber: Number? = null
        val json = Json().json {
            "a" to listOf(1, 2.1f, nNumber, 3L)
            "b" to 2
        }
        expect("$json").toEqual("""{"a":[1,2.1,null,3],"b":2}""")
    }


    @Test
    @JsName("CanCreateJsonWithListOfString")
	fun `can create json with list of String`() {
        val json = Json().json {
            "a" to listOf("x1", "x2", "x3")
            "b" to 2
        }
        expect("$json").toEqual("""{"a":["x1","x2","x3"],"b":2}""")
    }

    @Test
    @JsName("CanCreateJsonWithListOfNullableString")
	fun `can create json with list of nullable String`() {
        val nString: String? = null
        val json = Json().json {
            "a" to listOf("x1", nString, "x3")
        }
        expect("$json").toEqual("""{"a":["x1",null,"x3"]}""")
    }

    @Test
    @JsName("CanCreateJsonWithInnerJson")
	fun `can create json with inner json`() {
        val json = Json().json {
            "a" to json {
                "i" to 42
                "ii" to "abc"
                "iii" to listOf("x", "y", "z")
            }
            "b" to 2
        }
        expect("$json").toEqual("""{"a":{"i":42,"ii":"abc","iii":["x","y","z"]},"b":2}""")
    }

    @Test
    @JsName("CanCreateJsonWithInnerNullableJson")
	fun `can create json with inner nullable json`() {
        val nJson: Json? = null
        val json = Json().json {
            "a" to nJson
        }
        expect("$json").toEqual("""{"a":null}""")
    }


    @Test
    @JsName("CanCreateJsonWithInnerListOfAny")
	fun `can create json with inner list of any`() {
        val json = Json().json {
            "a" to json {
                "i" to listOf("x", 42, 2.1, "any")
            }
        }
        expect("$json").toEqual("""{"a":{"i":["x",42,2.1,"any"]}}""")
    }

    @Test
    @JsName("CanCreateJsonWithInnerListOfNullableAny")
	fun `can create json with inner list of nullable any`() {
        val json = Json().json {
            "a" to json {
                "i" to listOf("x", 42, 2.1, null, "any")
            }
        }
        expect("$json").toEqual("""{"a":{"i":["x",42,2.1,null,"any"]}}""")
    }

    @Test
    @JsName("CanCreateJsonWithBoolean")
	fun `can create json with boolean`() {
        val json = Json().json {
            "a" to true
        }
        expect("$json").toEqual("""{"a":true}""")
    }

    @Test
    @JsName("CanCreateJsonWithNullableBoolean")
	fun `can create json with nullable boolean`() {
        val aNullableBoolean: Boolean? = null
        val json = Json().json {
            "a" to aNullableBoolean
        }
        expect("$json").toEqual("""{"a":null}""")
    }

}