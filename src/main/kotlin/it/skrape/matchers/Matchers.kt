package it.skrape.matchers

import assertk.assertThat
import assertk.assertions.*
import it.skrape.SkrapeItAssertion
import it.skrape.selects.DocElement
import it.skrape.selects.DocElements
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNotEqualTo

@SkrapeItAssertion
infix fun Int.toBe(expected: Int): Int {
    expectThat(this).isEqualTo(expected)
    return this
}

@SkrapeItAssertion
infix fun Int.`to be`(expected: Int) = this toBe expected

@SkrapeItAssertion
infix fun String?.toBe(expected: String?): String? {
    expectThat(this).isEqualTo(expected)
    return this
}

@SkrapeItAssertion
infix fun String?.`to be`(expected: String?) = this toBe expected

@SkrapeItAssertion
infix fun String?.toBeNot(expected: String?): String? {
    expectThat(this).isNotEqualTo(expected)
    return this
}

@SkrapeItAssertion
infix fun String?.`to be not`(expected: String?) = this toBeNot expected

@SkrapeItAssertion
infix fun String?.toContain(expected: String): String? {
    assertThat(this.toString()).contains(expected)
    return this
}

@SkrapeItAssertion
infix fun String?.`to contain`(expected: String) = this toContain expected

@SkrapeItAssertion
infix fun String?.toNotContain(expected: String): String? {
    assertThat(this.toString()).doesNotContain(expected)
    return this
}

@SkrapeItAssertion
infix fun String?.`to not contain`(expected: String) = this toNotContain expected

@SkrapeItAssertion
infix fun List<Any>.toContain(expected: String): List<Any> {
    assertThat(this).contains(expected)
    return this
}

@SkrapeItAssertion
infix fun List<Any>.`to contain`(expected: String) = this.toContain(expected)

@SkrapeItAssertion
fun DocElements.toBePresent() {
    assertThat(this.size).isGreaterThanOrEqualTo(1)
}

@SkrapeItAssertion
fun DocElements.toBePresentTimes(amount: Int) {
    assertThat(this.size).isEqualTo(amount)
}

@SkrapeItAssertion
fun DocElements.toBePresentExactlyOnce() {
    toBePresentTimes(1)
}

@SkrapeItAssertion
fun DocElements.toBePresentExactlyTwice() {
    toBePresentTimes(2)
}

@SkrapeItAssertion
fun DocElement.toBePresent() {
    assertThat(this.isPresent()).isTrue()
}

@SkrapeItAssertion
fun DocElements.toBeNotPresent() {
    assertThat(this.size).isZero()
}
