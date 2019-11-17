package it.skrape.core

import it.skrape.exceptions.ElementNotFoundException
import org.jsoup.select.Elements

@Suppress("TooManyFunctions")
class DocElements(private val elements: Elements) : ArrayList<DocElement>() {

    override val size = elements.size

    /**
     * Get the combined text of all the matched elements.
     * <p>
     * Note that it is possible to get repeats if the matched elements contain both parent elements and their own
     * children, as the Element.text() method returns the combined text of a parent and all its children.
     * @return string of all text: unescaped and no HTML.
     * @see DocElement#text()
     */
    fun text(): String = elements.text().orEmpty()

    fun attr(attributeKey: String) = attribute(attributeKey)

    /**
     * Get the combined inner HTML of all matched elements.
     * @return string of all element's inner HTML.
     * @see #text()
     * @see #outerHtml()
     */
    fun html() = elements.html().orEmpty()

    /**
    Get an attribute value from the first matched element that has the attribute.
    @param attributeKey The attribute key.
    @return The attribute value from the first matched element that has the attribute.
    If no elements were matched (isEmpty() == true),
    or if the no elements have the attribute, returns empty string.
    @see #hasAttr(String)
     */
    fun attribute(attributeKey: String): String = elements.attr(attributeKey)

    /**
    Checks if any of the matched elements have this attribute defined.
    @param attributeKey attribute key
    @return true if any of the elements have the attribute; false if none do.
     */
    fun hasAttribute(attributeKey: String): Boolean = elements.hasAttr(attributeKey)

    /**
     * Get the attribute value for each of the matched elements. If an element does not have this attribute, no value is
     * included in the result set for that element.
     * @param attributeKey the attribute name to return values for. You can add the {@code abs:} prefix to the key to
     * get absolute URLs from relative URLs, e.g.: {@code doc.select("a").eachAttr("abs:href")} .
     * @return a list of each element's attribute value for the attribute
     */
    fun eachAttribute(attributeKey: String): List<String> = elements.eachAttr(attributeKey).orEmpty()

    val eachHref: List<String> = eachAttribute("href")
    val eachHrefAsAbsoluteLink: List<String> = eachAttribute("abs:href")

    fun findAll() = DocElements(elements)
    fun findAll(cssSelector: String) = DocElements(elements.select(cssSelector))

    fun <T> findByIndex(index: Int, init: DocElement.() -> T) = DocElement(elements.elementAt(index)).init()

    fun <T> findFirst(init: DocElement.() -> T) =
            DocElement(elements.first() ?: throw ElementNotFoundException("")).init()

    fun <T> findSecond(init: DocElement.() -> T) = DocElement(elements.elementAt(index = 1)).init()
    fun <T> findThird(init: DocElement.() -> T) = DocElement(elements.elementAt(index = 2)).init()
    fun <T> findSecondLast(init: DocElement.() -> T) = DocElement(elements.elementAt(elements.size - 2)).init()
    fun <T> findLast(init: DocElement.() -> T) = DocElement(elements.last()).init()

    fun <T> selection(selector: String, init: CssSelector.() -> T) =
            CssSelector(rawCssSelector = selector).init()

}
