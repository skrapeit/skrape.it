package it.skrape.selects.html5

import it.skrape.aSelfClosingTag
import it.skrape.aStandardTag
import it.skrape.aValidDocument
import it.skrape.selects.findFirst
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class FormSelectorsKtTest {

    @Test
    fun `can parse button-tag`() {
        val selector = aValidDocument(aStandardTag("button")).button {
            findFirst {
                expectThat(text()).isEqualTo("i'm a button")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("button")
    }

    @Test
    fun `can parse datalist-tag`() {
        val selector = aValidDocument(aStandardTag("datalist")).datalist {
            findFirst {
                expectThat(text()).isEqualTo("i'm a datalist")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("datalist")
    }

    @Test
    fun `can parse fieldset-tag`() {
        val selector = aValidDocument(aStandardTag("fieldset")).fieldset {
            findFirst {
                expectThat(text()).isEqualTo("i'm a fieldset")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("fieldset")
    }

    @Test
    fun `can parse form-tag`() {
        val selector = aValidDocument(aStandardTag("form")).form {
            findFirst {
                expectThat(text()).isEqualTo("i'm a form")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("form")
    }

    @Test
    fun `can parse input-tag`() {
        val selector = aValidDocument(aSelfClosingTag("input")).input {
            findFirst {
                expectThat(attr("custom-attr")).isEqualTo("input-attr")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("input")
    }

    @Test
    fun `can parse label-tag`() {
        val selector = aValidDocument(aStandardTag("label")).label {
            findFirst {
                expectThat(text()).isEqualTo("i'm a label")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("label")
    }

    @Test
    fun `can parse legend-tag`() {
        val selector = aValidDocument(aStandardTag("legend")).legend {
            findFirst {
                expectThat(text()).isEqualTo("i'm a legend")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("legend")
    }

    @Test
    fun `can parse meter-tag`() {
        val selector = aValidDocument(aStandardTag("meter")).meter {
            findFirst {
                expectThat(text()).isEqualTo("i'm a meter")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("meter")
    }

    @Test
    fun `can parse optgroup-tag`() {
        val selector = aValidDocument(aStandardTag("optgroup")).optgroup {
            findFirst {
                expectThat(text()).isEqualTo("i'm a optgroup")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("optgroup")
    }

    @Test
    fun `can parse option-tag`() {
        val selector = aValidDocument(aStandardTag("option")).option {
            findFirst {
                expectThat(text()).isEqualTo("i'm a option")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("option")
    }

    @Test
    fun `can parse output-tag`() {
        val selector = aValidDocument(aStandardTag("output")).output {
            findFirst {
                expectThat(text()).isEqualTo("i'm a output")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("output")
    }

    @Test
    fun `can parse progress-tag`() {
        val selector = aValidDocument(aStandardTag("progress")).progress {
            findFirst {
                expectThat(text()).isEqualTo("i'm a progress")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("progress")
    }

    @Test
    fun `can parse select-tag`() {
        val selector = aValidDocument(aStandardTag("select")).select {
            findFirst {
                expectThat(text()).isEqualTo("i'm a select")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("select")
    }

    @Test
    fun `can parse textarea-tag`() {
        val selector = aValidDocument(aStandardTag("textarea")).textarea {
            findFirst {
                expectThat(text()).isEqualTo("i'm a textarea")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("textarea")
    }
}