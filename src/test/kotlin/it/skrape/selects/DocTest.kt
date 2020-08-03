package it.skrape.selects

import it.skrape.core.htmlDocument
import it.skrape.exceptions.ElementNotFoundException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT
import strikt.api.expectThat
import strikt.assertions.containsExactly
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo

internal class DocTest {

    private fun aValidDocument(suffix: String = "") =
            htmlDocument("<p>Hello <b> there </b> now! </p> $suffix")

    @Test
    fun `can get text`() {
        expectThat(aValidDocument().text).isEqualTo("Hello there now!")
    }

    @Test
    fun `can get whole text`() {
        expectThat(aValidDocument().wholeText).isEqualTo("Hello  there  now!  ")
    }

    @Test
    fun `can get html`() {
        expectThat(aValidDocument().html).isEqualTo("""<html>
              | <head></head>
              | <body>
              |  <p>Hello <b> there </b> now! </p> 
              | </body>
              |</html>""".trimMargin()
        )
    }

    @Test
    fun `can get outer html`() {
        expectThat(aValidDocument().html).isEqualTo("""<html>
              | <head></head>
              | <body>
              |  <p>Hello <b> there </b> now! </p> 
              | </body>
              |</html>""".trimMargin())
    }

    @Test
    fun `can get title text`() {
        expectThat(aValidDocument("<title>hallo</title>").titleText).isEqualTo("hallo")
    }

    @Test
    fun `can get all elements of the document`() {
        val tags = aValidDocument("<title>hallo</title>").findAll {
            map { it.tagName }
        }
        expectThat(tags).containsExactly("#root", "html", "head", "body", "p", "b", "title")
    }

    @Test
    fun `throw exception if elements could not be found by default`() {
        val doc = Doc(aValidDocument().document)
        Assertions.assertThrows(ElementNotFoundException::class.java) {
            doc findAll ".non-existent"
        }
    }

    @Test
    fun `throw exception if element could not be found by default`() {
        val doc = Doc(aValidDocument().document)
        Assertions.assertThrows(ElementNotFoundException::class.java) {
            doc findFirst ".non-existent"
        }
    }

    @Test
    fun `will return empty list in relaxed mode if element could not be found`() {
        val doc = Doc(aValidDocument().document, relaxed = true)
        expectThat(doc findAll ".non-existent").isEmpty()
    }

    @Test
    fun `will return empty element in relaxed mode if element could not be found`() {
        val doc = Doc(aValidDocument().document, relaxed = true)
        expectThat(doc.findFirst(".non-existent").text).isEmpty()
    }
}