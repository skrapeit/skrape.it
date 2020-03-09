package it.skrape.selects.html5

import it.skrape.aStandardTag
import it.skrape.aValidDocument
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class EmbeddedContentSelectorsKtTest {

    @Test
    fun `can parse applet-tag`() {
        val selector = aValidDocument(aStandardTag("applet")).applet {
            findFirst {
                expectThat(text).isEqualTo("i'm a applet")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("applet")
    }

    @Test
    fun `can parse embed-tag`() {
        val selector = aValidDocument("<embed src=\"helloworld.swf\">").embed {
            findFirst {
                expectThat(attribute("src")).isEqualTo("helloworld.swf")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("embed")
    }

    @Test
    fun `can parse iframe-tag`() {
        val selector = aValidDocument(aStandardTag("iframe")).iframe {
            findFirst {
                expectThat(text).isEqualTo("i'm a iframe")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("iframe")
    }

    @Test
    fun `can parse noembed-tag`() {
        val selector = aValidDocument(aStandardTag("noembed")).noembed {
            findFirst {
                expectThat(text).isEqualTo("i'm a noembed")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("noembed")
    }

    @Test
    fun `can parse object-tag`() {
        val selector = aValidDocument(aStandardTag("object")).`object` {
            findFirst {
                expectThat(text).isEqualTo("i'm a object")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("object")
    }

    @Test
    fun `can parse param-tag`() {
        val selector = aValidDocument("<param name=\"autoplay\" value=\"true\">").param {
            findFirst {
                expectThat(attribute("name")).isEqualTo("autoplay")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("param")
    }

    @Test
    fun `can parse picture-tag`() {
        val selector = aValidDocument(aStandardTag("picture")).picture {
            findFirst {
                expectThat(text).isEqualTo("i'm a picture")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("picture")
    }

    @Test
    fun `can parse source-tag`() {
        val selector = aValidDocument("<source src=\"horse.ogg\" type=\"audio/ogg\">").source {
            findFirst {
                expectThat(attribute("src")).isEqualTo("horse.ogg")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("source")
    }
}