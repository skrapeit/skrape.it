package it.skrape.selects.html5

import it.skrape.a3TimesNestedTag
import it.skrape.aValidDocument
import it.skrape.core.htmlDocument
import it.skrape.matchers.toBe
import it.skrape.matchers.toBePresentExactlyOnce
import it.skrape.selects.text
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class SectioningSelectorsKtTest {

    @Test
    fun `can parse body-tag`() {
        val selector = aValidDocument().body {
            findAll {
                toBePresentExactlyOnce
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("body")
    }

    @Test
    fun `can parse div-tag`() {

        htmlDocument(a3TimesNestedTag("div")) {
            div {
                findAll {
                    // find text of all divs including it children (1 2 3 & 2 3 & 3)
                    text toBe "1 2 3 2 3 3"
                }
                findFirst {
                    text toBe "1 2 3"
                }
                div {
                    findAll {
                        text toBe "2 3 3"
                    }
                    findFirst {
                        text toBe "2 3"
                        div {
                            findAll {
                                text toBe "3"
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    fun `can parse section-tag`() {
        htmlDocument(a3TimesNestedTag("section")) {
            section {
                findAll {
                    text toBe "1 2 3 2 3 3"
                }
                findFirst {
                    text toBe "1 2 3"
                }
                section {
                    findAll {
                        text toBe "2 3 3"
                    }
                    findFirst {
                        text toBe "2 3"
                        section {
                            findAll {
                                text toBe "3"
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    fun `can parse nav-tag`() {
        htmlDocument(a3TimesNestedTag("nav")) {
            nav {
                findAll {
                    text toBe "1 2 3 2 3 3"
                }
                findFirst {
                    text toBe "1 2 3"
                }
                nav {
                    findAll {
                        text toBe "2 3 3"
                    }
                    findFirst {
                        text toBe "2 3"
                        nav {
                            findAll {
                                text toBe "3"
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    fun `can parse article-tag`() {
        htmlDocument(a3TimesNestedTag("article")) {
            article {
                findAll {
                    text toBe "1 2 3 2 3 3"
                }
                findFirst {
                    text toBe "1 2 3"
                }
                article {
                    findAll {
                        text toBe "2 3 3"
                    }
                    findFirst {
                        text toBe "2 3"
                        article {
                            findAll {
                                text toBe "3"
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    fun `can parse aside-tag`() {
        htmlDocument(a3TimesNestedTag("aside")) {
            aside {
                findAll {
                    text toBe "1 2 3 2 3 3"
                }
                findFirst {
                    text toBe "1 2 3"
                }
                aside {
                    findAll {
                        text toBe "2 3 3"
                    }
                    findFirst {
                        text toBe "2 3"
                        aside {
                            findAll {
                                text toBe "3"
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    fun `can parse h1-tag`() {
        htmlDocument("<div><h1>hello</h1></div>") {
            h1 {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    h1 { findFirst { text toBe "hello" } }
                }
                h1 {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse h2-tag`() {
        htmlDocument("<div><h2>hello</h2></div>") {
            h2 {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    h2 { findFirst { text toBe "hello" } }
                }
                h2 {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse h3-tag`() {
        htmlDocument("<div><h3>hello</h3></div>") {
            h3 {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    h3 { findFirst { text toBe "hello" } }
                }
                h3 {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse h4-tag`() {
        htmlDocument("<div><h4>hello</h4></div>") {
            h4 {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    h4 { findFirst { text toBe "hello" } }
                }
                h4 {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse h5-tag`() {
        htmlDocument("<div><h5>hello</h5></div>") {
            h5 {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    h5 { findFirst { text toBe "hello" } }
                }
                h5 {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse h6-tag`() {
        htmlDocument("<div><h6>hello</h6></div>") {
            h6 {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    h6 { findFirst { text toBe "hello" } }
                }
                h6 {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse header-tag`() {
        htmlDocument(a3TimesNestedTag("header")) {
            header {
                findAll {
                    text toBe "1 2 3 2 3 3"
                }
                findFirst {
                    text toBe "1 2 3"
                }
                header {
                    findAll {
                        text toBe "2 3 3"
                    }
                    findFirst {
                        text toBe "2 3"
                        header {
                            findAll {
                                text toBe "3"
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    fun `can parse footer-tag`() {
        htmlDocument(a3TimesNestedTag("footer")) {
            footer {
                findAll {
                    text toBe "1 2 3 2 3 3"
                }
                findFirst {
                    text toBe "1 2 3"
                }
                footer {
                    findAll {
                        text toBe "2 3 3"
                    }
                    findFirst {
                        text toBe "2 3"
                        footer {
                            findAll {
                                text toBe "3"
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    fun `can parse address-tag`() {
        htmlDocument(a3TimesNestedTag("address")) {
            address {
                findAll {
                    text toBe "1 2 3 2 3 3"
                }
                findFirst {
                    text toBe "1 2 3"
                }
                address {
                    findAll {
                        text toBe "2 3 3"
                    }
                    findFirst {
                        text toBe "2 3"
                        address {
                            findAll {
                                text toBe "3"
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    fun `can parse main-tag`() {
        htmlDocument(a3TimesNestedTag("main")) {
            main {
                findAll {
                    text toBe "1 2 3 2 3 3"
                }
                findFirst {
                    text toBe "1 2 3"
                }
                main {
                    findAll {
                        text toBe "2 3 3"
                    }
                    findFirst {
                        text toBe "2 3"
                        main {
                            findAll {
                                text toBe "3"
                            }
                        }
                    }
                }
            }
        }
    }
}