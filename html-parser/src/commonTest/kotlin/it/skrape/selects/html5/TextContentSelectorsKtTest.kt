package it.skrape.selects.html5

import aValidDocument
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class TextContentSelectorsKtTest: FunSpec({

    test("can parse blockquote-tag") {
        val selector = aValidDocument().blockquote {
            findFirst {
                text.shouldBe("i'm a quote")
            }
            toCssSelector
        }

        selector.shouldBe("blockquote")
    }

    test("can parse dd-tag") {
        val selector = aValidDocument().dd {
            findAll {
                this.size.shouldBe(2)
            }
            toCssSelector
        }

        selector.shouldBe("dd")
    }

    test("can parse dir-tag") {
        val selector = aValidDocument().dir {
            findAll {
                this.size.shouldBe(1)
            }
            toCssSelector
        }

        selector.shouldBe("dir")
    }

    test("can parse dl-tag") {
        val selector = aValidDocument().dl {
            findAll {
                this.size.shouldBe(1)
            }
            toCssSelector
        }

        selector.shouldBe("dl")
    }

    test("can parse dt-tag") {
        val selector = aValidDocument().dt {
            findAll {
                this.size.shouldBe(2)
            }
            toCssSelector
        }

        selector.shouldBe("dt")
    }

    test("can parse figcaption-tag") {
        val selector = aValidDocument().figcaption {
            findAll {
                this.size.shouldBe(1)
            }
            toCssSelector
        }

        selector.shouldBe("figcaption")
    }

    test("can parse figure-tag") {
        val selector = aValidDocument().figure {
            findAll {
                this.size.shouldBe(1)
            }
            toCssSelector
        }

        selector.shouldBe("figure")
    }

    test("can parse hr-tag") {
        val selector = aValidDocument().hr {
            findAll {
                this.size.shouldBe(2)
            }
            toCssSelector
        }

        selector.shouldBe("hr")
    }

    test("can parse li-tag") {
        val selector = aValidDocument().li {
            findAll {
                this.size.shouldBe(11)
            }
            toCssSelector
        }

        selector.shouldBe("li")
    }

    test("can parse ol-tag") {
        val selector = aValidDocument().ol {
            findAll {
                this.size.shouldBe(1)
            }
            toCssSelector
        }

        selector.shouldBe("ol")
    }

    test("can parse ul-tag") {
        val selector = aValidDocument().ul {
            findAll {
                this.size.shouldBe(1)
            }
            toCssSelector
        }

        selector.shouldBe("ul")
    }

    test("can parse p-tag") {
        val selector = aValidDocument().p {
            findLast {
                text.shouldBe("i'm the last paragraph")
            }
            toCssSelector
        }

        selector.shouldBe("p")
    }

    test("can cascade p-tag") {
        val selector = aValidDocument().p {
            withClass = "first"
            p {
                withClass = "second"
                p {
                    withClass = "third"
                    toCssSelector
                }
            }
        }

        selector.shouldBe("p.first p.second p.third")
    }

    test("can parse pre-tag") {
        val selector = aValidDocument().pre {
            findFirst {
                text.shouldBe("i'm a pre")
            }
            toCssSelector
        }

        selector.shouldBe("pre")
    }
})