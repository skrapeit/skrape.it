package it.skrape.selects

import it.skrape.SkrapeItElementPicker
import it.skrape.SkrapeItValuePicker
import it.skrape.exceptions.ElementNotFoundException
import org.jsoup.select.Elements

@Suppress("TooManyFunctions")
class DocElements(private val elements: Elements) : Scrapable, ArrayList<DocElement>() {

    @SkrapeItValuePicker
    override val text: String = elements.text().orEmpty()

    @SkrapeItValuePicker
    override val html = elements.html().orEmpty()

    @SkrapeItValuePicker
    override val outerHtml: String = elements.outerHtml().orEmpty()

    @SkrapeItElementPicker
    override fun <T> findAll(cssSelector: String, init: DocElements.() -> T): T = findAll(cssSelector).init()

    override fun findAll(cssSelector: String): DocElements = DocElements(elements.select(cssSelector))

    override fun findFirst(cssSelector: String): DocElement = findAll(cssSelector).findFirst { this }

    @SkrapeItElementPicker
    override fun <T> findFirst(cssSelector: String, init: DocElement.() -> T): T = findFirst(cssSelector).init()

    @SkrapeItElementPicker
    fun <T> findFirst(init: DocElement.() -> T): T =
            DocElement(elements.first() ?: throw ElementNotFoundException("")).init()

    @SkrapeItElementPicker
    fun <T> findByIndex(index: Int, init: DocElement.() -> T): T = DocElement(elements.elementAt(index)).init()

    @SkrapeItElementPicker
    fun <T> findSecond(init: DocElement.() -> T): T = DocElement(elements.elementAt(index = 1)).init()

    @SkrapeItElementPicker
    fun <T> findThird(init: DocElement.() -> T): T = DocElement(elements.elementAt(index = 2)).init()

    @SkrapeItElementPicker
    fun <T> findSecondLast(init: DocElement.() -> T): T = DocElement(elements.elementAt(size - 2)).init()

    @SkrapeItElementPicker
    fun <T> findLast(init: DocElement.() -> T): T = DocElement(elements.last()).init()

    override val size = elements.size

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
    /**
     * Get the attribute value for each of the matched isPresent. If an findFirst does not have this attribute, no value is
     * included in the result set for that findFirst.
     * @param attributeKey the attribute name to return values for. You can add the {@code abs:} prefix to the key to
     * get absolute URLs from relative URLs, e.g.: {@code doc.isPresent("a").eachAttr("abs:href")} .
     * @return a list of each findFirst's attribute value for the attribute
     */
    fun eachAttribute(attributeKey: String): List<String> = elements.eachAttr(attributeKey).orEmpty()

    val eachHref: List<String> = eachAttribute("href")

    val eachHrefAsAbsoluteLink: List<String> = eachAttribute("abs:href")

    override fun <T> selection(cssSelector: String, init: CssSelector.() -> T) =
            CssSelector(rawCssSelector = cssSelector).init()

    override operator fun String.invoke(init: CssSelector.() -> Unit) =
            this@DocElements.selection(this, init)

    fun isPresent() = elements.size > 0

}
