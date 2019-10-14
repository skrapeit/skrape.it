package it.skrape.selects.html5

import it.skrape.aSelfClosingTag
import it.skrape.aStandardTag
import it.skrape.aValidDocument
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class FormElementPickersKtTest {

    @Test
    fun `can parse button-tag`() {
        val result = aValidDocument(aStandardTag("button"))
        result.button {
            expectThat(text()).isEqualTo("i'm a button")
        }
    }

    @Test
    fun `can parse datalist-tag`() {
        val result = aValidDocument(aStandardTag("datalist"))
        result.datalist {
            expectThat(text()).isEqualTo("i'm a datalist")
        }
    }

    @Test
    fun `can parse fieldset-tag`() {
        val result = aValidDocument(aStandardTag("fieldset"))
        result.fieldset {
            expectThat(text()).isEqualTo("i'm a fieldset")
        }
    }

    @Test
    fun `can parse form-tag`() {
        val result = aValidDocument(aStandardTag("form"))
        result.form {
            expectThat(text()).isEqualTo("i'm a form")
        }
    }

    @Test
    fun `can parse input-tag`() {
        val result = aValidDocument(aSelfClosingTag("input"))
        result.input {
            expectThat(attr("custom-attr")).isEqualTo("input-attr")
        }
    }

    @Test
    fun `can parse label-tag`() {
        val result = aValidDocument(aStandardTag("label"))
        result.label {
            expectThat(text()).isEqualTo("i'm a label")
        }
    }

    @Test
    fun `can parse legend-tag`() {
        val result = aValidDocument(aStandardTag("legend"))
        result.legend {
            expectThat(text()).isEqualTo("i'm a legend")
        }
    }

    @Test
    fun `can parse meter-tag`() {
        val result = aValidDocument(aStandardTag("meter"))
        result.meter {
            expectThat(text()).isEqualTo("i'm a meter")
        }
    }

    @Test
    fun `can parse optgroup-tag`() {
        val result = aValidDocument(aStandardTag("optgroup"))
        result.optgroup {
            expectThat(text()).isEqualTo("i'm a optgroup")
        }
    }

    @Test
    fun `can parse option-tag`() {
        val result = aValidDocument(aStandardTag("option"))
        result.option {
            expectThat(text()).isEqualTo("i'm a option")
        }
    }

    @Test
    fun `can parse output-tag`() {
        val result = aValidDocument(aStandardTag("output"))
        result.output {
            expectThat(text()).isEqualTo("i'm a output")
        }
    }

    @Test
    fun `can parse progress-tag`() {
        val result = aValidDocument(aStandardTag("progress"))
        result.progress {
            expectThat(text()).isEqualTo("i'm a progress")
        }
    }

    @Test
    fun `can parse select-tag`() {
        val result = aValidDocument(aStandardTag("select"))
        result.select {
            expectThat(text()).isEqualTo("i'm a select")
        }
    }

    @Test
    fun `can parse textarea-tag`() {
        val result = aValidDocument(aStandardTag("textarea"))
        result.textarea {
            expectThat(text()).isEqualTo("i'm a textarea")
        }
    }
}