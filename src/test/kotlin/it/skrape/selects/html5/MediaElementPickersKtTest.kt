package it.skrape.selects.html5

import assertk.assertThat
import assertk.assertions.isEqualTo
import it.skrape.aStandardTag
import it.skrape.aValidHtml
import it.skrape.aValidResult
import it.skrape.aSelfClosingTag
import it.skrape.selects.firstOccurrence
import org.junit.jupiter.api.Test

import java.util.*

internal class MediaElementPickersKtTest {

    @Test
    fun `can parse area-tag`() {
        val result = aValidResult(aStandardTag("area"))
        result.area {
            firstOccurrence {
                assertThat(className()).isEqualTo("area-class")
            }
        }
    }

    @Test
    fun `can parse audio-tag`() {
        val uniqueString = UUID.randomUUID().toString()
        val result = aValidResult(aValidHtml("<audio>$uniqueString</audio>"))
        result.audio {
            assertThat(text()).isEqualTo(uniqueString)
        }
    }

    @Test
    fun `can parse img-tag`() {
        val result = aValidResult(aSelfClosingTag("img"))
        result.img {
            assertThat(attr("custom-attr")).isEqualTo("img-attr")
        }
    }

    @Test
    fun `can parse map-tag`() {
        val uniqueString = UUID.randomUUID().toString()
        val result = aValidResult(aValidHtml("<map>$uniqueString</map>"))
        result.map {
            assertThat(text()).isEqualTo(uniqueString)
        }
    }

    @Test
    fun `can parse track-tag`() {
        val result = aValidResult(aSelfClosingTag("track"))
        result.track {
            assertThat(attr("custom-attr")).isEqualTo("track-attr")
        }
    }

    @Test
    fun `can parse video-tag`() {
        val uniqueString = UUID.randomUUID().toString()
        val result = aValidResult(aValidHtml("<video>$uniqueString</video>"))
        result.video {
            assertThat(text()).isEqualTo(uniqueString)
        }
    }
}