package it.skrape.selects

import it.skrape.SkrapeItDsl
import it.skrape.exceptions.ElementNotFoundException
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

@Suppress("TooManyFunctions")
@SkrapeItDsl
class Doc(val document: Document) {

    /**
     * Gets the combined text of this element and all its children. Whitespace is normalized and trimmed.
     * For example, given HTML {@code <p>Hello  <b>there</b> now! </p>} returns {@code "Hello there now!"}
     *
     * @return unencoded, normalized text, or empty string if none.
     * @see wholeText if you don't want the text to be normalized.
     * @see #ownText()
     * @see #textNodes()
     */
    val text = document.text().orEmpty()

    /**
     * Get the (unencoded) text of all children of this element, including any newlines and spaces present in the
     * original.
     *
     * @return unencoded, un-normalized text
     * @see text
     */
    val wholeText = document.wholeText().orEmpty()

    /**
     * Retrieves the element's inner HTML. E.g. on a {@code <div>} with one empty {@code <p>}, would return
     * {@code <p></p>}. (Whereas {@link outerHtml} would return {@code <div><p></p></div>}.)
     * @return String of HTML.
     * @see outerHtml
     */
    val html: String = document.html().orEmpty()

    /**
     * Get the outer HTML of this node. For example, on a {@code p} element, may return {@code <p>Para</p>}.
     * @return outer HTML
     * @see html
     * @see text
     */
    val outerHtml: String = document.outerHtml().orEmpty()

    infix fun findAll(cssSelector: String) = document.select(cssSelector)
            .map { DocElement(it) }
            .takeIf { it.isNotEmpty() } ?: throw ElementNotFoundException(cssSelector)

    fun <T> findAll(cssSelector: String, init: List<DocElement>.() -> T) = findAll(cssSelector).init()

    infix fun findFirst(cssSelector: String) =
            findFirstOrNull(cssSelector) ?: throw ElementNotFoundException(cssSelector)

    fun <T> findFirst(cssSelector: String, init: DocElement.() -> T) = findFirst(cssSelector).init()

    fun findFirstOrNull(cssSelector: String): DocElement? {
        val element: Element? = document.selectFirst(cssSelector)
        return element?.let { DocElement(it) }
    }

    val titleText = document.title().orEmpty()

    override fun toString() = document.toString()

    operator fun String.invoke(init: CssSelector.() -> Unit) =
            this@Doc.selection(this, init)

    fun <T> selection(cssSelector: String, init: CssSelector.() -> T) =
            CssSelector(rawCssSelector = cssSelector, doc = this).init()

}
