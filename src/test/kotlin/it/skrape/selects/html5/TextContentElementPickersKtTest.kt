package it.skrape.selects.html5

import assertk.assertThat
import assertk.assertions.isEqualTo
import it.skrape.aValidResult
import it.skrape.selects.lastOccurrence
import org.junit.jupiter.api.Test

internal class TextContentElementPickersKtTest {

    @Test
    fun `can parse blockquote-tag`() {
        val result = aValidResult()
        result.blockquote {
            assertThat(text()).isEqualTo("i'm a quote")
        }
    }

    @Test
    fun `can parse dd-tag`() {
        val result = aValidResult()
        result.dd {
            assertThat(size).isEqualTo(2)
        }
    }

    @Test
    fun `can parse dir-tag`() {
        val result = aValidResult()
        result.dir {
            assertThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse dl-tag`() {
        val result = aValidResult()
        result.dl {
            assertThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse dt-tag`() {
        val result = aValidResult()
        result.dt {
            assertThat(size).isEqualTo(2)
        }
    }

    @Test
    fun `can parse figcaption-tag`() {
        val result = aValidResult()
        result.figcaption {
            assertThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse figure-tag`() {
        val result = aValidResult()
        result.figure {
            assertThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse hr-tag`() {
        val result = aValidResult()
        result.hr {
            assertThat(size).isEqualTo(2)
        }
    }

    @Test
    fun `can parse li-tag`() {
        val result = aValidResult()
        result.li {
            assertThat(size).isEqualTo(11)
        }
    }

    @Test
    fun `can parse ol-tag`() {
        val result = aValidResult()
        result.ol {
            assertThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse ul-tag`() {
        val result = aValidResult()
        result.ul {
            assertThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse p-tag`() {
        val result = aValidResult()
        result.p {
            lastOccurrence {
                assertThat(text()).isEqualTo("i'm the last paragraph")
            }
        }
    }

    @Test
    fun `can parse pre-tag`() {
        val result = aValidResult()
        result.pre {
            assertThat(text()).isEqualTo("i'm a pre")
        }
    }
}