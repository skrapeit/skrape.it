package it.skrape.selects.html5

import aValidDocument
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import kotlin.js.JsName
import kotlin.test.Test
class TableSelectorsKtTest {

    @JsName("CanParseCaptionTag")
	@Test
	fun `can parse caption-tag`() {
        val selector = aValidDocument().caption {
            findFirst {
                expect(text).toEqual("i'm the caption")
            }
            toCssSelector
        }

        expect(selector).toEqual("caption")
    }

    @JsName("CanParseColTag")
	@Test
	fun `can parse col-tag`() {
        val selector = aValidDocument().col {
            findAll {
                expect(this.size).toEqual(2)
            }
            toCssSelector
        }

        expect(selector).toEqual("col")
    }

    @JsName("CanParseColgroupTag")
	@Test
	fun `can parse colgroup-tag`() {
        val selector = aValidDocument().colgroup {
            findAll {
                expect(this.size).toEqual(1)
            }
            toCssSelector
        }

        expect(selector).toEqual("colgroup")
    }

    @JsName("CanParseTableTag")
	@Test
	fun `can parse table-tag`() {
        val selector = aValidDocument().table {
            findAll {
                expect(this.size).toEqual(1)
            }
            toCssSelector
        }

        expect(selector).toEqual("table")
    }

    @JsName("CanParseTbodyTag")
	@Test
	fun `can parse tbody-tag`() {
        val selector = aValidDocument().tbody {
            findAll {
                expect(this.size).toEqual(1)
            }
            toCssSelector
        }

        expect(selector).toEqual("tbody")
    }

    @JsName("CanParseTdTag")
	@Test
	fun `can parse td-tag`() {
        val selector = aValidDocument().td {
            findAll {
                expect(this.size).toEqual(6)
            }
            toCssSelector
        }

        expect(selector).toEqual("td")
    }

    @JsName("CanParseTfootTag")
	@Test
	fun `can parse tfoot-tag`() {
        val selector = aValidDocument().tfoot {
            findAll {
                expect(this.size).toEqual(1)
            }
            toCssSelector
        }

        expect(selector).toEqual("tfoot")
    }

    @JsName("CanParseThTag")
	@Test
	fun `can parse th-tag`() {
        val selector = aValidDocument().th {
            findAll {
                expect(this.size).toEqual(2)
            }
            toCssSelector
        }

        expect(selector).toEqual("th")
    }

    @JsName("CanParseTheadTag")
	@Test
	fun `can parse thead-tag`() {
        val selector = aValidDocument().thead {
            findAll {
                expect(this.size).toEqual(1)
            }
            toCssSelector
        }

        expect(selector).toEqual("thead")
    }

    @JsName("CanParseTrTag")
	@Test
	fun `can parse tr-tag`() {
        val selector = aValidDocument().tr {
            findAll {
                expect(this.size).toEqual(4)
            }
            toCssSelector
        }

        expect(selector).toEqual("tr")
    }
}