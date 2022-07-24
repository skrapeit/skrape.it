package it.skrape.selects

import it.skrape.SkrapeItDsl
import org.jsoup.nodes.Element

@Suppress("TooManyFunctions")
@SkrapeItDsl
public actual class DocElement internal constructor(
    override val element: Element,
    override val relaxed: Boolean
) : DomTreeElement() {
    public constructor(element: Element) : this(element, false)

    /**
     * Get the name of the tag for this element. E.g. {@code div}.
     *
     * @return String of the tag's name
     */
    public actual val tagName: String by lazy { element.tagName().orEmpty() }

    /**
     * Gets the text owned by this element only; does not get the combined text of all children.
     * For example, given HTML {@code <p>Hello <b>there</b> now!</p>}, {@code p.ownText()} returns {@code "Hello now!"},
     * whereas {@code text} returns {@code "Hello there now!"}.
     * Note that the text within the {@code b} element is not returned, as it is not a direct child of the {@code p} element.
     *
     * @return unencoded text, or empty string if none.
     * @see text
     */
    public actual val ownText: String by lazy { element.ownText().orEmpty() }

    /**
     * Get all of the element's attributes.
     * @return Map<String, String>> of attribute key value pairs
     */
    public actual val attributes: Map<String, String> by lazy { element.attributes().map { it.key to it.value }.toMap() }

    /**
     * Get all attribute keys of the element.
     * @return List<String>
     */
    public actual val attributeKeys: List<String> by lazy { attributes.map { it.key } }

    /**
     * Get all attribute values of the element.
     * @return List<String>
     */
    public actual val attributeValues: List<String> by lazy { attributes.map { it.value } }

    /**
     * Get the element's attribute value of a given attribute key.
     * @return String of attribute value or empty if non existing.
     */
    public actual infix fun attribute(attributeKey: String): String = attributes[attributeKey].orEmpty()

    public actual fun hasAttribute(attributeKey: String): Boolean = attribute(attributeKey).isNotBlank()

    /**
     * Get all data-attributes of the element.
     * @return Map<String, String>> of data-attributes as key value pairs
     */
    public actual val dataAttributes: Map<String, String> by lazy { attributes.filter { it.key.startsWith("data-") } }

    /**
     * Gets the literal value of this element's "class" attribute, which may include multiple class names, space separated.
     * (E.g. on <div class="header gray"> returns, "header gray")
     * @return String of the literal class attribute, or empty string if no class attribute set.
     */
    public actual val className: String by lazy { attribute("class").trim() }

    /**
     * Get all of the element's class names. E.g. on element <div class="header gray">,
     * returns a set of two elements "header", "gray".
     * @return Set<String> distinct classnames, empty if no class attribute
     */
    public actual val classNames: Set<String> by lazy { className.split(" ").filter { it.isNotBlank() }.toSet() }

    /**
     * Case insensitive check if this element has a class.
     * @return Boolean
     */
    public actual fun hasClass(className: String): Boolean =
        classNames.map { it.toLowerCase() }.contains(className.toLowerCase())

    /**
     * Gets the literal value of this element's "id" attribute.
     * (E.g. on <div id="main"> returns, "main")
     * @return String of the literal id attribute value, or empty string if no id attribute set.
     */
    public actual val id: String by lazy { attribute("id").trim() }

    /**
     * Get this element's parent and ancestors, up to the document root.
     * @return List<DocElement> of parents, closest first.
     */
    public actual val parents: List<DocElement> by lazy { element.parents().map { DocElement(it) } }

    /**
     * Get this element's parent and ancestors, up to the document root as lambda.
     * @return T
     */
    public actual fun <T> parents(init: List<DocElement>.() -> T): T = parents.init()

    /**
     * Get this element's parent element.
     * @return DocElement
     */
    public actual val parent: DocElement by lazy {
        try {
            parents.first()
        } catch (e: NoSuchElementException) {
            throw ElementNotFoundException("parent")
        }
    }

    /**
     * Get this element's parent element as lambda.
     * @return T
     */
    public actual fun <T> parent(init: DocElement.() -> T): T = parent.init()

    /**
     * Get all elements that are siblings of this element.
     * @return List<DocElement> of all siblings.
     */
    public actual val siblings: List<DocElement> by lazy { element.siblingElements().map { DocElement(it) } }

    /**
     * Get all elements that are siblings of this element as lambda.
     * @return T
     */
    public actual fun <T> siblings(init: List<DocElement>.() -> T): T = siblings.init()

    /**
     * Check if the element is present thereby it will return true if the given node can be found otherwise false.
     * @return Boolean
     */
    public actual val isPresent: Boolean by lazy { allElements.isNotEmpty() }

    /**
     * Check if the element is NOT present thereby it will return true if the given node can not be found otherwise false.
     * @return Boolean
     */
    public actual val isNotPresent: Boolean by lazy { !isPresent }

    /**
     * Get a CSS selector that will uniquely select this element.
     * If the element has an ID, returns #id; otherwise returns the parent (if any) CSS selector, followed by '>',
     * followed by a unique selector for the element (tag.class.class:nth-child(n)).
     * @return String representing the CSS Path that can be used to retrieve the element in a selector.
     */
    override val toCssSelector: String
        get() = element.cssSelector()

    public actual val parentsCssSelector: String by lazy {
        parents {
            when {
                isNotEmpty() -> reversed().joinToString(separator = " > ") { it.tagName }
                else -> ""
            }
        }
    }

    public actual val ownCssSelector: String by lazy {
        fun String.orNull(): String? = if (isBlank()) null else this
        fun List<String>.orNull(): List<String>? = if (isEmpty()) null else this
        CssSelector(
            rawCssSelector = tagName,
            withClass = classNames.joinToString(separator = ".").orNull(),
            withId = id.orNull(),
            withAttributes = attributes
                .filterNot { it.key == "id" }
                .filterNot { it.key == "class" }
                .filterNot { it.value.isBlank() }
                .toList(),
            withAttributeKeys = attributes.filterValues { it.isBlank() }.map { it.key }.orNull()
        ).toString()
    }
}
