package it.skrape.fetcher.request

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

internal class JsonKtTest: FunSpec({

    test("can create string one field json") {
        val json = Json().json {
            "a" to "1"
        }
        "$json".shouldBe("""{"a":"1"}""")
    }

    test("can create nullable string one field json") {
        val nString: String? = null
        val json = Json().json {
            "a" to nString
        }
        "$json".shouldBe("""{"a":null}""")
    }

    test("can create two fields json") {
        val json = Json().json {
            "a" to "1"
            "b" to 2
        }
        "$json".shouldBe("""{"a":"1","b":2}""")
    }

    test("can create nullable number json") {
        val nNumber: Number? = null
        val json = Json().json {
            "a" to nNumber
        }
        "$json".shouldBe("""{"a":null}""")
    }

    test("can create json with list of Number") {
        val json = Json().json {
            "a" to listOf(1, 2.1f, 3L)
            "b" to 2
        }
        "$json".shouldBe("""{"a":[1,2.1,3],"b":2}""")
    }

    test("can create json with list of nullable Number") {
        val nNumber: Number? = null
        val json = Json().json {
            "a" to listOf(1, 2.1f, nNumber, 3L)
            "b" to 2
        }
        "$json".shouldBe("""{"a":[1,2.1,null,3],"b":2}""")
    }


    test("can create json with list of String") {
        val json = Json().json {
            "a" to listOf("x1", "x2", "x3")
            "b" to 2
        }
        "$json".shouldBe("""{"a":["x1","x2","x3"],"b":2}""")
    }

    test("can create json with list of nullable String") {
        val nString: String? = null
        val json = Json().json {
            "a" to listOf("x1", nString, "x3")
        }
        "$json".shouldBe("""{"a":["x1",null,"x3"]}""")
    }

    test("can create json with inner json") {
        val json = Json().json {
            "a" to json {
                "i" to 42
                "ii" to "abc"
                "iii" to listOf("x", "y", "z")
            }
            "b" to 2
        }
        "$json".shouldBe("""{"a":{"i":42,"ii":"abc","iii":["x","y","z"]},"b":2}""")
    }

    test("can create json with inner nullable json") {
        val nJson: Json? = null
        val json = Json().json {
            "a" to nJson
        }
        "$json".shouldBe("""{"a":null}""")
    }


    test("can create json with inner list of any") {
        val json = Json().json {
            "a" to json {
                "i" to listOf("x", 42, 2.1, "any")
            }
        }
        "$json".shouldBe("""{"a":{"i":["x",42,2.1,"any"]}}""")
    }

    test("can create json with inner list of nullable any") {
        val json = Json().json {
            "a" to json {
                "i" to listOf("x", 42, 2.1, null, "any")
            }
        }
        "$json".shouldBe("""{"a":{"i":["x",42,2.1,null,"any"]}}""")
    }

    test("can create json with boolean") {
        val json = Json().json {
            "a" to true
        }
        "$json".shouldBe("""{"a":true}""")
    }

    test("can create json with nullable boolean") {
        val aNullableBoolean: Boolean? = null
        val json = Json().json {
            "a" to aNullableBoolean
        }
        "$json".shouldBe("""{"a":null}""")
    }

})