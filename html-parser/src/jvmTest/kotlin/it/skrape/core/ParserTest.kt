package it.skrape.core

import it.skrape.selects.eachText
import it.skrape.selects.html5.button
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import strikt.api.expect
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.containsExactly
import strikt.assertions.hasEntry
import strikt.assertions.isEqualTo
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException

class ParserTest {

    @Test
    fun `can parse HTML`() {
        val htmlAsString = getMarkupFromFile("example.html")

        val result = htmlDocument(html = htmlAsString)

        expect {
            that(result.titleText).isEqualTo("i'm the title")
            that(result.findFirst("p").text).isEqualTo("i'm a paragraph")
        }
    }

    @Test
    fun `can parse HTML and invoke document lambda`() {
        val htmlAsString = getMarkupFromFile("example.html")

        htmlDocument(html = htmlAsString) {
            expect {
                that(titleText).isEqualTo("i'm the title")
                that(findFirst("p").text).isEqualTo("i'm a paragraph")
            }
        }
    }

    @Test
    fun `can parse JS rendered HTML using uri scheme`() {
        val htmlAsString = getMarkupFromFile("js.html")

        val result = htmlDocument(html = htmlAsString, jsExecution = true)

        expect {
            that(result.titleText).isEqualTo("i'm the title")
            that(result.findFirst("p").text).isEqualTo("i'm a paragraph")
            that(result.findFirst(".dynamic").text).isEqualTo("I have been dynamically added via Javascript")
        }
    }

    @Test
    fun `can parse JS rendered HTML using uri scheme and invoke document lambda`() {
        val htmlAsString = getMarkupFromFile("js.html")

        htmlDocument(html = htmlAsString, jsExecution = true) {
            expect {
                that(titleText).isEqualTo("i'm the title")
                that(findFirst("p").text).isEqualTo("i'm a paragraph")
                that(findFirst(".dynamic").text).isEqualTo("I have been dynamically added via Javascript")
            }
        }

    }

    @Test
    fun `can parse ES6 rendered HTML using uri scheme`() {
        val htmlAsString = getMarkupFromFile("es6.html")

        val result = htmlDocument(html = htmlAsString, jsExecution = true)

        expect {
            that(result.titleText).isEqualTo("i'm the title")
            that(result.findFirst("p").text).isEqualTo("dynamically added")
        }
    }

    @Test
    fun `can parse ES6 rendered HTML using uri scheme and invoke document lambda`() {
        val htmlAsString = getMarkupFromFile("es6.html")

        htmlDocument(html = htmlAsString, jsExecution = true) {
            expect {
                that(titleText).isEqualTo("i'm the title")
                that(findFirst("p").text).isEqualTo("dynamically added")
            }
        }

    }

    @Test
    fun `can parse XML`() {
        val xmlAsString = getMarkupFromFile("example.xml")

        htmlDocument(xmlAsString) {
            "plants" {
                withAttribute = "category" to "flowers"
                "plant" {
                    findAll {
                        expectThat(eachText).containsExactly("rose", "tulip")
                    }
                }
            }
        }

    }

    @Test
    fun `can read html from file`() {
        val fileToParse = File("src/test/resources/__files/example.html")
        val parsedFile = htmlDocument(fileToParse)
        expectThat(parsedFile.titleText).isEqualTo("i'm the title")
    }


    @Test
    fun `can read html from file and invoke document lambda`() {
        val fileToParse = File("src/test/resources/__files/example.html")
        htmlDocument(fileToParse) {
            expectThat(titleText).isEqualTo("i'm the title")
        }
    }

    @Test
    fun `can read html from file with custom charset`() {
        val fileToParse = File("src/test/resources/__files/example.html")
        val parsedFile = htmlDocument(fileToParse, charset = Charsets.ISO_8859_1)
        expectThat(parsedFile.titleText).isEqualTo("i'm the title")
    }

    @Test
    fun `can read html from file with custom charset and invoke document lambda`() {
        val fileToParse = File("src/test/resources/__files/example.html")
        htmlDocument(fileToParse, charset = Charsets.ISO_8859_1) {
            expectThat(titleText).isEqualTo("i'm the title")
        }
    }

    @Test
    fun `will throw exception if file not found`() {
        expectThrows<FileNotFoundException> {
            htmlDocument(File("invalid"))
        }
    }

    @Test
    fun `will throw exception if file not found and invoke document lambda`() {
        expectThrows<FileNotFoundException> {
            htmlDocument(File("invalid")) {}
        }
    }

    @Test
    fun `can read html from input stream`() {
        val fileToParse = FileInputStream(File("src/test/resources/__files/example.html"))
        val parsedFile = htmlDocument(fileToParse)
        expectThat(parsedFile.titleText).isEqualTo("i'm the title")
    }


    @Test
    fun `can read html from input stream and invoke document lambda`() {
        val fileToParse = FileInputStream(File("src/test/resources/__files/example.html"))
        htmlDocument(fileToParse) {
            expectThat(titleText).isEqualTo("i'm the title")
        }
    }

    @Test
    fun `can read html from input stream with custom charset`() {
        val fileToParse = FileInputStream(File("src/test/resources/__files/example.html"))
        val parsedFile = htmlDocument(fileToParse, charset = Charsets.ISO_8859_1)
        expectThat(parsedFile.titleText).isEqualTo("i'm the title")
    }

    @Test
    fun `can read html from input stream with custom charset and invoke document lambda`() {
        val fileToParse = FileInputStream(File("src/test/resources/__files/example.html"))
        htmlDocument(fileToParse, charset = Charsets.ISO_8859_1) {
            expectThat(titleText).isEqualTo("i'm the title")
        }
    }

    @Test
    fun `will convert 'key only'-attributes to have empty string value`() {
        @Language("HTML") val markup = "<button disabled>submit</button>"

        with(htmlDocument(html = markup)) {
            expectThat(button { findFirst { attributes } }).hasEntry("disabled", "")
        }

    }

    private fun getMarkupFromFile(file: String) = javaClass.getResource("/__files/$file").readText()
}
