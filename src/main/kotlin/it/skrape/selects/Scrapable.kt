package it.skrape.selects

import it.skrape.SkrapeItElementPicker

interface Scrapable {

    /**
     * Gets the combined text of this element and all its children. Whitespace is normalized and trimmed.
     * <p>
     * For example, given HTML {@code <p>Hello  <b>there</b> now! </p>}, {@code p.text()} returns {@code "Hello there now!"}
     *
     * @return unencoded, normalized text, or empty string if none.
     * @see #wholeText() if you don't want the text to be normalized.
     * @see #ownText()
     * @see #textNodes()
     */
    val text: String

    /**
     * Retrieves the element's inner HTML. E.g. on a {@code <div>} with one empty {@code <p>}, would return
     * {@code <p></p>}. (Whereas {@link #outerHtml()} would return {@code <div><p></p></div>}.)
     *
     * @return String of HTML.
     * @see outerHtml
     * @see text
     */
    val html: String

    /**
    * Get the outer HTML of this node. For example, on a {@code p} element, may return {@code <p>Para</p>}.
    * @return outer HTML
    * @see html
    * @see text
     */
    val outerHtml: String

    /**
     * Will pick all occurrences of elements that are matching the CSS-Selector
     * and return it as DocElements which is basically a List<DocElement>
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
     * @param cssSelector that represents an CSS-Selector
     * @return DocElements
     */
    infix fun findAll(cssSelector: String): DocElements

    /**
     * Will pick all occurrences of elements that are matching the CSS-Selector
     * and return it as Elements which is basically a List<Element>
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
     * @param cssSelector that represents an CSS-Selector
     * @return T
     */
    @SkrapeItElementPicker
    fun <T> findAll(cssSelector: String, init: DocElements.() -> T): T

    /**
     * Will pick the first occurrence of an element that
     * is matching the CSS-Selector from a parsed document.
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
     * @param cssSelector that represents an CSS-Selector
     * @return DocElement
     */
    infix fun findFirst(cssSelector: String): DocElement

    /**
     * Will pick the first occurrence of an element that
     * is matching the CSS-Selector from a parsed document and invoke it to a lambda function.
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
     * @param cssSelector that represents an CSS-Selector
     * @return T
     */
    @SkrapeItElementPicker
    fun <T> findFirst(cssSelector: String, init: DocElement.() -> T): T

    /**
     * Will convert an invoked String to a CssSelector scope.
     */
    @SkrapeItElementPicker
    operator fun String.invoke(init: CssSelector.() -> Unit)

    /**
     * Will create a CssSelector scope to calculate a css selector
     * @param cssSelector that represents an CSS-Selector that will be considered during calculation
     * @return T
     */
    fun <T> selection(cssSelector: String, init: CssSelector.() -> T): T
}
