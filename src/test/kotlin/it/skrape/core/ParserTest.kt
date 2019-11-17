package it.skrape.core

import org.junit.jupiter.api.Test
import strikt.api.expect
import strikt.assertions.isEqualTo

internal class ParserTest {

    @Test
    internal fun `can parse HTML`() {
        // given
        val htmlAsString = getMarkupFromFile("example.html")

        // when
        val result = Parser(htmlAsString).parse()

        // then
        expect {
            that(result.title()).isEqualTo("i'm the title")
            that(result.findFirst("p").text()).isEqualTo("i'm a paragraph")
        }
    }

    @Test
    internal fun `can parse HTML using uri scheme`() {
        // given
        val htmlAsString = getMarkupFromFile("example.html")

        val result = Parser(htmlAsString).parseDom()

        // then
        expect {
            that(result.title()).isEqualTo("i'm the title")
            that(result.findFirst("p").text()).isEqualTo("i'm a paragraph")
        }
    }

    @Test
    internal fun `can parse JS rendered HTML using uri scheme`() {
        // given
        val htmlAsString = getMarkupFromFile("js.html")

        // when
        val result = Parser(htmlAsString).parseDom()

        // then
        expect {
            that(result.title()).isEqualTo("i'm the title")
            that(result.findFirst("p").text()).isEqualTo("i'm a paragraph")
            that(result.findFirst(".dynamic").text()).isEqualTo("I have been dynamically added via Javascript")
        }
    }

    @Test
    internal fun `can parse ES6 rendered HTML using uri scheme`() {
        // given
        val htmlAsString = getMarkupFromFile("es6.html")

        // when
        val result = Parser(htmlAsString).parseDom()

        // then
        expect {
            that(result.title()).isEqualTo("i'm the title")
            that(result.findFirst("p").text()).isEqualTo("dynamically added")
        }
    }

    private fun getMarkupFromFile(file: String) = javaClass.getResource("/__files/$file").readText()
}
