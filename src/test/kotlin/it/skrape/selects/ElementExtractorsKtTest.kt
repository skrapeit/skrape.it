package it.skrape.selects

import assertk.assertThat
import assertk.assertions.isEqualTo
import it.skrape.aValidResult
import it.skrape.selects.html5.p
import org.junit.jupiter.api.Test

internal class ElementExtractorsKtTest {

    private val result = aValidResult()

    @Test
    fun `can pick element from element`() {
        // TODO
    }

    @Test
    fun `can pick elements from element`() {
        // TODO
    }

    @Test
    fun `can pick element from elements`() {
        // TODO
    }

    @Test
    fun `can pick elements from elements`() {
        // TODO
    }

    @Test
    fun `can pick elements firstOccurrence`() {
        result.p {
            firstOccurrence {
                assertThat(text()).isEqualTo("i'm a paragraph")
            }
        }
    }

    @Test
    fun `can pick elements secondOccurrence`() {
        result.p {
            secondOccurrence {
                assertThat(text()).isEqualTo("i'm a second paragraph")
            }
        }
    }

    @Test
    fun `can pick elements thirdOccurrence`() {
        result.p {
            thirdOccurrence {
                assertThat(text()).isEqualTo("i'm a paragraph with word break")
            }
        }
    }

    @Test
    fun `can pick elements lastOccurrence`() {
        result.p {
            lastOccurrence {
                assertThat(text()).isEqualTo("i'm the last paragraph")
            }
        }
    }

    @Test
    fun `can pick elements secondlastOccurrence`() {
        result.p {
            secondLastOccurrence {
                assertThat(text()).isEqualTo("i'm a paragraph with word break")
            }
        }
    }
}