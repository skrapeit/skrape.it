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
     * Get all data-attributes of the element.
     * @return Map<String, String>> of data-attributes as key value pairs
     */
    public val dataAttributes: Map<String, String> by lazy { attributes.filter { it.key.startsWith("data-") } }

    /**
     * Gets the literal value of this element's "class" attribute, which may include multiple class names, space separated.
     * (E.g. on <div class="header gray"> returns, "header gray")
     * @return String of the literal class attribute, or empty string if no class attribute set.
     */
    public val className: String by lazy { attribute("class").trim() }

    /**
     * Get all of the element's class names. E.g. on element <div class="header gray">,
     * returns a set of two elements "header", "gray".
     * @return Set<String> distinct classnames, empty if no class attribute
     */
    public val classNames: Set<String> by lazy { className.split(" ").filter { it.isNotBlank() }.toSet() }

    /**
     * Case insensitive check if this element has a class.
     * @return Boolean
     */
    public fun hasClass(className: String): Boolean =
        classNames.map { it.toLowerCase() }.contains(className.toLowerCase())

    /**
     * Get this element's parent and ancestors, up to the document root.
     * @return List<DocElement> of parents, closest first.
     */
    public val parents: List<DocElement> by lazy { element.parents().map { DocElement(it) } }

    /**
     * Get this element's parent and ancestors, up to the document root as lambda.
     * @return T
     */
    public fun <T> parents(init: List<DocElement>.() -> T): T = parents.init()

    /**
     * Get this element's parent element.
     * TODO: what happens if no parent exists
     * @return DocElement
     */
    public val parent: DocElement by lazy { parents.first() }

    /**
     * Get this element's parent element as lambda.
     * @return T
     */
    public fun <T> parent(init: DocElement.() -> T): T = parent.init()

    /**
     * Get all elements that are siblings of this element.
     * @return List<DocElement> of all siblings.
     */
    public val siblings: List<DocElement> by lazy { element.siblingElements().map { DocElement(it) } }

    /**
     * Get all elements that are siblings of this element as lambda.
     * @return T
     */
    public fun <T> siblings(init: List<DocElement>.() -> T): T = siblings.init()

    /**
     * Check if the element is present thereby it will return true if the given node can be found otherwise false.
     * @return Boolean
     */
    public val isPresent: Boolean by lazy { allElements.isNotEmpty() }

    /**
     * Check if the element is NOT present thereby it will return true if the given node can not be found otherwise false.
     * @return Boolean
     */
    public val isNotPresent: Boolean by lazy { !isPresent }

    /**
     * Get a CSS selector that will uniquely select this element.
     * If the element has an ID, returns #id; otherwise returns the parent (if any) CSS selector, followed by '>',
     * followed by a unique selector for the element (tag.class.class:nth-child(n)).
     * @return String representing the CSS Path that can be used to retrieve the element in a selector.
     */
    override val toCssSelector: String
        get() = element.cssSelector().orEmpty()
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

public val List<DocElement>.eachAttribute: Map<String, String>
    get() = map { it.attributes }.flatMap { it.toList() }.toMap()

public val List<DocElement>.eachDataAttribute: Map<String, String>
    get() = map { it.dataAttributes }.flatMap { it.toList() }.toMap()

public infix fun List<DocElement>.attribute(attributeKey: String): String =
    filter { it.hasAttribute(attributeKey) }
        .joinToString { it.attribute(attributeKey) }

public infix fun List<DocElement>.eachAttribute(attributeKey: String): List<String> =
    map { it attribute attributeKey }
        .filter { it.isNotEmpty() }

public val List<DocElement>.eachClassName: List<String>
    get(): List<String> = flatMap { it.classNames }.distinct()

public val List<DocElement>.eachHref: List<String>
    get(): List<String> = eachAttribute("href")

public val List<DocElement>.eachSrc: List<String>
    get(): List<String> = eachAttribute("src")

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
    eachLink.forEach { init(it.key, it.value) }
}

public fun <T> List<DocElement>.forEachImage(init: (altText: String, url: String) -> T) {
    eachImage.forEach { init(it.key, it.value) }
}
