package it.skrape.selects.html5

import aSelfClosingTag
import aStandardTag
import aValidDocument
import io.kotest.matchers.shouldBe
import kotlin.test.Test
import kotlin.js.JsName

class MediaSelectorsKtTest {

    @JsName("CanParseAreaTag")
	@Test
	fun `can parse area-tag`() {
        val selector = aValidDocument(aStandardTag("area")).area {
            findFirst {
                className.shouldBe("area-class")
            }
            toCssSelector
        }

        selector.shouldBe("area")
    }

    @JsName("CanParseAudioTag")
	@Test
	fun `can parse audio-tag`() {
        val selector = aValidDocument(aStandardTag("audio")).audio {
            findFirst {
                text.shouldBe("i'm a audio")
            }
            toCssSelector
        }

        selector.shouldBe("audio")
    }

    @JsName("CanParseImgTag")
	@Test
	fun `can parse img-tag`() {
        val selector = aValidDocument(aSelfClosingTag("img")).img {
            findFirst {
                attribute("custom-attr").shouldBe("img-attr")
            }
            toCssSelector
        }

        selector.shouldBe("img")
    }

    @JsName("CanParseMapTag")
	@Test
	fun `can parse map-tag`() {
        val selector = aValidDocument(aStandardTag("map")).map {
            findFirst {
                text.shouldBe("i'm a map")
            }
            toCssSelector
        }

        selector.shouldBe("map")
    }

    @JsName("CanParseTrackTag")
	@Test
	fun `can parse track-tag`() {
        val selector = aValidDocument(aSelfClosingTag("track")).track {
            findFirst {
                attribute("custom-attr").shouldBe("track-attr")
            }
            toCssSelector
        }

        selector.shouldBe("track")
    }

    @JsName("CanParseVideoTag")
	@Test
	fun `can parse video-tag`() {
        val selector = aValidDocument(aStandardTag("video")).video {
            findFirst {
                text.shouldBe("i'm a video")
            }
            toCssSelector
        }

        selector.shouldBe("video")
    }
}