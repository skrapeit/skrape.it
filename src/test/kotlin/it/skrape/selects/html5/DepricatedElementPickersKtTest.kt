package it.skrape.selects.html5

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import it.skrape.aStandardTag
import it.skrape.aValidResult
import it.skrape.aSelfClosingTag
import it.skrape.aValidHtml
import org.junit.jupiter.api.Test

internal class DepricatedElementPickersKtTest {

    @Test
    fun `can parse acronym-tag`() {
        val result = aValidResult(aStandardTag("acronym"))
        result.acronym {
            assertThat(text()).isEqualTo("i'm a acronym")
        }
    }

    @Test
    fun `can parse basefont-tag`() {
        val result = aValidResult(aSelfClosingTag("basefont"))
        result.basefont {
            assertThat(attr("custom-attr")).isEqualTo("basefont-attr")
        }
    }

    @Test
    fun `can parse bgsound-tag`() {
        val result = aValidResult(aSelfClosingTag("bgsound"))
        result.bgsound {
            assertThat(attr("custom-attr")).isEqualTo("bgsound-attr")
        }
    }

    @Test
    fun `can parse big-tag`() {
        val result = aValidResult(aStandardTag("big"))
        result.big {
            assertThat(text()).isEqualTo("i'm a big")
        }
    }

    @Test
    fun `can parse blink-tag`() {
        val result = aValidResult(aStandardTag("blink"))
        result.blink {
            assertThat(text()).isEqualTo("i'm a blink")
        }
    }

    @Test
    fun `can parse center-tag`() {
        val result = aValidResult(aStandardTag("center"))
        result.center {
            assertThat(text()).isEqualTo("i'm a center")
        }
    }

    @Test
    fun `can parse command-tag`() {
        val result = aValidResult(aSelfClosingTag("command"))
        result.command {
            assertThat(attr("custom-attr")).isEqualTo("command-attr")
        }
    }

    @Test
    fun `can parse font-tag`() {
        val result = aValidResult(aStandardTag("font"))
        result.font {
            assertThat(text()).isEqualTo("i'm a font")
        }
    }

    @Test
    fun `can parse frameset-tag`() {
        val result = aValidResult(aSelfClosingTag("frameset"))
        result.frameset {
            assertThat(attr("custom-attr")).isEqualTo("frameset-attr")
        }
    }

    @Test
    fun `can parse keygen-tag`() {
        val result = aValidResult(aSelfClosingTag("keygen"))
        result.keygen {
            assertThat(attr("custom-attr")).isEqualTo("keygen-attr")
        }
    }

    @Test
    fun `can parse listing-tag`() {
        val result = aValidResult(aStandardTag("listing"))
        result.listing {
            assertThat(text()).isEqualTo("i'm a listing")
        }
    }

    @Test
    fun `can parse marquee-tag`() {
        val result = aValidResult(aStandardTag("marquee"))
        result.marquee {
            assertThat(text()).isEqualTo("i'm a marquee")
        }
    }

    @Test
    fun `can parse multicol-tag`() {
        val result = aValidResult(aStandardTag("multicol"))
        result.multicol {
            assertThat(text()).isEqualTo("i'm a multicol")
        }
    }

    @Test
    fun `can parse nextid-tag`() {
        val result = aValidResult(aStandardTag("nextid"))
        result.nextid {
            assertThat(text()).isEqualTo("i'm a nextid")
        }
    }

    @Test
    fun `can parse nobr-tag`() {
        val result = aValidResult(aStandardTag("nobr"))
        result.nobr {
            assertThat(text()).isEqualTo("i'm a nobr")
        }
    }

    @Test
    fun `can parse noframes-tag`() {
        val result = aValidResult(aStandardTag("noframes"))
        result.noframes {
            assertThat(text()).isEqualTo("i'm a noframes")
        }
    }

    @Test
    fun `can parse plaintext-tag`() {
        val result = aValidResult("<plaintext>")
        result.plaintext {
            assertThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse spacer-tag`() {
        val result = aValidResult(aStandardTag("spacer"))
        result.spacer {
            assertThat(text()).isEqualTo("i'm a spacer")
        }
    }

    @Test
    fun `can parse strike-tag`() {
        val result = aValidResult(aStandardTag("strike"))
        result.strike {
            assertThat(text()).isEqualTo("i'm a strike")
        }
    }

    @Test
    fun `can parse xmp-tag`() {
        val result = aValidResult(aStandardTag("xmp"))
        result.xmp {
            assertThat(text()).isEqualTo("i'm a xmp")
        }
    }
}