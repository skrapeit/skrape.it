package it.skrape.selects.html5

import assertk.assertThat
import assertk.assertions.isEqualTo
import it.skrape.aValidHtml
import it.skrape.aValidResult
import it.skrape.aValidSelfClosingTag
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.*

internal class FormElementPickersKtTest {

    @Test
    fun `can parse button-tag`() {
        val uniqueString = UUID.randomUUID().toString()
        val result = aValidResult(aValidHtml("<button>$uniqueString</button>"))
        result.button {
            assertThat(text()).isEqualTo(uniqueString)
        }
    }

    @Test
    fun `can parse datalist-tag`() {
        val uniqueString = UUID.randomUUID().toString()
        val result = aValidResult(aValidHtml("<datalist>$uniqueString</datalist>"))
        result.datalist {
            assertThat(text()).isEqualTo(uniqueString)
        }
    }

    @Test
    fun `can parse fieldset-tag`() {
        val uniqueString = UUID.randomUUID().toString()
        val result = aValidResult(aValidHtml("<fieldset>$uniqueString</fieldset>"))
        result.fieldset {
            assertThat(text()).isEqualTo(uniqueString)
        }
    }

    @Test
    fun `can parse form-tag`() {
        val uniqueString = UUID.randomUUID().toString()
        val result = aValidResult(aValidHtml("<form>$uniqueString</form>"))
        result.form {
            assertThat(text()).isEqualTo(uniqueString)
        }
    }

    @Test
    fun `can parse input-tag`() {
        val result = aValidResult(aValidSelfClosingTag("input"))
        result.input {
            assertThat(attr("custom-attr")).isEqualTo("input-attr")
        }
    }

    @Test
    fun `can parse label-tag`() {
        val uniqueString = UUID.randomUUID().toString()
        val result = aValidResult(aValidHtml("<label>$uniqueString</label>"))
        result.label {
            assertThat(text()).isEqualTo(uniqueString)
        }
    }

    @Test
    fun `can parse legend-tag`() {
        val uniqueString = UUID.randomUUID().toString()
        val result = aValidResult(aValidHtml("<legend>$uniqueString</legend>"))
        result.legend {
            assertThat(text()).isEqualTo(uniqueString)
        }
    }

    @Test
    fun `can parse meter-tag`() {
        val uniqueString = UUID.randomUUID().toString()
        val result = aValidResult(aValidHtml("<meter>$uniqueString</meter>"))
        result.meter {
            assertThat(text()).isEqualTo(uniqueString)
        }
    }

    @Test
    fun `can parse optgroup-tag`() {
        val uniqueString = UUID.randomUUID().toString()
        val result = aValidResult(aValidHtml("<optgroup>$uniqueString</optgroup>"))
        result.optgroup {
            assertThat(text()).isEqualTo(uniqueString)
        }
    }

    @Test
    fun `can parse option-tag`() {
        val uniqueString = UUID.randomUUID().toString()
        val result = aValidResult(aValidHtml("<option>$uniqueString</option>"))
        result.option {
            assertThat(text()).isEqualTo(uniqueString)
        }
    }

    @Test
    fun `can parse output-tag`() {
        val uniqueString = UUID.randomUUID().toString()
        val result = aValidResult(aValidHtml("<output>$uniqueString</output>"))
        result.output {
            assertThat(text()).isEqualTo(uniqueString)
        }
    }

    @Test
    fun `can parse progress-tag`() {
        val uniqueString = UUID.randomUUID().toString()
        val result = aValidResult(aValidHtml("<progress>$uniqueString</progress>"))
        result.progress {
            assertThat(text()).isEqualTo(uniqueString)
        }
    }

    @Test
    fun `can parse select-tag`() {
        val uniqueString = UUID.randomUUID().toString()
        val result = aValidResult(aValidHtml("<select>$uniqueString</select>"))
        result.select {
            assertThat(text()).isEqualTo(uniqueString)
        }
    }

    @Test
    fun `can parse textarea-tag`() {
        val uniqueString = UUID.randomUUID().toString()
        val result = aValidResult(aValidHtml("<textarea>$uniqueString</textarea>"))
        result.textarea {
            assertThat(text()).isEqualTo(uniqueString)
        }
    }
}