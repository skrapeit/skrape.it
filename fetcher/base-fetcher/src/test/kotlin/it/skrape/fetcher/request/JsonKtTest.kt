package it.skrape.fetcher.request

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class JsonKtTest {

    @Test
    fun `can create string one field json`() {
        val json = Json().json {
            "a" to "1"
        }
        expectThat("$json").isEqualTo("""{"a":"1"}""")
    }

    @Test
    fun `can create nullable string one field json`() {
        val nString: String? = null
        val json = Json().json {
            "a" to nString
        }
        expectThat("$json").isEqualTo("""{"a":null}""")
    }

    @Test
    fun `can create two fields json`() {
        val json = Json().json {
            "a" to "1"
            "b" to 2
        }
        expectThat("$json").isEqualTo("""{"a":"1","b":2}""")
    }

    @Test
    fun `can create nullable number json`() {
        val nNumber: Number? = null
        val json = Json().json {
            "a" to nNumber
        }
        expectThat("$json").isEqualTo("""{"a":null}""")
    }

    @Test
    fun `can create json with list of Number`() {
        val json = Json().json {
            "a" to listOf(1, 2f, 3L)
            "b" to 2
        }
        expectThat("$json").isEqualTo("""{"a":[1,2.0,3],"b":2}""")
    }

    @Test
    fun `can create json with list of nullable Number`() {
        val nNumber: Number? = null
        val json = Json().json {
            "a" to listOf(1, 2f, nNumber, 3L)
            "b" to 2
        }
        expectThat("$json").isEqualTo("""{"a":[1,2.0,null,3],"b":2}""")
    }


    @Test
    fun `can create json with list of String`() {
        val json = Json().json {
            "a" to listOf("x1", "x2", "x3")
            "b" to 2
        }
        expectThat("$json").isEqualTo("""{"a":["x1","x2","x3"],"b":2}""")
    }

    @Test
    fun `can create json with list of nullable String`() {
        val nString: String? = null
        val json = Json().json {
            "a" to listOf("x1", nString, "x3")
        }
        expectThat("$json").isEqualTo("""{"a":["x1",null,"x3"]}""")
    }

    @Test
    fun `can create json with inner json`() {
        val json = Json().json {
            "a" to json {
                "i" to 42
                "ii" to "abc"
                "iii" to listOf("x", "y", "z")
            }
            "b" to 2
        }
        expectThat("$json").isEqualTo("""{"a":{"i":42,"ii":"abc","iii":["x","y","z"]},"b":2}""")
    }

    @Test
    fun `can create json with inner nullable json`() {
        val nJson: Json? = null
        val json = Json().json {
            "a" to nJson
        }
        expectThat("$json").isEqualTo("""{"a":null}""")
    }


    @Test
    fun `can create json with inner list of any`() {
        val json = Json().json {
            "a" to json {
                "i" to listOf("x", 42, 2.0, "any")
            }
        }
        expectThat("$json").isEqualTo("""{"a":{"i":["x",42,2.0,"any"]}}""")
    }

    @Test
    fun `can create json with inner list of nullable any`() {
        val json = Json().json {
            "a" to json {
                "i" to listOf("x", 42, 2.0, null, "any")
            }
        }
        expectThat("$json").isEqualTo("""{"a":{"i":["x",42,2.0,null,"any"]}}""")
    }

    @Test
    fun `can create json with boolean`() {
        val json = Json().json {
            "a" to true
        }
        expectThat("$json").isEqualTo("""{"a":true}""")
    }

    @Test
    fun `can create json with nullable boolean`() {
        val aNullableBoolean: Boolean? = null
        val json = Json().json {
            "a" to aNullableBoolean
        }
        expectThat("$json").isEqualTo("""{"a":null}""")
    }

}