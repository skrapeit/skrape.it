package it.skrape.matchers

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import it.skrape.selects.DocElement

class MatchersKtTest: FunSpec({

    val anInt = 200
    val aString = "test"
    val aNullableString: String? = null

    test("toBe is working type safe for Int") {
        anInt toBe 200
    }

    test("toBe is working type safe for String") {
        aString toBe "test"
    }

    test("toBe can handle expected is null") {
        shouldThrow<AssertionError> {
            "null" toBe null
        }
    }


    test("toBe can handle actual is null") {
        shouldThrow<AssertionError> {
            aNullableString toBe "foo"
        }
    }

    test("toBe is working type safe for null String") {
        aNullableString toBe null
    }

    test("toNotBe is working type safe for null String") {
        aString toBeNot null
    }

    test("toContain is working type safe for String") {
        aString toContain "es"
    }

    test("toContain is throwing exception on assertion failure") {
        shouldThrow<AssertionError> {
            aString toContain "foo"
        }
    }

    test("toNotContain is working type safe for String") {
        aString toNotContain "foo"
    }

    test("toNotContain is throwing exception on assertion failure") {
        shouldThrow<AssertionError> {
            aString toNotContain "es"
        }
    }

    test("toContain is working with lists") {
        listOf("foo", "bar") toContain "bar"
    }

    test("toContain on lists is throwing exception on assertion failure") {
        shouldThrow<AssertionError> {
            listOf("foo", "bar") toContain "schnitzel"
        }
    }

    test("toBePresent can handle multiple presents of matching ELEMENTS") {
        val elements = mockDocElementListOfSize(2)
        elements.toBePresent
    }

    test("toBePresent can handle single occurrence of matching ELEMENTS") {
        val elements = mockDocElementListOfSize(1)
        elements.toBePresent
    }

    test("toBePresent is throwing exception if no ELEMENTS matches") {
        val elements = mockDocElementListOfSize(0)
        shouldThrow<AssertionError> {
            elements.toBePresent
        }
    }

    test("toBePresent can handle multiple occurrence of an ELEMENT") {
        val element = mockDocElement(true, ".foo")
        element.toBePresent
    }

    test("toBePresent can handle single occurrence of an ELEMENT") {
        val element = mockDocElement(true, ".foo")
        element.toBePresent
    }

    test("toBePresent is throwing exception if no ELEMENT matches") {
        val element = mockDocElement(false, ".foo")
        shouldThrow<AssertionError> {
            element.toBePresent
        }
    }

    test("toBeNotPresent can handle multiple presents of matching ELEMENTS") {
        val elements = mockDocElementListOfSize(1)
        elements.toBePresent
    }

    test("toBeNotPresent can handle non existent ELEMENTS") {
        val elements = mockDocElementListOfSize(0)
        elements.toBeNotPresent
    }

    test("toBeNotPresent is throwing exception on single occurrence of matching ELEMENTS") {
        val elements = mockDocElementListOfSize(1)
        shouldThrow<AssertionError> {
            elements.toBeNotPresent
        }
    }

    test("toBeNotPresent is throwing exception on multiple presents of matching ELEMENTS") {
        val elements = mockDocElementListOfSize(2)
        shouldThrow<AssertionError> {
            elements.toBeNotPresent
        }
    }

    test("toBeEmpty can handle empty list") {
        emptyList<Any>().toBeEmpty
    }

    test("toBeEmpty is throwing exception NON empty list") {
        shouldThrow<AssertionError> {
            listOf(1, 2, 3).toBeEmpty
        }
    }

    test("toBeNotEmpty can handle NON empty list") {
        listOf(1, 2, 3).toBeNotEmpty
    }

    test("toBeNotEmpty is throwing exception empty list") {
        shouldThrow<AssertionError> {
            emptyList<Any>().toBeNotEmpty
        }
    }

    test("isNumeric will throw assertion if a string is null") {
        shouldThrow<AssertionError> {
            null.isNumeric
        }
    }

    test("isNumeric will not throw assertion if a string is an integer") {
        "42".isNumeric
    }

    test("isNumeric will not throw assertion if a string is a decimal") {
        "34211.535456".isNumeric
    }

    test("isNumeric will throw assertion if a string contains an integer") {
        shouldThrow<AssertionError> {
            "$-foo11%bar/".isNumeric
        }
    }

    test("isNumeric will throw assertion if a string contains a decimal") {
        shouldThrow<AssertionError> {
            "$-foo11.5%bar/".isNumeric
        }
    }

    test("isInteger will throw assertion if a string is null") {
        shouldThrow<AssertionError> {
            null.isInteger
        }
    }

    test("isInteger will not throw assertion if a string is an integer") {
        "42".isInteger
    }

    test("isInteger will throw assertion if a string is a decimal") {
        shouldThrow<AssertionError> {
            "34211.535456".isInteger
        }
    }

    test("isInteger will throw assertion if a string contains an integer") {
        shouldThrow<AssertionError> {
            "$-foo11%bar/".isInteger
        }
    }

    test("isInteger will throw assertion if a string contains a decimal") {
        shouldThrow<AssertionError> {
            "$-foo11.5%bar/".isInteger
        }
    }

    test("isDecimal will throw assertion if a string is null") {
        shouldThrow<AssertionError> {
            null.isDecimal
        }
    }

    test("isDecimal will throw assertion if a string is an integer") {
        shouldThrow<AssertionError> {
            "42".isDecimal
        }
    }

    test("isDecimal will not throw assertion if a string is a decimal") {
        "34211.535456".isDecimal
    }

    test("isDecimal will throw assertion if a string contains an integer") {
        shouldThrow<AssertionError> {
            "$-foo11%bar/".isDecimal
        }
    }

    test("isDecimal will throw assertion if a string contains a decimal") {
        shouldThrow<AssertionError> {
            "$-foo11.5%bar/".isDecimal
        }
    }

    test("containsNumeric will throw assertion if a string is null") {
        shouldThrow<AssertionError> {
            null.containsNumeric
        }
    }

    test("containsNumeric will not throw assertion if a string is an integer") {
        "42".containsNumeric
    }

    test("containsNumeric will not throw assertion if a string is a decimal") {
        "34211.535456".containsNumeric
    }

    test("containsNumeric will not throw assertion if a string contains an integer") {
        "$-foo11%bar/".containsNumeric
    }

    test("containsNumeric will not throw assertion if a string contains a decimal") {
        "$-foo11.5%bar/".containsNumeric
    }

    test("containsNumeric will throw assertion if a string not contains a number") {
        shouldThrow<AssertionError> {
            "$-foo%bar/".containsNumeric
        }
    }

})

expect fun mockDocElementListOfSize(mockSize: Int): List<DocElement>
expect fun mockDocElement(mockIsPresent: Boolean, mockCssSelector: String = ".foo"): DocElement
