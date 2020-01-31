package it.skrape.selects

import it.skrape.SkrapeItDsl
import org.jsoup.nodes.Element

@Suppress("TooManyFunctions")
@SkrapeItDsl
class DocElement(private val element: Element) {

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
    val text= element.text().orEmpty()

    /**
     * Retrieves the element's inner HTML. E.g. on a {@code <div>} with one empty {@code <p>}, would return
     * {@code <p></p>}. (Whereas {@link #outerHtml()} would return {@code <div><p></p></div>}.)
     *
     * @return String of HTML.
     * @see outerHtml
     * @see text
     */
    val html= element.html().orEmpty()

    /**
     * Get the outer HTML of this node. For example, on a {@code p} element, may return {@code <p>Para</p>}.
     * @return outer HTML
     * @see html
     * @see text
     */
    val outerHtml= element.outerHtml().orEmpty()

    /**
     * Will pick all occurrences of elements that are matching the CSS-Selector
     * and return it as Elements which is basically a List<Element>
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

    fun attribute(attributeKey: String): String = element.attr(attributeKey)

    fun hasAttribute(attributeKey: String): Boolean = element.hasAttr(attributeKey)

    /**
     * Will create a CssSelector scope to calculate a css selector
     * @param cssSelector that represents an CSS-Selector that will be considered during calculation
     * @return T
     */
    fun <T> selection(cssSelector: String, init: CssSelector.() -> T) =
            CssSelector(rawCssSelector = cssSelector).init()

    /**
     * Will convert an invoked String to a CssSelector scope.
     */
    operator fun String.invoke(init: CssSelector.() -> Unit) =
            this@DocElement.selection(this, init)

    override fun toString() = element.toString()

    val isPresent= element.allElements.size > 0

    fun select(cssSelector: String) = element.select(cssSelector).map { DocElement(it) }
}

fun List<DocElement>.attribute(attributeKey: String): String =
        filter { it.hasAttribute(attributeKey) }.joinToString { it.attribute(attributeKey) }

fun List<DocElement>.text(): String = joinToString(separator = " ") { it.text }

fun List<DocElement>.eachText(): List<String> = map { it.text }

fun List<DocElement>.eachAttribute(attributeKey: String): List<String> = map { it.attribute(attributeKey) }

fun List<DocElement>.eachHref(): List<String> = eachAttribute("href")

fun List<DocElement>.eachHrefAsAbsoluteLink(): List<String> = eachAttribute("abs:href")


