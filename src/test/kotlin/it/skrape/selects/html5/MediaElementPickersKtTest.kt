package it.skrape.selects.html5

import it.skrape.aSelfClosingTag
import it.skrape.aStandardTag
import it.skrape.aValidDocument
import it.skrape.selects.firstOccurrence
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class MediaElementPickersKtTest {

    @Test
    fun `can parse area-tag`() {
        val result = aValidDocument(aStandardTag("area"))
        result.area {
            firstOccurrence {
                expectThat(className()).isEqualTo("area-class")
            }
        }
    }

    @Test
    fun `can parse audio-tag`() {
        val result = aValidDocument(aStandardTag("audio"))
        result.audio {
            expectThat(text()).isEqualTo("i'm a audio")
        }
    }

    @Test
    fun `can parse img-tag`() {
        val result = aValidDocument(aSelfClosingTag("img"))
        result.img {
            expectThat(attr("custom-attr")).isEqualTo("img-attr")
        }
    }

    @Test
    fun `can parse map-tag`() {
        val result = aValidDocument(aStandardTag("map"))
        result.map {
            expectThat(text()).isEqualTo("i'm a map")
        }
    }

    @Test
    fun `can parse track-tag`() {
        val result = aValidDocument(aSelfClosingTag("track"))
        result.track {
            expectThat(attr("custom-attr")).isEqualTo("track-attr")
        }
    }

    @Test
    fun `can parse video-tag`() {
        val result = aValidDocument(aStandardTag("video"))
        result.video {
            expectThat(text()).isEqualTo("i'm a video")
        }
    }
}