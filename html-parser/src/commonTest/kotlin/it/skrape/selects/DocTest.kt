package it.skrape.selects

import ch.tutteli.atrium.api.fluent.en_GB.toBeEmpty
import ch.tutteli.atrium.api.fluent.en_GB.toContainExactly
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.expect
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
        expect(aValidDocument().text).toEqual("Hello there now!")
    }

    @JsName("CanGetWholeText")
	@Test
	fun `can get whole text`() {
        expect(aValidDocument().wholeText).toEqual("Hello  there  now!  ")
    }

    @JsName("CanGetHtml")
	@Test
	fun `can get html`() {
        expect(aValidDocument().html).toEqual("""<html>
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
        expect(aValidDocument().html).toEqual("""<html>
              | <head></head>
              | <body>
              |  <p>Hello <b> there </b> now! </p> 
              | </body>
              |</html>""".trimMargin())
    }

    @JsName("CanGetTitleText")
	@Test
	fun `can get title text`() {
        expect(aValidDocument("<title>hallo</title>").titleText).toEqual("hallo")
    }

    @JsName("CanGetAllElementsOfTheDocument")
	@Test
	fun `can get all elements of the document`() {
        val tags = aValidDocument("<title>hallo</title>").findAll {
            map { it.tagName }
        }
        expect(tags).toContainExactly("#root", "html", "head", "body", "p", "b", "title")
    }

    @JsName("ThrowExceptionIfElementsCouldNotBeFoundByDefault")
	@Test
	fun `throw exception if elements could not be found by default`() {
        val doc = Doc(aValidDocument().document)
        expect {
            doc findAll ".non-existent"
        }.toThrow<ElementNotFoundException>()
    }

    @JsName("ThrowExceptionIfElementCouldNotBeFoundByDefault")
	@Test
	fun `throw exception if element could not be found by default`() {
        val doc = Doc(aValidDocument().document)
        expect {
            doc findFirst ".non-existent"
        }.toThrow<ElementNotFoundException>()
    }

    @JsName("WillReturnEmptyListInRelaxedModeIfElementCouldNotBeFound")
	@Test
	fun `will return empty list in relaxed mode if element could not be found`() {
        val doc = Doc(aValidDocument().document, relaxed = true)
        expect(doc findAll ".non-existent").toBeEmpty()
    }

    @JsName("WillReturnEmptyElementInRelaxedModeIfElementCouldNotBeFound")
	@Test
	fun `will return empty element in relaxed mode if element could not be found`() {
        val doc = Doc(aValidDocument().document, relaxed = true)
        expect(doc.findFirst(".non-existent").text).toBeEmpty()
    }

    @JsName("CanGetAllChildrenOfDocument")
    @Test
    @Ignore
    fun `can get all children of document`() {
        val children = aValidDocument().children
        expect(children.map { it.tagName }).toContainExactly("html")
        expect(children.first().children.map { it.tagName } ).toContainExactly("head", "body")
    }

    @JsName("CanGetAllChildrenOfDocumentViaLambda")
	@Test
    fun `can get all children of document via lambda`() {
        val children = aValidDocument().children { this }
        expect(children.map { it.tagName }).toContainExactly("html")
        expect(children.first().children { map { it.tagName } }).toContainExactly("head", "body")
    }

    @JsName("CanConvertDocElementToJsoupElement")
	@Test
    @Ignore
    //Temporarily disabled
	fun `can convert DocElement to jsoup element`() {
        //expect(aValidDocument().element).isA<Element>()
    }
}