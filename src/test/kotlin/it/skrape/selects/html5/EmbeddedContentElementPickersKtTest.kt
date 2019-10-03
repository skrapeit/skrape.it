package it.skrape.selects.html5

import assertk.assertThat
import assertk.assertions.isEqualTo
import it.skrape.aValidHtml
import it.skrape.aValidResult
import it.skrape.aValidSelfClosingTag
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.*

internal class EmbeddedContentElementPickersKtTest {

    @Test
    fun `can parse applet-tag`() {
        val uniqueString = UUID.randomUUID().toString()
        val result = aValidResult(aValidHtml("<applet>$uniqueString</applet>"))
        result.applet {
            assertThat(text()).isEqualTo(uniqueString)
        }
    }

    @Test
    fun `can parse embed-tag`() {
        val result = aValidResult("<embed src=\"helloworld.swf\">")
        result.embed {
            assertThat(attr("src")).isEqualTo("helloworld.swf")
        }
    }

    @Test
    fun `can parse iframe-tag`() {
        val uniqueString = UUID.randomUUID().toString()
        val result = aValidResult(aValidHtml("<iframe>$uniqueString</iframe>"))
        result.iframe {
            assertThat(text()).isEqualTo(uniqueString)
        }
    }

    @Test
    fun `can parse noembed-tag`() {
        val uniqueString = UUID.randomUUID().toString()
        val result = aValidResult(aValidHtml("<noembed>$uniqueString</noembed>"))
        result.noembed {
            assertThat(text()).isEqualTo(uniqueString)
        }
    }

    @Test
    fun `can parse object-tag`() {
        val uniqueString = UUID.randomUUID().toString()
        val result = aValidResult(aValidHtml("<object>$uniqueString</object>"))
        result.`object` {
            assertThat(text()).isEqualTo(uniqueString)
        }
    }

    @Test
    fun `can parse param-tag`() {
        val result = aValidResult("<param name=\"autoplay\" value=\"true\">")
        result.param {
            assertThat(attr("name")).isEqualTo("autoplay")
        }
    }

    @Test
    fun `can parse picture-tag`() {
        val uniqueString = UUID.randomUUID().toString()
        val result = aValidResult(aValidHtml("<picture>$uniqueString</picture>"))
        result.picture {
            assertThat(text()).isEqualTo(uniqueString)
        }
    }

    @Test
    fun `can parse source-tag`() {
        val result = aValidResult("<source src=\"horse.ogg\" type=\"audio/ogg\">")
        result.source {
            assertThat(attr("src")).isEqualTo("horse.ogg")
        }
    }
}