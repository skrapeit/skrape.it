package it.skrape.selects.html5

import aSelfClosingTag
import aStandardTag
import aValidDocument
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test
import kotlin.js.JsName

class FormSelectorsKtTest {

    @JsName("CanParseButtonTag")
	@Test
	fun `can parse button-tag`() {
        val selector = aValidDocument(aStandardTag("button")).button {
            findFirst {
                expect(text).toEqual("i'm a button")
            }
            toCssSelector
        }

        expect(selector).toEqual("button")
    }

    @JsName("CanParseDatalistTag")
	@Test
	fun `can parse datalist-tag`() {
        val selector = aValidDocument(aStandardTag("datalist")).datalist {
            findFirst {
                expect(text).toEqual("i'm a datalist")
            }
            toCssSelector
        }

        expect(selector).toEqual("datalist")
    }

    @JsName("CanParseFieldsetTag")
	@Test
	fun `can parse fieldset-tag`() {
        val selector = aValidDocument(aStandardTag("fieldset")).fieldset {
            findFirst {
                expect(text).toEqual("i'm a fieldset")
            }
            toCssSelector
        }

        expect(selector).toEqual("fieldset")
    }

    @JsName("CanParseFormTag")
	@Test
	fun `can parse form-tag`() {
        val selector = aValidDocument(aStandardTag("form")).form {
            findFirst {
                expect(text).toEqual("i'm a form")
            }
            toCssSelector
        }

        expect(selector).toEqual("form")
    }

    @JsName("CanParseInputTag")
	@Test
	fun `can parse input-tag`() {
        val selector = aValidDocument(aSelfClosingTag("input")).input {
            findFirst {
                expect(attribute("custom-attr")).toEqual("input-attr")
            }
            toCssSelector
        }

        expect(selector).toEqual("input")
    }

    @JsName("CanParseLabelTag")
	@Test
	fun `can parse label-tag`() {
        val selector = aValidDocument(aStandardTag("label")).label {
            findFirst {
                expect(text).toEqual("i'm a label")
            }
            toCssSelector
        }

        expect(selector).toEqual("label")
    }

    @JsName("CanParseLegendTag")
	@Test
	fun `can parse legend-tag`() {
        val selector = aValidDocument(aStandardTag("legend")).legend {
            findFirst {
                expect(text).toEqual("i'm a legend")
            }
            toCssSelector
        }

        expect(selector).toEqual("legend")
    }

    @JsName("CanParseMeterTag")
	@Test
	fun `can parse meter-tag`() {
        val selector = aValidDocument(aStandardTag("meter")).meter {
            findFirst {
                expect(text).toEqual("i'm a meter")
            }
            toCssSelector
        }

        expect(selector).toEqual("meter")
    }

    @JsName("CanParseOptgroupTag")
	@Test
	fun `can parse optgroup-tag`() {
        val selector = aValidDocument(aStandardTag("optgroup")).optgroup {
            findFirst {
                expect(text).toEqual("i'm a optgroup")
            }
            toCssSelector
        }

        expect(selector).toEqual("optgroup")
    }

    @JsName("CanParseOptionTag")
	@Test
	fun `can parse option-tag`() {
        val selector = aValidDocument(aStandardTag("option")).option {
            findFirst {
                expect(text).toEqual("i'm a option")
            }
            toCssSelector
        }

        expect(selector).toEqual("option")
    }

    @JsName("CanParseOutputTag")
	@Test
	fun `can parse output-tag`() {
        val selector = aValidDocument(aStandardTag("output")).output {
            findFirst {
                expect(text).toEqual("i'm a output")
            }
            toCssSelector
        }

        expect(selector).toEqual("output")
    }

    @JsName("CanParseProgressTag")
	@Test
	fun `can parse progress-tag`() {
        val selector = aValidDocument(aStandardTag("progress")).progress {
            findFirst {
                expect(text).toEqual("i'm a progress")
            }
            toCssSelector
        }

        expect(selector).toEqual("progress")
    }

    @JsName("CanParseSelectTag")
	@Test
	fun `can parse select-tag`() {
        val selector = aValidDocument(aStandardTag("select")).select {
            findFirst {
                expect(text).toEqual("i'm a select")
            }
            toCssSelector
        }

        expect(selector).toEqual("select")
    }

    @JsName("CanParseTextareaTag")
	@Test
	fun `can parse textarea-tag`() {
        val selector = aValidDocument(aStandardTag("textarea")).textarea {
            findFirst {
                expect(text).toEqual("i'm a textarea")
            }
            toCssSelector
        }

        expect(selector).toEqual("textarea")
    }
}
