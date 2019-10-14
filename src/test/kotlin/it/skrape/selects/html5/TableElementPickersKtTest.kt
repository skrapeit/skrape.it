package it.skrape.selects.html5

import it.skrape.aValidDocument
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class TableElementPickersKtTest {

    @Test
    fun `can parse caption-tag`() {
        val result = aValidDocument()
        result.caption {
            expectThat(text()).isEqualTo("i'm the caption")
        }
    }

    @Test
    fun `can parse col-tag`() {
        val result = aValidDocument()
        result.col {
            expectThat(size).isEqualTo(2)
        }
    }

    @Test
    fun `can parse colgroup-tag`() {
        val result = aValidDocument()
        result.colgroup {
            expectThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse table-tag`() {
        val result = aValidDocument()
        result.table {
            expectThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse tbody-tag`() {
        val result = aValidDocument()
        result.tbody {
            expectThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse td-tag`() {
        val result = aValidDocument()
        result.td {
            expectThat(size).isEqualTo(6)
        }
    }

    @Test
    fun `can parse tfoot-tag`() {
        val result = aValidDocument()
        result.tfoot {
            expectThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse th-tag`() {
        val result = aValidDocument()
        result.th {
            expectThat(size).isEqualTo(2)
        }
    }

    @Test
    fun `can parse thead-tag`() {
        val result = aValidDocument()
        result.thead {
            expectThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse tr-tag`() {
        val result = aValidDocument()
        result.tr {
            expectThat(size).isEqualTo(4)
        }
    }
}