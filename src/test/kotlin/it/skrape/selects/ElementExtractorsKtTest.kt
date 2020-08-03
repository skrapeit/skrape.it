package it.skrape.selects

import it.skrape.aValidDocument
import it.skrape.matchers.toBe
import it.skrape.selects.html5.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@Execution(CONCURRENT)
internal class ElementExtractorsKtTest {

    private val document = aValidDocument() { this }

    @Test
    fun `can pick element from element`() {
        // TODO
    }

    @Test
    fun `can pick elements from element`() {
        // TODO
    }

    @Test
    fun `can pick element from elements`() {
        // TODO
    }

    @Test
    fun `can pick elements from elements`() {
        // TODO
    }

    @Test
    fun `can pick elements firstOccurrence`() {
        val firstText = document.p {
            findFirst {
                text toBe "i'm a paragraph"
            }
        }
        expectThat(firstText).isEqualTo("i'm a paragraph")
    }

    @Test
    fun `can pick own element text only`() {
        val firstText = document.div {
            withClass = "with-children"
            findFirst {
                ownText toBe "i'm a parent div"
            }
        }
        expectThat(firstText).isEqualTo("i'm a parent div")
    }

    @Test
    fun `can pick elements secondOccurrence`() {
        val secondText = document.p {
            findSecond {
                text toBe "i'm a second paragraph"
            }
        }
        expectThat(secondText).isEqualTo("i'm a second paragraph")
    }

    @Test
    fun `can pick elements by index`() {
        val secondText = document.p {
            findByIndex(1) {
                text toBe "i'm a second paragraph"
            }
        }
        expectThat(secondText).isEqualTo("i'm a second paragraph")
    }

    @Test
    fun `can pick elements by index via invoke index`() {
        val secondText = document.p {
            1 {
                text toBe "i'm a second paragraph"
            }
        }
        expectThat(secondText).isEqualTo("i'm a second paragraph")
    }

    @Test
    fun `can pick elements thirdOccurrence`() {
        val thirdText = document.p {
            findThird {
                text toBe "i'm a paragraph with word break"
            }
        }
        expectThat(thirdText).isEqualTo("i'm a paragraph with word break")
    }

    @Test
    fun `can pick elements lastOccurrence`() {
        val lastText = document.p {
            findLast {
                text toBe "i'm the last paragraph"
            }
        }
        expectThat(lastText).isEqualTo("i'm the last paragraph")
    }

    @Test
    fun `can pick elements secondlastOccurrence`() {
        val secondLastText = document.p {
            findSecondLast {
                text toBe "i'm a paragraph with word break"
            }
        }
        expectThat(secondLastText).isEqualTo("i'm a paragraph with word break")
    }

    @Test
    fun `can pick element with cascading selector on table - foot`() {
        val pickedElementText = document.table {
            tfoot {
                tr {
                    td {
                        findSecond {
                            text toBe "second foot td"
                        }
                    }
                }
            }
        }
        expectThat(pickedElementText).isEqualTo("second foot td")
    }

    @Test
    fun `can pick element with cascading selector on table - head`() {
        val pickedElementText = document.table {
            thead {
                tr {
                    th {
                        findFirst {
                            text toBe "first th"
                        }
                    }
                }
            }
        }
        expectThat(pickedElementText).isEqualTo("first th")
    }

    @Test
    fun `can pick element with cascading selector on table - body`() {
        val pickedElementText = document.table {
            tbody {
                tr {
                    findSecond {
                        findFirst("td") {
                            text
                        }
                    }
                }
            }
        }
        expectThat(pickedElementText).isEqualTo("barfoo")
    }

    @Test
    fun `can pick element with cascading selector on table - colgroup`() {
        val pickedElementText = document.table {
            colgroup {
                col {
                    withAttributeKey = "span"
                    findFirst {
                        attribute("span")
                    }
                }
            }
        }
        expectThat(pickedElementText).isEqualTo("2")
    }
}