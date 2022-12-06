package it.skrape.selects

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldBeEmpty
import it.skrape.core.htmlDocument
import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test

class DocTest {

    private fun aValidDocument(suffix: String = "") =
            htmlDocument("<p>Hello <b> there </b> now! </p> $suffix")

    @JsName("CanGetText")
	@Test
	fun `can get text`() {
        aValidDocument().text.shouldBe("Hello there now!")
    }

    @JsName("CanGetWholeText")
	@Test
	fun `can get whole text`() {
        aValidDocument().wholeText.shouldBe("Hello  there  now!  ")
    }

    @JsName("CanGetHtml")
	@Test
	fun `can get html`() {
        aValidDocument().html.shouldBe("""<html>
              | <head></head>
              | <body>
              |  <p>Hello <b> there </b> now! </p> 
              | </body>
              |</html>""".trimMargin()
        )
    }

    @JsName("CanGetOuterHtml")
	@Test
	fun `can get outer html`() {
        aValidDocument().html.shouldBe("""<html>
              | <head></head>
              | <body>
              |  <p>Hello <b> there </b> now! </p> 
              | </body>
              |</html>""".trimMargin())
    }

    @JsName("CanGetTitleText")
	@Test
	fun `can get title text`() {
        aValidDocument("<title>hallo</title>").titleText.shouldBe("hallo")
    }

    @JsName("CanGetAllElementsOfTheDocument")
	@Test
	fun `can get all elements of the document`() {
        val tags = aValidDocument("<title>hallo</title>").findAll {
            map { it.tagName }
        }
        tags.shouldContainExactly("#root", "html", "head", "body", "p", "b", "title")
    }

    @JsName("ThrowExceptionIfElementsCouldNotBeFoundByDefault")
	@Test
	fun `throw exception if elements could not be found by default`() {
        val doc = Doc(aValidDocument().document)
        shouldThrow<ElementNotFoundException> {
            doc findAll ".non-existent"
        }
    }

    @JsName("ThrowExceptionIfElementCouldNotBeFoundByDefault")
	@Test
	fun `throw exception if element could not be found by default`() {
        val doc = Doc(aValidDocument().document)
        shouldThrow<ElementNotFoundException> {
            doc findFirst ".non-existent"
        }
    }

    @JsName("WillReturnEmptyListInRelaxedModeIfElementCouldNotBeFound")
	@Test
	fun `will return empty list in relaxed mode if element could not be found`() {
        val doc = Doc(aValidDocument().document, relaxed = true)
        (doc findAll ".non-existent").shouldBeEmpty()
    }

    @JsName("WillReturnEmptyElementInRelaxedModeIfElementCouldNotBeFound")
	@Test
	fun `will return empty element in relaxed mode if element could not be found`() {
        val doc = Doc(aValidDocument().document, relaxed = true)
        doc.findFirst(".non-existent").text.shouldBeEmpty()
    }

    @JsName("CanGetAllChildrenOfDocument")
    @Test
    @Ignore
    fun `can get all children of document`() {
        val children = aValidDocument().children
        children.map { it.tagName }.shouldContainExactly("html")
        children.first().children.map { it.tagName }.shouldContainExactly("head", "body")
    }

    @JsName("CanGetAllChildrenOfDocumentViaLambda")
	@Test
    fun `can get all children of document via lambda`() {
        val children = aValidDocument().children { this }
        children.map { it.tagName }.shouldContainExactly("html")
        children.first().children { map { it.tagName } }.shouldContainExactly("head", "body")
    }

    @JsName("CanConvertDocElementToJsoupElement")
	@Test
    @Ignore
    //Temporarily disabled
	fun `can convert DocElement to jsoup element`() {
        //aValidDocument(.element).isA<Element>()
    }
}