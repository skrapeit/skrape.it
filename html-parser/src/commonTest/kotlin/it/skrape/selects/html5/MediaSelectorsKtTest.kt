package it.skrape.selects.html5

import aSelfClosingTag
import aStandardTag
import aValidDocument
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MediaSelectorsKtTest: FunSpec({

    test("can parse area-tag") {
        val selector = aValidDocument(aStandardTag("area")).area {
            findFirst {
                className.shouldBe("area-class")
            }
            toCssSelector
        }

        selector.shouldBe("area")
    }

    test("can parse audio-tag") {
        val selector = aValidDocument(aStandardTag("audio")).audio {
            findFirst {
                text.shouldBe("i'm a audio")
            }
            toCssSelector
        }

        selector.shouldBe("audio")
    }

    test("can parse img-tag") {
        val selector = aValidDocument(aSelfClosingTag("img")).img {
            findFirst {
                attribute("custom-attr").shouldBe("img-attr")
            }
            toCssSelector
        }

        selector.shouldBe("img")
    }

    test("can parse map-tag") {
        val selector = aValidDocument(aStandardTag("map")).map {
            findFirst {
                text.shouldBe("i'm a map")
            }
            toCssSelector
        }

        selector.shouldBe("map")
    }

    test("can parse track-tag") {
        val selector = aValidDocument(aSelfClosingTag("track")).track {
            findFirst {
                attribute("custom-attr").shouldBe("track-attr")
            }
            toCssSelector
        }

        selector.shouldBe("track")
    }

    test("can parse video-tag") {
        val selector = aValidDocument(aStandardTag("video")).video {
            findFirst {
                text.shouldBe("i'm a video")
            }
            toCssSelector
        }

        selector.shouldBe("video")
    }
})