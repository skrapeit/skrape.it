package it.skrape.selects.html5

import aSelfClosingTag
import aStandardTag
import aValidDocument
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import it.skrape.selects.attribute
import kotlin.test.Test
import kotlin.js.JsName

class MetadataSelectorsKtTest {

    @JsName("CanParseBaseTag")
	@Test
	fun `can parse base-tag`() {
        val selector = aValidDocument(aSelfClosingTag("base")).base {
            findFirst {
                attribute("custom-attr").shouldBe("base-attr")
            }
            toCssSelector
        }

        selector.shouldBe("base")
    }

    @JsName("CanParseHeadTag")
	@Test
	fun `can parse head-tag`() {
        val selector = aValidDocument().head {
            findFirst {
                val myHtml = this.html
                val myOuter = this.outerHtml
                myHtml.shouldContain("<title>i'm the title</title>")
                myOuter.shouldContain("<title>i'm the title</title>")
            }
            toCssSelector
        }

        selector.shouldBe("head")
    }


    @JsName("CanParseLinkTag")
	@Test
	fun `can parse link-tag`() {
        val selector = aValidDocument(aSelfClosingTag("link")).link {
            findAll {
                attribute("custom-attr").shouldBe("link-attr")
            }
            toCssSelector
        }

        selector.shouldBe("link")
    }

    @JsName("CanParseMetaTag")
	@Test
	fun `can parse meta-tag`() {
        val selector = aValidDocument(aSelfClosingTag("meta")).meta {
            findAll {
                attribute("custom-attr").shouldBe("meta-attr")
            }
            toCssSelector
        }

        selector.shouldBe("meta")
    }

    @JsName("CanParseStyleTag")
	@Test
	fun `can parse style-tag`() {
        val selector = aValidDocument().style {
            findFirst {
                toString().shouldContain(".top-bar{margin-top")
            }
            toCssSelector
        }

        selector.shouldBe("style")
    }

    @JsName("CanParseTitleTag")
	@Test
	fun `can parse title-tag`() {
        val selector = aValidDocument(aStandardTag("title")).title {
            findFirst {
                text.shouldBe("i'm the title")
            }
            toCssSelector
        }

        selector.shouldBe("title")
    }
}