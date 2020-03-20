package it.skrape.selects

import it.skrape.SkrapeItDsl
import it.skrape.exceptions.ElementNotFoundException
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

@Suppress("TooManyFunctions")
@SkrapeItDsl
class Doc(val document: Document, var relaxed: Boolean = false) {

    /**
     * Gets the combined text of this element and all its children. Whitespace is normalized and trimmed.
     * For example, given HTML {@code <p>Hello  <b>there</b> now! </p>} returns {@code "Hello there now!"}
     *
     * @return unencoded, normalized text, or empty string if none.
     * @see wholeText if you don't want the text to be normalized.
     * @see #ownText()
     * @see #textNodes()
     */
    val text by lazy { document.text().orEmpty() }

    /**
     * Get the (unencoded) text of all children of this element, including any newlines and spaces present in the
     * original.
     *
     * @return unencoded, un-normalized text
     * @see text
     */
    val wholeText by lazy { document.wholeText().orEmpty() }

    /**
     * Retrieves the element's inner HTML. E.g. on a {@code <div>} with one empty {@code <p>}, would return
     * {@code <p></p>}. (Whereas {@link outerHtml} would return {@code <div><p></p></div>}.)
     * @return String of HTML.
     * @see outerHtml
     */
    val html: String by lazy { document.html().orEmpty() }

    /**
     * Get the outer HTML of this node. For example, on a {@code p} element, may return {@code <p>Para</p>}.
     * @return outer HTML
     * @see html
     * @see text
     */
    val outerHtml: String by lazy { document.outerHtml().orEmpty() }

    infix fun findAll(cssSelector: String): List<DocElement> {
        val selected = document.select(cssSelector)
                .map { DocElement(it) }
                .takeIf { it.isNotEmpty() }
        return if (relaxed) selected.orEmpty() else selected ?: throw ElementNotFoundException(cssSelector)
    }

    fun <T> findAll(cssSelector: String, init: List<DocElement>.() -> T) = findAll(cssSelector).init()

    infix fun findFirst(cssSelector: String) = findAll(cssSelector).firstOrNull() ?: DocElement(Element(cssSelector))

    fun <T> findFirst(cssSelector: String, init: DocElement.() -> T) = findFirst(cssSelector).init()

    val titleText = document.title().orEmpty()

    override fun toString() = document.toString()

    operator fun <T> String.invoke(init: CssSelector.() -> T) =
            this@Doc.selection(this, init)

    fun <T> selection(cssSelector: String, init: CssSelector.() -> T) =
            CssSelector(rawCssSelector = cssSelector, doc = this).init()

}
