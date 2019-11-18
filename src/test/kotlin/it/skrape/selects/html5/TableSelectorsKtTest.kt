package it.skrape.selects.html5

import it.skrape.aValidDocument
import it.skrape.matchers.toBePresentExactlyOnce
import it.skrape.matchers.toBePresentTimes
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class TableSelectorsKtTest {

    @Test
    fun `can parse caption-tag`() {
        val selector = aValidDocument().caption {
            findFirst {
                expectThat(text).isEqualTo("i'm the caption")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("caption")
    }

    @Test
    fun `can parse col-tag`() {
        val selector = aValidDocument().col {
            findAll {
                toBePresentTimes(2)
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("col")
    }

    @Test
    fun `can parse colgroup-tag`() {
        val selector = aValidDocument().colgroup {
            findAll {
                toBePresentExactlyOnce()
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("colgroup")
    }

    @Test
    fun `can parse table-tag`() {
        val selector = aValidDocument().table {
            findAll {
                toBePresentExactlyOnce()
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("table")
    }

    @Test
    fun `can parse tbody-tag`() {
        val selector = aValidDocument().tbody {
            findAll {
                toBePresentExactlyOnce()
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("tbody")
    }

    @Test
    fun `can parse td-tag`() {
        val selector = aValidDocument().td {
            findAll {
                toBePresentTimes(6)
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("td")
    }

    @Test
    fun `can parse tfoot-tag`() {
        val selector = aValidDocument().tfoot {
            findAll {
                toBePresentExactlyOnce()
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("tfoot")
    }

    @Test
    fun `can parse th-tag`() {
        val selector = aValidDocument().th {
            findAll {
                toBePresentTimes(2)
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("th")
    }

    @Test
    fun `can parse thead-tag`() {
        val selector = aValidDocument().thead {
            findAll {
                toBePresentExactlyOnce()
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("thead")
    }

    @Test
    fun `can parse tr-tag`() {
        val selector = aValidDocument().tr {
            findAll {
                toBePresentTimes(4)
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("tr")
    }
}