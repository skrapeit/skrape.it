package it.skrape.core

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.maps.shouldContain
import io.kotest.matchers.shouldBe
import io.ktor.http.ContentDisposition.Companion.File
import io.ktor.utils.io.charsets.*
import io.ktor.utils.io.core.*
import it.skrape.selects.eachText
import it.skrape.selects.html5.button
import kotlinx.coroutines.test.runTest
import kotlin.js.JsName
import kotlin.test.Test

class ParserTest {

    @Test
    @JsName("CanParseHTML")
	fun `can parse HTML`() = runTest {
        kotlin.runCatching {
            val htmlAsString = getMarkupFromFile("example.html")

            val result = htmlDocument(html = htmlAsString)

            assertSoftly(result) {
                titleText.shouldBe("i'm the title")
                result.findFirst("p").text.shouldBe("i'm a paragraph")
            }
        }
    }

    @Test
    @JsName("CanParseHTMLAndInvokeDocumentLambda")
	fun `can parse HTML and invoke document lambda`() = runTest {
        val htmlAsString = getMarkupFromFile("example.html")

        htmlDocument(html = htmlAsString) {
            assertSoftly(this) {
                titleText.shouldBe("i'm the title")
                findFirst("p").text.shouldBe("i'm a paragraph")
            }
        }
    }

    @Test
    @JsName("CanParseJSRenderedHTMLUsingUriScheme")
	fun `can parse JS rendered HTML using uri scheme`() = runTest {
        val htmlAsString = getMarkupFromFile("js.html")

        val result = htmlDocument(html = htmlAsString, jsExecution = true)

        assertSoftly(result) {
            titleText.shouldBe("i'm the title")
            findFirst("p").text.shouldBe("i'm a paragraph")
            findFirst(".dynamic").text.shouldBe("I have been dynamically added via Javascript")
        }
    }

    @Test
    @JsName("CanParseJSRenderedHTMLUsingUriSchemeAndInvokeDocumentLambda")
	fun `can parse JS rendered HTML using uri scheme and invoke document lambda`() = runTest {
        val htmlAsString = getMarkupFromFile("js.html")

        htmlDocument(html = htmlAsString, jsExecution = true) {
            assertSoftly(this) {
                titleText.shouldBe("i'm the title")
                findFirst("p").text.shouldBe("i'm a paragraph")
                findFirst(".dynamic").text.shouldBe("I have been dynamically added via Javascript")
            }
        }

    }

    @Test
    @JsName("CanParseES6RenderedHTMLUsingUriScheme")
	fun `can parse ES6 rendered HTML using uri scheme`() = runTest {
        val htmlAsString = getMarkupFromFile("es6.html")

        val result = htmlDocument(html = htmlAsString, jsExecution = true)

        assertSoftly(result) {
            result.titleText.shouldBe("i'm the title")
            result.findFirst("p").text.shouldBe("dynamically added")
        }
    }

    @Test
    @JsName("CanParseES6RenderedHTMLUsingUriSchemeAndInvokeDocumentLambda")
	fun `can parse ES6 rendered HTML using uri scheme and invoke document lambda`() = runTest {
        val htmlAsString = getMarkupFromFile("es6.html")

        htmlDocument(html = htmlAsString, jsExecution = true) {
            assertSoftly(this) {
                titleText.shouldBe("i'm the title")
                findFirst("p").text.shouldBe("dynamically added")
            }
        }

    }

    @Test
    @JsName("CanParseXML")
	fun `can parse XML`() = runTest {
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

    @Test
    @JsName("CanReadHtmlFromInputStream")
	fun `can read html from input stream`() = runTest {
        val fileToParse = getInputFromFile("example.html")
        val parsedFile = htmlDocument(fileToParse)
        parsedFile.titleText.shouldBe("i'm the title")
    }


    @Test
    @JsName("CanReadHtmlFromInputStreamAndInvokeDocumentLambda")
	fun `can read html from input stream and invoke document lambda`() = runTest {
        val fileToParse = getInputFromFile("example.html")
        htmlDocument(fileToParse) {
            titleText.shouldBe("i'm the title")
        }
    }

    @Test
    @JsName("CanReadHtmlFromInputStreamWithCustomCharset")
	fun `can read html from input stream with custom charset`() = runTest {
        val fileToParse = getInputFromFile("example.html")
        val parsedFile = htmlDocument(fileToParse, charset = Charsets.ISO_8859_1)
        parsedFile.titleText.shouldBe("i'm the title")
    }

    @Test
    @JsName("CanReadHtmlFromInputStreamWithCustomCharsetAndInvokeDocumentLambda")
	fun `can read html from input stream with custom charset and invoke document lambda`() = runTest {
        val fileToParse = getInputFromFile("example.html")
        htmlDocument(fileToParse, charset = Charsets.ISO_8859_1) {
            titleText.shouldBe("i'm the title")
        }
    }

    @Test
    @JsName("WillConvertKeyOnlyAttributesToHaveEmptyStringValue")
	fun `will convert 'key only'-attributes to have empty string value`() = runTest {
        val markup = "<button disabled>submit</button>"

        with(htmlDocument(html = markup)) {
            button { findFirst { attributes } }.shouldContain("disabled" to "")
        }

    }

}

expect suspend fun getInputFromFile(location: String): Input
expect suspend fun getMarkupFromFile(location: String): String
