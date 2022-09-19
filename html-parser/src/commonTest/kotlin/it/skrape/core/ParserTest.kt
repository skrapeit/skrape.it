package it.skrape.core

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import io.ktor.http.ContentDisposition.Companion.File
import io.ktor.utils.io.charsets.*
import io.ktor.utils.io.core.*
import it.skrape.selects.eachText
import it.skrape.selects.html5.button
import kotlin.js.JsName
import kotlin.test.Test

class ParserTest {

    @Test
    @JsName("CanParseHTML")
	fun `can parse HTML`() {
        val htmlAsString = getMarkupFromFile("example.html")

        val result = htmlDocument(html = htmlAsString)

        expect(result) {
            its { titleText }.toEqual("i'm the title")
            its { result.findFirst("p").text }.toEqual("i'm a paragraph")
        }
    }

    @Test
    @JsName("CanParseHTMLAndInvokeDocumentLambda")
	fun `can parse HTML and invoke document lambda`() {
        val htmlAsString = getMarkupFromFile("example.html")

        htmlDocument(html = htmlAsString) {
            expect(this) {
                its { titleText }.toEqual("i'm the title")
                its { findFirst("p").text }.toEqual("i'm a paragraph")
            }
        }
    }

    @Test
    @JsName("CanParseJSRenderedHTMLUsingUriScheme")
	fun `can parse JS rendered HTML using uri scheme`() {
        val htmlAsString = getMarkupFromFile("js.html")

        val result = htmlDocument(html = htmlAsString, jsExecution = true)

        expect(result) {
            its { titleText }.toEqual("i'm the title")
            its { findFirst("p").text }.toEqual("i'm a paragraph")
            its { findFirst(".dynamic").text }.toEqual("I have been dynamically added via Javascript")
        }
    }

    @Test
    @JsName("CanParseJSRenderedHTMLUsingUriSchemeAndInvokeDocumentLambda")
	fun `can parse JS rendered HTML using uri scheme and invoke document lambda`() {
        val htmlAsString = getMarkupFromFile("js.html")

        htmlDocument(html = htmlAsString, jsExecution = true) {
            expect(this) {
                its { titleText }.toEqual("i'm the title")
                its { findFirst("p").text }.toEqual("i'm a paragraph")
                its { findFirst(".dynamic").text }.toEqual("I have been dynamically added via Javascript")
            }
        }

    }

    @Test
    @JsName("CanParseES6RenderedHTMLUsingUriScheme")
	fun `can parse ES6 rendered HTML using uri scheme`() {
        val htmlAsString = getMarkupFromFile("es6.html")

        val result = htmlDocument(html = htmlAsString, jsExecution = true)

        expect(result) {
            its { result.titleText }.toEqual("i'm the title")
            its { result.findFirst("p").text }.toEqual("dynamically added")
        }
    }

    @Test
    @JsName("CanParseES6RenderedHTMLUsingUriSchemeAndInvokeDocumentLambda")
	fun `can parse ES6 rendered HTML using uri scheme and invoke document lambda`() {
        val htmlAsString = getMarkupFromFile("es6.html")

        htmlDocument(html = htmlAsString, jsExecution = true) {
            expect(this) {
                its { titleText }.toEqual("i'm the title")
                its { findFirst("p").text }.toEqual("dynamically added")
            }
        }

    }

    @Test
    @JsName("CanParseXML")
	fun `can parse XML`() {
        val xmlAsString = getMarkupFromFile("example.xml")

        htmlDocument(xmlAsString) {
            "plants" {
                withAttribute = "category" to "flowers"
                "plant" {
                    findAll {
                        expect(eachText).toContainExactly("rose", "tulip")
                    }
                }
            }
        }

    }

    @Test
    @JsName("CanReadHtmlFromInputStream")
	fun `can read html from input stream`() {
        val fileToParse = getInputFromFile("src/commonTest/resources/__files/example.html")
        val parsedFile = htmlDocument(fileToParse)
        expect(parsedFile.titleText).toEqual("i'm the title")
    }


    @Test
    @JsName("CanReadHtmlFromInputStreamAndInvokeDocumentLambda")
	fun `can read html from input stream and invoke document lambda`() {
        val fileToParse = getInputFromFile("src/commonTest/resources/__files/example.html")
        htmlDocument(fileToParse) {
            expect(titleText).toEqual("i'm the title")
        }
    }

    @Test
    @JsName("CanReadHtmlFromInputStreamWithCustomCharset")
	fun `can read html from input stream with custom charset`() {
        val fileToParse = getInputFromFile("src/commonTest/resources/__files/example.html")
        val parsedFile = htmlDocument(fileToParse, charset = Charsets.ISO_8859_1)
        expect(parsedFile.titleText).toEqual("i'm the title")
    }

    @Test
    @JsName("CanReadHtmlFromInputStreamWithCustomCharsetAndInvokeDocumentLambda")
	fun `can read html from input stream with custom charset and invoke document lambda`() {
        val fileToParse = getInputFromFile("src/commonTest/resources/__files/example.html")
        htmlDocument(fileToParse, charset = Charsets.ISO_8859_1) {
            expect(titleText).toEqual("i'm the title")
        }
    }

    @Test
    @JsName("WillConvertKeyOnlyAttributesToHaveEmptyStringValue")
	fun `will convert 'key only'-attributes to have empty string value`() {
        val markup = "<button disabled>submit</button>"

        with(htmlDocument(html = markup)) {
            expect(button { findFirst { attributes } }).toContain("disabled" to "")
        }

    }

}

expect fun getInputFromFile(location: String): Input
expect fun getMarkupFromFile(location: String): String
