package it.skrape.selects.html5

import it.skrape.a3TimesNestedTag
import it.skrape.aValidDocument
import it.skrape.core.htmlDocument
import it.skrape.selects.text
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class SectioningSelectorsKtTest {

    @Test
    fun `can parse body-tag`() {
        val selector = aValidDocument().body {
            findAll {
                expectThat(this.size).isEqualTo(1)
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
                    expectThat(text).isEqualTo("1 2 3 2 3 3")
                }
                findFirst {
                    expectThat(text).isEqualTo("1 2 3")
                }
                div {
                    findAll {
                        expectThat(text).isEqualTo("2 3 3")
                    }
                    findFirst {
                        expectThat(text).isEqualTo("2 3")
                        div {
                            findAll {
                                expectThat(text).isEqualTo("3")
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
                    expectThat(text).isEqualTo("1 2 3 2 3 3")
                }
                findFirst {
                    expectThat(text).isEqualTo("1 2 3")
                }
                section {
                    findAll {
                        expectThat(text).isEqualTo("2 3 3")
                    }
                    findFirst {
                        expectThat(text).isEqualTo("2 3")
                        section {
                            findAll {
                                expectThat(text).isEqualTo("3")
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
                    expectThat(text).isEqualTo("1 2 3 2 3 3")
                }
                findFirst {
                    expectThat(text).isEqualTo("1 2 3")
                }
                nav {
                    findAll {
                        expectThat(text).isEqualTo("2 3 3")
                    }
                    findFirst {
                        expectThat(text).isEqualTo("2 3")
                        nav {
                            findAll {
                                expectThat(text).isEqualTo("3")
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
                    expectThat(text).isEqualTo("1 2 3 2 3 3")
                }
                findFirst {
                    expectThat(text).isEqualTo("1 2 3")
                }
                article {
                    findAll {
                        expectThat(text).isEqualTo("2 3 3")
                    }
                    findFirst {
                        expectThat(text).isEqualTo("2 3")
                        article {
                            findAll {
                                expectThat(text).isEqualTo("3")
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
                    expectThat(text).isEqualTo("1 2 3 2 3 3")
                }
                findFirst {
                    expectThat(text).isEqualTo("1 2 3")
                }
                aside {
                    findAll {
                        expectThat(text).isEqualTo("2 3 3")
                    }
                    findFirst {
                        expectThat(text).isEqualTo("2 3")
                        aside {
                            findAll {
                                expectThat(text).isEqualTo("3")
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
                findAll {
                    expectThat(text).isEqualTo("hello")
                }
            }
            div {
                findFirst {
                    h1 {
                        findFirst {
                            expectThat(text).isEqualTo("hello")
                        }
                    }
                }
                h1 {
                    findFirst {
                        expectThat(text).isEqualTo("hello")
                    }
                }
            }
        }
    }

    @Test
    fun `can parse h2-tag`() {
        htmlDocument("<div><h2>hello</h2></div>") {
            h2 {
                findAll {
                    expectThat(text).isEqualTo("hello")
                }
            }
            div {
                findFirst {
                    h2 {
                        findFirst {
                            expectThat(text).isEqualTo("hello")
                        }
                    }
                }
                h2 {
                    findFirst {
                        expectThat(text).isEqualTo("hello")
                    }
                }
            }
        }
    }

    @Test
    fun `can parse h3-tag`() {
        htmlDocument("<div><h3>hello</h3></div>") {
            h3 {
                findAll {
                    expectThat(text).isEqualTo("hello")
                }
            }
            div {
                findFirst {
                    h3 {
                        findFirst {
                            expectThat(text).isEqualTo("hello")
                        }
                    }
                }
                h3 {
                    findFirst {
                        expectThat(text).isEqualTo("hello")
                    }
                }
            }
        }
    }

    @Test
    fun `can parse h4-tag`() {
        htmlDocument("<div><h4>hello</h4></div>") {
            h4 {
                findAll {
                    expectThat(text).isEqualTo("hello")
                }
            }
            div {
                findFirst {
                    h4 {
                        findFirst {
                            expectThat(text).isEqualTo("hello")
                        }
                    }
                }
                h4 {
                    findFirst {
                        expectThat(text).isEqualTo("hello")
                    }
                }
            }
        }
    }

    @Test
    fun `can parse h5-tag`() {
        htmlDocument("<div><h5>hello</h5></div>") {
            h5 {
                findAll {
                    expectThat(text).isEqualTo("hello")
                }
            }
            div {
                findFirst {
                    h5 {
                        findFirst {
                            expectThat(text).isEqualTo("hello")
                        }
                    }
                }
                h5 {
                    findFirst {
                        expectThat(text).isEqualTo("hello")
                    }
                }
            }
        }
    }

    @Test
    fun `can parse h6-tag`() {
        htmlDocument("<div><h6>hello</h6></div>") {
            h6 {
                findAll {
                    expectThat(text).isEqualTo("hello")
                }
            }
            div {
                findFirst {
                    h6 {
                        findFirst {
                            expectThat(text).isEqualTo("hello")
                        }
                    }
                }
                h6 {
                    findFirst {
                        expectThat(text).isEqualTo("hello")
                    }
                }
            }
        }
    }

    @Test
    fun `can parse header-tag`() {
        htmlDocument(a3TimesNestedTag("header")) {
            header {
                findAll {
                    expectThat(text).isEqualTo("1 2 3 2 3 3")
                }
                findFirst {
                    expectThat(text).isEqualTo("1 2 3")
                }
                header {
                    findAll {
                        expectThat(text).isEqualTo("2 3 3")
                    }
                    findFirst {
                        expectThat(text).isEqualTo("2 3")
                        header {
                            findAll {
                                expectThat(text).isEqualTo("3")
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
                    expectThat(text).isEqualTo("1 2 3 2 3 3")
                }
                findFirst {
                    expectThat(text).isEqualTo("1 2 3")
                }
                footer {
                    findAll {
                        expectThat(text).isEqualTo("2 3 3")
                    }
                    findFirst {
                        expectThat(text).isEqualTo("2 3")
                        footer {
                            findAll {
                                expectThat(text).isEqualTo("3")
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
                    expectThat(text).isEqualTo("1 2 3 2 3 3")
                }
                findFirst {
                    expectThat(text).isEqualTo("1 2 3")
                }
                address {
                    findAll {
                        expectThat(text).isEqualTo("2 3 3")
                    }
                    findFirst {
                        expectThat(text).isEqualTo("2 3")
                        address {
                            findAll {
                                expectThat(text).isEqualTo("3")
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
                    expectThat(text).isEqualTo("1 2 3 2 3 3")
                }
                findFirst {
                    expectThat(text).isEqualTo("1 2 3")
                }
                main {
                    findAll {
                        expectThat(text).isEqualTo("2 3 3")
                    }
                    findFirst {
                        expectThat(text).isEqualTo("2 3")
                        main {
                            findAll {
                                expectThat(text).isEqualTo("3")
                            }
                        }
                    }
                }
            }
        }
    }
}