package it.skrape.selects.html5

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import it.skrape.aStandardTag
import it.skrape.aValidResult
import it.skrape.aValidSelfClosingTag
import it.skrape.selects.firstOccurrence
import org.junit.jupiter.api.Test

internal class MetadataElementPickersKtTest {

    @Test
    fun `can parse base-tag`() {
        val result = aValidResult(aValidSelfClosingTag("base"))
        result.base {
            assertThat(attr("custom-attr")).isEqualTo("base-attr")
        }
    }

    @Test
    fun `can parse head-tag`() {
        val result = aValidResult(aStandardTag("head"))
        result.head {
            firstOccurrence {
                assertThat(this.className()).isEqualTo("head-class")
            }
        }
    }


    @Test
    fun `can parse link-tag`() {
        val result = aValidResult(aValidSelfClosingTag("link"))
        result.link {
            assertThat(attr("custom-attr")).isEqualTo("link-attr")
        }
    }

    @Test
    fun `can parse meta-tag`() {
        val result = aValidResult(aValidSelfClosingTag("meta"))
        result.meta {
            assertThat(attr("custom-attr")).isEqualTo("meta-attr")
        }
    }

    @Test
    fun `can parse style-tag`() {
        val result = aValidResult()
        result.style {
            firstOccurrence {
                assertThat(this.toString()).contains(".top-bar{margin-top")
            }
        }
    }

    @Test
    fun `can parse title-tag`() {
        val result = aValidResult(aStandardTag("title"))
        result.title {
            firstOccurrence {
                assertThat(text()).isEqualTo("i'm a title")
            }
        }
    }
}