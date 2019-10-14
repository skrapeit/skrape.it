package it.skrape.selects.html5

import it.skrape.aStandardTag
import it.skrape.aValidDocument
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class EmbeddedContentElementPickersKtTest {

    @Test
    fun `can parse applet-tag`() {
        val result = aValidDocument(aStandardTag("applet"))
        result.applet {
            expectThat(text()).isEqualTo("i'm a applet")
        }
    }

    @Test
    fun `can parse embed-tag`() {
        val result = aValidDocument("<embed src=\"helloworld.swf\">")
        result.embed {
            expectThat(attr("src")).isEqualTo("helloworld.swf")
        }
    }

    @Test
    fun `can parse iframe-tag`() {
        val result = aValidDocument(aStandardTag("iframe"))
        result.iframe {
            expectThat(text()).isEqualTo("i'm a iframe")
        }
    }

    @Test
    fun `can parse noembed-tag`() {
        val result = aValidDocument(aStandardTag("noembed"))
        result.noembed {
            expectThat(text()).isEqualTo("i'm a noembed")
        }
    }

    @Test
    fun `can parse object-tag`() {
        val result = aValidDocument(aStandardTag("object"))
        result.`object` {
            expectThat(text()).isEqualTo("i'm a object")
        }
    }

    @Test
    fun `can parse param-tag`() {
        val result = aValidDocument("<param name=\"autoplay\" value=\"true\">")
        result.param {
            expectThat(attr("name")).isEqualTo("autoplay")
        }
    }

    @Test
    fun `can parse picture-tag`() {
        val result = aValidDocument(aStandardTag("picture"))
        result.picture {
            expectThat(text()).isEqualTo("i'm a picture")
        }
    }

    @Test
    fun `can parse source-tag`() {
        val result = aValidDocument("<source src=\"horse.ogg\" type=\"audio/ogg\">")
        result.source {
            expectThat(attr("src")).isEqualTo("horse.ogg")
        }
    }
}