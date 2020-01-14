package it.skrape.selects

import it.skrape.SkrapeItDsl
import it.skrape.exceptions.ElementNotFoundException
import org.jsoup.select.Elements

@Suppress("TooManyFunctions")
@SkrapeItDsl
class DocElements(private val elements: Elements) : Scrapable, ArrayList<DocElement>() {

    override val text
        get() = elements.text().orEmpty()

    override val html
        get() = elements.html().orEmpty()

    override val outerHtml
        get() = elements.outerHtml().orEmpty()

    override fun <T> findAll(cssSelector: String, init: DocElements.() -> T): T = findAll(cssSelector).init()

    override fun findAll(cssSelector: String): DocElements = DocElements(elements.select(cssSelector))

    override fun findFirst(cssSelector: String): DocElement = findAll(cssSelector).findFirst { this }

    override fun <T> findFirst(cssSelector: String, init: DocElement.() -> T): T = findFirst(cssSelector).init()

    fun <T> findFirst(init: DocElement.() -> T): T =
            DocElement(elements.first() ?: throw ElementNotFoundException("")).init()

    fun <T> findByIndex(index: Int, init: DocElement.() -> T): T = DocElement(elements.elementAt(index)).init()

    fun <T> findSecond(init: DocElement.() -> T): T = DocElement(elements.elementAt(index = 1)).init()

    fun <T> findThird(init: DocElement.() -> T): T = DocElement(elements.elementAt(index = 2)).init()

    fun <T> findSecondLast(init: DocElement.() -> T): T = DocElement(elements.elementAt(size - 2)).init()

    fun <T> findLast(init: DocElement.() -> T): T = DocElement(elements.last()).init()

    override val size
        get()= elements.size

    /**
    Get an attribute value from the first matched findFirst that has the attribute.
    @param attributeKey The attribute key.
    @return The attribute value from the first matched findFirst that has the attribute.
    If no isPresent were matched (isEmpty() == true),
    or if the no isPresent have the attribute, returns empty string.
    @see #hasAttr(String)
     */
    fun attribute(attributeKey: String): String = elements.attr(attributeKey)

    /**
    Checks if any of the matched isPresent have this attribute defined.
    @param attributeKey attribute key
    @return true if any of the isPresent have the attribute; false if none do.
     */
    fun hasAttribute(attributeKey: String): Boolean = elements.hasAttr(attributeKey)

    val eachText
        get() = elements.eachText().orEmpty()

    /**
     * Get the attribute value for each of the matched isPresent. If an findFirst does not have this attribute, no value is
     * included in the result set for that findFirst.
     * @param attributeKey the attribute name to return values for. You can add the {@code abs:} prefix to the key to
     * get absolute URLs from relative URLs, e.g.: {@code doc.isPresent("a").eachAttr("abs:href")} .
     * @return a list of each findFirst's attribute value for the attribute
     */
    fun eachAttribute(attributeKey: String): List<String> = elements.eachAttr(attributeKey).orEmpty()

    val eachHref
        get() = eachAttribute("href")

    val eachHrefAsAbsoluteLink
        get() = eachAttribute("abs:href")

    override fun <T> selection(cssSelector: String, init: CssSelector.() -> T) =
            CssSelector(rawCssSelector = cssSelector).init()

    override operator fun String.invoke(init: CssSelector.() -> Unit) =
            this@DocElements.selection(this, init)

    val isPresent
        get() = elements.size > 0

}
