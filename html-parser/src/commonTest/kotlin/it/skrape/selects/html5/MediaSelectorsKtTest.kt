package it.skrape.selects.html5

import aSelfClosingTag
import aStandardTag
import aValidDocument
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test
import kotlin.js.JsName

class MediaSelectorsKtTest {

    @JsName("CanParseAreaTag")
	@Test
	fun `can parse area-tag`() {
        val selector = aValidDocument(aStandardTag("area")).area {
            findFirst {
                expect(className).toEqual("area-class")
            }
            toCssSelector
        }

        expect(selector).toEqual("area")
    }

    @JsName("CanParseAudioTag")
	@Test
	fun `can parse audio-tag`() {
        val selector = aValidDocument(aStandardTag("audio")).audio {
            findFirst {
                expect(text).toEqual("i'm a audio")
            }
            toCssSelector
        }

        expect(selector).toEqual("audio")
    }

    @JsName("CanParseImgTag")
	@Test
	fun `can parse img-tag`() {
        val selector = aValidDocument(aSelfClosingTag("img")).img {
            findFirst {
                expect(attribute("custom-attr")).toEqual("img-attr")
            }
            toCssSelector
        }

        expect(selector).toEqual("img")
    }

    @JsName("CanParseMapTag")
	@Test
	fun `can parse map-tag`() {
        val selector = aValidDocument(aStandardTag("map")).map {
            findFirst {
                expect(text).toEqual("i'm a map")
            }
            toCssSelector
        }

        expect(selector).toEqual("map")
    }

    @JsName("CanParseTrackTag")
	@Test
	fun `can parse track-tag`() {
        val selector = aValidDocument(aSelfClosingTag("track")).track {
            findFirst {
                expect(attribute("custom-attr")).toEqual("track-attr")
            }
            toCssSelector
        }

        expect(selector).toEqual("track")
    }

    @JsName("CanParseVideoTag")
	@Test
	fun `can parse video-tag`() {
        val selector = aValidDocument(aStandardTag("video")).video {
            findFirst {
                expect(text).toEqual("i'm a video")
            }
            toCssSelector
        }

        expect(selector).toEqual("video")
    }
}