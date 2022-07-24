package it.skrape.selects

public expect abstract class DomTreeElement : CssSelectable {

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
    public val text: String

    /**
     * Retrieves the element's inner HTML. E.g. on a {@code <div>} with one empty {@code <p>}, would return
     * {@code <p></p>}. (Whereas {@link outerHtml} would return {@code <div><p></p></div>}.)
     * @return String of HTML.
     * @see outerHtml
     */
    public val html: String

    /**
     * Get the outer HTML of this node. For example, on a {@code p} element, may return {@code <p>Para</p>}.
     * @return outer HTML
     * @see html
     * @see text
     */
    public val outerHtml: String

    /**
     * Find all elements in the document.
     * @return List<DocElement>
     */
    public val allElements: List<DocElement>

    /**
     * Get this element's child elements.
     * @return List<DocElement> of child elements. If this element has no children, returns an empty list.
     */
    public val children: List<DocElement>

    /**
     * Get this element's child elements.
     * @return T
     */
    public fun <T> children(init: List<DocElement>.() -> T): T

    public fun eachAttribute(attributeKey: String): List<String>

    public val eachHref: List<String>

    public val eachSrc: List<String>

    public val eachLink: Map<String, String>

    public val eachImage: Map<String, String>

    public fun makeDefaultElement(cssSelector: String): DocElement
}
