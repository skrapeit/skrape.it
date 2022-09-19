package it.skrape.selects.html5

import a3TimesNestedTag
import aValidDocument
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import it.skrape.core.htmlDocument
import it.skrape.selects.text
import kotlin.js.JsName
import kotlin.test.Test

class SectioningSelectorsKtTest {

    @JsName("CanParseBodyTag")
	@Test
	fun `can parse body-tag`() {
        val selector = aValidDocument().body {
            findAll {
                expect(this.size).toEqual(1)
            }
            toCssSelector
        }

        expect(selector).toEqual("body")
    }

    @JsName("CanParseDivTag")
	@Test
	fun `can parse div-tag`() {

        htmlDocument(a3TimesNestedTag("div")) {
            div {
                findAll {
                    expect(text).toEqual("1 2 3 2 3 3")
                }
                findFirst {
                    expect(text).toEqual("1 2 3")
                }
                div {
                    findAll {
                        expect(text).toEqual("2 3 3")
                    }
                    findFirst {
                        expect(text).toEqual("2 3")
                        div {
                            findAll {
                                expect(text).toEqual("3")
                            }
                        }
                    }
                }
            }
        }
    }

    @JsName("CanParseSectionTag")
	@Test
	fun `can parse section-tag`() {
        htmlDocument(a3TimesNestedTag("section")) {
            section {
                findAll {
                    expect(text).toEqual("1 2 3 2 3 3")
                }
                findFirst {
                    expect(text).toEqual("1 2 3")
                }
                section {
                    findAll {
                        expect(text).toEqual("2 3 3")
                    }
                    findFirst {
                        expect(text).toEqual("2 3")
                        section {
                            findAll {
                                expect(text).toEqual("3")
                            }
                        }
                    }
                }
            }
        }
    }

    @JsName("CanParseNavTag")
	@Test
	fun `can parse nav-tag`() {
        htmlDocument(a3TimesNestedTag("nav")) {
            nav {
                findAll {
                    expect(text).toEqual("1 2 3 2 3 3")
                }
                findFirst {
                    expect(text).toEqual("1 2 3")
                }
                nav {
                    findAll {
                        expect(text).toEqual("2 3 3")
                    }
                    findFirst {
                        expect(text).toEqual("2 3")
                        nav {
                            findAll {
                                expect(text).toEqual("3")
                            }
                        }
                    }
                }
            }
        }
    }

    @JsName("CanParseArticleTag")
	@Test
	fun `can parse article-tag`() {
        htmlDocument(a3TimesNestedTag("article")) {
            article {
                findAll {
                    expect(text).toEqual("1 2 3 2 3 3")
                }
                findFirst {
                    expect(text).toEqual("1 2 3")
                }
                article {
                    findAll {
                        expect(text).toEqual("2 3 3")
                    }
                    findFirst {
                        expect(text).toEqual("2 3")
                        article {
                            findAll {
                                expect(text).toEqual("3")
                            }
                        }
                    }
                }
            }
        }
    }

    @JsName("CanParseAsideTag")
	@Test
	fun `can parse aside-tag`() {
        htmlDocument(a3TimesNestedTag("aside")) {
            aside {
                findAll {
                    expect(text).toEqual("1 2 3 2 3 3")
                }
                findFirst {
                    expect(text).toEqual("1 2 3")
                }
                aside {
                    findAll {
                        expect(text).toEqual("2 3 3")
                    }
                    findFirst {
                        expect(text).toEqual("2 3")
                        aside {
                            findAll {
                                expect(text).toEqual("3")
                            }
                        }
                    }
                }
            }
        }
    }

    @JsName("CanParseH1Tag")
	@Test
	fun `can parse h1-tag`() {
        htmlDocument("<div><h1>hello</h1></div>") {
            println("Doc contains $allElements")
            h1 {
                findAll {
                    expect(text).toEqual("hello")
                }
            }
            div {
                findFirst {
                    h1 {
                        findFirst {
                            expect(text).toEqual("hello")
                        }
                    }
                }
                h1 {
                    findFirst {
                        expect(text).toEqual("hello")
                    }
                }
            }
        }
    }

    @JsName("CanParseH2Tag")
	@Test
	fun `can parse h2-tag`() {
        htmlDocument("<div><h2>hello</h2></div>") {
            h2 {
                findAll {
                    expect(text).toEqual("hello")
                }
            }
            div {
                findFirst {
                    h2 {
                        findFirst {
                            expect(text).toEqual("hello")
                        }
                    }
                }
                h2 {
                    findFirst {
                        expect(text).toEqual("hello")
                    }
                }
            }
        }
    }

    @JsName("CanParseH3Tag")
	@Test
	fun `can parse h3-tag`() {
        htmlDocument("<div><h3>hello</h3></div>") {
            h3 {
                findAll {
                    expect(text).toEqual("hello")
                }
            }
            div {
                findFirst {
                    h3 {
                        findFirst {
                            expect(text).toEqual("hello")
                        }
                    }
                }
                h3 {
                    findFirst {
                        expect(text).toEqual("hello")
                    }
                }
            }
        }
    }

    @JsName("CanParseH4Tag")
	@Test
	fun `can parse h4-tag`() {
        htmlDocument("<div><h4>hello</h4></div>") {
            h4 {
                findAll {
                    expect(text).toEqual("hello")
                }
            }
            div {
                findFirst {
                    h4 {
                        findFirst {
                            expect(text).toEqual("hello")
                        }
                    }
                }
                h4 {
                    findFirst {
                        expect(text).toEqual("hello")
                    }
                }
            }
        }
    }

    @JsName("CanParseH5Tag")
	@Test
	fun `can parse h5-tag`() {
        htmlDocument("<div><h5>hello</h5></div>") {
            h5 {
                findAll {
                    expect(text).toEqual("hello")
                }
            }
            div {
                findFirst {
                    h5 {
                        findFirst {
                            expect(text).toEqual("hello")
                        }
                    }
                }
                h5 {
                    findFirst {
                        expect(text).toEqual("hello")
                    }
                }
            }
        }
    }

    @JsName("CanParseH6Tag")
	@Test
	fun `can parse h6-tag`() {
        htmlDocument("<div><h6>hello</h6></div>") {
            h6 {
                findAll {
                    expect(text).toEqual("hello")
                }
            }
            div {
                findFirst {
                    h6 {
                        findFirst {
                            expect(text).toEqual("hello")
                        }
                    }
                }
                h6 {
                    findFirst {
                        expect(text).toEqual("hello")
                    }
                }
            }
        }
    }

    @JsName("CanParseHeaderTag")
	@Test
	fun `can parse header-tag`() {
        htmlDocument(a3TimesNestedTag("header")) {
            header {
                findAll {
                    expect(text).toEqual("1 2 3 2 3 3")
                }
                findFirst {
                    expect(text).toEqual("1 2 3")
                }
                header {
                    findAll {
                        expect(text).toEqual("2 3 3")
                    }
                    findFirst {
                        expect(text).toEqual("2 3")
                        header {
                            findAll {
                                expect(text).toEqual("3")
                            }
                        }
                    }
                }
            }
        }
    }

    @JsName("CanParseFooterTag")
	@Test
	fun `can parse footer-tag`() {
        htmlDocument(a3TimesNestedTag("footer")) {
            footer {
                findAll {
                    expect(text).toEqual("1 2 3 2 3 3")
                }
                findFirst {
                    expect(text).toEqual("1 2 3")
                }
                footer {
                    findAll {
                        expect(text).toEqual("2 3 3")
                    }
                    findFirst {
                        expect(text).toEqual("2 3")
                        footer {
                            findAll {
                                expect(text).toEqual("3")
                            }
                        }
                    }
                }
            }
        }
    }

    @JsName("CanParseAddressTag")
	@Test
	fun `can parse address-tag`() {
        htmlDocument(a3TimesNestedTag("address")) {
            address {
                findAll {
                    expect(text).toEqual("1 2 3 2 3 3")
                }
                findFirst {
                    expect(text).toEqual("1 2 3")
                }
                address {
                    findAll {
                        expect(text).toEqual("2 3 3")
                    }
                    findFirst {
                        expect(text).toEqual("2 3")
                        address {
                            findAll {
                                expect(text).toEqual("3")
                            }
                        }
                    }
                }
            }
        }
    }

    @JsName("CanParseMainTag")
	@Test
	fun `can parse main-tag`() {
        htmlDocument(a3TimesNestedTag("main")) {
            main {
                findAll {
                    expect(text).toEqual("1 2 3 2 3 3")
                }
                findFirst {
                    expect(text).toEqual("1 2 3")
                }
                main {
                    findAll {
                        expect(text).toEqual("2 3 3")
                    }
                    findFirst {
                        expect(text).toEqual("2 3")
                        main {
                            findAll {
                                expect(text).toEqual("3")
                            }
                        }
                    }
                }
            }
        }
    }
}