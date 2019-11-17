package it.skrape.matchers

import it.skrape.SkrapeItAssertion
import it.skrape.selects.DocElement
import it.skrape.selects.DocElements
import strikt.api.expectThat
import strikt.assertions.*

@SkrapeItAssertion
infix fun Int.toBe(expected: Int): Int {
    expectThat(this).isEqualTo(expected)
    return this
}

@SkrapeItAssertion
infix fun Int.`to be`(expected: Int): Int = this toBe expected

@SkrapeItAssertion
infix fun String?.toBe(expected: String?): String? {
    expectThat(this).isEqualTo(expected)
    return this
}

@SkrapeItAssertion
infix fun String?.`to be`(expected: String?): String? = this toBe expected

@SkrapeItAssertion
infix fun String?.toBeNot(expected: String?): String? {
    expectThat(this).isNotEqualTo(expected)
    return this
}

@SkrapeItAssertion
infix fun String?.`to be not`(expected: String?): String? = this toBeNot expected

@SkrapeItAssertion
infix fun String?.toContain(expected: String): String? {
    expectThat(this.toString()).contains(expected)
    return this
}

@SkrapeItAssertion
infix fun String?.`to contain`(expected: String): String? = this toContain expected

@SkrapeItAssertion
infix fun String?.toNotContain(expected: String): String? {
    expectThat(this.toString()).not().contains(expected)
    return this
}

@SkrapeItAssertion
infix fun String?.`to not contain`(expected: String): String? = this toNotContain expected

@SkrapeItAssertion
infix fun List<Any>.toContain(expected: String): List<Any> {
    expectThat(this).contains(expected)
    return this
}

@SkrapeItAssertion
infix fun List<Any>.`to contain`(expected: String): List<Any> = this.toContain(expected)

@SkrapeItAssertion
fun DocElements.toBePresent() {
    expectThat(this.size).isGreaterThanOrEqualTo(1)
}

@SkrapeItAssertion
fun DocElements.toBePresentTimes(amount: Int) {
    expectThat(this.size).isEqualTo(amount)
}

@SkrapeItAssertion
fun DocElements.toBePresentExactlyOnce(): DocElements {
    toBePresentTimes(1)
    return this
}

@SkrapeItAssertion
fun DocElements.toBePresentExactlyTwice(): DocElements {
    toBePresentTimes(2)
    return this
}

@SkrapeItAssertion
fun DocElement.toBePresent(): DocElement {
    expectThat(this.isPresent()).isTrue()
    return this
}

@SkrapeItAssertion
fun DocElements.toBeNotPresent(): DocElements {
    expectThat(this.size).isEqualTo(0)
    return this
}
