package it.skrape.matchers

import it.skrape.selects.DocElement
import it.skrape.selects.isNotPresent
import it.skrape.selects.isPresent

infix fun Int.toBe(expected: Int): Int {
    generalAssertion(this == expected, expected)
    return this
}

@JvmName("to_be")
infix fun Int.`to be`(expected: Int): Int = this toBe expected

infix fun String?.toBe(expected: String?): String? {
    generalAssertion(this == expected, expected)
    return this
}

@JvmName("to_be")
infix fun String?.`to be`(expected: String?): String? = this toBe expected

infix fun String?.toBeNot(expected: String?): String? {
    generalAssertion(this != expected, expected)
    return this
}

@JvmName("to_be_not")
infix fun String?.`to be not`(expected: String?): String? = this toBeNot expected

infix fun String?.toContain(expected: String): String? {
    generalAssertion("$this".contains(expected), expected)
    return this
}

@JvmName("to_contain")
infix fun String?.`to contain`(expected: String): String? = this toContain expected

infix fun String?.toNotContain(expected: String): String? {
    generalAssertion(!"$this".contains(expected), expected)
    return this
}

@JvmName("to_not_contain")
infix fun String?.`to not contain`(expected: String): String? = this toNotContain expected

infix fun List<Any>.toContain(expected: String): List<Any> {
    generalAssertion(this.contains(expected), expected)
    return this
}

@JvmName("to_contain")
infix fun List<Any>.`to contain`(expected: String): List<Any> = this.toContain(expected)

val List<DocElement>.toBePresent
    get() = generalAssertion(
            isPresent,
            "${this::class}",
            "is present"
    ).let { this }

infix fun List<DocElement>.toBePresentTimes(amount: Int) = generalAssertion(
        size == amount,
        "${this::class}",
        "is present $amount times"
    ).let { this }

val List<DocElement>.toBePresentExactlyOnce
    get() = this toBePresentTimes 1

val List<DocElement>.toBePresentExactlyTwice
    get() = this toBePresentTimes 2

val DocElement.toBePresent
    get() = generalAssertion(
            isPresent,
            "element '$cssSelector'",
            "is present"
    ).let { this }

val List<DocElement>.toBeNotPresent
    get() = generalAssertion(
            isNotPresent,
            "${this::class}",
            "is not present"
    ).let { this }

val List<Any>.toBeEmpty
    get() = generalAssertion(size == 0, "list", "is empty").let { this }

val List<Any>.toBeNotEmpty
    get() = generalAssertion(size > 0, "list", "is none empty").let { this }
