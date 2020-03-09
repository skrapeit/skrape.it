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
            toCssSelector
        }

        expectThat(selector).isEqualTo("a")
    }

    @Test
    fun `can parse abbr-tag`() {
        val selector = aValidDocument().abbr {
            findFirst {
                expectThat(text).isEqualTo("i'm an abbr")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("abbr")
    }

    @Test
    fun `can parse b-tag`() {
        val selector = aValidDocument().b {
            findFirst {
                expectThat(text).isEqualTo("i'm a bold text")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("b")
    }

    @Test
    fun `can parse bdi-tag`() {
        val selector = aValidDocument().bdi {
            findFirst {
                expectThat(text).isEqualTo("i'm a bdi")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("bdi")
    }

    @Test
    fun `can parse bdo-tag`() {
        val selector = aValidDocument().bdo {
            findFirst {
                expectThat(text).isEqualTo("i'm a bdo")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("bdo")
    }

    @Test
    fun `can parse br-tag`() {
        val selector = aValidDocument().br {
            findAll {
                toBePresentTimes(2)
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("br")
    }

    @Test
    fun `can parse cite-tag`() {
        val selector = aValidDocument().cite {
            findFirst {
                expectThat(text).isEqualTo("i'm a cite")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("cite")
    }

    @Test
    fun `can parse code-tag`() {
        val selector = aValidDocument().code {
            findFirst {
                expectThat(text).isEqualTo("i'm a code")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("code")
    }

    @Test
    fun `can parse data-tag`() {
        val selector = aValidDocument().data {
            findFirst {
                expectThat(text).isEqualTo("i'm a data")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("data")
    }

    @Test
    fun `can parse dfn-tag`() {
        val selector = aValidDocument().dfn {
            findFirst {
                expectThat(text).isEqualTo("i'm a dfn")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("dfn")
    }

    @Test
    fun `can parse em-tag`() {
        val selector = aValidDocument().em {
            findFirst {
                expectThat(text).isEqualTo("i'm a em")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("em")
    }

    @Test
    fun `can parse i-tag`() {
        val selector = aValidDocument().i {
            findFirst {
                expectThat(text).isEqualTo("i'm a i")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("i")
    }

    @Test
    fun `can parse kbd-tag`() {
        val selector = aValidDocument().kbd {
            findFirst {
                expectThat(text).isEqualTo("i'm a kbd")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("kbd")
    }

    @Test
    fun `can parse mark-tag`() {
        val selector = aValidDocument().mark {
            findFirst {
                expectThat(text).isEqualTo("i'm a mark")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("mark")
    }

    @Test
    fun `can parse q-tag`() {
        val selector = aValidDocument().q {
            findFirst {
                expectThat(text).isEqualTo("i'm a q")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("q")
    }

    @Test
    fun `can parse rb-tag`() {
        val selector = aValidDocument().rb {
            findFirst {
                expectThat(text).isEqualTo("i'm a rb")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("rb")
    }

    @Test
    fun `can parse rp-tag`() {
        val selector = aValidDocument().rp {
            findFirst {
                expectThat(text).isEqualTo("i'm a rp")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("rp")
    }

    @Test
    fun `can parse rt-tag`() {
        val selector = aValidDocument().rt {
            findFirst {
                expectThat(text).isEqualTo("i'm a rt")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("rt")
    }

    @Test
    fun `can parse rtc-tag`() {
        val selector = aValidDocument().rtc {
            findFirst {
                expectThat(text).isEqualTo("i'm a rtc")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("rtc")
    }

    @Test
    fun `can parse ruby-tag`() {
        val selector = aValidDocument().ruby {
            findFirst {
                expectThat(text).contains("i'm a ruby")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("ruby")
    }

    @Test
    fun `can parse s-tag`() {
        val selector = aValidDocument().s {
            findFirst {
                expectThat(text).isEqualTo("i'm a s")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("s")
    }

    @Test
    fun `can parse samp-tag`() {
        val selector = aValidDocument().samp {
            findFirst {
                expectThat(text).isEqualTo("i'm a samp")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("samp")
    }

    @Test
    fun `can parse small-tag`() {
        val selector = aValidDocument().small {
            findFirst {
                expectThat(text).isEqualTo("i'm a small")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("small")
    }

    @Test
    fun `can parse span-tag`() {
        val selector = aValidDocument().span {
            findAll {
                toBePresentTimes(6)
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("span")
    }

    @Test
    fun `can parse strong-tag`() {
        val selector = aValidDocument().strong {
            findFirst {
                expectThat(text).isEqualTo("i'm a strong")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("strong")
    }

    @Test
    fun `can parse sub-tag`() {
        val selector = aValidDocument().sub {
            findFirst {
                expectThat(text).isEqualTo("i'm a sub")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("sub")
    }

    @Test
    fun `can parse sup-tag`() {
        val selector = aValidDocument().sup {
            findFirst {
                expectThat(text).isEqualTo("i'm a sup")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("sup")
    }

    @Test
    fun `can parse time-tag`() {
        val selector = aValidDocument().time {
            findFirst {
                expectThat(text).isEqualTo("i'm a time")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("time")
    }

    @Test
    fun `can parse tt-tag`() {
        val selector = aValidDocument().tt {
            findFirst {
                expectThat(text).isEqualTo("i'm a tt")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("tt")
    }

    @Test
    fun `can parse u-tag`() {
        val selector = aValidDocument().u {
            findFirst {
                expectThat(text).isEqualTo("i'm a u")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("u")
    }

    @Test
    fun `can parse var-tag`() {
        val selector = aValidDocument().`var` {
            findFirst {
                expectThat(text).isEqualTo("i'm a var")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("var")
    }

    @Test
    fun `can parse wbr-tag`() {
        val selector = aValidDocument().wbr {
            findAll {
                toBePresentExactlyOnce
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("wbr")
    }
}