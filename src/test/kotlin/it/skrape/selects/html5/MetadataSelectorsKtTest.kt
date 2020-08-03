package it.skrape.selects.html5

import it.skrape.aSelfClosingTag
import it.skrape.aStandardTag
import it.skrape.aValidDocument
import it.skrape.selects.attribute
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.isEqualTo

internal class MetadataSelectorsKtTest {

    @Test
    fun `can parse base-tag`() {
        val selector = aValidDocument(aSelfClosingTag("base")).base {
            findFirst {
                expectThat(attribute("custom-attr")).isEqualTo("base-attr")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("base")
    }

    @Test
    fun `can parse head-tag`() {
        val selector = aValidDocument().head {
            findFirst {
                expectThat(html).contains("<title>i'm the title</title>")
                expectThat(outerHtml).contains("<title>i'm the title</title>")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("head")
    }


    @Test
    fun `can parse link-tag`() {
        val selector = aValidDocument(aSelfClosingTag("link")).link {
            findAll {
                expectThat(attribute("custom-attr")).isEqualTo("link-attr")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("link")
    }

    @Test
    fun `can parse meta-tag`() {
        val selector = aValidDocument(aSelfClosingTag("meta")).meta {
            findAll {
                expectThat(attribute("custom-attr")).isEqualTo("meta-attr")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("meta")
    }

    @Test
    fun `can parse style-tag`() {
        val selector = aValidDocument().style {
            findFirst {
                expectThat(toString()).contains(".top-bar{margin-top")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("style")
    }

    @Test
    fun `can parse title-tag`() {
        val selector = aValidDocument(aStandardTag("title")).title {
            findFirst {
                expectThat(text).isEqualTo("i'm the title")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("title")
    }
}