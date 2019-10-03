package it.skrape.selects.html5

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import it.skrape.aValidResult
import org.junit.jupiter.api.Test


internal class TextSemanticsElementPickersKtTest {

    @Test
    fun `can parse a-tag`() {
        val result = aValidResult()
        result.a {
            assertThat(text()).isEqualTo("i'm an anchor")
        }
    }

    @Test
    fun `can parse abbr-tag`() {
        val result = aValidResult()
        result.abbr {
            assertThat(text()).isEqualTo("i'm an abbr")
        }
    }

    @Test
    fun `can parse b-tag`() {
        val result = aValidResult()
        result.b {
            assertThat(text()).isEqualTo("i'm a bold text")
        }
    }

    @Test
    fun `can parse bdi-tag`() {
        val result = aValidResult()
        result.bdi {
            assertThat(text()).isEqualTo("i'm a bdi")
        }
    }

    @Test
    fun `can parse bdo-tag`() {
        val result = aValidResult()
        result.bdo {
            assertThat(text()).isEqualTo("i'm a bdo")
        }
    }

    @Test
    fun `can parse br-tag`() {
        val result = aValidResult()
        result.br {
            assertThat(size).isEqualTo(2)
        }
    }

    @Test
    fun `can parse cite-tag`() {
        val result = aValidResult()
        result.cite {
            assertThat(text()).isEqualTo("i'm a cite")
        }
    }

    @Test
    fun `can parse code-tag`() {
        val result = aValidResult()
        result.code {
            assertThat(text()).isEqualTo("i'm a code")
        }
    }

    @Test
    fun `can parse data-tag`() {
        val result = aValidResult()
        result.data {
            assertThat(text()).isEqualTo("i'm a data")
        }
    }

    @Test
    fun `can parse dfn-tag`() {
        val result = aValidResult()
        result.dfn {
            assertThat(text()).isEqualTo("i'm a dfn")
        }
    }

    @Test
    fun `can parse em-tag`() {
        val result = aValidResult()
        result.em {
            assertThat(text()).isEqualTo("i'm a em")
        }
    }

    @Test
    fun `can parse i-tag`() {
        val result = aValidResult()
        result.i {
            assertThat(text()).isEqualTo("i'm a i")
        }
    }

    @Test
    fun `can parse kbd-tag`() {
        val result = aValidResult()
        result.kbd {
            assertThat(text()).isEqualTo("i'm a kbd")
        }
    }

    @Test
    fun `can parse mark-tag`() {
        val result = aValidResult()
        result.mark {
            assertThat(text()).isEqualTo("i'm a mark")
        }
    }

    @Test
    fun `can parse q-tag`() {
        val result = aValidResult()
        result.q {
            assertThat(text()).isEqualTo("i'm a q")
        }
    }

    @Test
    fun `can parse rb-tag`() {
        val result = aValidResult()
        result.rb {
            assertThat(text()).isEqualTo("i'm a rb")
        }
    }

    @Test
    fun `can parse rp-tag`() {
        val result = aValidResult()
        result.rp {
            assertThat(text()).isEqualTo("i'm a rp")
        }
    }

    @Test
    fun `can parse rt-tag`() {
        val result = aValidResult()
        result.rt {
            assertThat(text()).isEqualTo("i'm a rt")
        }
    }

    @Test
    fun `can parse rtc-tag`() {
        val result = aValidResult()
        result.rtc {
            assertThat(text()).isEqualTo("i'm a rtc")
        }
    }

    @Test
    fun `can parse ruby-tag`() {
        val result = aValidResult()
        result.ruby {
            assertThat(text()).contains("i'm a ruby")
        }
    }

    @Test
    fun `can parse s-tag`() {
        val result = aValidResult()
        result.s {
            assertThat(text()).isEqualTo("i'm a s")
        }
    }

    @Test
    fun `can parse samp-tag`() {
        val result = aValidResult()
        result.samp {
            assertThat(text()).isEqualTo("i'm a samp")
        }
    }

    @Test
    fun `can parse small-tag`() {
        val result = aValidResult()
        result.small {
            assertThat(text()).isEqualTo("i'm a small")
        }
    }

    @Test
    fun `can parse span-tag`() {
        val result = aValidResult()
        result.span {
            assertThat(size).isEqualTo(6)
        }
    }

    @Test
    fun `can parse strong-tag`() {
        val result = aValidResult()
        result.strong {
            assertThat(text()).isEqualTo("i'm a strong")
        }
    }

    @Test
    fun `can parse sub-tag`() {
        val result = aValidResult()
        result.sub {
            assertThat(text()).isEqualTo("i'm a sub")
        }
    }

    @Test
    fun `can parse sup-tag`() {
        val result = aValidResult()
        result.sup {
            assertThat(text()).isEqualTo("i'm a sup")
        }
    }

    @Test
    fun `can parse time-tag`() {
        val result = aValidResult()
        result.time {
            assertThat(text()).isEqualTo("i'm a time")
        }
    }

    @Test
    fun `can parse tt-tag`() {
        val result = aValidResult()
        result.tt {
            assertThat(text()).isEqualTo("i'm a tt")
        }
    }

    @Test
    fun `can parse u-tag`() {
        val result = aValidResult()
        result.u {
            assertThat(text()).isEqualTo("i'm a u")
        }
    }

    @Test
    fun `can parse var-tag`() {
        val result = aValidResult()
        result.`var` {
            assertThat(text()).isEqualTo("i'm a var")
        }
    }

    @Test
    fun `can parse wbr-tag`() {
        val result = aValidResult()
        result.wbr {
            assertThat(size).isEqualTo(1)
        }
    }
}