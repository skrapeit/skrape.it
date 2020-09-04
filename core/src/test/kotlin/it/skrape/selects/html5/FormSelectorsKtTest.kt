package it.skrape.selects.html5

import it.skrape.aSelfClosingTag
import it.skrape.aStandardTag
import it.skrape.aValidDocument
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class FormSelectorsKtTest {

    @Test
    fun `can parse button-tag`() {
        val selector = aValidDocument(aStandardTag("button")).button {
            findFirst {
                expectThat(text).isEqualTo("i'm a button")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("button")
    }

    @Test
    fun `can parse datalist-tag`() {
        val selector = aValidDocument(aStandardTag("datalist")).datalist {
            findFirst {
                expectThat(text).isEqualTo("i'm a datalist")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("datalist")
    }

    @Test
    fun `can parse fieldset-tag`() {
        val selector = aValidDocument(aStandardTag("fieldset")).fieldset {
            findFirst {
                expectThat(text).isEqualTo("i'm a fieldset")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("fieldset")
    }

    @Test
    fun `can parse form-tag`() {
        val selector = aValidDocument(aStandardTag("form")).form {
            findFirst {
                expectThat(text).isEqualTo("i'm a form")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("form")
    }

    @Test
    fun `can parse input-tag`() {
        val selector = aValidDocument(aSelfClosingTag("input")).input {
            findFirst {
                expectThat(attribute("custom-attr")).isEqualTo("input-attr")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("input")
    }

    @Test
    fun `can parse label-tag`() {
        val selector = aValidDocument(aStandardTag("label")).label {
            findFirst {
                expectThat(text).isEqualTo("i'm a label")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("label")
    }

    @Test
    fun `can parse legend-tag`() {
        val selector = aValidDocument(aStandardTag("legend")).legend {
            findFirst {
                expectThat(text).isEqualTo("i'm a legend")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("legend")
    }

    @Test
    fun `can parse meter-tag`() {
        val selector = aValidDocument(aStandardTag("meter")).meter {
            findFirst {
                expectThat(text).isEqualTo("i'm a meter")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("meter")
    }

    @Test
    fun `can parse optgroup-tag`() {
        val selector = aValidDocument(aStandardTag("optgroup")).optgroup {
            findFirst {
                expectThat(text).isEqualTo("i'm a optgroup")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("optgroup")
    }

    @Test
    fun `can parse option-tag`() {
        val selector = aValidDocument(aStandardTag("option")).option {
            findFirst {
                expectThat(text).isEqualTo("i'm a option")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("option")
    }

    @Test
    fun `can parse output-tag`() {
        val selector = aValidDocument(aStandardTag("output")).output {
            findFirst {
                expectThat(text).isEqualTo("i'm a output")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("output")
    }

    @Test
    fun `can parse progress-tag`() {
        val selector = aValidDocument(aStandardTag("progress")).progress {
            findFirst {
                expectThat(text).isEqualTo("i'm a progress")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("progress")
    }

    @Test
    fun `can parse select-tag`() {
        val selector = aValidDocument(aStandardTag("isPresent")).select {
            findFirst {
                expectThat(text).isEqualTo("i'm a isPresent")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("isPresent")
    }

    @Test
    fun `can parse textarea-tag`() {
        val selector = aValidDocument(aStandardTag("textarea")).textarea {
            findFirst {
                expectThat(text).isEqualTo("i'm a textarea")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("textarea")
    }
}