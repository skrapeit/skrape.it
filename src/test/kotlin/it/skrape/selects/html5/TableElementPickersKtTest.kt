package it.skrape.selects.html5

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import it.skrape.aValidResult
import it.skrape.aValidSelfClosingTag
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class TableElementPickersKtTest {

    @Test
    fun `can parse caption-tag`() {
        val result = aValidResult()
        result.caption {
            assertThat(text()).isEqualTo("i'm the caption")
        }
    }

    @Test
    fun `can parse col-tag`() {
        val result = aValidResult()
        result.col {
            assertThat(size).isEqualTo(2)
        }
    }

    @Test
    fun `can parse colgroup-tag`() {
        val result = aValidResult()
        result.colgroup {
            assertThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse table-tag`() {
        val result = aValidResult()
        result.table {
            assertThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse tbody-tag`() {
        val result = aValidResult()
        result.tbody {
            assertThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse td-tag`() {
        val result = aValidResult()
        result.td {
            assertThat(size).isEqualTo(6)
        }
    }

    @Test
    fun `can parse tfoot-tag`() {
        val result = aValidResult()
        result.tfoot {
            assertThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse th-tag`() {
        val result = aValidResult()
        result.th {
            assertThat(size).isEqualTo(2)
        }
    }

    @Test
    fun `can parse thead-tag`() {
        val result = aValidResult()
        result.thead {
            assertThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse tr-tag`() {
        val result = aValidResult()
        result.tr {
            assertThat(size).isEqualTo(4)
        }
    }
}