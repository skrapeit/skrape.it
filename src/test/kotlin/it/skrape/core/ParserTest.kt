package it.skrape.core

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import strikt.api.expect
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.io.File
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
        Assertions.assertThrows(FileNotFoundException::class.java) {
            htmlDocument(File("invalid"))
        }
    }

    @Test
    fun `will throw exception if file not found and invoke document lambda`() {
        Assertions.assertThrows(FileNotFoundException::class.java) {
            htmlDocument(File("invalid")) {}
        }
    }

    private fun getMarkupFromFile(file: String) = javaClass.getResource("/__files/$file").readText()
}
