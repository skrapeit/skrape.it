package it.skrape.selects

import aValidDocument
import aValidMarkupWithLinks
import aValidMarkupWithPictures
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
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
        tagName.shouldBe("p")
    }

    @JsName("CanGetAllTagNamesOfAListOfElements")
    @Test
    fun `can get all tag names of a list of elements`() {
        val someElements = listOf(aValidElement, aValidElement)
        someElements.eachTagName.shouldContainExactly("div", "div")
    }

    @JsName("CanGetTheElementsTextIncludingTextOfChildren")
    @Test
    fun `can get the element's text - including text of children`() {
        aValidElement.text.shouldBe("divs text headline paragraph foo bar fizz buzz")
    }

    @JsName("CanGetEachTextFromAListOfElements")
    @Test
    fun `can get each text from a list of elements`() {
        val someElements = listOf(aValidElement, aValidElement)
        someElements.eachText.shouldContainExactly(
            "divs text headline paragraph foo bar fizz buzz",
            "divs text headline paragraph foo bar fizz buzz"
        )
    }

    @JsName("CanGetTheElementsOwnTextExcludingTextOfChildren")
    @Test
    fun `can get the element's own text - excluding text of children`() {
        aValidElement.ownText.shouldBe("divs text")
    }

    @JsName("CanGetInnerHtmlOfAnElement")
    @Test
    fun `can get inner html of an element`() {
        aValidElement.html.shouldBe(
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

    @JsName("isPresentWillReturnTrueIfElementIsPresent")
    @Test
    fun `'isPresent' will return true if element is present`() {
        aValidElement.isPresent.shouldBe(true)
    }

    @JsName("isPresentWillReturnFalseIfElementIsNotPresent")
    @Test
    fun `'isPresent' will return false if element is not present`() {
        val element = object : Element("-") {
            override fun getAllElements() = emptyElements()
        }
        val docElement = DocElement(element)
        docElement.isPresent.shouldBe(false)
    }

    @JsName("isNotPresentWillReturnFalseIfElementIsPresent")
    @Test
    fun `'isNotPresent' will return false if element is present`() {
        aValidElement.isNotPresent.shouldBe(false)
    }

    @JsName("isNotPresentWillReturnTrueIfElementIsNotPresent")
    @Test
    fun `'isNotPresent' will return true if element is not present`() {
        val element = object : Element("-") {
            override fun getAllElements() = emptyElements()
        }
        val docElement = DocElement(element)
        docElement.isNotPresent.shouldBe(true)
    }

    @JsName("isPresentWillReturnTrueIfListOfElementsArePresent")
    @Test
    fun `'isPresent' will return true if list of elements are present`() {
        aValidElement.allElements.isPresent.shouldBe(true)
    }

    @JsName("isPresentWillReturnFalseIfListOfElementsAreNotPresent")
    @Test
    fun `'isPresent' will return false if list of elements are not present`() {
        emptyList<DocElement>().isPresent.shouldBe(false)
    }

    @JsName("isNotPresentWillReturnFalseIfListOfElementsArePresent")
    @Test
    fun `'isNotPresent' will return false if list of elements are present`() {
        aValidElement.allElements.isNotPresent.shouldBe(false)
    }

    @JsName("isNotPresentWillReturnTrueIfListOfElementsAreNotPpresent")
    @Test
    fun `'isNotPresent' will return true if list of elements are not present`() {
        emptyList<DocElement>().isNotPresent.shouldBe(true)
    }

    @JsName("CanGetAllElementsUnderThisElementIncludingItself")
    @Test
    fun `can get all elements under this element including itself`() {
        aValidElement.allElements.shouldHaveSize(7)
        aValidElement.allElements[1].outerHtml.shouldBe("""<h2 class="welcome" disabled>headline</h2>""")
    }

    @JsName("CanFindAllElementsWithinThisElementIncludingItselfAndInvokeThemToALambda")
    @Test
    fun `can find all elements within this element (including itself) and invoke them to a lambda`() {
        val text = aValidElement.findAll {
            size.shouldBe(7)
            get(1).outerHtml.shouldBe("""<h2 class="welcome" disabled>headline</h2>""")
            get(1).text
        }
        text.shouldBe("headline")
    }

    @JsName("CanFindAllElementsBySelectorFromWithinThisElementIncludingItself")
    @Test
    fun `can find all elements by selector from within this element (including itself)`() {
        val selection = aValidElement.findAll(".welcome")
        selection.shouldHaveSize(1)
        selection.text.shouldBe("headline")
    }

    @JsName("CanFindAllElementsWithinThisElementBySelectorAndInvokeThemToLambdaThatWillReturnGenericValue")
    @Test
    fun `can find all elements within this element by selector and invoke them to lambda that will return generic value`() {
        val text = aValidElement.findAll(".welcome") {
            this.shouldHaveSize(1)
            text
        }
        text.shouldBe("headline")
    }

    @JsName("CanFindFirstElementWithinThisElementBySelectorAndInvokeThemToLambdaThatWillReturnGenericValue")
    @Test
    fun `can find first element within this element by selector and invoke them to lambda that will return generic value`() {
        val text = aValidElement.findFirst(".welcome") {
            text
        }
        text.shouldBe("headline")
    }

    @JsName("CanFindFirstElementWithinThisElementBySelector")
    @Test
    fun `can find first element within this element by selector`() {
        val text = aValidElement.findFirst(".welcome").text
        text.shouldBe("headline")
    }

    @JsName("CanGetTheCssClassNameOfAGivenElement")
    @Test
    fun `can get the css-class name of a given element`() {
        aValidElement.className.shouldBe("clazz klass")
    }

    @JsName("CanGetTheCssSelectorOfAGivenElement")
    @Test
    fun `can get the css-selector of a given element`() {
        aValidElement.toCssSelector.shouldBe("div.clazz.klass")
    }

    @JsName("WillReturnIdOnlyCssSelectorIfGivenElementHasAnIdAttribute")
    @Test
    fun `will return id only css-selector if given element has an id attribute`() {
        val elementWithId = DocElement(anElement.attr("id", "bazinga"))
        elementWithId.toCssSelector.shouldBe("#bazinga")
    }

    @JsName("CanGetOwnCssSelector")
    @Test
    fun `can get ownCssSelector`() {
        val elementWithId = DocElement(anElement.apply {
            attr("id", "bazinga")
            attr("key-only-attr", "")
        })
        //Complex expect since the order of the Attributes does not matter and might be different depending on the platform
        val attributes = listOf(
            "div#bazinga.clazz.klass", "['key-only-attr']", "[foo='bar']", "[fizz='buzz']", "[data-foo='foobar']"
        )
        assertSoftly(elementWithId) {
            ownCssSelector.shouldStartWith("div#bazinga.clazz.klass")
            attributes.forAll {
                ownCssSelector.shouldContainOnlyOnce(it)
            }
        }
    }

    @JsName("OwnCssSelectorWillNotIncludeParentsIfAnyAvailable")
    @Test
    fun `ownCssSelector will not include parents if any available`() {
        val verboseCssSelector = htmlDocument(aValidMarkup) { b { findLast { ownCssSelector } } }
        verboseCssSelector.shouldBe("b#xxx")
    }

    @JsName("CanGetParentsAsCssSelectorSyntaxIfAnyAvailable")
    @Test
    fun `can get parents as css selector syntax if any available`() {
        val verboseCssSelector = htmlDocument(aValidMarkup) { b { findLast { parentsCssSelector } } }
        verboseCssSelector.shouldBe("html > body > p > span")
    }

    @JsName("ParentsAsCssSelectorSyntaxWillBeEmptyIfNoneExist")
    @Test
    fun `parents as css selector syntax will be empty if none exist`() {
        aValidElement.parentsCssSelector.shouldBeEmpty()
    }

    @JsName("WillReturnEmptyStringOnNonExistingAttribute")
    @Test
    fun `will return empty string on non existing attribute`() {
        aValidElement.attribute("non-existing").shouldBeEmpty()
    }

    @JsName("WillReturnAttributeValueForAGivenAttributeKeyThatExists")
    @Test
    fun `will return attribute value for a given attribute key that exists`() {
        aValidElement.attribute("foo").shouldBe("bar")
    }

    @JsName("WillReturnTrueIfAGivenAttributeKeyIsPresentAtGivenElement")
    @Test
    fun `will return true if a given attribute key is present at given element`() {
        aValidElement.hasAttribute("foo").shouldBe(true)
    }

    @JsName("WillReturnFalseIfAGivenAttributeKeyIsNotPresentAtGivenElement")
    @Test
    fun `will return false if a given attribute key is not present at given element`() {
        aValidElement.hasAttribute("non-existing").shouldBe(false)
    }

    @JsName("CanGetAllAttributesOfAnElement")
    @Test
    fun `can get all attributes of an element`() {
        aValidElement.attributes.shouldBe(
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
        aValidElement.attributeKeys.shouldContainExactlyInAnyOrder(
            "class", "foo", "fizz", "data-foo"
        )
    }

    @JsName("CanGetAllAttributeValuesOfAnElement")
    @Test
    fun `can get all attribute values of an element`() {
        aValidElement.attributeValues.shouldContainExactlyInAnyOrder(
            "clazz klass", "bar", "buzz", "foobar"
        )
    }

    @JsName("CanGetAllDistinctAttributesFromAListOfElements")
    @Test
    fun `can get all distinct attributes from a list of elements`() {
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

    @JsName("CanGetAllDataAttributesOfAnElements")
    @Test
    fun `can get all data attributes of an elements`() {
        aValidElement.dataAttributes.shouldBe(
            mapOf("data-foo" to "foobar")
        )
    }

    @JsName("CanGetAllDataAttributesFromAListOfElements")
    @Test
    fun `can get all data attributes from a list of elements`() {
        val someElements = listOf(aValidElement, aValidElement)
        someElements.eachDataAttribute.shouldBe(
            mapOf("data-foo" to "foobar")
        )
    }

    @JsName("CanGetClassAttributeOfAnElements")
    @Test
    fun `can get class attribute of an elements`() {
        aValidElement.className.shouldBe("clazz klass")
    }

    @JsName("CanGetAllClassNamesAListOfElements")
    @Test
    fun `can get all class names a list of elements`() {
        val someElements = listOf(aValidElement, aValidElement)
        someElements.eachClassName.shouldContainExactly("clazz", "klass")
    }

    @JsName("CanCheckIfAnElementHasACertainClass")
    @Test
    fun `can check if an element has a certain class`() {
        aValidElement.hasClass("clazz").shouldBe(true)
        aValidElement.hasClass("invalid").shouldBe(false)
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

    @JsName("CanGetAllParentsOfAnElement")
    @Test
    fun `can get all parents of an element`() {
        val parents = htmlDocument(aValidMarkup).findFirst("b") { parents }
        parents.map { it.tagName }.shouldContainExactly("span", "p", "body", "html")
    }

    @JsName("WillReturnEmptyListIfNoParentsExists")
    @Test
    fun `will return empty list if no parents exists`() {
        val parents = htmlDocument(aValidMarkup).findFirst("html") { parents }
        parents.shouldBeEmpty()
    }

    @JsName("CanGetAllParentsOfAnElementViaLambda")
    @Test
    fun `can get all parents of an element via lambda`() {
        val parents = htmlDocument(aValidMarkup).findFirst("b") { parents { this } }
        parents.map { it.tagName }.shouldContainExactly("span", "p", "body", "html")
    }

    @JsName("CanGetParentOfAnElement")
    @Test
    fun `can get parent of an element`() {
        val parent = htmlDocument(aValidMarkup).findFirst("b") { parent }
        parent.tagName.shouldBe("span")
    }

    @JsName("WillThrowExceptionIfTryingToGetParentButNoParentExists")
    @Test
    fun `will throw exception if trying to get parent but no parent exists`() {
        shouldThrow<ElementNotFoundException> {
            htmlDocument(aValidMarkup).findFirst("html").parent
        }
    }

    @JsName("CanGetParentOfAnElementViaLambda")
    @Test
    fun `can get parent of an element via lambda`() {
        val parent = htmlDocument(aValidMarkup).findFirst("b") { parent { this } }
        parent.tagName.shouldBe("span")
    }

    @JsName("CanGetAllChildrenOfAnElement")
    @Test
    fun `can get all children of an element`() {
        val children = htmlDocument(aValidMarkup).findFirst("p").children
        children.map { it.tagName }.shouldContainExactly("span", "span")
    }

    @JsName("CanGetAllChildrenOfAnElementViaLambda")
    @Test
    fun `can get all children of an element via lambda`() {
        val children = htmlDocument(aValidMarkup).findFirst("p").children { this }
        children.map { it.tagName }.shouldContainExactly("span", "span")
    }

    @JsName("CanGetAllSiblingsOfAnElement")
    @Test
    fun `can get all siblings of an element`() {
        val siblings = htmlDocument(aValidMarkup).findFirst("p").siblings
        siblings.map { it.tagName }.shouldContainExactly("h2")
    }

    @JsName("CanGetAllSiblingsOfAnElementViaLambda")
    @Test
    fun `can get all siblings of an element via lambda`() {
        val siblings = htmlDocument(aValidMarkup).findFirst("p").siblings { this }
        siblings.map { it.tagName }.shouldContainExactly("h2")
    }

    @JsName("StringRepresentationHasCertainFormat")
    @Test
    @Ignore
    //Ignored since it's the same as 'can get outer html of an element'
    fun `string representation has certain format`() {
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

    @JsName("CanSelectAllElementsBySelectorFromWithinThisElement")
    @Test
    fun `can select all elements by selector from within this element`() {
        val selection = aValidElement.findAll(".welcome")
        selection.shouldHaveSize(1)
        selection.text.shouldBe("headline")
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

        links.shouldBe(mapOf("foobar" to "http://foo.bar"))

    }

    @JsName("CanConvenientlyGetAllLinksAsMapOfTextAndHrefFromDoc")
    @Test
    fun `can conveniently get all links as map of text and href from Doc`() {
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

    @JsName("CanConvenientlyIterateOverAllImagesValues")
    @Test
    fun `can conveniently iterate over all images values`() {
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

    @JsName("CanConvenientlyGetAllValuesOfHrefAttributes")
    @Test
    fun `can conveniently get all values of href attributes`() {
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

    @JsName("CanConvenientlyGetAllValuesOfSrcAttributes")
    @Test
    fun `can conveniently get all values of src attributes`() {
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

    @JsName("CanConvenientlyGetAllCustomAttributes")
    @Test
    fun `can conveniently get all custom attributes`() {
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

        pictures.shouldBe(
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

        pictures.shouldBe(
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

        pictures.shouldBe(mapOf("foobar" to "http://foo.bar"))

    }

    @JsName("CanConvenientlyGetAllPicturesAsMapOfAltTextAndSrcFromDoc")
    @Test
    fun `can conveniently get all pictures as map of alt-text and src from Doc`() {
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

    @JsName("CanConvertDocElementToJsoupElement")
    @Test
    fun `can convert DocElement to jsoup element`() {
        aValidElement.element.shouldBeInstanceOf<Element>()
    }
}