package it.skrape.selects

import aValidDocument
import aValidMarkupWithLinks
import aValidMarkupWithPictures
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.*
import io.kotest.matchers.types.shouldBeInstanceOf
import it.skrape.core.htmlDocument
import it.skrape.selects.html5.*
import it.skrape.selects.platform.Element
import it.skrape.selects.platform.emptyElements

class DocElementKtTest: FunSpec({

    val aValidMarkup = """
        <h2 class='welcome' disabled>headline</h2>
        <p class='fancy'>paragraph
            <span>foo <b>bar</b></span>
            <span>fizz <b id="xxx">buzz</b></span>
        </p>
    """.trimMargin()

    val anElement = Element("div").apply {
        prependText("divs text")
        addClass("clazz")
        addClass("klass")
        attr("foo", "bar")
        attr("fizz", "buzz")
        attr("data-foo", "foobar")
        append(aValidMarkup)
    }

    val aValidElement = DocElement(anElement)

    test("can get the element's tag name") {
        val tagName = htmlDocument(aValidMarkup).findFirst(".fancy") { tagName }
        tagName.shouldBe("p")
    }

    test("can get all tag names of a list of elements") {
        val someElements = listOf(aValidElement, aValidElement)
        someElements.eachTagName.shouldContainExactly("div", "div")
    }

    test("can get the element's text - including text of children") {
        aValidElement.text.shouldBe("divs text headline paragraph foo bar fizz buzz")
    }

    test("can get each text from a list of elements") {
        val someElements = listOf(aValidElement, aValidElement)
        someElements.eachText.shouldContainExactly(
            "divs text headline paragraph foo bar fizz buzz",
            "divs text headline paragraph foo bar fizz buzz"
        )
    }

    test("can get the element's own text - excluding text of children") {
        aValidElement.ownText.shouldBe("divs text")
    }

    test("can get inner html of an element") {
        aValidElement.html.shouldBe(
            """
            divs text 
            <h2 class="welcome" disabled>headline</h2> 
            <p class="fancy">paragraph <span>foo <b>bar</b></span> <span>fizz <b id="xxx">buzz</b></span> </p>
            """.trimIndent()
        )
    }

    test("can get outer html of an element") {
        assertSoftly(aValidElement.outerHtml) {
            lines().first()
                .shouldStartWith("<div")
                .shouldEndWith(">")
                .should {
                    " [\\w-]+(=\"[^\"]*\")?(?=\\s|>)".toRegex().findAll(it).map { it.value.trim() }.toList()
                        .shouldContainExactlyInAnyOrder(
                            "class=\"clazz klass\"",
                            "foo=\"bar\"",
                            "fizz=\"buzz\"",
                            "data-foo=\"foobar\""
                        )
                }
            lines().drop(1)
                .shouldContainExactly(
                    " divs text ",
                    " <h2 class=\"welcome\" disabled>headline</h2> ",
                    " <p class=\"fancy\">paragraph <span>foo <b>bar</b></span> <span>fizz <b id=\"xxx\">buzz</b></span> </p>",
                    "</div>"
                )
        }
    }

    test("'isPresent' will return true if element is present") {
        aValidElement.isPresent.shouldBe(true)
    }

    test("'isPresent' will return false if element is not present") {
        val element = object : Element("-") {
            override fun getAllElements() = emptyElements()
        }
        val docElement = DocElement(element)
        docElement.isPresent.shouldBe(false)
    }

    test("'isNotPresent' will return false if element is present") {
        aValidElement.isNotPresent.shouldBe(false)
    }

    test("'isNotPresent' will return true if element is not present") {
        val element = object : Element("-") {
            override fun getAllElements() = emptyElements()
        }
        val docElement = DocElement(element)
        docElement.isNotPresent.shouldBe(true)
    }

    test("'isPresent' will return true if list of elements are present") {
        aValidElement.allElements.isPresent.shouldBe(true)
    }

    test("'isPresent' will return false if list of elements are not present") {
        emptyList<DocElement>().isPresent.shouldBe(false)
    }

    test("'isNotPresent' will return false if list of elements are present") {
        aValidElement.allElements.isNotPresent.shouldBe(false)
    }

    test("'isNotPresent' will return true if list of elements are not present") {
        emptyList<DocElement>().isNotPresent.shouldBe(true)
    }

    test("can get all elements under this element including itself") {
        aValidElement.allElements.shouldHaveSize(7)
        aValidElement.allElements[1].outerHtml.shouldBe("""<h2 class="welcome" disabled>headline</h2>""")
    }

    test("can find all elements within this element (including itself) and invoke them to a lambda") {
        val text = aValidElement.findAll {
            size.shouldBe(7)
            get(1).outerHtml.shouldBe("""<h2 class="welcome" disabled>headline</h2>""")
            get(1).text
        }
        text.shouldBe("headline")
    }

    test("can find all elements by selector from within this element (including itself)") {
        val selection = aValidElement.findAll(".welcome")
        selection.shouldHaveSize(1)
        selection.text.shouldBe("headline")
    }

    test("can find all elements within this element by selector and invoke them to lambda that will return generic value") {
        val text = aValidElement.findAll(".welcome") {
            this.shouldHaveSize(1)
            text
        }
        text.shouldBe("headline")
    }

    test("can find first element within this element by selector and invoke them to lambda that will return generic value") {
        val text = aValidElement.findFirst(".welcome") {
            text
        }
        text.shouldBe("headline")
    }

    test("can find first element within this element by selector") {
        val text = aValidElement.findFirst(".welcome").text
        text.shouldBe("headline")
    }

    test("can get the css-class name of a given element") {
        aValidElement.className.shouldBe("clazz klass")
    }

    test("can get the css-selector of a given element") {
        aValidElement.toCssSelector.shouldBe("div.clazz.klass")
    }

    test("will return id only css-selector if given element has an id attribute") {
        val elementWithId = DocElement(anElement.attr("id", "bazinga"))
        elementWithId.toCssSelector.shouldBe("#bazinga")
    }

    test("can get ownCssSelector") {
        val elementWithId = DocElement(anElement.apply {
            attr("id", "bazinga")
            attr("key-only-attr", "")
        })
        //Complex expect since the order of the Attributes does not matter and might be different depending on the platform
        val attributes = listOf(
            "div#bazinga.clazz.klass", "[key-only-attr]", "[foo='bar']", "[fizz='buzz']", "[data-foo='foobar']"
        )
        assertSoftly(elementWithId) {
            ownCssSelector.shouldStartWith("div#bazinga.clazz.klass")
            attributes.forAll {
                ownCssSelector.shouldContainOnlyOnce(it)
            }
        }
    }

    test("ownCssSelector will not include parents if any available") {
        val verboseCssSelector = htmlDocument(aValidMarkup) { b { findLast { ownCssSelector } } }
        verboseCssSelector.shouldBe("b#xxx")
    }

    test("can get parents as css selector syntax if any available") {
        val verboseCssSelector = htmlDocument(aValidMarkup) { b { findLast { parentsCssSelector } } }
        verboseCssSelector.shouldBe("html > body > p > span")
    }

    test("parents as css selector syntax will be empty if none exist") {
        aValidElement.parentsCssSelector.shouldBeEmpty()
    }

    test("will return empty string on non existing attribute") {
        aValidElement.attribute("non-existing").shouldBeEmpty()
    }

    test("will return attribute value for a given attribute key that exists") {
        aValidElement.attribute("foo").shouldBe("bar")
    }

    test("will return true if a given attribute key is present at given element") {
        aValidElement.hasAttribute("foo").shouldBe(true)
    }

    test("will return false if a given attribute key is not present at given element") {
        aValidElement.hasAttribute("non-existing").shouldBe(false)
    }

    test("can get all attributes of an element") {
        aValidElement.attributes.shouldBe(
            mapOf(
                "class" to "clazz klass",
                "foo" to "bar",
                "fizz" to "buzz",
                "data-foo" to "foobar"
            )
        )
    }

    test("can get all attribute keys of an element") {
        aValidElement.attributeKeys.shouldContainExactlyInAnyOrder(
            "class", "foo", "fizz", "data-foo"
        )
    }

    test("can get all attribute values of an element") {
        aValidElement.attributeValues.shouldContainExactlyInAnyOrder(
            "clazz klass", "bar", "buzz", "foobar"
        )
    }

    test("can get all distinct attributes from a list of elements") {
        val someElements = listOf(aValidElement, aValidElement)
        someElements.eachAttribute.shouldBe(
            mapOf(
                "class" to "clazz klass",
                "foo" to "bar",
                "fizz" to "buzz",
                "data-foo" to "foobar"
            )
        )
    }

    test("can get all data attributes of an elements") {
        aValidElement.dataAttributes.shouldBe(
            mapOf("data-foo" to "foobar")
        )
    }

    test("can get all data attributes from a list of elements") {
        val someElements = listOf(aValidElement, aValidElement)
        someElements.eachDataAttribute.shouldBe(
            mapOf("data-foo" to "foobar")
        )
    }

    test("can get class attribute of an elements") {
        aValidElement.className.shouldBe("clazz klass")
    }

    test("can get all class names a list of elements") {
        val someElements = listOf(aValidElement, aValidElement)
        someElements.eachClassName.shouldContainExactly("clazz", "klass")
    }

    test("can check if an element has a certain class") {
        aValidElement.hasClass("clazz").shouldBe(true)
        aValidElement.hasClass("invalid").shouldBe(false)
    }

    test("can invoke a css-selector as string to search children of given element") {
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
                    size.shouldBe(2)
                    "h1" {
                        withClass = "welcome"
                        findFirst { text }
                    }
                }
            }
        }
        text.shouldBe("first headline")
    }

    test("can get all parents of an element") {
        val parents = htmlDocument(aValidMarkup).findFirst("b") { parents }
        parents.map { it.tagName }.shouldContainExactly("span", "p", "body", "html")
    }

    test("will return empty list if no parents exists") {
        val parents = htmlDocument(aValidMarkup).findFirst("html") { parents }
        parents.shouldBeEmpty()
    }

    test("can get all parents of an element via lambda") {
        val parents = htmlDocument(aValidMarkup).findFirst("b") { parents { this } }
        parents.map { it.tagName }.shouldContainExactly("span", "p", "body", "html")
    }

    test("can get parent of an element") {
        val parent = htmlDocument(aValidMarkup).findFirst("b") { parent }
        parent.tagName.shouldBe("span")
    }

    test("will throw exception if trying to get parent but no parent exists") {
        shouldThrow<ElementNotFoundException> {
            htmlDocument(aValidMarkup).findFirst("html").parent
        }
    }

    test("can get parent of an element via lambda") {
        val parent = htmlDocument(aValidMarkup).findFirst("b") { parent { this } }
        parent.tagName.shouldBe("span")
    }

    test("can get all children of an element") {
        val children = htmlDocument(aValidMarkup).findFirst("p").children
        children.map { it.tagName }.shouldContainExactly("span", "span")
    }

    test("can get all children of an element via lambda") {
        val children = htmlDocument(aValidMarkup).findFirst("p").children { this }
        children.map { it.tagName }.shouldContainExactly("span", "span")
    }

    test("can get all siblings of an element") {
        val siblings = htmlDocument(aValidMarkup).findFirst("p").siblings
        siblings.map { it.tagName }.shouldContainExactly("h2")
    }

    test("can get all siblings of an element via lambda") {
        val siblings = htmlDocument(aValidMarkup).findFirst("p").siblings { this }
        siblings.map { it.tagName }.shouldContainExactly("h2")
    }

    //Ignored since it's the same as 'can get outer html of an element'
    xtest("string representation has certain format") {
        aValidElement.toString().shouldBe(
            """
            <div class="clazz klass" foo="bar" fizz="buzz" data-foo="foobar">
             divs text 
             <h2 class="welcome" disabled>headline</h2> 
             <p class="fancy">paragraph <span>foo <b>bar</b></span> <span>fizz <b id="xxx">buzz</b></span> </p>
            </div>
            """.trimIndent()
        )
    }

    test("can select all elements by selector from within this element") {
        val selection = aValidElement.findAll(".welcome")
        selection.shouldHaveSize(1)
        selection.text.shouldBe("headline")
    }

    test("can conveniently iterate over all href values") {
        aValidDocument {
            "[href]" {
                findAll {
                    forEachLink { text, url -> print("$text - $url") }
                }
            }
        }
    }

    test("can handle complex selectors and will throw custom exception when not found") {
        shouldThrow<ElementNotFoundException> {
            aValidDocument(aValidMarkup) {
                div {
                    withId = "non-existend"
                    withClass = "non-existend"
                    withAttribute = "non" to "existend"
                    withAttributeKey = "non-existend"
                    withAttributes = listOf("non" to "existend")
                    withAttributeKeys = listOf("non-existend")
                    findFirst {}
                }
            }
        }
    }

    test("can conveniently get all links as map of text and href from list of DocElement") {

        val links = aValidDocument(aValidMarkupWithLinks) {
            a {
                findAll {
                    eachLink
                }
            }
        }

        links.shouldBe(
            mapOf(
                "foobar" to "http://foo.bar",
                "relative link" to "/relative",
                "modal" to "#modal",
                "nested link" to "http://nested.link",
                "i'm an anchor" to "http://some.link"
            )
        )
    }

    //TODO make eachLink also filter child notes
    xtest("can conveniently get all links as map of text and href from list of DocElement and its children") {

        val links = aValidDocument(aValidMarkupWithLinks) {
            div {
                findAll {
                    println(toString())
                    eachLink
                }
            }
        }

        links.shouldBe(
            mapOf(
                "foobar" to "http://foo.bar",
                "fizzbuzz" to "http://fizz.buzz",
                "schnitzel" to "http://schnitzel.de",
                "nested link" to "http://nested.link",
                "i'm an anchor" to "http://some.link"
            )
        )

    }

    test("can conveniently get all links as map of text and href from DocElement") {
        val links = aValidDocument(aValidMarkupWithLinks) {
            a {
                findFirst {
                    eachLink
                }
            }
        }

        links.shouldBe(mapOf("foobar" to "http://foo.bar"))

    }

    test("can conveniently get all links as map of text and href from Doc") {
        val links = aValidDocument(aValidMarkupWithLinks) {
            eachLink
        }

        links.shouldBe(
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

    test("can conveniently iterate over all images values") {
        val images = mutableMapOf<String, String>()
        aValidDocument(aValidMarkupWithPictures) {
            img {
                findAll {
                    forEachImage { altText, url ->
                        images.put(altText, url)
                    }
                }
            }
        }
        images.toMap().shouldBe(
            mapOf(
                "foobar" to "http://foo.bar",
                "" to "http://fizz.buzz",
                "yummi" to "http://schnitzel.de",
                "nested" to "http://nested.image"
            )
        )
    }

    test("can conveniently get all values of href attributes") {
        aValidDocument(aValidMarkupWithLinks) {
            eachHref.shouldContainExactly(
                "https://some.url/icon",
                "http://foo.bar",
                "/relative",
                "#modal",
                "http://nested.link",
                "http://some.link"
            )
            a {
                findAll {
                    eachHref.shouldContainExactly(
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

    test("can conveniently get all values of src attributes") {
        aValidDocument(aValidMarkupWithPictures) {
            eachSrc.shouldContainExactly(
                "https://some.url/some-script.js",
                "http://foo.bar",
                "http://fizz.buzz",
                "http://schnitzel.de",
                "http://nested.image"
            )
            img {
                findAll {
                    eachSrc.shouldContainExactly(
                        "http://foo.bar",
                        "http://fizz.buzz",
                        "http://schnitzel.de",
                        "http://nested.image"
                    )
                }
            }
        }
    }

    test("can conveniently get all custom attributes") {
        aValidDocument(aValidMarkupWithPictures) {
            eachAttribute("rel").shouldContainExactly(
                "shortcut icon"
            )
            link {
                findAll {
                    eachAttribute("rel").shouldContainExactly(
                        "shortcut icon"
                    )
                }
            }
            div {
                findAll {
                    eachAttribute("rel").shouldBeEmpty()
                }
            }
        }
    }

    test("can conveniently get all images as map of alt-text and src from list of DocElement") {

        val pictures = aValidDocument(aValidMarkupWithPictures) {
            img {
                findAll {
                    eachImage
                }
            }
        }

        pictures.shouldBe(
            mapOf(
                "foobar" to "http://foo.bar",
                "" to "http://fizz.buzz",
                "yummi" to "http://schnitzel.de",
                "nested" to "http://nested.image"
            )
        )
    }

    //TODO make eachImage also filter child notes
    xtest("can conveniently get all images as map of alt-text and src from list of DocElement and its children") {

        val pictures = aValidDocument(aValidMarkupWithPictures) {
            div {
                findAll {
                    eachImage
                }
            }
        }

        pictures.shouldBe(
            mapOf(
                "foobar" to "http://foo.bar",
                "" to "http://fizz.buzz",
                "yummi" to "http://schnitzel.de",
                "nested" to "http://nested.image"
            )
        )

    }

    test("can conveniently get all images as map of alt-text and src from DocElement") {
        val pictures = aValidDocument(aValidMarkupWithPictures) {
            img {
                findFirst {
                    eachImage
                }
            }
        }

        pictures.shouldBe(mapOf("foobar" to "http://foo.bar"))

    }

    test("can conveniently get all pictures as map of alt-text and src from Doc") {
        val pictures = aValidDocument(aValidMarkupWithPictures) {
            eachImage
        }

        pictures.shouldBe(
            mapOf(
                "foobar" to "http://foo.bar",
                "" to "http://fizz.buzz",
                "yummi" to "http://schnitzel.de",
                "nested" to "http://nested.image"
            )
        )

    }

    test("can convert DocElement to jsoup element") {
        aValidElement.element.shouldBeInstanceOf<Element>()
    }
})