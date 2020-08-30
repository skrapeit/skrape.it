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

val List<DocElement>.isNumeric
    get() = this.forEach { it.text.matches("-?\\d+(\\.\\d+)?".toRegex()) }

val List<Any>.toBeEmpty
    get() = this.apply { generalAssertion(size == 0, "list", "is empty") }

val List<Any>.toBeNotEmpty
    get() = this.apply { generalAssertion(size > 0, "list", "is none empty") }
