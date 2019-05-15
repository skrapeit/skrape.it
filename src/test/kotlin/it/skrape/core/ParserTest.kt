package it.skrape.core

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class ParserTest {

    @Test
    internal fun `can parse HTML`() {
        // given
        val htmlAsString = getMarkupFromFile("example.html")

        // when
        val result = Parser(htmlAsString).parse()

        // then
        assertThat(result.title()).isEqualTo("i'm the title")
        assertThat(result.selectFirst("p").text()).isEqualTo("i'm a paragraph")
    }

    @Test
    internal fun `can parse HTML using uri scheme`() {
        // given
        val htmlAsString = getMarkupFromFile("example.html")

        val result = Parser(htmlAsString).parseDom()

        // then
        assertThat(result.title()).isEqualTo("i'm the title")
        assertThat(result.selectFirst("p").text()).isEqualTo("i'm a paragraph")
    }

    @Test
    internal fun `can parse JS rendered HTML using uri scheme`() {
        // given
        val htmlAsString = getMarkupFromFile("js.html")

        val result = Parser(htmlAsString).parseDom()

        println(result.body())

        assertThat(result.title()).isEqualTo("i'm the title")
        assertThat(result.selectFirst("p").text()).isEqualTo("i'm a paragraph")
        assertThat(result.selectFirst(".dynamic").text()).isEqualTo("I have been dynamically added via Javascript")
    }

    @Test
    internal fun `can parse ES6 rendered HTML using uri scheme`() {
        // given
        val htmlAsString = getMarkupFromFile("es6.html")

        val result = Parser(htmlAsString).parseDom()

        assertThat(result.title()).isEqualTo("i'm the title")
        assertThat(result.selectFirst("p").text()).isEqualTo("dynamically added")
    }

    private fun getMarkupFromFile(file: String) = javaClass.getResource("/__files/$file").readText()
}
