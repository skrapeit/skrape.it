package it.skrape.matchers

import it.skrape.selects.DocElement
import it.skrape.selects.isNotPresent
import it.skrape.selects.isPresent

infix fun Int.toBe(expected: Int) =
        this.apply { generalAssertion(this == expected, expected) }

infix fun String?.toBe(expected: String?) =
        this.apply { generalAssertion(this == expected, expected) }

infix fun String?.toBeNot(expected: String?) =
        this.apply { generalAssertion(this != expected, expected) }

infix fun String?.toContain(expected: String) =
        this.apply { generalAssertion("$this".contains(expected), expected) }

infix fun String?.toNotContain(expected: String) =
        this.apply { generalAssertion(!"$this".contains(expected), expected) }

val String?.isNumeric
    get() = this?.apply {
        generalAssertion(
            this.matches("^-?\\d+(\\.\\d+)?$".toRegex()), "'$this'", "is an integer or a decimal")
    }?: throw AssertionError("expect text to be a numeric but it was 'null'")

val String?.isInteger
    get() = this?.apply {
        generalAssertion(
            this.matches("^-?\\d+?\$".toRegex()), "'$this'", "is an integer")
    } ?: throw AssertionError("expect text to be an integer but it was 'null'")

val String?.isDecimal
    get() = this?.apply {
        generalAssertion(
            this.matches("^\\d+\\.\\d+\$".toRegex()), "'$this'", "is a decimal")
    } ?: throw AssertionError("expect text to be a decimal but it was 'null'")

val String?.containsNumeric
    get() = this?.apply {
        generalAssertion(
            this.matches(".*-?\\d+(\\.\\d+)?.*".toRegex()), "'$this'", "contains an integer or a decimal")
    }?: throw AssertionError("expect text to contain a numeric but it was 'null'")

infix fun List<Any>.toContain(expected: String) =
        this.apply { generalAssertion(this.contains(expected), expected) }

val List<DocElement>.toBePresent
    get() = this.apply {
        generalAssertion(
                isPresent,
                "${this::class}",
                "is present"
        )
    }

infix fun List<DocElement>.toBePresentTimes(amount: Int) =
        this.apply {
            generalAssertion(
                    size == amount,
                    "${this::class}",
                    "is present $amount times"
            )
        }

val List<DocElement>.toBePresentExactlyOnce
    get() = this toBePresentTimes 1

val List<DocElement>.toBePresentExactlyTwice
    get() = this toBePresentTimes 2

val DocElement.toBePresent
    get() = this.apply {
        generalAssertion(
                isPresent,
                "element '$cssSelector'",
                "is present"
        )
    }

val List<DocElement>.toBeNotPresent
    get() = this.apply {
        generalAssertion(
                isNotPresent,
                "${this::class}",
                "is not present"
        )
    }

val List<Any>.toBeEmpty
    get() = this.apply { generalAssertion(size == 0, "list", "is empty") }

val List<Any>.toBeNotEmpty
    get() = this.apply { generalAssertion(size > 0, "list", "is none empty") }
