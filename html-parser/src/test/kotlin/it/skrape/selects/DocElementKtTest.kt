package it.skrape.selects

import aValidDocument
import aValidMarkupWithLinks
import aValidMarkupWithPictures
import io.mockk.every
import io.mockk.mockk
import it.skrape.core.htmlDocument
import it.skrape.selects.html5.*
import org.intellij.lang.annotations.Language
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.*

class DocElementKtTest {

    @Language("HTML")
    private val aValidMarkup = """
        <h2 class='welcome' disabled>headline</h2>
        <p class='fancy'>paragraph
            <span>foo <b>bar</b></span>
            <span>fizz <b id="xxx">buzz</b></span>
        </p>
    """.trimMargin()

    private val anElement = Element("div").apply {
        prependText("divs text")
        addClass("clazz")
        addClass("klass")
        attr("foo", "bar")
        attr("fizz", "buzz")
        attr("data-foo", "foobar")
        append(aValidMarkup)
    }

    val aValidElement = DocElement(anElement)

    @Test
    fun `can get the element's tag name `() {
        val tagName = htmlDocument(aValidMarkup).findFirst(".fancy") { tagName }
        expectThat(tagName).isEqualTo("p")
    }

    @Test
    fun `can get all tag names of a list of elements`() {
        val someElements = listOf(aValidElement, aValidElement)
        expectThat(someElements.eachTagName).containsExactly("div", "div")
    }

    @Test
    fun `can get the element's text - including text of children`() {
        expectThat(aValidElement.text).isEqualTo("divs text headline paragraph foo bar fizz buzz")
    }

    @Test
    fun `can get each text from a list of elements`() {
        val someElements = listOf(aValidElement, aValidElement)
        expectThat(someElements.eachText).containsExactly(
            "divs text headline paragraph foo bar fizz buzz",
            "divs text headline paragraph foo bar fizz buzz"
        )
    }

    @Test
    fun `can get the element's own text - excluding text of children`() {
        expectThat(aValidElement.ownText).isEqualTo("divs text")
    }

    @Test
    fun `can get the element's whole text - not-encoded, un-normalized with whitespaces etc`() {
        expectThat(aValidElement.wholeText).isEqualTo(
            """divs text        headline
        paragraph
            foo bar
            fizz buzz
        """
        )
    }

    @Test
    fun `can get inner html of an element`() {
        expectThat(aValidElement.html).isEqualTo(
            """
            divs text 
            <h2 class="welcome" disabled>headline</h2> 
            <p class="fancy">paragraph <span>foo <b>bar</b></span> <span>fizz <b id="xxx">buzz</b></span> </p>
            """.trimIndent()
        )
    }

    @Test
    fun `can get outer html of an element`() {
        expectThat(aValidElement.outerHtml).isEqualTo(
            """
            <div class="clazz klass" foo="bar" fizz="buzz" data-foo="foobar">
             divs text 
             <h2 class="welcome" disabled>headline</h2> 
             <p class="fancy">paragraph <span>foo <b>bar</b></span> <span>fizz <b id="xxx">buzz</b></span> </p>
            </div>
            """.trimIndent()
        )
    }

    @Test
    fun `'isPresent' will return true if element is present`() {
        expectThat(aValidElement.isPresent).isTrue()
    }

    @Test
    fun `'isPresent' will return false if element is not present`() {
        val element = mockk<Element> { every { allElements } returns Elements() }
        val docElement = DocElement(element)
        expectThat(docElement.isPresent).isFalse()
    }

    @Test
    fun `'isNotPresent' will return false if element is present`() {
        expectThat(aValidElement.isNotPresent).isFalse()
    }

    @Test
    fun `'isNotPresent' will return true if element is not present`() {
        val element = mockk<Element> { every { allElements } returns Elements() }
        val docElement = DocElement(element)
        expectThat(docElement.isNotPresent).isTrue()
    }

    @Test
    fun `'isPresent' will return true if list of elements are present`() {
        expectThat(aValidElement.allElements.isPresent).isTrue()
    }

    @Test
    fun `'isPresent' will return false if list of elements are not present`() {
        expectThat(emptyList<DocElement>().isPresent).isFalse()
    }

    @Test
    fun `'isNotPresent' will return false if list of elements are present`() {
        expectThat(aValidElement.allElements.isNotPresent).isFalse()
    }

    @Test
    fun `'isNotPresent' will return true if list of elements are not present`() {
        expectThat(emptyList<DocElement>().isNotPresent).isTrue()
    }

    @Test
    fun `can get all elements under this element including itself`() {
        expectThat(aValidElement.allElements).hasSize(7)
        expectThat(aValidElement.allElements[1].outerHtml).isEqualTo("""<h2 class="welcome" disabled>headline</h2>""")
    }

    @Test
    fun `can find all elements within this element (including itself) and invoke them to a lambda`() {
        val text = aValidElement.findAll {
            expectThat(size).isEqualTo(7)
            expectThat(get(1).outerHtml).isEqualTo("""<h2 class="welcome" disabled>headline</h2>""")
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
        expectThat(aValidElement.className).isEqualTo("clazz klass")
    }

    @Test
    fun `can get the css-selector of a given element`() {
        expectThat(aValidElement.toCssSelector).isEqualTo("div.clazz.klass")
    }

    @Test
    fun `will return id only css-selector if given element has an id attribute`() {
        val elementWithId = DocElement(anElement.attr("id", "bazinga"))
        expectThat(elementWithId.toCssSelector).isEqualTo("#bazinga")
    }

    @Test
    fun `can get ownCssSelector`() {
        val elementWithId = DocElement(anElement.apply {
            attr("id", "bazinga")
            attr("key-only-attr", "")
        })
        expectThat(elementWithId.ownCssSelector).isEqualTo(
            "div#bazinga.clazz.klass['key-only-attr'][foo='bar'][fizz='buzz'][data-foo='foobar']"
        )
    }

    @Test
    fun `ownCssSelector will not include parents if any available`() {
        val verboseCssSelector = htmlDocument(aValidMarkup) { b { findLast { ownCssSelector } } }
        expectThat(verboseCssSelector).isEqualTo("b#xxx")
    }

    @Test
    fun `can get parents as css selector syntax if any available`() {
        val verboseCssSelector = htmlDocument(aValidMarkup) { b { findLast { parentsCssSelector } } }
        expectThat(verboseCssSelector).isEqualTo("html > body > p > span")
    }

    @Test
    fun `parents as css selector syntax will be empty if none exist`() {
        expectThat(aValidElement.parentsCssSelector).isEmpty()
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
    fun `can get all attributes of an element`() {
        expectThat(aValidElement.attributes).isEqualTo(
            mapOf(
                "class" to "clazz klass",
                "foo" to "bar",
                "fizz" to "buzz",
                "data-foo" to "foobar"
            )
        )
    }

    @Test
    fun `can get all attribute keys of an element`() {
        expectThat(aValidElement.attributeKeys).containsExactly(
            "class", "foo", "fizz", "data-foo"
        )
    }

    @Test
    fun `can get all attribute values of an element`() {
        expectThat(aValidElement.attributeValues).containsExactly(
            "clazz klass", "bar", "buzz", "foobar"
        )
    }

    @Test
    fun `can get all distinct attributes from a list of elements`() {
        val someElements = listOf(aValidElement, aValidElement)
        expectThat(someElements.eachAttribute).isEqualTo(
            mapOf(
                "class" to "clazz klass",
                "foo" to "bar",
                "fizz" to "buzz",
                "data-foo" to "foobar"
            )
        )
    }

    @Test
    fun `can get all data attributes of an elements`() {
        expectThat(aValidElement.dataAttributes).isEqualTo(
            mapOf("data-foo" to "foobar")
        )
    }

    @Test
    fun `can get all data attributes from a list of elements`() {
        val someElements = listOf(aValidElement, aValidElement)
        expectThat(someElements.eachDataAttribute).isEqualTo(
            mapOf("data-foo" to "foobar")
        )
    }

    @Test
    fun `can get class attribute of an elements`() {
        expectThat(aValidElement.className).isEqualTo("clazz klass")
    }

    @Test
    fun `can get all class names a list of elements`() {
        val someElements = listOf(aValidElement, aValidElement)
        expectThat(someElements.eachClassName).containsExactly("clazz", "klass")
    }

    @Test
    fun `can check if an element has a certain class`() {
        expectThat(aValidElement.hasClass("clazz")).isTrue()
        expectThat(aValidElement.hasClass("invalid")).isFalse()
    }

    @Test
    fun `can invoke a css-selector as string to search children of given element`() {
        val markup = """
            <div class="foo">xxx<span>yyy</span></div>
            <div>zzz<h1>aaa</h1></div>
            <div class="my-class"><h1 class="welcome">first headline</h1></div>
            <div class="my-class"><h1 class="welcome">second headline</h1></div>
        """.trimIndent()


        val text = htmlDocument(markup) {
            div {
                withClass = "my-class"
                findAll {
                    expectThat(size).isEqualTo(2)
                    "h1" {
                        withClass = "welcome"
                        findFirst { text }
                    }
                }
            }
        }
        expectThat(text).isEqualTo("first headline")
    }

    @Test
    fun `can get all parents of an element`() {
        val parents = htmlDocument(aValidMarkup).findFirst("b") { parents }
        expectThat(parents.map { it.tagName }).containsExactly("span", "p", "body", "html")
    }

    @Test
    fun `will return empty list if no parents exists`() {
        val parents = htmlDocument(aValidMarkup).findFirst("html") { parents }
        expectThat(parents).isEmpty()
    }

    @Test
    fun `can get all parents of an element via lambda`() {
        val parents = htmlDocument(aValidMarkup).findFirst("b") { parents { this } }
        expectThat(parents.map { it.tagName }).containsExactly("span", "p", "body", "html")
    }

    @Test
    fun `can get parent of an element`() {
        val parent = htmlDocument(aValidMarkup).findFirst("b") { parent }
        expectThat(parent.tagName).isEqualTo("span")
    }

    @Test
    fun `will throw exception if trying to get parent but no parent exists`() {
        expectThrows<ElementNotFoundException> {
            htmlDocument(aValidMarkup).findFirst("html").parent
        }
    }

    @Test
    fun `can get parent of an element via lambda`() {
        val parent = htmlDocument(aValidMarkup).findFirst("b") { parent { this } }
        expectThat(parent.tagName).isEqualTo("span")
    }

    @Test
    fun `can get all children of an element`() {
        val children = htmlDocument(aValidMarkup).findFirst("p").children
        expectThat(children.map { it.tagName }).containsExactly("span", "span")
    }

    @Test
    fun `can get all children of an element via lambda`() {
        val children = htmlDocument(aValidMarkup).findFirst("p").children { this }
        expectThat(children.map { it.tagName }).containsExactly("span", "span")
    }

    @Test
    fun `can get all siblings of an element`() {
        val siblings = htmlDocument(aValidMarkup).findFirst("p").siblings
        expectThat(siblings.map { it.tagName }).containsExactly("h2")
    }

    @Test
    fun `can get all siblings of an element via lambda`() {
        val siblings = htmlDocument(aValidMarkup).findFirst("p").siblings { this }
        expectThat(siblings.map { it.tagName }).containsExactly("h2")
    }

    @Test
    fun `string representation has certain format`() {
        expectThat(aValidElement.toString()).isEqualTo(
            """
            <div class="clazz klass" foo="bar" fizz="buzz" data-foo="foobar">
             divs text 
             <h2 class="welcome" disabled>headline</h2> 
             <p class="fancy">paragraph <span>foo <b>bar</b></span> <span>fizz <b id="xxx">buzz</b></span> </p>
            </div>
            """.trimIndent()
        )
    }

    @Test
    fun `can select all elements by selector from within this element`() {
        val selection = aValidElement.findAll(".welcome")
        expectThat(selection).hasSize(1)
        expectThat(selection.text).isEqualTo("headline")
    }

    @Test
    fun `can conveniently iterate over all href values`() {
        aValidDocument {
            "[href]" {
                findAll {
                    forEachLink { text, url -> print("$text - $url") }
                }
            }
        }
    }

    @Test
    fun `can conveniently get all links as map of text and href from list of DocElement`() {

        val links = aValidDocument(aValidMarkupWithLinks) {
            a {
                findAll {
                    eachLink
                }
            }
        }

        expectThat(links).isEqualTo(
            mapOf(
                "foobar" to "http://foo.bar",
                "relative link" to "/relative",
                "modal" to "#modal",
                "nested link" to "http://nested.link",
                "i'm an anchor" to "http://some.link"
            )
        )
    }

    @Test
    @Disabled("TODO: make eachLink also filter child notes")
    fun `can conveniently get all links as map of text and href from list of DocElement and its children`() {

        val links = aValidDocument(aValidMarkupWithLinks) {
            div {
                findAll {
                    println(toString())
                    eachLink
                }
            }
        }

        expectThat(links).isEqualTo(
            mapOf(
                "foobar" to "http://foo.bar",
                "fizzbuzz" to "http://fizz.buzz",
                "schnitzel" to "http://schnitzel.de",
                "nested link" to "http://nested.link",
                "i'm an anchor" to "http://some.link"
            )
        )

    }

    @Test
    fun `can conveniently get all links as map of text and href from DocElement`() {
        val links = aValidDocument(aValidMarkupWithLinks) {
            a {
                findFirst {
                    eachLink
                }
            }
        }

        expectThat(links).isEqualTo(mapOf("foobar" to "http://foo.bar"))

    }

    @Test
    fun `can conveniently get all links as map of text and href from Doc`() {
        val links = aValidDocument(aValidMarkupWithLinks) {
            eachLink
        }

        expectThat(links).isEqualTo(
            mapOf(
                "" to "https://some.url/icon",
                "foobar" to "http://foo.bar",
                "relative link" to "/relative",
                "modal" to "#modal",
                "nested link" to "http://nested.link",
                "i'm an anchor" to "http://some.link"
            )
        )

    }

    @Test
    fun `can conveniently iterate over all images values`() {
        var images = mutableMapOf<String, String>()
        aValidDocument(aValidMarkupWithPictures) {
            img {
                findAll {
                    forEachImage { altText, url ->
                        images.put(altText, url)
                    }
                }
            }
        }
        expectThat(images.toMap()).isEqualTo(
            mapOf(
                "foobar" to "http://foo.bar",
                "" to "http://fizz.buzz",
                "yummi" to "http://schnitzel.de",
                "nested" to "http://nested.image"
            )
        )
    }

    @Test
    fun `can conveniently get all values of href attributes`() {
        aValidDocument(aValidMarkupWithLinks) {
            expectThat(eachHref).containsExactly(
                "https://some.url/icon",
                "http://foo.bar",
                "/relative",
                "#modal",
                "http://nested.link",
                "http://some.link"
            )
            a {
                findAll {
                    expectThat(eachHref).containsExactly(
                        "http://foo.bar",
                        "/relative",
                        "#modal",
                        "http://nested.link",
                        "http://some.link"
                    )
                }
            }
        }
    }

    @Test
    fun `can conveniently get all values of src attributes`() {
        aValidDocument(aValidMarkupWithPictures) {
            expectThat(eachSrc).containsExactly(
                "https://some.url/some-script.js",
                "http://foo.bar",
                "http://fizz.buzz",
                "http://schnitzel.de",
                "http://nested.image"
            )
            img {
                findAll {
                    expectThat(eachSrc).containsExactly(
                        "http://foo.bar",
                        "http://fizz.buzz",
                        "http://schnitzel.de",
                        "http://nested.image"
                    )
                }
            }
        }
    }

    @Test
    fun `can conveniently get all custom attributes`() {
        aValidDocument(aValidMarkupWithPictures) {
            expectThat(eachAttribute("rel")).containsExactly(
                "shortcut icon"
            )
            link {
                findAll {
                    expectThat(eachAttribute("rel")).containsExactly(
                        "shortcut icon"
                    )
                }
            }
            div {
                findAll {
                    expectThat(eachAttribute("rel")).isEmpty()
                }
            }
        }
    }

    @Test
    fun `can conveniently get all images as map of alt-text and src from list of DocElement`() {

        val pictures = aValidDocument(aValidMarkupWithPictures) {
            img {
                findAll {
                    eachImage
                }
            }
        }

        expectThat(pictures).isEqualTo(
            mapOf(
                "foobar" to "http://foo.bar",
                "" to "http://fizz.buzz",
                "yummi" to "http://schnitzel.de",
                "nested" to "http://nested.image"
            )
        )
    }

    @Test
    @Disabled("TODO: make eachImage also filter child notes")
    fun `can conveniently get all images as map of alt-text and src from list of DocElement and its children`() {

        val pictures = aValidDocument(aValidMarkupWithPictures) {
            div {
                findAll {
                    eachImage
                }
            }
        }

        expectThat(pictures).isEqualTo(
            mapOf(
                "foobar" to "http://foo.bar",
                "" to "http://fizz.buzz",
                "yummi" to "http://schnitzel.de",
                "nested" to "http://nested.image"
            )
        )

    }

    @Test
    fun `can conveniently get all images as map of alt-text and src from DocElement`() {
        val pictures = aValidDocument(aValidMarkupWithPictures) {
            img {
                findFirst {
                    eachImage
                }
            }
        }

        expectThat(pictures).isEqualTo(mapOf("foobar" to "http://foo.bar"))

    }

    @Test
    fun `can conveniently get all pictures as map of alt-text and src from Doc`() {
        val pictures = aValidDocument(aValidMarkupWithPictures) {
            eachImage
        }

        expectThat(pictures).isEqualTo(
            mapOf(
                "foobar" to "http://foo.bar",
                "" to "http://fizz.buzz",
                "yummi" to "http://schnitzel.de",
                "nested" to "http://nested.image"
            )
        )

    }

    @Test
    fun `can convert DocElement to jsoup element`() {
        expectThat(aValidElement.element).isA<Element>()
    }


    @Test
    fun `children, parents, siblings and allElements of relaxed element are also relaxed`() {
        htmlDocument("<div><a>skrape.it</a></div><div>sibling</div>") {
            findFirst("div") {
                expectThat(
                    children[0].relaxed || parents[0].relaxed || siblings[0].relaxed || allElements[0].relaxed
                ).isEqualTo(false)
            }

            relaxed = true

            findFirst("div") {
                expectThat(
                    children[0].relaxed && parents[0].relaxed && siblings[0].relaxed && allElements[0].relaxed
                ).isEqualTo(true)
            }
        }
    }
}