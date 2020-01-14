package it.skrape.matchers

import it.skrape.selects.DocElement
import it.skrape.selects.DocElements
import strikt.api.expectThat
import strikt.assertions.*

infix fun Int.toBe(expected: Int): Int {
    expectThat(this).isEqualTo(expected)
    return this
}

infix fun Int.`to be`(expected: Int): Int = this toBe expected

infix fun String?.toBe(expected: String?): String? {
    expectThat(this).isEqualTo(expected)
    return this
}

infix fun String?.`to be`(expected: String?): String? = this toBe expected

infix fun String?.toBeNot(expected: String?): String? {
    expectThat(this).isNotEqualTo(expected)
    return this
}

infix fun String?.`to be not`(expected: String?): String? = this toBeNot expected

infix fun String?.toContain(expected: String): String? {
    expectThat(this.toString()).contains(expected)
    return this
}

infix fun String?.`to contain`(expected: String): String? = this toContain expected

infix fun String?.toNotContain(expected: String): String? {
    expectThat(this.toString()).not().contains(expected)
    return this
}

infix fun String?.`to not contain`(expected: String): String? = this toNotContain expected

infix fun List<Any>.toContain(expected: String): List<Any> {
    expectThat(this).contains(expected)
    return this
}

infix fun List<Any>.`to contain`(expected: String): List<Any> = this.toContain(expected)

val DocElements.toBePresent
    get() = expectThat(this.size).isGreaterThanOrEqualTo(1).let { this }

infix fun DocElements.toBePresentTimes(amount: Int) =
        expectThat(this.size).isEqualTo(amount).let { this }

val DocElements.toBePresentExactlyOnce
    get() = this toBePresentTimes 1

val DocElements.toBePresentExactlyTwice
    get() = this toBePresentTimes 2

val DocElement.toBePresent
    get() = expectThat(this.isPresent).isTrue().let { this }

val DocElements.toBeNotPresent
    get() = expectThat(this.size).isEqualTo(0).let { this }
