package it.skrape.selects

import aValidDocument
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import it.skrape.selects.html5.*
import kotlin.js.JsName
import kotlin.test.Test

class ElementExtractorsKtTest {

    private val document = aValidDocument { this }

    @JsName("CanPickElementsFirstOccurrence")
	@Test
	fun `can pick elements firstOccurrence`() {
        val firstText = document.p {
            findFirst {
                text.also { it.shouldBe("i'm a paragraph") }
            }
        }
        firstText.shouldBe("i'm a paragraph")
    }

    @JsName("CanPickOwnElementTextOnly")
	@Test
	fun `can pick own element text only`() {
        val firstText = document.div {
            withClass = "with-children"
            findFirst {
                ownText.also { it.shouldBe("i'm a parent div") }
            }
        }
        firstText.shouldBe("i'm a parent div")
    }

    @JsName("CanPickElementsSecondOccurrence")
	@Test
	fun `can pick elements secondOccurrence`() {
        val secondText = document.p {
            findSecond {
                text.also { it.shouldBe("i'm a second paragraph") }
            }
        }
        secondText.shouldBe("i'm a second paragraph")
    }

    @JsName("CanPickElementsByIndex")
	@Test
	fun `can pick elements by index`() {
        val secondText = document.p {
            findByIndex(1) {
                text.also { it.shouldBe("i'm a second paragraph") }
            }
        }
        secondText.shouldBe("i'm a second paragraph")
    }

    @JsName("CanPickElementsByIndexViaInvokeIndex")
	@Test
	fun `can pick elements by index via invoke index`() {
        val secondText = document.p {
            1 {
                text.also { it.shouldBe("i'm a second paragraph") }
            }
        }
        secondText.shouldBe("i'm a second paragraph")
    }

    @JsName("CanPickElementsThirdOccurrence")
	@Test
	fun `can pick elements thirdOccurrence`() {
        val thirdText = document.p {
            findThird {
                text.also { it.shouldBe("i'm a paragraph with word break") }
            }
        }
        thirdText.shouldBe("i'm a paragraph with word break")
    }

    @JsName("CanPickElementsLastOccurrence")
	@Test
	fun `can pick elements lastOccurrence`() {
        val lastText = document.p {
            findLast {
                text.also { it.shouldBe("i'm the last paragraph") }
            }
        }
        lastText.shouldBe("i'm the last paragraph")
    }

    @JsName("CanPickElementsSecondlastOccurrence")
	@Test
	fun `can pick elements secondlastOccurrence`() {
        val secondLastText = document.p {
            findSecondLast {
                text.also { it.shouldBe("i'm a paragraph with word break") }
            }
        }
        secondLastText.shouldBe("i'm a paragraph with word break")
    }

    @JsName("CanPickElementWithCascadingSelectorOnTableFoot")
	@Test
	fun `can pick element with cascading selector on table - foot`() {
        val pickedElementText = document.table {
            tfoot {
                tr {
                    td {
                        findSecond {
                            text.also { it.shouldBe("second foot td") }
                        }
                    }
                }
            }
        }
        pickedElementText.shouldBe("second foot td")
    }

    @JsName("CanPickElementWithCascadingSelectorOnTableHead")
	@Test
	fun `can pick element with cascading selector on table - head`() {
        val pickedElementText = document.table {
            thead {
                tr {
                    th {
                        findFirst {
                            text.also { it.shouldBe("first th") }
                        }
                    }
                }
            }
        }
        pickedElementText.shouldBe("first th")
    }

    @JsName("CanPickElementWithCascadingSelectorOnTableBody")
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
        pickedElementText.shouldBe("barfoo")
    }

    @JsName("CanPickElementWithCascadingSelectorOnTableColgroup")
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
        pickedElementText.shouldBe("2")
    }

    @JsName("CanPickElementByCssSelectorMatchingRegex")
	@Test
	fun `can pick element by css selector matching regex`() {
        val someRegex = "^(ol|ul).*navigation$".toRegex()

        aValidDocument {
            findBySelectorMatching(someRegex) {
                map { it.toCssSelector }.shouldContainExactly(
                    "html > body > header > nav > ol.ordered-navigation",
                    "html > body > header > nav > ul.unordered-navigation"
                )
            }
        }
    }

    @JsName("CanPickElementByCssSelectorMatchingRegexDSLInvoke")
	@Test
	fun `can pick element by css selector matching regex DSL invoke`() {
        val someRegex = "^(ol|ul).*navigation$".toRegex()

        aValidDocument {
            someRegex {
                map { it.toCssSelector }.shouldContainExactly(
                    "html > body > header > nav > ol.ordered-navigation",
                    "html > body > header > nav > ul.unordered-navigation"
                )
            }
        }
    }
}