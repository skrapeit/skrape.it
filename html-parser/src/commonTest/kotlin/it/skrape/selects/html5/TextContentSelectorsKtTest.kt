package it.skrape.selects.html5

import aValidDocument
import io.kotest.matchers.shouldBe
import kotlin.js.JsName
import kotlin.test.Test

class TextContentSelectorsKtTest {

    @JsName("CanParseBlockquoteTag")
	@Test
	fun `can parse blockquote-tag`() {
        val selector = aValidDocument().blockquote {
            findFirst {
                text.shouldBe("i'm a quote")
            }
            toCssSelector
        }

        selector.shouldBe("blockquote")
    }

    @JsName("CanParseDdTag")
	@Test
	fun `can parse dd-tag`() {
        val selector = aValidDocument().dd {
            findAll {
                this.size.shouldBe(2)
            }
            toCssSelector
        }

        selector.shouldBe("dd")
    }

    @JsName("CanParseDirTag")
	@Test
	fun `can parse dir-tag`() {
        val selector = aValidDocument().dir {
            findAll {
                this.size.shouldBe(1)
            }
            toCssSelector
        }

        selector.shouldBe("dir")
    }

    @JsName("CanParseDlTag")
	@Test
	fun `can parse dl-tag`() {
        val selector = aValidDocument().dl {
            findAll {
                this.size.shouldBe(1)
            }
            toCssSelector
        }

        selector.shouldBe("dl")
    }

    @JsName("CanParseDtTag")
	@Test
	fun `can parse dt-tag`() {
        val selector = aValidDocument().dt {
            findAll {
                this.size.shouldBe(2)
            }
            toCssSelector
        }

        selector.shouldBe("dt")
    }

    @JsName("CanParseFigcaptionTag")
	@Test
	fun `can parse figcaption-tag`() {
        val selector = aValidDocument().figcaption {
            findAll {
                this.size.shouldBe(1)
            }
            toCssSelector
        }

        selector.shouldBe("figcaption")
    }

    @JsName("CanParseFigureTag")
	@Test
	fun `can parse figure-tag`() {
        val selector = aValidDocument().figure {
            findAll {
                this.size.shouldBe(1)
            }
            toCssSelector
        }

        selector.shouldBe("figure")
    }

    @JsName("CanParseHrTag")
	@Test
	fun `can parse hr-tag`() {
        val selector = aValidDocument().hr {
            findAll {
                this.size.shouldBe(2)
            }
            toCssSelector
        }

        selector.shouldBe("hr")
    }

    @JsName("CanParseLiTag")
	@Test
	fun `can parse li-tag`() {
        val selector = aValidDocument().li {
            findAll {
                this.size.shouldBe(11)
            }
            toCssSelector
        }

        selector.shouldBe("li")
    }

    @JsName("CanParseOlTag")
	@Test
	fun `can parse ol-tag`() {
        val selector = aValidDocument().ol {
            findAll {
                this.size.shouldBe(1)
            }
            toCssSelector
        }

        selector.shouldBe("ol")
    }

    @JsName("CanParseUlTag")
	@Test
	fun `can parse ul-tag`() {
        val selector = aValidDocument().ul {
            findAll {
                this.size.shouldBe(1)
            }
            toCssSelector
        }

        selector.shouldBe("ul")
    }

    @JsName("CanParsePTag")
	@Test
	fun `can parse p-tag`() {
        val selector = aValidDocument().p {
            findLast {
                text.shouldBe("i'm the last paragraph")
            }
            toCssSelector
        }

        selector.shouldBe("p")
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

        selector.shouldBe("p.first p.second p.third")
    }

    @JsName("CanParsePreTag")
	@Test
	fun `can parse pre-tag`() {
        val selector = aValidDocument().pre {
            findFirst {
                text.shouldBe("i'm a pre")
            }
            toCssSelector
        }

        selector.shouldBe("pre")
    }
}