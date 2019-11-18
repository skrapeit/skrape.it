package it.skrape.selects.html5

import it.skrape.aValidDocument
import it.skrape.matchers.toBePresentExactlyOnce
import it.skrape.matchers.toBePresentTimes
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.isEqualTo


internal class TextSemanticsSelectorsKtTest {

    @Test
    fun `can parse a-tag`() {
        val selector = aValidDocument().a {
            findFirst {
                expectThat(text).isEqualTo("i'm an anchor")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("a")
    }

    @Test
    fun `can parse abbr-tag`() {
        val selector = aValidDocument().abbr {
            findFirst {
                expectThat(text).isEqualTo("i'm an abbr")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("abbr")
    }

    @Test
    fun `can parse b-tag`() {
        val selector = aValidDocument().b {
            findFirst {
                expectThat(text).isEqualTo("i'm a bold text")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("b")
    }

    @Test
    fun `can parse bdi-tag`() {
        val selector = aValidDocument().bdi {
            findFirst {
                expectThat(text).isEqualTo("i'm a bdi")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("bdi")
    }

    @Test
    fun `can parse bdo-tag`() {
        val selector = aValidDocument().bdo {
            findFirst {
                expectThat(text).isEqualTo("i'm a bdo")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("bdo")
    }

    @Test
    fun `can parse br-tag`() {
        val selector = aValidDocument().br {
            findAll {
                toBePresentTimes(2)
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("br")
    }

    @Test
    fun `can parse cite-tag`() {
        val selector = aValidDocument().cite {
            findFirst {
                expectThat(text).isEqualTo("i'm a cite")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("cite")
    }

    @Test
    fun `can parse code-tag`() {
        val selector = aValidDocument().code {
            findFirst {
                expectThat(text).isEqualTo("i'm a code")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("code")
    }

    @Test
    fun `can parse data-tag`() {
        val selector = aValidDocument().data {
            findFirst {
                expectThat(text).isEqualTo("i'm a data")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("data")
    }

    @Test
    fun `can parse dfn-tag`() {
        val selector = aValidDocument().dfn {
            findFirst {
                expectThat(text).isEqualTo("i'm a dfn")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("dfn")
    }

    @Test
    fun `can parse em-tag`() {
        val selector = aValidDocument().em {
            findFirst {
                expectThat(text).isEqualTo("i'm a em")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("em")
    }

    @Test
    fun `can parse i-tag`() {
        val selector = aValidDocument().i {
            findFirst {
                expectThat(text).isEqualTo("i'm a i")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("i")
    }

    @Test
    fun `can parse kbd-tag`() {
        val selector = aValidDocument().kbd {
            findFirst {
                expectThat(text).isEqualTo("i'm a kbd")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("kbd")
    }

    @Test
    fun `can parse mark-tag`() {
        val selector = aValidDocument().mark {
            findFirst {
                expectThat(text).isEqualTo("i'm a mark")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("mark")
    }

    @Test
    fun `can parse q-tag`() {
        val selector = aValidDocument().q {
            findFirst {
                expectThat(text).isEqualTo("i'm a q")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("q")
    }

    @Test
    fun `can parse rb-tag`() {
        val selector = aValidDocument().rb {
            findFirst {
                expectThat(text).isEqualTo("i'm a rb")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("rb")
    }

    @Test
    fun `can parse rp-tag`() {
        val selector = aValidDocument().rp {
            findFirst {
                expectThat(text).isEqualTo("i'm a rp")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("rp")
    }

    @Test
    fun `can parse rt-tag`() {
        val selector = aValidDocument().rt {
            findFirst {
                expectThat(text).isEqualTo("i'm a rt")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("rt")
    }

    @Test
    fun `can parse rtc-tag`() {
        val selector = aValidDocument().rtc {
            findFirst {
                expectThat(text).isEqualTo("i'm a rtc")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("rtc")
    }

    @Test
    fun `can parse ruby-tag`() {
        val selector = aValidDocument().ruby {
            findFirst {
                expectThat(text).contains("i'm a ruby")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("ruby")
    }

    @Test
    fun `can parse s-tag`() {
        val selector = aValidDocument().s {
            findFirst {
                expectThat(text).isEqualTo("i'm a s")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("s")
    }

    @Test
    fun `can parse samp-tag`() {
        val selector = aValidDocument().samp {
            findFirst {
                expectThat(text).isEqualTo("i'm a samp")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("samp")
    }

    @Test
    fun `can parse small-tag`() {
        val selector = aValidDocument().small {
            findFirst {
                expectThat(text).isEqualTo("i'm a small")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("small")
    }

    @Test
    fun `can parse span-tag`() {
        val selector = aValidDocument().span {
            findAll {
                toBePresentTimes(6)
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("span")
    }

    @Test
    fun `can parse strong-tag`() {
        val selector = aValidDocument().strong {
            findFirst {
                expectThat(text).isEqualTo("i'm a strong")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("strong")
    }

    @Test
    fun `can parse sub-tag`() {
        val selector = aValidDocument().sub {
            findFirst {
                expectThat(text).isEqualTo("i'm a sub")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("sub")
    }

    @Test
    fun `can parse sup-tag`() {
        val selector = aValidDocument().sup {
            findFirst {
                expectThat(text).isEqualTo("i'm a sup")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("sup")
    }

    @Test
    fun `can parse time-tag`() {
        val selector = aValidDocument().time {
            findFirst {
                expectThat(text).isEqualTo("i'm a time")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("time")
    }

    @Test
    fun `can parse tt-tag`() {
        val selector = aValidDocument().tt {
            findFirst {
                expectThat(text).isEqualTo("i'm a tt")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("tt")
    }

    @Test
    fun `can parse u-tag`() {
        val selector = aValidDocument().u {
            findFirst {
                expectThat(text).isEqualTo("i'm a u")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("u")
    }

    @Test
    fun `can parse var-tag`() {
        val selector = aValidDocument().`var` {
            findFirst {
                expectThat(text).isEqualTo("i'm a var")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("var")
    }

    @Test
    fun `can parse wbr-tag`() {
        val selector = aValidDocument().wbr {
            findAll {
                toBePresentExactlyOnce()
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("wbr")
    }
}