package it.skrape.selects.html5

import aValidDocument
import io.kotest.matchers.shouldBe
import kotlin.js.JsName
import kotlin.test.Test
class TableSelectorsKtTest {

    @JsName("CanParseCaptionTag")
	@Test
	fun `can parse caption-tag`() {
        val selector = aValidDocument().caption {
            findFirst {
                text.shouldBe("i'm the caption")
            }
            toCssSelector
        }

        selector.shouldBe("caption")
    }

    @JsName("CanParseColTag")
	@Test
	fun `can parse col-tag`() {
        val selector = aValidDocument().col {
            findAll {
                this.size.shouldBe(2)
            }
            toCssSelector
        }

        selector.shouldBe("col")
    }

    @JsName("CanParseColgroupTag")
	@Test
	fun `can parse colgroup-tag`() {
        val selector = aValidDocument().colgroup {
            findAll {
                this.size.shouldBe(1)
            }
            toCssSelector
        }

        selector.shouldBe("colgroup")
    }

    @JsName("CanParseTableTag")
	@Test
	fun `can parse table-tag`() {
        val selector = aValidDocument().table {
            findAll {
                this.size.shouldBe(1)
            }
            toCssSelector
        }

        selector.shouldBe("table")
    }

    @JsName("CanParseTbodyTag")
	@Test
	fun `can parse tbody-tag`() {
        val selector = aValidDocument().tbody {
            findAll {
                this.size.shouldBe(1)
            }
            toCssSelector
        }

        selector.shouldBe("tbody")
    }

    @JsName("CanParseTdTag")
	@Test
	fun `can parse td-tag`() {
        val selector = aValidDocument().td {
            findAll {
                this.size.shouldBe(6)
            }
            toCssSelector
        }

        selector.shouldBe("td")
    }

    @JsName("CanParseTfootTag")
	@Test
	fun `can parse tfoot-tag`() {
        val selector = aValidDocument().tfoot {
            findAll {
                this.size.shouldBe(1)
            }
            toCssSelector
        }

        selector.shouldBe("tfoot")
    }

    @JsName("CanParseThTag")
	@Test
	fun `can parse th-tag`() {
        val selector = aValidDocument().th {
            findAll {
                this.size.shouldBe(2)
            }
            toCssSelector
        }

        selector.shouldBe("th")
    }

    @JsName("CanParseTheadTag")
	@Test
	fun `can parse thead-tag`() {
        val selector = aValidDocument().thead {
            findAll {
                this.size.shouldBe(1)
            }
            toCssSelector
        }

        selector.shouldBe("thead")
    }

    @JsName("CanParseTrTag")
	@Test
	fun `can parse tr-tag`() {
        val selector = aValidDocument().tr {
            findAll {
                this.size.shouldBe(4)
            }
            toCssSelector
        }

        selector.shouldBe("tr")
    }
}