package it.skrape.core

import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Test

internal class ParserTest {

    @Test
    internal fun `can parse HTML`() {
        val htmlAsString = """
            <html>
                <head>
                    <title>some title</title>
                </head>
                <body>
                    <p>some text</p>
                </body>
            </html>
        """.trimIndent()

        val result = Parser(htmlAsString).parse()

        assertThat(result.title()).isEqualTo("some title")
        assertThat(result.selectFirst("p").text()).isEqualTo("some text")
    }
}