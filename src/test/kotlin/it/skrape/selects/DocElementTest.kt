package it.skrape.selects

import io.mockk.every
import io.mockk.mockk
import org.intellij.lang.annotations.Language
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.*

internal class DocElementTest {

    @Language("HTML")
    private val aValidMarkup = """
        <h2 class='welcome'>headline</h2>
        <p class='fancy'>paragraph
            <span>foo <b>bar</b></span>
            <span>fizz <b>buzz</b></span>
        </p>
    """.trimMargin()

    private val anElement = Element("div").apply {
        prependText("divs text")
        addClass("clazz")
        attr("foo", "bar")
        append(aValidMarkup)
    }

    val aValidElement = DocElement(anElement)

    @Test
    fun `can get the element's tag name `() {
        val tagName = htmlDocument(aValidMarkup).findFirst(".fancy") { tagName }
        expectThat(tagName).isEqualTo("p")
    }

    @Test
    fun `can get the element's text - including text of children`() {
        expectThat(aValidElement.text).isEqualTo("divs text headline paragraph foo bar fizz buzz")
    }

    @Test
    fun `can get the elements own text - excluding text of children`() {
        expectThat(aValidElement.ownText).isEqualTo("divs text")
    }

    @Test
    fun `can get inner html of an element`() {
        expectThat(aValidElement.html).isEqualTo("""
            divs text 
            <h2 class="welcome">headline</h2> 
            <p class="fancy">paragraph <span>foo <b>bar</b></span> <span>fizz <b>buzz</b></span> </p>
            """.trimIndent()
        )
    }

    @Test
    fun `can get outer html of an element`() {
        expectThat(aValidElement.outerHtml).isEqualTo("""
            <div class="clazz" foo="bar">
             divs text 
             <h2 class="welcome">headline</h2> 
             <p class="fancy">paragraph <span>foo <b>bar</b></span> <span>fizz <b>buzz</b></span> </p>
            </div>
            """.trimIndent()
        )
    }

    @Test
    fun `will return true if element is present`() {
        expectThat(aValidElement.isPresent).isTrue()
    }

    @Test
    fun `will return false if element is not present`() {
        val element = mockk<Element> { every { allElements } returns Elements() }
        val docElement = DocElement(element)
        expectThat(docElement.isPresent).isFalse()
    }

    @Test
    fun `can get all elements under this element including itself`() {
        expectThat(aValidElement.allElements).hasSize(7)
        expectThat(aValidElement.allElements[1].outerHtml).isEqualTo("""<h2 class="welcome">headline</h2>""")
    }

    @Test
    fun `can find all elements within this element (including itself) and invoke them to a lambda`() {
        val text = aValidElement.findAll {
            expectThat(size).isEqualTo(7)
            expectThat(get(1).outerHtml).isEqualTo("""<h2 class="welcome">headline</h2>""")
            get(1).text
        }
        expectThat(text).isEqualTo("headline")
    }

    @Test
    fun `can find all elements by selector from within this element (including itself)`() {
        val selection = aValidElement.findAll(".welcome")
        expectThat(selection).hasSize(1)
        expectThat(selection.text).isEqualTo("headline")
    }

    @Test
    fun `can find all elements within this element by selector and invoke them to lambda that will return generic value`() {
        val text = aValidElement.findAll(".welcome") {
            expectThat(this).hasSize(1)
            text
        }
        expectThat(text).isEqualTo("headline")
    }

    @Test
    fun `can find first element within this element by selector and invoke them to lambda that will return generic value`() {
        val text = aValidElement.findFirst(".welcome") {
            text
        }
        expectThat(text).isEqualTo("headline")
    }

    @Test
    fun `can find first element within this element by selector`() {
        val text = aValidElement.findFirst(".welcome").text
        expectThat(text).isEqualTo("headline")
    }

    @Test
    fun `can get the css-class name of a given element`() {
        expectThat(aValidElement.className).isEqualTo("clazz")
    }

    @Test
    fun `can get the css-selector of a given element`() {
        expectThat(aValidElement.cssSelector).isEqualTo("div.clazz")
    }

    @Test
    fun `will return empty string on non existing attribute`() {
        expectThat(aValidElement.attribute("non-existing")).isEmpty()
    }

    @Test
    fun `will return attribute value for a given attribute key that exists`() {
        expectThat(aValidElement.attribute("foo")).isEqualTo("bar")
    }

    @Test
    fun `will return true if a given attribute key is present at given element`() {
        expectThat(aValidElement.hasAttribute("foo")).isTrue()
    }

    @Test
    fun `will return false if a given attribute key is not present at given element`() {
        expectThat(aValidElement.hasAttribute("non-existing")).isFalse()
    }

    @Test
    @Disabled("FIXME")
    fun `can invoke a css-selector as string to search children of given element`() {
        val text = aValidElement.run {
            ".welcome" {
                // should return the matching DocElement
                findFirst { text }
            }
        }
        expectThat(text).isEqualTo("headline")
    }

    @Test
    fun `string representation has certain format`() {
        expectThat(aValidElement.toString()).isEqualTo("""
            <div class="clazz" foo="bar">
             divs text 
             <h2 class="welcome">headline</h2> 
             <p class="fancy">paragraph <span>foo <b>bar</b></span> <span>fizz <b>buzz</b></span> </p>
            </div>
            """.trimIndent()
        )
    }

    @Test
    fun `can select all elements by selector from within this element`() {
        val selection = aValidElement.select(".welcome")
        expectThat(selection).hasSize(1)
        expectThat(selection.text).isEqualTo("headline")
    }
}