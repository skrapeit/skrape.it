package it.skrape.selects.html5

import it.skrape.aSelfClosingTag
import it.skrape.aStandardTag
import it.skrape.aValidDocument
import it.skrape.selects.firstOccurrence
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.isEqualTo

internal class MetadataElementPickersKtTest {

    @Test
    fun `can parse base-tag`() {
        val result = aValidDocument(aSelfClosingTag("base"))
        result.base {
            expectThat(attr("custom-attr")).isEqualTo("base-attr")
        }
    }

    @Test
    fun `can parse head-tag`() {
        val result = aValidDocument()
        result.head {
            expectThat(html()).contains("<title>i'm the title</title>")
        }
    }


    @Test
    fun `can parse link-tag`() {
        val result = aValidDocument(aSelfClosingTag("link"))
        result.link {
            expectThat(attr("custom-attr")).isEqualTo("link-attr")
        }
    }

    @Test
    fun `can parse meta-tag`() {
        val result = aValidDocument(aSelfClosingTag("meta"))
        result.meta {
            expectThat(attr("custom-attr")).isEqualTo("meta-attr")
        }
    }

    @Test
    fun `can parse style-tag`() {
        val result = aValidDocument()
        result.style {
            firstOccurrence {
                expectThat(this.toString()).contains(".top-bar{margin-top")
            }
        }
    }

    @Test
    fun `can parse title-tag`() {
        val result = aValidDocument(aStandardTag("title"))
        result.title {
            firstOccurrence {
                expectThat(text()).isEqualTo("i'm the title")
            }
        }
    }
}