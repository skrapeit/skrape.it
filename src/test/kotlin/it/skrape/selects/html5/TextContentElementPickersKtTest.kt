package it.skrape.selects.html5

import it.skrape.aValidDocument
import it.skrape.selects.lastOccurrence
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class TextContentElementPickersKtTest {

    @Test
    fun `can parse blockquote-tag`() {
        val result = aValidDocument()
        result.blockquote {
            expectThat(text()).isEqualTo("i'm a quote")
        }
    }

    @Test
    fun `can parse dd-tag`() {
        val result = aValidDocument()
        result.dd {
            expectThat(size).isEqualTo(2)
        }
    }

    @Test
    fun `can parse dir-tag`() {
        val result = aValidDocument()
        result.dir {
            expectThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse dl-tag`() {
        val result = aValidDocument()
        result.dl {
            expectThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse dt-tag`() {
        val result = aValidDocument()
        result.dt {
            expectThat(size).isEqualTo(2)
        }
    }

    @Test
    fun `can parse figcaption-tag`() {
        val result = aValidDocument()
        result.figcaption {
            expectThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse figure-tag`() {
        val result = aValidDocument()
        result.figure {
            expectThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse hr-tag`() {
        val result = aValidDocument()
        result.hr {
            expectThat(size).isEqualTo(2)
        }
    }

    @Test
    fun `can parse li-tag`() {
        val result = aValidDocument()
        result.li {
            expectThat(size).isEqualTo(11)
        }
    }

    @Test
    fun `can parse ol-tag`() {
        val result = aValidDocument()
        result.ol {
            expectThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse ul-tag`() {
        val result = aValidDocument()
        result.ul {
            expectThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse p-tag`() {
        val result = aValidDocument()
        result.p {
            lastOccurrence {
                expectThat(text()).isEqualTo("i'm the last paragraph")
            }
        }
    }

    @Test
    fun `can parse pre-tag`() {
        val result = aValidDocument()
        result.pre {
            expectThat(text()).isEqualTo("i'm a pre")
        }
    }
}