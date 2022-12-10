package it.skrape.selects.html5

import a3TimesNestedTag
import aValidDocument
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import it.skrape.core.htmlDocument
import it.skrape.selects.text

class SectioningSelectorsKtTest: FunSpec({

    test("can parse body-tag") {
        val selector = aValidDocument().body {
            findAll {
                this.size.shouldBe(1)
            }
            toCssSelector
        }

        selector.shouldBe("body")
    }

    test("can parse div-tag") {

        htmlDocument(a3TimesNestedTag("div")) {
            div {
                findAll {
                    text.shouldBe("1 2 3 2 3 3")
                }
                findFirst {
                    text.shouldBe("1 2 3")
                }
                div {
                    findAll {
                        text.shouldBe("2 3 3")
                    }
                    findFirst {
                        text.shouldBe("2 3")
                        div {
                            findAll {
                                text.shouldBe("3")
                            }
                        }
                    }
                }
            }
        }
    }

    test("can parse section-tag") {
        htmlDocument(a3TimesNestedTag("section")) {
            section {
                findAll {
                    text.shouldBe("1 2 3 2 3 3")
                }
                findFirst {
                    text.shouldBe("1 2 3")
                }
                section {
                    findAll {
                        text.shouldBe("2 3 3")
                    }
                    findFirst {
                        text.shouldBe("2 3")
                        section {
                            findAll {
                                text.shouldBe("3")
                            }
                        }
                    }
                }
            }
        }
    }

    test("can parse nav-tag") {
        htmlDocument(a3TimesNestedTag("nav")) {
            nav {
                findAll {
                    text.shouldBe("1 2 3 2 3 3")
                }
                findFirst {
                    text.shouldBe("1 2 3")
                }
                nav {
                    findAll {
                        text.shouldBe("2 3 3")
                    }
                    findFirst {
                        text.shouldBe("2 3")
                        nav {
                            findAll {
                                text.shouldBe("3")
                            }
                        }
                    }
                }
            }
        }
    }

    test("can parse article-tag") {
        htmlDocument(a3TimesNestedTag("article")) {
            article {
                findAll {
                    text.shouldBe("1 2 3 2 3 3")
                }
                findFirst {
                    text.shouldBe("1 2 3")
                }
                article {
                    findAll {
                        text.shouldBe("2 3 3")
                    }
                    findFirst {
                        text.shouldBe("2 3")
                        article {
                            findAll {
                                text.shouldBe("3")
                            }
                        }
                    }
                }
            }
        }
    }

    test("can parse aside-tag") {
        htmlDocument(a3TimesNestedTag("aside")) {
            aside {
                findAll {
                    text.shouldBe("1 2 3 2 3 3")
                }
                findFirst {
                    text.shouldBe("1 2 3")
                }
                aside {
                    findAll {
                        text.shouldBe("2 3 3")
                    }
                    findFirst {
                        text.shouldBe("2 3")
                        aside {
                            findAll {
                                text.shouldBe("3")
                            }
                        }
                    }
                }
            }
        }
    }

    test("can parse h1-tag") {
        htmlDocument("<div><h1>hello</h1></div>") {
            println("Doc contains $allElements")
            h1 {
                findAll {
                    text.shouldBe("hello")
                }
            }
            div {
                findFirst {
                    h1 {
                        findFirst {
                            text.shouldBe("hello")
                        }
                    }
                }
                h1 {
                    findFirst {
                        text.shouldBe("hello")
                    }
                }
            }
        }
    }

    test("can parse h2-tag") {
        htmlDocument("<div><h2>hello</h2></div>") {
            h2 {
                findAll {
                    text.shouldBe("hello")
                }
            }
            div {
                findFirst {
                    h2 {
                        findFirst {
                            text.shouldBe("hello")
                        }
                    }
                }
                h2 {
                    findFirst {
                        text.shouldBe("hello")
                    }
                }
            }
        }
    }

    test("can parse h3-tag") {
        htmlDocument("<div><h3>hello</h3></div>") {
            h3 {
                findAll {
                    text.shouldBe("hello")
                }
            }
            div {
                findFirst {
                    h3 {
                        findFirst {
                            text.shouldBe("hello")
                        }
                    }
                }
                h3 {
                    findFirst {
                        text.shouldBe("hello")
                    }
                }
            }
        }
    }

    test("can parse h4-tag") {
        htmlDocument("<div><h4>hello</h4></div>") {
            h4 {
                findAll {
                    text.shouldBe("hello")
                }
            }
            div {
                findFirst {
                    h4 {
                        findFirst {
                            text.shouldBe("hello")
                        }
                    }
                }
                h4 {
                    findFirst {
                        text.shouldBe("hello")
                    }
                }
            }
        }
    }

    test("can parse h5-tag") {
        htmlDocument("<div><h5>hello</h5></div>") {
            h5 {
                findAll {
                    text.shouldBe("hello")
                }
            }
            div {
                findFirst {
                    h5 {
                        findFirst {
                            text.shouldBe("hello")
                        }
                    }
                }
                h5 {
                    findFirst {
                        text.shouldBe("hello")
                    }
                }
            }
        }
    }

    test("can parse h6-tag") {
        htmlDocument("<div><h6>hello</h6></div>") {
            h6 {
                findAll {
                    text.shouldBe("hello")
                }
            }
            div {
                findFirst {
                    h6 {
                        findFirst {
                            text.shouldBe("hello")
                        }
                    }
                }
                h6 {
                    findFirst {
                        text.shouldBe("hello")
                    }
                }
            }
        }
    }

    test("can parse header-tag") {
        htmlDocument(a3TimesNestedTag("header")) {
            header {
                findAll {
                    text.shouldBe("1 2 3 2 3 3")
                }
                findFirst {
                    text.shouldBe("1 2 3")
                }
                header {
                    findAll {
                        text.shouldBe("2 3 3")
                    }
                    findFirst {
                        text.shouldBe("2 3")
                        header {
                            findAll {
                                text.shouldBe("3")
                            }
                        }
                    }
                }
            }
        }
    }

    test("can parse footer-tag") {
        htmlDocument(a3TimesNestedTag("footer")) {
            footer {
                findAll {
                    text.shouldBe("1 2 3 2 3 3")
                }
                findFirst {
                    text.shouldBe("1 2 3")
                }
                footer {
                    findAll {
                        text.shouldBe("2 3 3")
                    }
                    findFirst {
                        text.shouldBe("2 3")
                        footer {
                            findAll {
                                text.shouldBe("3")
                            }
                        }
                    }
                }
            }
        }
    }

    test("can parse address-tag") {
        htmlDocument(a3TimesNestedTag("address")) {
            address {
                findAll {
                    text.shouldBe("1 2 3 2 3 3")
                }
                findFirst {
                    text.shouldBe("1 2 3")
                }
                address {
                    findAll {
                        text.shouldBe("2 3 3")
                    }
                    findFirst {
                        text.shouldBe("2 3")
                        address {
                            findAll {
                                text.shouldBe("3")
                            }
                        }
                    }
                }
            }
        }
    }

    test("can parse main-tag") {
        htmlDocument(a3TimesNestedTag("main")) {
            main {
                findAll {
                    text.shouldBe("1 2 3 2 3 3")
                }
                findFirst {
                    text.shouldBe("1 2 3")
                }
                main {
                    findAll {
                        text.shouldBe("2 3 3")
                    }
                    findFirst {
                        text.shouldBe("2 3")
                        main {
                            findAll {
                                text.shouldBe("3")
                            }
                        }
                    }
                }
            }
        }
    }
})