package it.skrape.selects

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.exceptions.*
import org.jsoup.nodes.Element
import org.jsoup.select.Elements


/**
 * Will pick the first occurrence of an Element that
 * is matching the CSS-Selector from a parsed document.
 * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
 * @param selector that represents an CSS-Selector
 * @return Element
 */
@Suppress("MaxLineLength")
fun Doc.element(selector: String, init: Element.() -> Unit): Element {
    val element = selectFirst(selector) ?: throw ElementNotFoundException(selector)
    element.apply(init)
    return element
}

fun Doc.element(selector: String): Element = selectFirst(selector)
        ?: throw ElementNotFoundException(selector)

/**
 * Will pick all occurrences of an Elements that are matching the CSS-Selector
 * and return it as Elements which is basically a List<Element>
 * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
 * @param selector that represents an CSS-Selector
 * @return Elements
 */
fun Doc.elements(selector: String, init: Elements.() -> Unit): Elements {
    val elements = select(selector)
    if (elements.isEmpty()) throw ElementNotFoundException(selector)
    elements.apply(init)
    return elements
}

fun Doc.elements(selector: String): Elements = select(selector)
