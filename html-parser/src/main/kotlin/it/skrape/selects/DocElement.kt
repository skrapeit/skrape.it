package it.skrape.selects

import it.skrape.SkrapeItDsl
import org.jsoup.nodes.Element

@Suppress("TooManyFunctions")
@SkrapeItDsl
public class DocElement internal constructor(
    override val element: Element,
    override val relaxed: Boolean
) : DomTreeElement() {
    public constructor(element: Element) : this(element, false)

    /**
     * Get the name of the tag for this element. E.g. {@code div}.
     *
     * @return String of the tag's name
     */
    public val tagName: String by lazy { element.tagName().orEmpty() }

    /**
     * Gets the text owned by this element only; does not get the combined text of all children.
     * For example, given HTML {@code <p>Hello <b>there</b> now!</p>}, {@code p.ownText()} returns {@code "Hello now!"},
     * whereas {@code text} returns {@code "Hello there now!"}.
     * Note that the text within the {@code b} element is not returned, as it is not a direct child of the {@code p} element.
     *
     * @return unencoded text, or empty string if none.
     * @see text
     */
    public val ownText: String by lazy { element.ownText().orEmpty() }

    /**
     * Get all of the element's attributes.
     * @return Map<String, String>> of attribute key value pairs
     */
    public val attributes: Map<String, String> by lazy { element.attributes().map { it.key to it.value }.toMap() }

    /**
     * Get all attribute keys of the element.
     * @return List<String>
     */
    public val attributeKeys: List<String> by lazy { attributes.map { it.key } }

    /**
     * Get all attribute values of the element.
     * @return List<String>
     */
    public val attributeValues: List<String> by lazy { attributes.map { it.value } }

    /**
     * Get the element's attribute value of a given attribute key.
     * @return String of attribute value or empty if non existing.
     */
    public infix fun attribute(attributeKey: String): String = attributes[attributeKey].orEmpty()

    public fun hasAttribute(attributeKey: String): Boolean = attribute(attributeKey).isNotBlank()

    /**
     * Check if the element is present thereby it will return true if the given node can be found otherwise false.
     * @return Boolean
     */
    public val isPresent: Boolean by lazy { allElements.isNotEmpty() }

    public val isNotPresent: Boolean by lazy { !isPresent }

    public val className: String by lazy { element.className().orEmpty() }

    public val cssSelector: String by lazy { element.cssSelector().orEmpty() }

    override val toCssSelector: String
        get() = cssSelector

    @Deprecated(
        "use 'findAll(cssSelector: String) instead'",
        ReplaceWith("findAll(cssSelector)")
    )
    public fun select(cssSelector: String): List<DocElement> =
        element.select(cssSelector).map { DocElement(it, relaxed) }
}

public val List<DocElement>.text: String
    get(): String = joinToString(separator = " ") { it.text }

public val List<DocElement>.html: String
    get(): String = joinToString(separator = "\n") { it.outerHtml }

public val List<DocElement>.isPresent: Boolean
    get(): Boolean = size > 0

public val List<DocElement>.isNotPresent: Boolean
    get(): Boolean = !isPresent

public val List<DocElement>.eachText: List<String>
    get(): List<String> = map { it.text }

public infix fun List<DocElement>.attribute(attributeKey: String): String =
        filter { it.hasAttribute(attributeKey) }
                .joinToString { it.attribute(attributeKey) }

public infix fun List<DocElement>.eachAttribute(attributeKey: String): List<String> =
        map { it attribute attributeKey }
                .filter { it.isNotEmpty() }

public val List<DocElement>.eachHref: List<String>
    get(): List<String> = eachAttribute("href")
            .filter { it.isNotEmpty() }

public val List<DocElement>.eachSrc: List<String>
    get(): List<String> = eachAttribute("src")
            .filter { it.isNotEmpty() }

public val List<DocElement>.eachLink: Map<String, String>
    get(): Map<String, String> =
        filter { it.hasAttribute("href") }
                .associate { it.text to it.attribute("href") }

public val List<DocElement>.eachImage: Map<String, String>
    get(): Map<String, String> =
        filter { it.tagName == "img" }
                .filter { it.hasAttribute("src") }
                .associate { it.attribute("alt") to it.attribute("src") }


public fun <T> List<DocElement>.forEachLink(init: (text: String, url: String) -> T) {
    filter { it.hasAttribute("href") }
            .forEach { init(it.text, it.attribute("href")) }
}

public fun <T> List<DocElement>.forEachImage(init: (altText: String, url: String) -> T) {
    filter { it.tagName == "img" }
            .filter { it.hasAttribute("src") }
            .forEach { init(it.attribute("alt"), it.attribute("src")) }
}

