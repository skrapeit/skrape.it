package it.skrape.selects

import it.skrape.core.htmlDocument
import org.junit.jupiter.api.Test

import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class DocTest {

    private fun aValidDocument(suffix: String = "") =
            htmlDocument("<p>Hello <b> there </b> now! </p> $suffix")

    @Test
    fun getText() {
        expectThat(aValidDocument().text).isEqualTo("Hello there now!")
    }

    @Test
    fun getWholeText() {
        expectThat(aValidDocument().wholeText).isEqualTo("Hello  there  now!  ")
    }

    @Test
    fun getHtml() {
        expectThat(aValidDocument().html).isEqualTo("""<html>
              | <head></head>
              | <body>
              |  <p>Hello <b> there </b> now! </p> 
              | </body>
              |</html>""".trimMargin()
        )
    }

    @Test
    fun getOuterHtml() {
        expectThat(aValidDocument().html).isEqualTo("""<html>
              | <head></head>
              | <body>
              |  <p>Hello <b> there </b> now! </p> 
              | </body>
              |</html>""".trimMargin())
    }

    @Test
    fun getTitleText() {
        expectThat(aValidDocument("<title>hallo</title>").titleText).isEqualTo("hallo")
    }
}