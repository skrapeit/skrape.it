package it.skrape.selects

import assertk.assertThat
import assertk.assertions.isEqualTo
import it.skrape.aValidDocument
import it.skrape.selects.html5.p
import org.junit.jupiter.api.Test

internal class ElementExtractorsKtTest {

    private val document = aValidDocument()

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
        document.p {
            firstOccurrence {
                assertThat(text()).isEqualTo("i'm a paragraph")
            }
        }
    }

    @Test
    fun `can pick elements secondOccurrence`() {
        document.p {
            secondOccurrence {
                assertThat(text()).isEqualTo("i'm a second paragraph")
            }
        }
    }

    @Test
    fun `can pick elements thirdOccurrence`() {
        document.p {
            thirdOccurrence {
                assertThat(text()).isEqualTo("i'm a paragraph with word break")
            }
        }
    }

    @Test
    fun `can pick elements lastOccurrence`() {
        document.p {
            lastOccurrence {
                assertThat(text()).isEqualTo("i'm the last paragraph")
            }
        }
    }

    @Test
    fun `can pick elements secondlastOccurrence`() {
        document.p {
            secondLastOccurrence {
                assertThat(text()).isEqualTo("i'm a paragraph with word break")
            }
        }
    }
}