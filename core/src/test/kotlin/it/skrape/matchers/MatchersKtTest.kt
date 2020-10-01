package it.skrape.matchers

import io.mockk.every
import io.mockk.mockk
import it.skrape.selects.DocElement
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MatchersKtTest {

    private val anInt = 200
    private val aString = "test"
    private val aNullableString: String? = null

    @Test
    fun `toBe is working type safe for Int`() {
        anInt toBe 200
    }

    @Test
    fun `toBe is working type safe for String`() {
        aString toBe "test"
    }

    @Test
    fun `toBe can handle expected is null`() {
        Assertions.assertThrows(AssertionError::class.java) {
            "null" toBe null
        }
    }

    @Test
    fun `toBe can handle actual is null`() {
        Assertions.assertThrows(AssertionError::class.java) {
            aNullableString toBe "foo"
        }
    }

    @Test
    fun `toBe is working type safe for null String`() {
        aNullableString toBe null
    }

    @Test
    fun `toNotBe is working type safe for null String`() {
        aString toBeNot null
    }

    @Test
    fun `toContain is working type safe for String`() {
        aString toContain "es"
    }

    @Test
    fun `toContain is throwing exception on assertion failure`() {
        Assertions.assertThrows(AssertionError::class.java) {
            aString toContain "foo"
        }
    }

    @Test
    fun `toNotContain is working type safe for String`() {
        aString toNotContain "foo"
    }

    @Test
    fun `toNotContain is throwing exception on assertion failure`() {
        Assertions.assertThrows(AssertionError::class.java) {
            aString toNotContain "es"
        }
    }

    @Test
    fun `toContain is working with lists`() {
        listOf("foo", "bar") toContain "bar"
    }

    @Test
    fun `toContain on lists is throwing exception on assertion failure`() {
        Assertions.assertThrows(AssertionError::class.java) {
            listOf("foo", "bar") toContain "schnitzel"
        }
    }

    @Test
    fun `toBePresent can handle multiple presents of matching ELEMENTS`() {
        val elements = mockk<List<DocElement>> { every { size } returns 2 }
        elements.toBePresent
    }

    @Test
    fun `toBePresent can handle single occurrence of matching ELEMENTS`() {
        val elements = mockk<List<DocElement>> { every { size } returns 1 }
        elements.toBePresent
    }

    @Test
    fun `toBePresent is throwing exception if no ELEMENTS matches`() {
        val elements = mockk<List<DocElement>> { every { size } returns 0 }
        Assertions.assertThrows(AssertionError::class.java) {
            elements.toBePresent
        }
    }

    @Test
    fun `toBePresent can handle multiple occurrence of an ELEMENT`() {
        val element = mockk<DocElement> {
            every { isPresent } returns true
            every { cssSelector } returns ".foo"
        }
        element.toBePresent
    }

    @Test
    fun `toBePresent can handle single occurrence of an ELEMENT`() {
        val element = mockk<DocElement> {
            every { isPresent } returns true
            every { cssSelector } returns ".foo"
        }
        element.toBePresent
    }

    @Test
    fun `toBePresent is throwing exception if no ELEMENT matches`() {
        val element = mockk<DocElement> {
            every { isPresent } returns false
            every { cssSelector } returns ".foo"
        }
        Assertions.assertThrows(AssertionError::class.java) {
            element.toBePresent
        }
    }

    @Test
    fun `toBeNotPresent can handle multiple presents of matching ELEMENTS`() {
        val elements = mockk<List<DocElement>> { every { size } returns 2 }
        elements.toBePresent
    }

    @Test
    fun `toBeNotPresent can handle non existent ELEMENTS`() {
        val elements = mockk<List<DocElement>> { every { size } returns 0 }
        elements.toBeNotPresent
    }

    @Test
    fun `toBeNotPresent is throwing exception on single occurrence of matching ELEMENTS`() {
        val elements = mockk<List<DocElement>> { every { size } returns 1 }
        Assertions.assertThrows(AssertionError::class.java) {
            elements.toBeNotPresent
        }
    }

    @Test
    fun `toBeNotPresent is throwing exception on multiple presents of matching ELEMENTS`() {
        val elements = mockk<List<DocElement>> { every { size } returns 2 }
        Assertions.assertThrows(AssertionError::class.java) {
            elements.toBeNotPresent
        }
    }

    @Test
    fun `toBeEmpty can handle empty list`() {
        emptyList<Any>().toBeEmpty
    }

    @Test
    fun `toBeEmpty is throwing exception NON empty list`() {
        Assertions.assertThrows(AssertionError::class.java) {
            listOf(1, 2, 3).toBeEmpty
        }
    }

    @Test
    fun `toBeNotEmpty can handle NON empty list`() {
        listOf(1, 2, 3).toBeNotEmpty
    }

    @Test
    fun `toBeNotEmpty is throwing exception empty list`() {
        Assertions.assertThrows(AssertionError::class.java) {
            emptyList<Any>().toBeNotEmpty
        }
    }

    @Test
    fun `isNumeric will throw assertion if a string is null`() {
        Assertions.assertThrows(AssertionError::class.java) {
            null.isNumeric
        }
    }

    @Test
    fun `isNumeric will not throw assertion if a string is an integer`() {
        "42".isNumeric
    }

    @Test
    fun `isNumeric will not throw assertion if a string is a decimal`() {
        "34211.535456".isNumeric
    }

    @Test
    fun `isNumeric will throw assertion if a string contains an integer`() {
        Assertions.assertThrows(AssertionError::class.java) {
            "$-foo11%bar/".isNumeric
        }
    }

    @Test
    fun `isNumeric will throw assertion if a string contains a decimal`() {
        Assertions.assertThrows(AssertionError::class.java) {
            "$-foo11.5%bar/".isNumeric
        }
    }

    @Test
    fun `isInteger will throw assertion if a string is null`() {
        Assertions.assertThrows(AssertionError::class.java) {
            null.isInteger
        }
    }

    @Test
    fun `isInteger will not throw assertion if a string is an integer`() {
        "42".isInteger
    }

    @Test
    fun `isInteger will throw assertion if a string is a decimal`() {
        Assertions.assertThrows(AssertionError::class.java) {
            "34211.535456".isInteger
        }
    }

    @Test
    fun `isInteger will throw assertion if a string contains an integer`() {
        Assertions.assertThrows(AssertionError::class.java) {
            "$-foo11%bar/".isInteger
        }
    }

    @Test
    fun `isInteger will throw assertion if a string contains a decimal`() {
        Assertions.assertThrows(AssertionError::class.java) {
            "$-foo11.5%bar/".isInteger
        }
    }

    @Test
    fun `isDecimal will throw assertion if a string is null`() {
        Assertions.assertThrows(AssertionError::class.java) {
            null.isDecimal
        }
    }

    @Test
    fun `isDecimal will throw assertion if a string is an integer`() {
        Assertions.assertThrows(AssertionError::class.java) {
            "42".isDecimal
        }
    }

    @Test
    fun `isDecimal will not throw assertion if a string is a decimal`() {
        "34211.535456".isDecimal
    }

    @Test
    fun `isDecimal will throw assertion if a string contains an integer`() {
        Assertions.assertThrows(AssertionError::class.java) {
            "$-foo11%bar/".isDecimal
        }
    }

    @Test
    fun `isDecimal will throw assertion if a string contains a decimal`() {
        Assertions.assertThrows(AssertionError::class.java) {
            "$-foo11.5%bar/".isDecimal
        }
    }

    @Test
    fun `containsNumeric will throw assertion if a string is null`() {
        Assertions.assertThrows(AssertionError::class.java) {
            null.containsNumeric
        }
    }

    @Test
    fun `containsNumeric will not throw assertion if a string is an integer`() {
        "42".containsNumeric
    }

    @Test
    fun `containsNumeric will not throw assertion if a string is a decimal`() {
        "34211.535456".containsNumeric
    }

    @Test
    fun `containsNumeric will not throw assertion if a string contains an integer`() {
        "$-foo11%bar/".containsNumeric
    }

    @Test
    fun `containsNumeric will not throw assertion if a string contains a decimal`() {
        "$-foo11.5%bar/".containsNumeric
    }

    @Test
    fun `containsNumeric will throw assertion if a string not contains a number`() {
        Assertions.assertThrows(AssertionError::class.java) {
            "$-foo%bar/".containsNumeric
        }
    }
}
