package it.skrape.matchers

import it.skrape.selects.DocElement
import it.skrape.selects.isNotPresent
import it.skrape.selects.isPresent

public infix fun Int.toBe(expected: Int): Int =
        this.apply { generalAssertion(this == expected, expected) }

public infix fun String?.toBe(expected: String?): String? =
        this.apply { generalAssertion(this == expected, expected) }

public infix fun String?.toBeNot(expected: String?): String? =
        this.apply { generalAssertion(this != expected, expected) }

public infix fun String?.toContain(expected: String): String? =
        this.apply { generalAssertion("$this".contains(expected), expected) }

public infix fun String?.toNotContain(expected: String): String? =
        this.apply { generalAssertion(!"$this".contains(expected), expected) }

public val String?.isNumeric: String
    get() = this?.apply {
        generalAssertion(
            this.matches("^-?\\d+(\\.\\d+)?$".toRegex()), "'$this'", "is an integer or a decimal")
    }?: throw AssertionError("expect text to be a numeric but it was 'null'")

public val String?.isInteger: String
    get() = this?.apply {
        generalAssertion(
            this.matches("^-?\\d+?\$".toRegex()), "'$this'", "is an integer")
    } ?: throw AssertionError("expect text to be an integer but it was 'null'")

public val String?.isDecimal: String
    get() = this?.apply {
        generalAssertion(
            this.matches("^\\d+\\.\\d+\$".toRegex()), "'$this'", "is a decimal")
    } ?: throw AssertionError("expect text to be a decimal but it was 'null'")

public val String?.containsNumeric: String
    get() = this?.apply {
        generalAssertion(
            this.matches(".*-?\\d+(\\.\\d+)?.*".toRegex()), "'$this'", "contains an integer or a decimal")
    }?: throw AssertionError("expect text to contain a numeric but it was 'null'")

public infix fun List<Any>.toContain(expected: String): List<Any> =
        this.apply { generalAssertion(this.contains(expected), expected) }

public val List<DocElement>.toBePresent: List<DocElement>
    get() = this.apply {
        generalAssertion(
                isPresent,
                "${this::class}",
                "is present"
        )
    }

public infix fun List<DocElement>.toBePresentTimes(amount: Int): List<DocElement> =
        this.apply {
            generalAssertion(
                    size == amount,
                    "${this::class}",
                    "is present $amount times"
            )
        }

public val List<DocElement>.toBePresentExactlyOnce: List<DocElement>
    get() = this toBePresentTimes 1

public val List<DocElement>.toBePresentExactlyTwice: List<DocElement>
    get() = this toBePresentTimes 2

public val DocElement.toBePresent: DocElement
    get() = this.apply {
        generalAssertion(
                isPresent,
                "element '$cssSelector'",
                "is present"
        )
    }

public val List<DocElement>.toBeNotPresent: List<DocElement>
    get() = this.apply {
        generalAssertion(
                isNotPresent,
                "${this::class}",
                "is not present"
        )
    }

public val List<Any>.toBeEmpty: List<Any>
    get() = this.apply { generalAssertion(size == 0, "list", "is empty") }

public val List<Any>.toBeNotEmpty: List<Any>
    get() = this.apply { generalAssertion(size > 0, "list", "is none empty") }
