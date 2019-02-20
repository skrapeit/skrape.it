package it.skrape.matchers

import org.assertj.core.api.KotlinAssertions.assertThat


infix fun Int.toBe(expected: Int) {
    assertThat(this).isEqualTo(expected)
}

infix fun String.toBe(expected: String) {
    assertThat(this).isEqualTo(expected)
}
