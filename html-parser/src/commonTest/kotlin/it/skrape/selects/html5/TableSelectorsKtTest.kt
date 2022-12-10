package it.skrape.selects.html5

import aValidDocument
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class TableSelectorsKtTest: FunSpec({

    test("can parse caption-tag") {
        val selector = aValidDocument().caption {
            findFirst {
                text.shouldBe("i'm the caption")
            }
            toCssSelector
        }

        selector.shouldBe("caption")
    }

    test("can parse col-tag") {
        val selector = aValidDocument().col {
            findAll {
                this.size.shouldBe(2)
            }
            toCssSelector
        }

        selector.shouldBe("col")
    }

    test("can parse colgroup-tag") {
        val selector = aValidDocument().colgroup {
            findAll {
                this.size.shouldBe(1)
            }
            toCssSelector
        }

        selector.shouldBe("colgroup")
    }

    test("can parse table-tag") {
        val selector = aValidDocument().table {
            findAll {
                this.size.shouldBe(1)
            }
            toCssSelector
        }

        selector.shouldBe("table")
    }

    test("can parse tbody-tag") {
        val selector = aValidDocument().tbody {
            findAll {
                this.size.shouldBe(1)
            }
            toCssSelector
        }

        selector.shouldBe("tbody")
    }

    test("can parse td-tag") {
        val selector = aValidDocument().td {
            findAll {
                this.size.shouldBe(6)
            }
            toCssSelector
        }

        selector.shouldBe("td")
    }

    test("can parse tfoot-tag") {
        val selector = aValidDocument().tfoot {
            findAll {
                this.size.shouldBe(1)
            }
            toCssSelector
        }

        selector.shouldBe("tfoot")
    }

    test("can parse th-tag") {
        val selector = aValidDocument().th {
            findAll {
                this.size.shouldBe(2)
            }
            toCssSelector
        }

        selector.shouldBe("th")
    }

    test("can parse thead-tag") {
        val selector = aValidDocument().thead {
            findAll {
                this.size.shouldBe(1)
            }
            toCssSelector
        }

        selector.shouldBe("thead")
    }

    test("can parse tr-tag") {
        val selector = aValidDocument().tr {
            findAll {
                this.size.shouldBe(4)
            }
            toCssSelector
        }

        selector.shouldBe("tr")
    }
})