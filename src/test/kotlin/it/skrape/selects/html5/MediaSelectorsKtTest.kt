package it.skrape.selects.html5

import it.skrape.aSelfClosingTag
import it.skrape.aStandardTag
import it.skrape.aValidDocument
import it.skrape.selects.findFirst
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class MediaSelectorsKtTest {

    @Test
    fun `can parse area-tag`() {
        val selector = aValidDocument(aStandardTag("area")).area {
            findFirst {
                expectThat(className()).isEqualTo("area-class")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("area")
    }

    @Test
    fun `can parse audio-tag`() {
        val selector = aValidDocument(aStandardTag("audio")).audio {
            findFirst {
                expectThat(text()).isEqualTo("i'm a audio")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("audio")
    }

    @Test
    fun `can parse img-tag`() {
        val selector = aValidDocument(aSelfClosingTag("img")).img {
            findFirst {
                expectThat(attr("custom-attr")).isEqualTo("img-attr")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("img")
    }

    @Test
    fun `can parse map-tag`() {
        val selector = aValidDocument(aStandardTag("map")).map {
            findFirst {
                expectThat(text()).isEqualTo("i'm a map")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("map")
    }

    @Test
    fun `can parse track-tag`() {
        val selector = aValidDocument(aSelfClosingTag("track")).track {
            findFirst {
                expectThat(attr("custom-attr")).isEqualTo("track-attr")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("track")
    }

    @Test
    fun `can parse video-tag`() {
        val selector = aValidDocument(aStandardTag("video")).video {
            findFirst {
                expectThat(text()).isEqualTo("i'm a video")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("video")
    }
}