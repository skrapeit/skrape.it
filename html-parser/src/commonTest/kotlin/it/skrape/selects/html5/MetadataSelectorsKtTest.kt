package it.skrape.selects.html5

import aSelfClosingTag
import aStandardTag
import aValidDocument
import ch.tutteli.atrium.api.fluent.en_GB.toContain
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import it.skrape.selects.attribute
import kotlin.test.Test
import kotlin.js.JsName

class MetadataSelectorsKtTest {

    @JsName("CanParseBaseTag")
	@Test
	fun `can parse base-tag`() {
        val selector = aValidDocument(aSelfClosingTag("base")).base {
            findFirst {
                expect(attribute("custom-attr")).toEqual("base-attr")
            }
            toCssSelector
        }

        expect(selector).toEqual("base")
    }

    @JsName("CanParseHeadTag")
	@Test
	fun `can parse head-tag`() {
        val selector = aValidDocument().head {
            findFirst {
                val myHtml = this.html
                val myOuter = this.outerHtml
                expect(myHtml).toContain("<title>i'm the title</title>")
                expect(myOuter).toContain("<title>i'm the title</title>")
            }
            toCssSelector
        }

        expect(selector).toEqual("head")
    }


    @JsName("CanParseLinkTag")
	@Test
	fun `can parse link-tag`() {
        val selector = aValidDocument(aSelfClosingTag("link")).link {
            findAll {
                expect(attribute("custom-attr")).toEqual("link-attr")
            }
            toCssSelector
        }

        expect(selector).toEqual("link")
    }

    @JsName("CanParseMetaTag")
	@Test
	fun `can parse meta-tag`() {
        val selector = aValidDocument(aSelfClosingTag("meta")).meta {
            findAll {
                expect(attribute("custom-attr")).toEqual("meta-attr")
            }
            toCssSelector
        }

        expect(selector).toEqual("meta")
    }

    @JsName("CanParseStyleTag")
	@Test
	fun `can parse style-tag`() {
        val selector = aValidDocument().style {
            findFirst {
                expect(toString()).toContain(".top-bar{margin-top")
            }
            toCssSelector
        }

        expect(selector).toEqual("style")
    }

    @JsName("CanParseTitleTag")
	@Test
	fun `can parse title-tag`() {
        val selector = aValidDocument(aStandardTag("title")).title {
            findFirst {
                expect(text).toEqual("i'm the title")
            }
            toCssSelector
        }

        expect(selector).toEqual("title")
    }
}