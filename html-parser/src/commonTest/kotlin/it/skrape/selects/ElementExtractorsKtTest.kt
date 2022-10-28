package it.skrape.selects

import aValidDocument
import ch.tutteli.atrium.api.fluent.en_GB.toContainExactly
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
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
                text.also { expect(it).toEqual("i'm a paragraph") }
            }
        }
        expect(firstText).toEqual("i'm a paragraph")
    }

    @JsName("CanPickOwnElementTextOnly")
	@Test
	fun `can pick own element text only`() {
        val firstText = document.div {
            withClass = "with-children"
            findFirst {
                ownText.also { expect(it).toEqual("i'm a parent div") }
            }
        }
        expect(firstText).toEqual("i'm a parent div")
    }

    @JsName("CanPickElementsSecondOccurrence")
	@Test
	fun `can pick elements secondOccurrence`() {
        val secondText = document.p {
            findSecond {
                text.also { expect(it).toEqual("i'm a second paragraph") }
            }
        }
        expect(secondText).toEqual("i'm a second paragraph")
    }

    @JsName("CanPickElementsByIndex")
	@Test
	fun `can pick elements by index`() {
        val secondText = document.p {
            findByIndex(1) {
                text.also { expect(it).toEqual("i'm a second paragraph") }
            }
        }
        expect(secondText).toEqual("i'm a second paragraph")
    }

    @JsName("CanPickElementsByIndexViaInvokeIndex")
	@Test
	fun `can pick elements by index via invoke index`() {
        val secondText = document.p {
            1 {
                text.also { expect(it).toEqual("i'm a second paragraph") }
            }
        }
        expect(secondText).toEqual("i'm a second paragraph")
    }

    @JsName("CanPickElementsThirdOccurrence")
	@Test
	fun `can pick elements thirdOccurrence`() {
        val thirdText = document.p {
            findThird {
                text.also { expect(it).toEqual("i'm a paragraph with word break") }
            }
        }
        expect(thirdText).toEqual("i'm a paragraph with word break")
    }

    @JsName("CanPickElementsLastOccurrence")
	@Test
	fun `can pick elements lastOccurrence`() {
        val lastText = document.p {
            findLast {
                text.also { expect(it).toEqual("i'm the last paragraph") }
            }
        }
        expect(lastText).toEqual("i'm the last paragraph")
    }

    @JsName("CanPickElementsSecondlastOccurrence")
	@Test
	fun `can pick elements secondlastOccurrence`() {
        val secondLastText = document.p {
            findSecondLast {
                text.also { expect(it).toEqual("i'm a paragraph with word break") }
            }
        }
        expect(secondLastText).toEqual("i'm a paragraph with word break")
    }

    @JsName("CanPickElementWithCascadingSelectorOnTableFoot")
	@Test
	fun `can pick element with cascading selector on table - foot`() {
        val pickedElementText = document.table {
            tfoot {
                tr {
                    td {
                        findSecond {
                            text.also { expect(it).toEqual("second foot td") }
                        }
                    }
                }
            }
        }
        expect(pickedElementText).toEqual("second foot td")
    }

    @JsName("CanPickElementWithCascadingSelectorOnTableHead")
	@Test
	fun `can pick element with cascading selector on table - head`() {
        val pickedElementText = document.table {
            thead {
                tr {
                    th {
                        findFirst {
                            text.also { expect(it).toEqual("first th") }
                        }
                    }
                }
            }
        }
        expect(pickedElementText).toEqual("first th")
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
        expect(pickedElementText).toEqual("barfoo")
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
        expect(pickedElementText).toEqual("2")
    }

    @JsName("CanPickElementByCssSelectorMatchingRegex")
	@Test
	fun `can pick element by css selector matching regex`() {
        val someRegex = "^(ol|ul).*navigation$".toRegex()

        aValidDocument {
            findBySelectorMatching(someRegex) {
                expect(map { it.toCssSelector }).toContainExactly(
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
                expect(map { it.toCssSelector }).toContainExactly(
                    "html > body > header > nav > ol.ordered-navigation",
                    "html > body > header > nav > ul.unordered-navigation"
                )
            }
        }
    }
}