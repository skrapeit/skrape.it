package it.skrape.matchers

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.doesNotContain
import assertk.assertions.isEqualTo
import assertk.assertions.isGreaterThanOrEqualTo
import assertk.assertions.isNotEqualTo
import assertk.assertions.isZero
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNotEqualTo

infix fun Int.toBe(expected: Int): Int {
    expectThat(this).isEqualTo(expected)
    return this
}

infix fun Int.`to be`(expected: Int) = this toBe expected

infix fun String?.toBe(expected: String?): String? {
    expectThat(this).isEqualTo(expected)
    return this
}

infix fun String?.`to be`(expected: String?) = this toBe expected

infix fun String?.toBeNot(expected: String?): String? {
    expectThat(this).isNotEqualTo(expected)
    return this
}

infix fun String?.`to be not`(expected: String?) = this toBeNot expected

infix fun String?.toContain(expected: String): String? {
    assertThat(this.toString()).contains(expected)
    return this
}

infix fun String?.`to contain`(expected: String) = this toContain expected

infix fun String?.toNotContain(expected: String): String? {
    assertThat(this.toString()).doesNotContain(expected)
    return this
}

infix fun String?.`to not contain`(expected: String) = this toNotContain expected

infix fun List<Any>.toContain(expected: String): List<Any> {
    assertThat(this).contains(expected)
    return this
}

fun Elements.toBePresent() {
    assertThat(this.size).isGreaterThanOrEqualTo(1)
}

fun Elements.toBePresentTimes(amount: Int) {
    assertThat(this.size).isEqualTo(amount)
}

fun Elements.toBePresentExactlyOnce() {
    toBePresentTimes(1)
}

fun Elements.toBePresentExactlyTwice() {
    toBePresentTimes(2)
}

fun Element.toBePresent() {
    assertThat(this.allElements.size).isGreaterThanOrEqualTo(1)
}

fun Elements.toBeNotPresent() {
    assertThat(this.size).isZero()
}

infix fun List<Any>.`to contain`(expected: String) = this.toContain(expected)
