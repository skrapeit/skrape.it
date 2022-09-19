package it.skrape.selects.html5

import aValidDocument
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import kotlin.js.JsName
import kotlin.test.Test

class TextContentSelectorsKtTest {

    @JsName("CanParseBlockquoteTag")
	@Test
	fun `can parse blockquote-tag`() {
        val selector = aValidDocument().blockquote {
            findFirst {
                expect(text).toEqual("i'm a quote")
            }
            toCssSelector
        }

        expect(selector).toEqual("blockquote")
    }

    @JsName("CanParseDdTag")
	@Test
	fun `can parse dd-tag`() {
        val selector = aValidDocument().dd {
            findAll {
                expect(this.size).toEqual(2)
            }
            toCssSelector
        }

        expect(selector).toEqual("dd")
    }

    @JsName("CanParseDirTag")
	@Test
	fun `can parse dir-tag`() {
        val selector = aValidDocument().dir {
            findAll {
                expect(this.size).toEqual(1)
            }
            toCssSelector
        }

        expect(selector).toEqual("dir")
    }

    @JsName("CanParseDlTag")
	@Test
	fun `can parse dl-tag`() {
        val selector = aValidDocument().dl {
            findAll {
                expect(this.size).toEqual(1)
            }
            toCssSelector
        }

        expect(selector).toEqual("dl")
    }

    @JsName("CanParseDtTag")
	@Test
	fun `can parse dt-tag`() {
        val selector = aValidDocument().dt {
            findAll {
                expect(this.size).toEqual(2)
            }
            toCssSelector
        }

        expect(selector).toEqual("dt")
    }

    @JsName("CanParseFigcaptionTag")
	@Test
	fun `can parse figcaption-tag`() {
        val selector = aValidDocument().figcaption {
            findAll {
                expect(this.size).toEqual(1)
            }
            toCssSelector
        }

        expect(selector).toEqual("figcaption")
    }

    @JsName("CanParseFigureTag")
	@Test
	fun `can parse figure-tag`() {
        val selector = aValidDocument().figure {
            findAll {
                expect(this.size).toEqual(1)
            }
            toCssSelector
        }

        expect(selector).toEqual("figure")
    }

    @JsName("CanParseHrTag")
	@Test
	fun `can parse hr-tag`() {
        val selector = aValidDocument().hr {
            findAll {
                expect(this.size).toEqual(2)
            }
            toCssSelector
        }

        expect(selector).toEqual("hr")
    }

    @JsName("CanParseLiTag")
	@Test
	fun `can parse li-tag`() {
        val selector = aValidDocument().li {
            findAll {
                expect(this.size).toEqual(11)
            }
            toCssSelector
        }

        expect(selector).toEqual("li")
    }

    @JsName("CanParseOlTag")
	@Test
	fun `can parse ol-tag`() {
        val selector = aValidDocument().ol {
            findAll {
                expect(this.size).toEqual(1)
            }
            toCssSelector
        }

        expect(selector).toEqual("ol")
    }

    @JsName("CanParseUlTag")
	@Test
	fun `can parse ul-tag`() {
        val selector = aValidDocument().ul {
            findAll {
                expect(this.size).toEqual(1)
            }
            toCssSelector
        }

        expect(selector).toEqual("ul")
    }

    @JsName("CanParsePTag")
	@Test
	fun `can parse p-tag`() {
        val selector = aValidDocument().p {
            findLast {
                expect(text).toEqual("i'm the last paragraph")
            }
            toCssSelector
        }

        expect(selector).toEqual("p")
    }

    @JsName("CanCascadePTag")
	@Test
	fun `can cascade p-tag`() {
        val selector = aValidDocument().p {
            withClass = "first"
            p {
                withClass = "second"
                p {
                    withClass = "third"
                    toCssSelector
                }
            }
        }

        expect(selector).toEqual("p.first p.second p.third")
    }

    @JsName("CanParsePreTag")
	@Test
	fun `can parse pre-tag`() {
        val selector = aValidDocument().pre {
            findFirst {
                expect(text).toEqual("i'm a pre")
            }
            toCssSelector
        }

        expect(selector).toEqual("pre")
    }
}