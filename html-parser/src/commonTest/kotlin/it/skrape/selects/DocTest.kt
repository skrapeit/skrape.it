package it.skrape.selects

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldBeEmpty
import it.skrape.core.htmlDocument

class DocTest: FunSpec({

    fun aValidDocument(suffix: String = "") =
            htmlDocument("<p>Hello <b> there </b> now! </p> $suffix")

    test("can get text") {
        aValidDocument().text.shouldBe("Hello there now!")
    }

    test("can get whole text") {
        aValidDocument().wholeText.shouldBe("Hello  there  now!  ")
    }

    test("can get html") {
        aValidDocument().html.shouldBe("""<html>
              | <head></head>
              | <body>
              |  <p>Hello <b> there </b> now! </p> 
              | </body>
              |</html>""".trimMargin()
        )
    }

    test("can get outer html") {
        aValidDocument().html.shouldBe("""<html>
              | <head></head>
              | <body>
              |  <p>Hello <b> there </b> now! </p> 
              | </body>
              |</html>""".trimMargin())
    }

    test("can get title text") {
        aValidDocument("<title>hallo</title>").titleText.shouldBe("hallo")
    }

    test("can get all elements of the document") {
        val tags = aValidDocument("<title>hallo</title>").findAll {
            map { it.tagName }
        }
        tags.shouldContainExactly("#root", "html", "head", "body", "p", "b", "title")
    }

    test("throw exception if elements could not be found by default") {
        val doc = Doc(aValidDocument().document)
        shouldThrow<ElementNotFoundException> {
            doc findAll ".non-existent"
        }
    }

    test("throw exception if element could not be found by default") {
        val doc = Doc(aValidDocument().document)
        shouldThrow<ElementNotFoundException> {
            doc findFirst ".non-existent"
        }
    }

    test("will return empty list in relaxed mode if element could not be found") {
        val doc = Doc(aValidDocument().document, relaxed = true)
        (doc findAll ".non-existent").shouldBeEmpty()
    }

    test("will return empty element in relaxed mode if element could not be found") {
        val doc = Doc(aValidDocument().document, relaxed = true)
        doc.findFirst(".non-existent").text.shouldBeEmpty()
    }

    xtest("can get all children of document") {
        val children = aValidDocument().children
        children.map { it.tagName }.shouldContainExactly("html")
        children.first().children.map { it.tagName }.shouldContainExactly("head", "body")
    }

    test("can get all children of document via lambda") {
        val children = aValidDocument().children { this }
        children.map { it.tagName }.shouldContainExactly("html")
        children.first().children { map { it.tagName } }.shouldContainExactly("head", "body")
    }

    //Temporarily disabled
	xtest("can convert DocElement to jsoup element") {
        //aValidDocument(.element).isA<Element>()
    }
})