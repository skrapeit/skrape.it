package it.skrape.core

import getInputFromFile
import getMarkupFromFile
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.maps.shouldContain
import io.kotest.matchers.shouldBe
import io.ktor.utils.io.charsets.*
import it.skrape.selects.eachText
import it.skrape.selects.html5.button

class ParserTest : FunSpec({

    test("can parse HTML") {
        val htmlAsString = getMarkupFromFile("example.html")

        val result = htmlDocument(html = htmlAsString)

        assertSoftly(result) {
            titleText.shouldBe("i'm the title")
            result.findFirst("p").text.shouldBe("i'm a paragraph")
        }
    }

    test("can parse HTML and invoke document lambda") {
        val htmlAsString = getMarkupFromFile("example.html")

        htmlDocument(html = htmlAsString) {
            assertSoftly(this) {
                titleText.shouldBe("i'm the title")
                findFirst("p").text.shouldBe("i'm a paragraph")
            }
        }
    }

    test("can parse JS rendered HTML using uri scheme") {
        val htmlAsString = getMarkupFromFile("js.html")

        val result = htmlDocument(html = htmlAsString, jsExecution = true)

        assertSoftly(result) {
            titleText.shouldBe("i'm the title")
            findFirst("p").text.shouldBe("i'm a paragraph")
            findFirst(".dynamic").text.shouldBe("I have been dynamically added via Javascript")
        }
    }

    test("can parse JS rendered HTML using uri scheme and invoke document lambda") {
        val htmlAsString = getMarkupFromFile("js.html")

        htmlDocument(html = htmlAsString, jsExecution = true) {
            assertSoftly(this) {
                titleText.shouldBe("i'm the title")
                findFirst("p").text.shouldBe("i'm a paragraph")
                findFirst(".dynamic").text.shouldBe("I have been dynamically added via Javascript")
            }
        }

    }

    test("can parse ES6 rendered HTML using uri scheme") {
        val htmlAsString = getMarkupFromFile("es6.html")

        val result = htmlDocument(html = htmlAsString, jsExecution = true)

        assertSoftly(result) {
            result.titleText.shouldBe("i'm the title")
            result.findFirst("p").text.shouldBe("dynamically added")
        }
    }

    test("can parse ES6 rendered HTML using uri scheme and invoke document lambda") {
        val htmlAsString = getMarkupFromFile("es6.html")

        htmlDocument(html = htmlAsString, jsExecution = true) {
            assertSoftly(this) {
                titleText.shouldBe("i'm the title")
                findFirst("p").text.shouldBe("dynamically added")
            }
        }

    }

    test("can parse XML") {
        val xmlAsString = getMarkupFromFile("example.xml")

        htmlDocument(xmlAsString) {
            "plants" {
                withAttribute = "category" to "flowers"
                "plant" {
                    findAll {
                        eachText.shouldContainExactly("rose", "tulip")
                    }
                }
            }
        }

    }

    test("can read html from input stream") {
        val fileToParse = getInputFromFile("example.html")
        val parsedFile = htmlDocument(fileToParse)
        parsedFile.titleText.shouldBe("i'm the title")
    }


    test("can read html from input stream and invoke document lambda") {
        val fileToParse = getInputFromFile("example.html")
        htmlDocument(fileToParse) {
            titleText.shouldBe("i'm the title")
        }
    }

    test("can read html from input stream with custom charset") {
        val fileToParse = getInputFromFile("example.html")
        val parsedFile = htmlDocument(fileToParse, charset = Charsets.ISO_8859_1)
        parsedFile.titleText.shouldBe("i'm the title")
    }

    test("can read html from input stream with custom charset and invoke document lambda") {
        val fileToParse = getInputFromFile("example.html")
        htmlDocument(fileToParse, charset = Charsets.ISO_8859_1) {
            titleText.shouldBe("i'm the title")
        }
    }

    test("will convert 'key only'-attributes to have empty string value") {
        val markup = "<button disabled>submit</button>"

        with(htmlDocument(html = markup)) {
            button { findFirst { attributes } }.shouldContain("disabled" to "")
        }

    }

})
