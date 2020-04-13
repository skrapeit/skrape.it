package it.skrape.selects

import it.skrape.SkrapeItDsl
import it.skrape.core.htmlDocument
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

@Suppress("TooManyFunctions")
@SkrapeItDsl
class DocElement(private val element: Element) {

    /**
     * Get the name of the tag for this element. E.g. {@code div}.
     *
     * @return String of the tag's name
     */
    val tagName by lazy { element.tagName().orEmpty() }

    /**
     * Gets the combined text of this element and all its children. Whitespace is normalized and trimmed.
     * <p>
     * For example, given HTML {@code <p>Hello <b>there</b> now! </p>}, {@code p.text()} returns {@code "Hello there now!"}
     *
     * @return unencoded, normalized text, or empty string if none.
     * @see #wholeText() if you don't want the text to be normalized.
     * @see #ownText()
     * @see #textNodes()
     */
    val text by lazy { element.text().orEmpty() }

    /**
     * Gets the text owned by this element only; does not get the combined text of all children.
     * For example, given HTML {@code <p>Hello <b>there</b> now!</p>}, {@code p.ownText()} returns {@code "Hello now!"},
     * whereas {@code text} returns {@code "Hello there now!"}.
     * Note that the text within the {@code b} element is not returned, as it is not a direct child of the {@code p} element.
     *
     * @return unencoded text, or empty string if none.
     * @see text
     */
    val ownText by lazy { element.ownText().orEmpty() }

    /**
     * Retrieves the element's inner HTML. E.g. on a {@code <div>} with one empty {@code <p>}, would return
     * {@code <p></p>}. (Whereas {@link #outerHtml()} would return {@code <div><p></p></div>}.)
     *
     * @return String of HTML.
     * @see outerHtml
     * @see text
     */
    val html by lazy { element.html().orEmpty() }

    /**
     * Get the outer HTML of this node. For example, on a {@code p} element, may return {@code <p>Para</p>}.
     * @return outer HTML
     * @see html
     * @see text
     */
    val outerHtml by lazy { element.outerHtml().orEmpty() }

    /**
     * Find all elements under this element (including self, and children of children).
     * @return List<DocElement>
     */
    val allElements by lazy { element.allElements.map { DocElement(it) } }

    /**
     * Get all of the element's attributes.
     * @return Map<String, String>> of attribute key value pairs
     */
    val attributes: Map<String, String> by lazy { element.attributes().map { it.key to it.value }.toMap() }

    /**
     * Get all attribute keys of the element.
     * @return List<String>
     */
    val attributeKeys by lazy { attributes.map { it.key } }

    /**
     * Get all attribute values of the element.
     * @return List<String>
     */
    val attributeValues by lazy { attributes.map { it.value } }

    /**
     * Get the element's attribute value of a given attribute key.
     * @return String of attribute value or empty if non existing.
     */
    infix fun attribute(attributeKey: String): String = attributes[attributeKey].orEmpty()

    fun hasAttribute(attributeKey: String): Boolean = attribute(attributeKey).isNotBlank()

    /**
     * Check if the element is present thereby it will return true if the given node can be found otherwise false.
     * @return Boolean
     */
    val isPresent by lazy { allElements.isNotEmpty() }

    val className by lazy { element.className().orEmpty() }

    val cssSelector by lazy { element.cssSelector().orEmpty() }

    /**
     * Find all elements under this element (including self, and children of children).
     * @return T
     */
    fun <T> findAll(init: List<DocElement>.() -> T): T = allElements.init()

    /**
     * Will pick all occurrences of elements that are matching the CSS-Selector
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
     * @param cssSelector that represents an CSS-Selector
     * @return T
     */
    fun <T> findAll(cssSelector: String, init: List<DocElement>.() -> T): T =
            findAll(cssSelector).init()

    /**
     * Will pick all occurrences of elements that are matching the CSS-Selector and return it as List<DocElement>.
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
     * @param cssSelector that represents an CSS-Selector
     * @return List<DocElement>
     */
    infix fun findAll(cssSelector: String): List<DocElement> =
            element.allElements.select(cssSelector).map { DocElement(it) }

    /**
     * Will pick the first occurrence of an element that
     * is matching the CSS-Selector from a parsed document and invoke it to a lambda function.
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
     * @param cssSelector that represents an CSS-Selector
     * @return T
     */
    fun <T> findFirst(cssSelector: String, init: DocElement.() -> T): T = findFirst(cssSelector).init()

    /**
     * Will pick the first occurrence of an element that
     * is matching the CSS-Selector from a parsed document.
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
     * @param cssSelector that represents an CSS-Selector
     * @return DocElement
     */
    infix fun findFirst(cssSelector: String): DocElement = findAll(cssSelector)[0]

    /**
     * Will create a CssSelector scope to calculate a css selector
     * @param cssSelector that represents an CSS-Selector that will be considered during calculation
     * @return T
     */
    operator fun <T> String.invoke(init: CssSelector.() -> T) =
            CssSelector(rawCssSelector = "$cssSelector $this").init()

    override fun toString() = element.toString()

    val toDoc: Doc
        get() = htmlDocument(html)

    @Deprecated("use 'findAll(cssSelector: String) instead'")
    fun select(cssSelector: String) = element.select(cssSelector).map { DocElement(it) }
}

val List<DocElement>.text
    get(): String = joinToString(separator = " ") { it.text }

val List<DocElement>.html
    get(): String = joinToString(separator = "\n") { it.outerHtml }

val List<DocElement>.eachText
    get(): List<String> = map { it.text }

infix fun List<DocElement>.attribute(attributeKey: String): String =
        filter { it.hasAttribute(attributeKey) }.joinToString { it.attribute(attributeKey) }

infix fun List<DocElement>.eachAttribute(attributeKey: String): List<String> = map { it attribute attributeKey }

val List<DocElement>.eachHref
    get(): List<String> = this eachAttribute "href"

fun <T> List<DocElement>.forEachLink(init: (text: String, url: String) -> T) {
    filter { it.hasAttribute("href") }
            .forEach { init(it.text, it.attribute("href")) }
}

val List<DocElement>.eachHrefAsAbsoluteLink
    get(): List<String> = this eachAttribute "abs:href"

// fun <T> List<DocElement>.findFirst(init: DocElement.() -> T): T = this[0].init()
