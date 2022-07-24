package it.skrape.selects

import it.skrape.core.htmlDocument
import org.jsoup.nodes.Element
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.containsExactly
import strikt.assertions.isA
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo

class DocTest {

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
        expectThrows<ElementNotFoundException> {
            doc findAll ".non-existent"
        }
    }

    @Test
    fun `throw exception if element could not be found by default`() {
        val doc = Doc(aValidDocument().document)
        expectThrows<ElementNotFoundException> {
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

    @Test
    @Disabled("")
    fun `can get all children of document`() {
        val children = aValidDocument().children
        expectThat(children.map { it.tagName }).containsExactly("html")
        expectThat(children.first().children.map { it.tagName } ).containsExactly("head", "body")
    }

    @Test
    fun `can get all children of document via lambda`() {
        val children = aValidDocument().children { this }
        expectThat(children.map { it.tagName }).containsExactly("html")
        expectThat(children.first().children { map { it.tagName } }).containsExactly("head", "body")
    }

    @Test
    fun `can convert DocElement to jsoup element`() {
        expectThat(aValidDocument().element).isA<Element>()
    }
}
