package it.skrape.selects

import it.skrape.SkrapeItDsl
import it.skrape.core.htmlDocument
import org.jsoup.nodes.Element

@Suppress("TooManyFunctions")
@SkrapeItDsl
class DocElement(override val element: Element): DomTreeElement() {
    /**
     * Get the name of the tag for this element. E.g. {@code div}.
     *
     * @return String of the tag's name
     */
    val tagName by lazy { element.tagName().orEmpty() }

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
     * Find all elements under this element (including self, and children of children).
     * @return List<DocElement>
     */
    override val allElements by lazy { element.allElements.map { DocElement(it) } }

    /**
     * Get all of the element's attributes.
     * @return Map<String, String>> of attribute key value pairs
     */
    val attributes: Map<String, String> by lazy { element.attributes().map { it.key to it.value }.toMap() }

    /**
     * Get all attribute keys of the element.
     * @return List<String>
     */
    val attributeKeys by lazy { attributes.map { it.key } }

    /**
     * Get all attribute values of the element.
     * @return List<String>
     */
    val attributeValues by lazy { attributes.map { it.value } }

    /**
     * Get the element's attribute value of a given attribute key.
     * @return String of attribute value or empty if non existing.
     */
    infix fun attribute(attributeKey: String): String = attributes[attributeKey].orEmpty()

    fun hasAttribute(attributeKey: String): Boolean = attribute(attributeKey).isNotBlank()

    /**
     * Check if the element is present thereby it will return true if the given node can be found otherwise false.
     * @return Boolean
     */
    val isPresent by lazy { allElements.isNotEmpty() }

    val className by lazy { element.className().orEmpty() }

    val cssSelector by lazy { element.cssSelector().orEmpty() }

    /**
     * Will pick all occurrences of elements that are matching the CSS-Selector and return it as List<DocElement>.
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
     * @param cssSelector that represents an CSS-Selector
     * @return List<DocElement>
     */
    override infix fun findAll(cssSelector: String): List<DocElement> =
            element.allElements.select(cssSelector).map { DocElement(it) }

    /**
     * Will pick the first occurrence of an element that
     * is matching the CSS-Selector from a parsed document.
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
     * @param cssSelector that represents an CSS-Selector
     * @return DocElement
     */
    override infix fun findFirst(cssSelector: String): DocElement =
            findAll(cssSelector)[0]

    override fun toRawCssSelector() = "$cssSelector $this"

    val toDoc: Doc
        get() = htmlDocument(html)

    @Deprecated("use 'findAll(cssSelector: String) instead'")
    fun select(cssSelector: String) = element.select(cssSelector).map { DocElement(it) }
}

val List<DocElement>.text
    get(): String = joinToString(separator = " ") { it.text }

val List<DocElement>.html
    get(): String = joinToString(separator = "\n") { it.outerHtml }

val List<DocElement>.eachText
    get(): List<String> = map { it.text }

infix fun List<DocElement>.attribute(attributeKey: String): String =
        filter { it.hasAttribute(attributeKey) }.joinToString { it.attribute(attributeKey) }

infix fun List<DocElement>.eachAttribute(attributeKey: String): List<String> = map { it attribute attributeKey }

val List<DocElement>.eachHref
    get(): List<String> = this eachAttribute "href"

fun <T> List<DocElement>.forEachLink(init: (text: String, url: String) -> T) {
    filter { it.hasAttribute("href") }
            .forEach { init(it.text, it.attribute("href")) }
}

val List<DocElement>.eachHrefAsAbsoluteLink
    get(): List<String> = this eachAttribute "abs:href"

// fun <T> List<DocElement>.findFirst(init: DocElement.() -> T): T = this[0].init()
