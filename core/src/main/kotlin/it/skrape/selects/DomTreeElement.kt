package it.skrape.selects

import it.skrape.exceptions.ElementNotFoundException
import org.jsoup.nodes.Element

public abstract class DomTreeElement : CssSelectable() {
    public abstract val element: Element

    public abstract val relaxed: Boolean

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
    public val text: String by lazy { element.text().orEmpty() }

    /**
     * Retrieves the element's inner HTML. E.g. on a {@code <div>} with one empty {@code <p>}, would return
     * {@code <p></p>}. (Whereas {@link outerHtml} would return {@code <div><p></p></div>}.)
     * @return String of HTML.
     * @see outerHtml
     */
    public val html: String by lazy { element.html().orEmpty() }

    /**
     * Get the outer HTML of this node. For example, on a {@code p} element, may return {@code <p>Para</p>}.
     * @return outer HTML
     * @see html
     * @see text
     */
    public val outerHtml: String by lazy { element.outerHtml().orEmpty() }

    /**
     * Find all elements in the document.
     * @return List<DocElement>
     */
    public val allElements: List<DocElement> by lazy { element.allElements.map { DocElement(it) } }

    public fun eachAttribute(attributeKey: String): List<String> =
            allElements.map { it attribute attributeKey }
                    .filter { it.isNotEmpty() }

    public val eachHref: List<String> by lazy { eachAttribute("href").filter { it.isNotEmpty() } }

    public val eachSrc: List<String> by lazy { eachAttribute("src").filter { it.isNotEmpty() } }

    public val eachLink: Map<String, String>
        get(): Map<String, String> =
            allElements.filter { it.hasAttribute("href") }
                    .associate { it.text to it.attribute("href") }

    public val eachImage: Map<String, String>
        get(): Map<String, String> =
            allElements.filter { it.tagName == "img" }
                    .filter { it.hasAttribute("src") }
                    .associate { it.attribute("alt") to it.attribute("src") }

    public open fun makeDefaultElement(cssSelector: String): DocElement {
        return super.makeDefault(cssSelector)
    }

    override fun makeDefault(cssSelector: String): DocElement {
        return if (relaxed) makeDefaultElement(cssSelector) else throw ElementNotFoundException(cssSelector)
    }

    override fun applySelector(rawCssSelector: String): List<DocElement> {
        if (rawCssSelector.isEmpty()) {
            return allElements
        }

        val queried = element.children().select(rawCssSelector).map { DocElement(it, relaxed) }
        val selected = queried.takeIf { it.isNotEmpty() }

        return if (relaxed) selected.orEmpty() else selected ?: throw ElementNotFoundException(rawCssSelector)
    }

    override fun toString(): String = element.toString()
}
