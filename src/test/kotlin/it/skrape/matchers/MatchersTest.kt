package it.skrape.matchers

import org.junit.jupiter.api.Test

internal class MatchersTest {

    private val anInt = 200
    private val aString = "test"

    @Test
    internal fun `toBe is working type safe for Int`() {
        anInt toBe 200
    }

    @Test
    internal fun `toBe is working type safe for String`() {
        aString toBe "test"
    }
}