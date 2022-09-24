package it.skrape.selects

import aValidDocument
import aValidMarkupWithLinks
import aValidMarkupWithPictures
import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import it.skrape.core.htmlDocument
import it.skrape.selects.html5.*
import it.skrape.selects.platform.Element
import it.skrape.selects.platform.Elements
import it.skrape.selects.platform.emptyElements
import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test

class DocElementKtTest {

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

    @JsName("CanGetTheElementsTagName")
    @Test
    fun `can get the element's tag name`() {
        val tagName = htmlDocument(aValidMarkup).findFirst(".fancy") { tagName }
        expect(tagName).toEqual("p")
    }

    @JsName("CanGetAllTagNamesOfAListOfElements")
    @Test
    fun `can get all tag names of a list of elements`() {
        val someElements = listOf(aValidElement, aValidElement)
        expect(someElements.eachTagName).toContainExactly("div", "div")
    }

    @JsName("CanGetTheElementsTextIncludingTextOfChildren")
    @Test
    fun `can get the element's text - including text of children`() {
        expect(aValidElement.text).toEqual("divs text headline paragraph foo bar fizz buzz")
    }

    @JsName("CanGetEachTextFromAListOfElements")
    @Test
    fun `can get each text from a list of elements`() {
        val someElements = listOf(aValidElement, aValidElement)
        expect(someElements.eachText).toContainExactly(
            "divs text headline paragraph foo bar fizz buzz",
            "divs text headline paragraph foo bar fizz buzz"
        )
    }

    @JsName("CanGetTheElementsOwnTextExcludingTextOfChildren")
    @Test
    fun `can get the element's own text - excluding text of children`() {
        expect(aValidElement.ownText).toEqual("divs text")
    }

    @JsName("CanGetInnerHtmlOfAnElement")
    @Test
    fun `can get inner html of an element`() {
        expect(aValidElement.html).toEqual(
            """
            divs text 
            <h2 class="welcome" disabled>headline</h2> 
            <p class="fancy">paragraph <span>foo <b>bar</b></span> <span>fizz <b id="xxx">buzz</b></span> </p>
            """.trimIndent()
        )
    }

    @JsName("CanGetOuterHtmlOfAnElement")
    @Test
    fun `can get outer html of an element`() {
        expect(aValidElement.outerHtml).toEqual(
            """
            <div class="clazz klass" foo="bar" fizz="buzz" data-foo="foobar">
             divs text 
             <h2 class="welcome" disabled>headline</h2> 
             <p class="fancy">paragraph <span>foo <b>bar</b></span> <span>fizz <b id="xxx">buzz</b></span> </p>
            </div>
            """.trimIndent()
        )
    }

    @JsName("isPresentWillReturnTrueIfElementIsPresent")
    @Test
    fun `'isPresent' will return true if element is present`() {
        expect(aValidElement.isPresent).toEqual(true)
    }

    @JsName("isPresentWillReturnFalseIfElementIsNotPresent")
    @Test
    fun `'isPresent' will return false if element is not present`() {
        val element = object : Element("-") {
            override fun getAllElements() = emptyElements()
        }
        val docElement = DocElement(element)
        expect(docElement.isPresent).toEqual(false)
    }

    @JsName("isNotPresentWillReturnFalseIfElementIsPresent")
    @Test
    fun `'isNotPresent' will return false if element is present`() {
        expect(aValidElement.isNotPresent).toEqual(false)
    }

    @JsName("isNotPresentWillReturnTrueIfElementIsNotPresent")
    @Test
    fun `'isNotPresent' will return true if element is not present`() {
        val element = object : Element("-") {
            override fun getAllElements() = emptyElements()
        }
        val docElement = DocElement(element)
        expect(docElement.isNotPresent).toEqual(true)
    }

    @JsName("isPresentWillReturnTrueIfListOfElementsArePresent")
    @Test
    fun `'isPresent' will return true if list of elements are present`() {
        expect(aValidElement.allElements.isPresent).toEqual(true)
    }

    @JsName("isPresentWillReturnFalseIfListOfElementsAreNotPresent")
    @Test
    fun `'isPresent' will return false if list of elements are not present`() {
        expect(emptyList<DocElement>().isPresent).toEqual(false)
    }

    @JsName("isNotPresentWillReturnFalseIfListOfElementsArePresent")
    @Test
    fun `'isNotPresent' will return false if list of elements are present`() {
        expect(aValidElement.allElements.isNotPresent).toEqual(false)
    }

    @JsName("isNotPresentWillReturnTrueIfListOfElementsAreNotPpresent")
    @Test
    fun `'isNotPresent' will return true if list of elements are not present`() {
        expect(emptyList<DocElement>().isNotPresent).toEqual(true)
    }

    @JsName("CanGetAllElementsUnderThisElementIncludingItself")
    @Test
    fun `can get all elements under this element including itself`() {
        expect(aValidElement.allElements).toHaveSize(7)
        expect(aValidElement.allElements[1].outerHtml).toEqual("""<h2 class="welcome" disabled>headline</h2>""")
    }

    @JsName("CanFindAllElementsWithinThisElementIncludingItselfAndInvokeThemToALambda")
    @Test
    fun `can find all elements within this element (including itself) and invoke them to a lambda`() {
        val text = aValidElement.findAll {
            expect(size).toEqual(7)
            expect(get(1).outerHtml).toEqual("""<h2 class="welcome" disabled>headline</h2>""")
            get(1).text
        }
        expect(text).toEqual("headline")
    }

    @JsName("CanFindAllElementsBySelectorFromWithinThisElementIncludingItself")
    @Test
    fun `can find all elements by selector from within this element (including itself)`() {
        val selection = aValidElement.findAll(".welcome")
        expect(selection).toHaveSize(1)
        expect(selection.text).toEqual("headline")
    }

    @JsName("CanFindAllElementsWithinThisElementBySelectorAndInvokeThemToLambdaThatWillReturnGenericValue")
    @Test
    fun `can find all elements within this element by selector and invoke them to lambda that will return generic value`() {
        val text = aValidElement.findAll(".welcome") {
            expect(this).toHaveSize(1)
            text
        }
        expect(text).toEqual("headline")
    }

    @JsName("CanFindFirstElementWithinThisElementBySelectorAndInvokeThemToLambdaThatWillReturnGenericValue")
    @Test
    fun `can find first element within this element by selector and invoke them to lambda that will return generic value`() {
        val text = aValidElement.findFirst(".welcome") {
            text
        }
        expect(text).toEqual("headline")
    }

    @JsName("CanFindFirstElementWithinThisElementBySelector")
    @Test
    fun `can find first element within this element by selector`() {
        val text = aValidElement.findFirst(".welcome").text
        expect(text).toEqual("headline")
    }

    @JsName("CanGetTheCssClassNameOfAGivenElement")
    @Test
    fun `can get the css-class name of a given element`() {
        expect(aValidElement.className).toEqual("clazz klass")
    }

    @JsName("CanGetTheCssSelectorOfAGivenElement")
    @Test
    fun `can get the css-selector of a given element`() {
        expect(aValidElement.toCssSelector).toEqual("div.clazz.klass")
    }

    @JsName("WillReturnIdOnlyCssSelectorIfGivenElementHasAnIdAttribute")
    @Test
    fun `will return id only css-selector if given element has an id attribute`() {
        val elementWithId = DocElement(anElement.attr("id", "bazinga"))
        expect(elementWithId.toCssSelector).toEqual("#bazinga")
    }

    @JsName("CanGetOwnCssSelector")
    @Test
    fun `can get ownCssSelector`() {
        val elementWithId = DocElement(anElement.apply {
            attr("id", "bazinga")
            attr("key-only-attr", "")
        })
        //Complex expect since the order of the Attributes does not matter and might be different depending on the platform
        expect(elementWithId.ownCssSelector).toStartWith("div#bazinga.clazz.klass").toContain.exactly(1).values(
            "div#bazinga.clazz.klass", "['key-only-attr']", "[foo='bar']", "[fizz='buzz']", "[data-foo='foobar']"
        )
    }

    @JsName("OwnCssSelectorWillNotIncludeParentsIfAnyAvailable")
    @Test
    fun `ownCssSelector will not include parents if any available`() {
        val verboseCssSelector = htmlDocument(aValidMarkup) { b { findLast { ownCssSelector } } }
        expect(verboseCssSelector).toEqual("b#xxx")
    }

    @JsName("CanGetParentsAsCssSelectorSyntaxIfAnyAvailable")
    @Test
    fun `can get parents as css selector syntax if any available`() {
        val verboseCssSelector = htmlDocument(aValidMarkup) { b { findLast { parentsCssSelector } } }
        expect(verboseCssSelector).toEqual("html > body > p > span")
    }

    @JsName("ParentsAsCssSelectorSyntaxWillBeEmptyIfNoneExist")
    @Test
    fun `parents as css selector syntax will be empty if none exist`() {
        expect(aValidElement.parentsCssSelector).toBeEmpty()
    }

    @JsName("WillReturnEmptyStringOnNonExistingAttribute")
    @Test
    fun `will return empty string on non existing attribute`() {
        expect(aValidElement.attribute("non-existing")).toBeEmpty()
    }

    @JsName("WillReturnAttributeValueForAGivenAttributeKeyThatExists")
    @Test
    fun `will return attribute value for a given attribute key that exists`() {
        expect(aValidElement.attribute("foo")).toEqual("bar")
    }

    @JsName("WillReturnTrueIfAGivenAttributeKeyIsPresentAtGivenElement")
    @Test
    fun `will return true if a given attribute key is present at given element`() {
        expect(aValidElement.hasAttribute("foo")).toEqual(true)
    }

    @JsName("WillReturnFalseIfAGivenAttributeKeyIsNotPresentAtGivenElement")
    @Test
    fun `will return false if a given attribute key is not present at given element`() {
        expect(aValidElement.hasAttribute("non-existing")).toEqual(false)
    }

    @JsName("CanGetAllAttributesOfAnElement")
    @Test
    fun `can get all attributes of an element`() {
        expect(aValidElement.attributes).toEqual(
            mapOf(
                "class" to "clazz klass",
                "foo" to "bar",
                "fizz" to "buzz",
                "data-foo" to "foobar"
            )
        )
    }

    @JsName("CanGetAllAttributeKeysOfAnElement")
    @Test
    fun `can get all attribute keys of an element`() {
        expect(aValidElement.attributeKeys).toContain.inAnyOrder.exactly(1).values(
            "class", "foo", "fizz", "data-foo"
        )
    }

    @JsName("CanGetAllAttributeValuesOfAnElement")
    @Test
    fun `can get all attribute values of an element`() {
        expect(aValidElement.attributeValues).toContain.inAnyOrder.exactly(1).values(
            "clazz klass", "bar", "buzz", "foobar"
        )
    }

    @JsName("CanGetAllDistinctAttributesFromAListOfElements")
    @Test
    fun `can get all distinct attributes from a list of elements`() {
        val someElements = listOf(aValidElement, aValidElement)
        expect(someElements.eachAttribute).toEqual(
            mapOf(
                "class" to "clazz klass",
                "foo" to "bar",
                "fizz" to "buzz",
                "data-foo" to "foobar"
            )
        )
    }

    @JsName("CanGetAllDataAttributesOfAnElements")
    @Test
    fun `can get all data attributes of an elements`() {
        expect(aValidElement.dataAttributes).toEqual(
            mapOf("data-foo" to "foobar")
        )
    }

    @JsName("CanGetAllDataAttributesFromAListOfElements")
    @Test
    fun `can get all data attributes from a list of elements`() {
        val someElements = listOf(aValidElement, aValidElement)
        expect(someElements.eachDataAttribute).toEqual(
            mapOf("data-foo" to "foobar")
        )
    }

    @JsName("CanGetClassAttributeOfAnElements")
    @Test
    fun `can get class attribute of an elements`() {
        expect(aValidElement.className).toEqual("clazz klass")
    }

    @JsName("CanGetAllClassNamesAListOfElements")
    @Test
    fun `can get all class names a list of elements`() {
        val someElements = listOf(aValidElement, aValidElement)
        expect(someElements.eachClassName).toContainExactly("clazz", "klass")
    }

    @JsName("CanCheckIfAnElementHasACertainClass")
    @Test
    fun `can check if an element has a certain class`() {
        expect(aValidElement.hasClass("clazz")).toEqual(true)
        expect(aValidElement.hasClass("invalid")).toEqual(false)
    }

    @JsName("CanInvokeACssSelectorAsStringToSearchChildrenOfGivenElement")
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
                    expect(size).toEqual(2)
                    "h1" {
                        withClass = "welcome"
                        findFirst { text }
                    }
                }
            }
        }
        expect(text).toEqual("first headline")
    }

    @JsName("CanGetAllParentsOfAnElement")
    @Test
    fun `can get all parents of an element`() {
        val parents = htmlDocument(aValidMarkup).findFirst("b") { parents }
        expect(parents.map { it.tagName }).toContainExactly("span", "p", "body", "html")
    }

    @JsName("WillReturnEmptyListIfNoParentsExists")
    @Test
    fun `will return empty list if no parents exists`() {
        val parents = htmlDocument(aValidMarkup).findFirst("html") { parents }
        expect(parents).toBeEmpty()
    }

    @JsName("CanGetAllParentsOfAnElementViaLambda")
    @Test
    fun `can get all parents of an element via lambda`() {
        val parents = htmlDocument(aValidMarkup).findFirst("b") { parents { this } }
        expect(parents.map { it.tagName }).toContainExactly("span", "p", "body", "html")
    }

    @JsName("CanGetParentOfAnElement")
    @Test
    fun `can get parent of an element`() {
        val parent = htmlDocument(aValidMarkup).findFirst("b") { parent }
        expect(parent.tagName).toEqual("span")
    }

    @JsName("WillThrowExceptionIfTryingToGetParentButNoParentExists")
    @Test
    fun `will throw exception if trying to get parent but no parent exists`() {
        expect {
            htmlDocument(aValidMarkup).findFirst("html").parent
        }.toThrow<ElementNotFoundException>()
    }

    @JsName("CanGetParentOfAnElementViaLambda")
    @Test
    fun `can get parent of an element via lambda`() {
        val parent = htmlDocument(aValidMarkup).findFirst("b") { parent { this } }
        expect(parent.tagName).toEqual("span")
    }

    @JsName("CanGetAllChildrenOfAnElement")
    @Test
    fun `can get all children of an element`() {
        val children = htmlDocument(aValidMarkup).findFirst("p").children
        expect(children.map { it.tagName }).toContainExactly("span", "span")
    }

    @JsName("CanGetAllChildrenOfAnElementViaLambda")
    @Test
    fun `can get all children of an element via lambda`() {
        val children = htmlDocument(aValidMarkup).findFirst("p").children { this }
        expect(children.map { it.tagName }).toContainExactly("span", "span")
    }

    @JsName("CanGetAllSiblingsOfAnElement")
    @Test
    fun `can get all siblings of an element`() {
        val siblings = htmlDocument(aValidMarkup).findFirst("p").siblings
        expect(siblings.map { it.tagName }).toContainExactly("h2")
    }

    @JsName("CanGetAllSiblingsOfAnElementViaLambda")
    @Test
    fun `can get all siblings of an element via lambda`() {
        val siblings = htmlDocument(aValidMarkup).findFirst("p").siblings { this }
        expect(siblings.map { it.tagName }).toContainExactly("h2")
    }

    @JsName("StringRepresentationHasCertainFormat")
    @Test
    fun `string representation has certain format`() {
        expect(aValidElement.toString()).toEqual(
            """
            <div class="clazz klass" foo="bar" fizz="buzz" data-foo="foobar">
             divs text 
             <h2 class="welcome" disabled>headline</h2> 
             <p class="fancy">paragraph <span>foo <b>bar</b></span> <span>fizz <b id="xxx">buzz</b></span> </p>
            </div>
            """.trimIndent()
        )
    }

    @JsName("CanSelectAllElementsBySelectorFromWithinThisElement")
    @Test
    fun `can select all elements by selector from within this element`() {
        val selection = aValidElement.findAll(".welcome")
        expect(selection).toHaveSize(1)
        expect(selection.text).toEqual("headline")
    }

    @JsName("CanConvenientlyIterateOverAllHrefValues")
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

    @JsName("CanConvenientlyGetAllLinksAsMapOfTextAndHrefFromListOfDocElement")
    @Test
    fun `can conveniently get all links as map of text and href from list of DocElement`() {

        val links = aValidDocument(aValidMarkupWithLinks) {
            a {
                findAll {
                    eachLink
                }
            }
        }

        expect(links).toEqual(
            mapOf(
                "foobar" to "http://foo.bar",
                "relative link" to "/relative",
                "modal" to "#modal",
                "nested link" to "http://nested.link",
                "i'm an anchor" to "http://some.link"
            )
        )
    }

    @JsName("CanConvenientlyGetAllLinksAsMapOfTextAndHrefFromListOfDocElementAndItsChildren")
    @Test
    @Ignore
    //TODO make eachLink also filter child notes
    fun `can conveniently get all links as map of text and href from list of DocElement and its children`() {

        val links = aValidDocument(aValidMarkupWithLinks) {
            div {
                findAll {
                    println(toString())
                    eachLink
                }
            }
        }

        expect(links).toEqual(
            mapOf(
                "foobar" to "http://foo.bar",
                "fizzbuzz" to "http://fizz.buzz",
                "schnitzel" to "http://schnitzel.de",
                "nested link" to "http://nested.link",
                "i'm an anchor" to "http://some.link"
            )
        )

    }

    @JsName("CanConvenientlyGetAllLinksAsMapOfTextAndHrefFromDocElement")
    @Test
    fun `can conveniently get all links as map of text and href from DocElement`() {
        val links = aValidDocument(aValidMarkupWithLinks) {
            a {
                findFirst {
                    eachLink
                }
            }
        }

        expect(links).toEqual(mapOf("foobar" to "http://foo.bar"))

    }

    @JsName("CanConvenientlyGetAllLinksAsMapOfTextAndHrefFromDoc")
    @Test
    fun `can conveniently get all links as map of text and href from Doc`() {
        val links = aValidDocument(aValidMarkupWithLinks) {
            eachLink
        }

        expect(links).toEqual(
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

    @JsName("CanConvenientlyIterateOverAllImagesValues")
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
        expect(images.toMap()).toEqual(
            mapOf(
                "foobar" to "http://foo.bar",
                "" to "http://fizz.buzz",
                "yummi" to "http://schnitzel.de",
                "nested" to "http://nested.image"
            )
        )
    }

    @JsName("CanConvenientlyGetAllValuesOfHrefAttributes")
    @Test
    fun `can conveniently get all values of href attributes`() {
        aValidDocument(aValidMarkupWithLinks) {
            expect(eachHref).toContainExactly(
                "https://some.url/icon",
                "http://foo.bar",
                "/relative",
                "#modal",
                "http://nested.link",
                "http://some.link"
            )
            a {
                findAll {
                    expect(eachHref).toContainExactly(
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

    @JsName("CanConvenientlyGetAllValuesOfSrcAttributes")
    @Test
    fun `can conveniently get all values of src attributes`() {
        aValidDocument(aValidMarkupWithPictures) {
            expect(eachSrc).toContainExactly(
                "https://some.url/some-script.js",
                "http://foo.bar",
                "http://fizz.buzz",
                "http://schnitzel.de",
                "http://nested.image"
            )
            img {
                findAll {
                    expect(eachSrc).toContainExactly(
                        "http://foo.bar",
                        "http://fizz.buzz",
                        "http://schnitzel.de",
                        "http://nested.image"
                    )
                }
            }
        }
    }

    @JsName("CanConvenientlyGetAllCustomAttributes")
    @Test
    fun `can conveniently get all custom attributes`() {
        aValidDocument(aValidMarkupWithPictures) {
            expect(eachAttribute("rel")).toContainExactly(
                "shortcut icon"
            )
            link {
                findAll {
                    expect(eachAttribute("rel")).toContainExactly(
                        "shortcut icon"
                    )
                }
            }
            div {
                findAll {
                    expect(eachAttribute("rel")).toBeEmpty()
                }
            }
        }
    }

    @JsName("CanConvenientlyGetAllImagesAsMapOfAltTextAndSrcFromListOfDocElement")
    @Test
    fun `can conveniently get all images as map of alt-text and src from list of DocElement`() {

        val pictures = aValidDocument(aValidMarkupWithPictures) {
            img {
                findAll {
                    eachImage
                }
            }
        }

        expect(pictures).toEqual(
            mapOf(
                "foobar" to "http://foo.bar",
                "" to "http://fizz.buzz",
                "yummi" to "http://schnitzel.de",
                "nested" to "http://nested.image"
            )
        )
    }

    @JsName("CanConvenientlyGetAllImagesAsMapOfAltTextAndSrcFromListOfDocElementAndItsChildren")
    @Test
    @Ignore
    //TODO make eachImage also filter child notes
    fun `can conveniently get all images as map of alt-text and src from list of DocElement and its children`() {

        val pictures = aValidDocument(aValidMarkupWithPictures) {
            div {
                findAll {
                    eachImage
                }
            }
        }

        expect(pictures).toEqual(
            mapOf(
                "foobar" to "http://foo.bar",
                "" to "http://fizz.buzz",
                "yummi" to "http://schnitzel.de",
                "nested" to "http://nested.image"
            )
        )

    }

    @JsName("CanConvenientlyGetAllImagesAsMapOfAltTextAndSrcFromDocElement")
    @Test
    fun `can conveniently get all images as map of alt-text and src from DocElement`() {
        val pictures = aValidDocument(aValidMarkupWithPictures) {
            img {
                findFirst {
                    eachImage
                }
            }
        }

        expect(pictures).toEqual(mapOf("foobar" to "http://foo.bar"))

    }

    @JsName("CanConvenientlyGetAllPicturesAsMapOfAltTextAndSrcFromDoc")
    @Test
    fun `can conveniently get all pictures as map of alt-text and src from Doc`() {
        val pictures = aValidDocument(aValidMarkupWithPictures) {
            eachImage
        }

        expect(pictures).toEqual(
            mapOf(
                "foobar" to "http://foo.bar",
                "" to "http://fizz.buzz",
                "yummi" to "http://schnitzel.de",
                "nested" to "http://nested.image"
            )
        )

    }

    @JsName("CanConvertDocElementToJsoupElement")
    @Test
    fun `can convert DocElement to jsoup element`() {
        expect(aValidElement.element).toBeAnInstanceOf<Element>()
    }
}