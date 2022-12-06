package it.skrape.selects.html5

import aSelfClosingTag
import aStandardTag
import aValidDocument
import io.kotest.matchers.shouldBe
import kotlin.test.Test
import kotlin.js.JsName

class FormSelectorsKtTest {

    @JsName("CanParseButtonTag")
	@Test
	fun `can parse button-tag`() {
        val selector = aValidDocument(aStandardTag("button")).button {
            findFirst {
                text.shouldBe("i'm a button")
            }
            toCssSelector
        }

        selector.shouldBe("button")
    }

    @JsName("CanParseDatalistTag")
	@Test
	fun `can parse datalist-tag`() {
        val selector = aValidDocument(aStandardTag("datalist")).datalist {
            findFirst {
                text.shouldBe("i'm a datalist")
            }
            toCssSelector
        }

        selector.shouldBe("datalist")
    }

    @JsName("CanParseFieldsetTag")
	@Test
	fun `can parse fieldset-tag`() {
        val selector = aValidDocument(aStandardTag("fieldset")).fieldset {
            findFirst {
                text.shouldBe("i'm a fieldset")
            }
            toCssSelector
        }

        selector.shouldBe("fieldset")
    }

    @JsName("CanParseFormTag")
	@Test
	fun `can parse form-tag`() {
        val selector = aValidDocument(aStandardTag("form")).form {
            findFirst {
                text.shouldBe("i'm a form")
            }
            toCssSelector
        }

        selector.shouldBe("form")
    }

    @JsName("CanParseInputTag")
	@Test
	fun `can parse input-tag`() {
        val selector = aValidDocument(aSelfClosingTag("input")).input {
            findFirst {
                attribute("custom-attr").shouldBe("input-attr")
            }
            toCssSelector
        }

        selector.shouldBe("input")
    }

    @JsName("CanParseLabelTag")
	@Test
	fun `can parse label-tag`() {
        val selector = aValidDocument(aStandardTag("label")).label {
            findFirst {
                text.shouldBe("i'm a label")
            }
            toCssSelector
        }

        selector.shouldBe("label")
    }

    @JsName("CanParseLegendTag")
	@Test
	fun `can parse legend-tag`() {
        val selector = aValidDocument(aStandardTag("legend")).legend {
            findFirst {
                text.shouldBe("i'm a legend")
            }
            toCssSelector
        }

        selector.shouldBe("legend")
    }

    @JsName("CanParseMeterTag")
	@Test
	fun `can parse meter-tag`() {
        val selector = aValidDocument(aStandardTag("meter")).meter {
            findFirst {
                text.shouldBe("i'm a meter")
            }
            toCssSelector
        }

        selector.shouldBe("meter")
    }

    @JsName("CanParseOptgroupTag")
	@Test
	fun `can parse optgroup-tag`() {
        val selector = aValidDocument(aStandardTag("optgroup")).optgroup {
            findFirst {
                text.shouldBe("i'm a optgroup")
            }
            toCssSelector
        }

        selector.shouldBe("optgroup")
    }

    @JsName("CanParseOptionTag")
	@Test
	fun `can parse option-tag`() {
        val selector = aValidDocument(aStandardTag("option")).option {
            findFirst {
                text.shouldBe("i'm a option")
            }
            toCssSelector
        }

        selector.shouldBe("option")
    }

    @JsName("CanParseOutputTag")
	@Test
	fun `can parse output-tag`() {
        val selector = aValidDocument(aStandardTag("output")).output {
            findFirst {
                text.shouldBe("i'm a output")
            }
            toCssSelector
        }

        selector.shouldBe("output")
    }

    @JsName("CanParseProgressTag")
	@Test
	fun `can parse progress-tag`() {
        val selector = aValidDocument(aStandardTag("progress")).progress {
            findFirst {
                text.shouldBe("i'm a progress")
            }
            toCssSelector
        }

        selector.shouldBe("progress")
    }

    @JsName("CanParseSelectTag")
	@Test
	fun `can parse select-tag`() {
        val selector = aValidDocument(aStandardTag("select")).select {
            findFirst {
                text.shouldBe("i'm a select")
            }
            toCssSelector
        }

        selector.shouldBe("select")
    }

    @JsName("CanParseTextareaTag")
	@Test
	fun `can parse textarea-tag`() {
        val selector = aValidDocument(aStandardTag("textarea")).textarea {
            findFirst {
                text.shouldBe("i'm a textarea")
            }
            toCssSelector
        }

        selector.shouldBe("textarea")
    }
}
