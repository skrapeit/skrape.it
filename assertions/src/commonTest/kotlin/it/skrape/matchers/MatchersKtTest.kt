package it.skrape.matchers

import io.kotest.assertions.throwables.shouldThrow
import it.skrape.selects.DocElement
import kotlin.js.JsName
import kotlin.test.Test

class MatchersKtTest {

    private val anInt = 200
    private val aString = "test"
    private val aNullableString: String? = null

    @JsName("ToBeIsWorkingTypeSafeForInt")
    @Test
    fun `toBe is working type safe for Int`() {
        anInt toBe 200
    }

    @JsName("ToBeIsWorkingTypeSafeForString")
    @Test
    fun `toBe is working type safe for String`() {
        aString toBe "test"
    }

    @JsName("ToBeCanHandleExpectedIsNull")
    @Test
    fun `toBe can handle expected is null`() {
        shouldThrow<AssertionError> {
            "null" toBe null
        }
    }


    @JsName("ToBeCanHandleActualIsNull")
    @Test
    fun `toBe can handle actual is null`() {
        shouldThrow<AssertionError> {
            aNullableString toBe "foo"
        }
    }

    @JsName("ToBeIsWorkingTypeSafeForNullString")
    @Test
    fun `toBe is working type safe for null String`() {
        aNullableString toBe null
    }

    @JsName("ToNotBeIsWorkingTypeSafeForNullString")
    @Test
    fun `toNotBe is working type safe for null String`() {
        aString toBeNot null
    }

    @JsName("ToContainIsWorkingTypeSafeForString")
    @Test
    fun `toContain is working type safe for String`() {
        aString toContain "es"
    }

    @JsName("ToContainIsThrowingExceptionOnAssertionFailure")
    @Test
    fun `toContain is throwing exception on assertion failure`() {
        shouldThrow<AssertionError> {
            aString toContain "foo"
        }
    }

    @JsName("ToNotContainIsWorkingTypeSafeForString")
    @Test
    fun `toNotContain is working type safe for String`() {
        aString toNotContain "foo"
    }

    @JsName("ToNotContainIsThrowingExceptionOnAssertionFailure")
    @Test
    fun `toNotContain is throwing exception on assertion failure`() {
        shouldThrow<AssertionError> {
            aString toNotContain "es"
        }
    }

    @JsName("ToContainIsWorkingWithLists")
    @Test
    fun `toContain is working with lists`() {
        listOf("foo", "bar") toContain "bar"
    }

    @JsName("ToContainOnListsIsThrowingExceptionOnAssertionFailure")
    @Test
    fun `toContain on lists is throwing exception on assertion failure`() {
        shouldThrow<AssertionError> {
            listOf("foo", "bar") toContain "schnitzel"
        }
    }

    @JsName("ToBePresentCanHandleMultiplePresentsOfMatchingELEMENTS")
    @Test
    fun `toBePresent can handle multiple presents of matching ELEMENTS`() {
        val elements = mockDocElementListOfSize(2)
        elements.toBePresent
    }

    @JsName("ToBePresentCanHandleSingleOccurrenceOfMatchingELEMENTS")
    @Test
    fun `toBePresent can handle single occurrence of matching ELEMENTS`() {
        val elements = mockDocElementListOfSize(1)
        elements.toBePresent
    }

    @JsName("ToBePresentIsThrowingExceptionIfNoELEMENTSMatches")
    @Test
    fun `toBePresent is throwing exception if no ELEMENTS matches`() {
        val elements = mockDocElementListOfSize(0)
        shouldThrow<AssertionError> {
            elements.toBePresent
        }
    }

    @JsName("ToBePresentCanHandleMultipleOccurrenceOfAnELEMENT")
    @Test
    fun `toBePresent can handle multiple occurrence of an ELEMENT`() {
        val element = mockDocElement(true, ".foo")
        element.toBePresent
    }

    @JsName("ToBePresentCanHandleSingleOccurrenceOfAnELEMENT")
    @Test
    fun `toBePresent can handle single occurrence of an ELEMENT`() {
        val element = mockDocElement(true, ".foo")
        element.toBePresent
    }

    @JsName("ToBePresentIsThrowingExceptionIfNoELEMENTMatches")
    @Test
    fun `toBePresent is throwing exception if no ELEMENT matches`() {
        val element = mockDocElement(false, ".foo")
        shouldThrow<AssertionError> {
            element.toBePresent
        }
    }

    @JsName("ToBeNotPresentCanHandleMultiplePresentsOfMatchingELEMENTS")
    @Test
    fun `toBeNotPresent can handle multiple presents of matching ELEMENTS`() {
        val elements = mockDocElementListOfSize(1)
        elements.toBePresent
    }

    @JsName("ToBeNotPresentCanHandleNonExistentELEMENTS")
    @Test
    fun `toBeNotPresent can handle non existent ELEMENTS`() {
        val elements = mockDocElementListOfSize(0)
        elements.toBeNotPresent
    }

    @JsName("ToBeNotPresentIsThrowingExceptionOnSingleOccurrenceOfMatchingELEMENTS")
    @Test
    fun `toBeNotPresent is throwing exception on single occurrence of matching ELEMENTS`() {
        val elements = mockDocElementListOfSize(1)
        shouldThrow<AssertionError> {
            elements.toBeNotPresent
        }
    }

    @JsName("ToBeNotPresentIsThrowingExceptionOnMultiplePresentsOfMatchingELEMENTS")
    @Test
    fun `toBeNotPresent is throwing exception on multiple presents of matching ELEMENTS`() {
        val elements = mockDocElementListOfSize(2)
        shouldThrow<AssertionError> {
            elements.toBeNotPresent
        }
    }

    @JsName("ToBeEmptyCanHandleEmptyList")
    @Test
    fun `toBeEmpty can handle empty list`() {
        emptyList<Any>().toBeEmpty
    }

    @JsName("ToBeEmptyIsThrowingExceptionNONEmptyList")
    @Test
    fun `toBeEmpty is throwing exception NON empty list`() {
        shouldThrow<AssertionError> {
            listOf(1, 2, 3).toBeEmpty
        }
    }

    @JsName("ToBeNotEmptyCanHandleNONEmptyList")
    @Test
    fun `toBeNotEmpty can handle NON empty list`() {
        listOf(1, 2, 3).toBeNotEmpty
    }

    @JsName("ToBeNotEmptyIsThrowingExceptionEmptyList")
    @Test
    fun `toBeNotEmpty is throwing exception empty list`() {
        shouldThrow<AssertionError> {
            emptyList<Any>().toBeNotEmpty
        }
    }

    @JsName("IsNumericWillThrowAssertionIfAStringIsNull")
    @Test
    fun `isNumeric will throw assertion if a string is null`() {
        shouldThrow<AssertionError> {
            null.isNumeric
        }
    }

    @JsName("IsNumericWillNotThrowAssertionIfAStringIsAnInteger")
    @Test
    fun `isNumeric will not throw assertion if a string is an integer`() {
        "42".isNumeric
    }

    @JsName("IsNumericWillNotThrowAssertionIfAStringIsADecimal")
    @Test
    fun `isNumeric will not throw assertion if a string is a decimal`() {
        "34211.535456".isNumeric
    }

    @JsName("IsNumericWillThrowAssertionIfAStringContainsAnInteger")
    @Test
    fun `isNumeric will throw assertion if a string contains an integer`() {
        shouldThrow<AssertionError> {
            "$-foo11%bar/".isNumeric
        }
    }

    @JsName("IsNumericWillThrowAssertionIfAStringContainsADecimal")
    @Test
    fun `isNumeric will throw assertion if a string contains a decimal`() {
        shouldThrow<AssertionError> {
            "$-foo11.5%bar/".isNumeric
        }
    }

    @JsName("IsIntegerWillThrowAssertionIfAStringIsNull")
    @Test
    fun `isInteger will throw assertion if a string is null`() {
        shouldThrow<AssertionError> {
            null.isInteger
        }
    }

    @JsName("IsIntegerWillNotThrowAssertionIfAStringIsAnInteger")
    @Test
    fun `isInteger will not throw assertion if a string is an integer`() {
        "42".isInteger
    }

    @JsName("IsIntegerWillThrowAssertionIfAStringIsADecimal")
    @Test
    fun `isInteger will throw assertion if a string is a decimal`() {
        shouldThrow<AssertionError> {
            "34211.535456".isInteger
        }
    }

    @JsName("IsIntegerWillThrowAssertionIfAStringContainsAnInteger")
    @Test
    fun `isInteger will throw assertion if a string contains an integer`() {
        shouldThrow<AssertionError> {
            "$-foo11%bar/".isInteger
        }
    }

    @JsName("IsIntegerWillThrowAssertionIfAStringContainsADecimal")
    @Test
    fun `isInteger will throw assertion if a string contains a decimal`() {
        shouldThrow<AssertionError> {
            "$-foo11.5%bar/".isInteger
        }
    }

    @JsName("IsDecimalWillThrowAssertionIfAStringIsNull")
    @Test
    fun `isDecimal will throw assertion if a string is null`() {
        shouldThrow<AssertionError> {
            null.isDecimal
        }
    }

    @JsName("IsDecimalWillThrowAssertionIfAStringIsAnInteger")
    @Test
    fun `isDecimal will throw assertion if a string is an integer`() {
        shouldThrow<AssertionError> {
            "42".isDecimal
        }
    }

    @JsName("IsDecimalWillNotThrowAssertionIfAStringIsADecimal")
    @Test
    fun `isDecimal will not throw assertion if a string is a decimal`() {
        "34211.535456".isDecimal
    }

    @JsName("IsDecimalWillThrowAssertionIfAStringContainsAnInteger")
    @Test
    fun `isDecimal will throw assertion if a string contains an integer`() {
        shouldThrow<AssertionError> {
            "$-foo11%bar/".isDecimal
        }
    }

    @JsName("IsDecimalWillThrowAssertionIfAStringContainsADecimal")
    @Test
    fun `isDecimal will throw assertion if a string contains a decimal`() {
        shouldThrow<AssertionError> {
            "$-foo11.5%bar/".isDecimal
        }
    }

    @JsName("ContainsNumericWillThrowAssertionIfAStringIsNull")
    @Test
    fun `containsNumeric will throw assertion if a string is null`() {
        shouldThrow<AssertionError> {
            null.containsNumeric
        }
    }

    @JsName("ContainsNumericWillNotThrowAssertionIfAStringIsAnInteger")
    @Test
    fun `containsNumeric will not throw assertion if a string is an integer`() {
        "42".containsNumeric
    }

    @JsName("ContainsNumericWillNotThrowAssertionIfAStringIsADecimal")
    @Test
    fun `containsNumeric will not throw assertion if a string is a decimal`() {
        "34211.535456".containsNumeric
    }

    @JsName("ContainsNumericWillNotThrowAssertionIfAStringContainsAnInteger")
    @Test
    fun `containsNumeric will not throw assertion if a string contains an integer`() {
        "$-foo11%bar/".containsNumeric
    }

    @JsName("ContainsNumericWillNotThrowAssertionIfAStringContainsADecimal")
    @Test
    fun `containsNumeric will not throw assertion if a string contains a decimal`() {
        "$-foo11.5%bar/".containsNumeric
    }

    @JsName("ContainsNumericWillThrowAssertionIfAStringNotContainsANumber")
    @Test
    fun `containsNumeric will throw assertion if a string not contains a number`() {
        shouldThrow<AssertionError> {
            "$-foo%bar/".containsNumeric
        }
    }

}

expect fun MatchersKtTest.mockDocElementListOfSize(mockSize: Int): List<DocElement>
expect fun MatchersKtTest.mockDocElement(mockIsPresent: Boolean, mockCssSelector: String = ".foo"): DocElement
