package it.skrape.selects.html5

import it.skrape.aValidDocument
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.isEqualTo


internal class TextSemanticsElementPickersKtTest {

    @Test
    fun `can parse a-tag`() {
        val result = aValidDocument()
        result.a {
            expectThat(text()).isEqualTo("i'm an anchor")
        }
    }

    @Test
    fun `can parse abbr-tag`() {
        val result = aValidDocument()
        result.abbr {
            expectThat(text()).isEqualTo("i'm an abbr")
        }
    }

    @Test
    fun `can parse b-tag`() {
        val result = aValidDocument()
        result.b {
            expectThat(text()).isEqualTo("i'm a bold text")
        }
    }

    @Test
    fun `can parse bdi-tag`() {
        val result = aValidDocument()
        result.bdi {
            expectThat(text()).isEqualTo("i'm a bdi")
        }
    }

    @Test
    fun `can parse bdo-tag`() {
        val result = aValidDocument()
        result.bdo {
            expectThat(text()).isEqualTo("i'm a bdo")
        }
    }

    @Test
    fun `can parse br-tag`() {
        val result = aValidDocument()
        result.br {
            expectThat(size).isEqualTo(2)
        }
    }

    @Test
    fun `can parse cite-tag`() {
        val result = aValidDocument()
        result.cite {
            expectThat(text()).isEqualTo("i'm a cite")
        }
    }

    @Test
    fun `can parse code-tag`() {
        val result = aValidDocument()
        result.code {
            expectThat(text()).isEqualTo("i'm a code")
        }
    }

    @Test
    fun `can parse data-tag`() {
        val result = aValidDocument()
        result.data {
            expectThat(text()).isEqualTo("i'm a data")
        }
    }

    @Test
    fun `can parse dfn-tag`() {
        val result = aValidDocument()
        result.dfn {
            expectThat(text()).isEqualTo("i'm a dfn")
        }
    }

    @Test
    fun `can parse em-tag`() {
        val result = aValidDocument()
        result.em {
            expectThat(text()).isEqualTo("i'm a em")
        }
    }

    @Test
    fun `can parse i-tag`() {
        val result = aValidDocument()
        result.i {
            expectThat(text()).isEqualTo("i'm a i")
        }
    }

    @Test
    fun `can parse kbd-tag`() {
        val result = aValidDocument()
        result.kbd {
            expectThat(text()).isEqualTo("i'm a kbd")
        }
    }

    @Test
    fun `can parse mark-tag`() {
        val result = aValidDocument()
        result.mark {
            expectThat(text()).isEqualTo("i'm a mark")
        }
    }

    @Test
    fun `can parse q-tag`() {
        val result = aValidDocument()
        result.q {
            expectThat(text()).isEqualTo("i'm a q")
        }
    }

    @Test
    fun `can parse rb-tag`() {
        val result = aValidDocument()
        result.rb {
            expectThat(text()).isEqualTo("i'm a rb")
        }
    }

    @Test
    fun `can parse rp-tag`() {
        val result = aValidDocument()
        result.rp {
            expectThat(text()).isEqualTo("i'm a rp")
        }
    }

    @Test
    fun `can parse rt-tag`() {
        val result = aValidDocument()
        result.rt {
            expectThat(text()).isEqualTo("i'm a rt")
        }
    }

    @Test
    fun `can parse rtc-tag`() {
        val result = aValidDocument()
        result.rtc {
            expectThat(text()).isEqualTo("i'm a rtc")
        }
    }

    @Test
    fun `can parse ruby-tag`() {
        val result = aValidDocument()
        result.ruby {
            expectThat(text()).contains("i'm a ruby")
        }
    }

    @Test
    fun `can parse s-tag`() {
        val result = aValidDocument()
        result.s {
            expectThat(text()).isEqualTo("i'm a s")
        }
    }

    @Test
    fun `can parse samp-tag`() {
        val result = aValidDocument()
        result.samp {
            expectThat(text()).isEqualTo("i'm a samp")
        }
    }

    @Test
    fun `can parse small-tag`() {
        val result = aValidDocument()
        result.small {
            expectThat(text()).isEqualTo("i'm a small")
        }
    }

    @Test
    fun `can parse span-tag`() {
        val result = aValidDocument()
        result.span {
            expectThat(size).isEqualTo(6)
        }
    }

    @Test
    fun `can parse strong-tag`() {
        val result = aValidDocument()
        result.strong {
            expectThat(text()).isEqualTo("i'm a strong")
        }
    }

    @Test
    fun `can parse sub-tag`() {
        val result = aValidDocument()
        result.sub {
            expectThat(text()).isEqualTo("i'm a sub")
        }
    }

    @Test
    fun `can parse sup-tag`() {
        val result = aValidDocument()
        result.sup {
            expectThat(text()).isEqualTo("i'm a sup")
        }
    }

    @Test
    fun `can parse time-tag`() {
        val result = aValidDocument()
        result.time {
            expectThat(text()).isEqualTo("i'm a time")
        }
    }

    @Test
    fun `can parse tt-tag`() {
        val result = aValidDocument()
        result.tt {
            expectThat(text()).isEqualTo("i'm a tt")
        }
    }

    @Test
    fun `can parse u-tag`() {
        val result = aValidDocument()
        result.u {
            expectThat(text()).isEqualTo("i'm a u")
        }
    }

    @Test
    fun `can parse var-tag`() {
        val result = aValidDocument()
        result.`var` {
            expectThat(text()).isEqualTo("i'm a var")
        }
    }

    @Test
    fun `can parse wbr-tag`() {
        val result = aValidDocument()
        result.wbr {
            expectThat(size).isEqualTo(1)
        }
    }
}