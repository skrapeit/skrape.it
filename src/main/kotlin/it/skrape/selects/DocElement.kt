package it.skrape.selects

import it.skrape.SkrapeItDsl
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

    val isPresent by lazy { allElements.isNotEmpty() }

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

    val className
        get() = element.className().orEmpty()

    val cssSelector
        get() = element.cssSelector().orEmpty()

    infix fun attribute(attributeKey: String): String = element.attr(attributeKey)

    fun hasAttribute(attributeKey: String): Boolean = element.hasAttr(attributeKey)

    /**
     * Will create a CssSelector scope to calculate a css selector
     * @param cssSelector that represents an CSS-Selector that will be considered during calculation
     * @return T
     */
    operator fun <T> String.invoke(init: CssSelector.() -> T) =
            CssSelector(rawCssSelector = cssSelector).init()

    override fun toString() = element.toString()

    @Deprecated("use 'findAll(cssSelector: String) instead'")
    fun select(cssSelector: String) = this@DocElement.element.select(cssSelector).map { DocElement(it) }
}

val List<DocElement>.text
    get(): String = joinToString(separator = " ") { it.text }

val List<DocElement>.eachText
    get(): List<String> = map { it.text }

infix fun List<DocElement>.attribute(attributeKey: String): String =
        filter { it.hasAttribute(attributeKey) }.joinToString { it.attribute(attributeKey) }

infix fun List<DocElement>.eachAttribute(attributeKey: String): List<String> = map { it attribute attributeKey }

val List<DocElement>.eachHref
    get(): List<String> = this eachAttribute "href"

val List<DocElement>.eachHrefAsAbsoluteLink
    get(): List<String> = this eachAttribute "abs:href"
