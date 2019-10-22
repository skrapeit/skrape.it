package it.skrape.selects

import it.skrape.core.Doc
import it.skrape.core.DomSelector
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
fun <T> Doc.element(selector: String, init: Element.() -> T) = element(selector).init()

fun Doc.element(selector: String): Element = selectFirst(selector)
        ?: throw ElementNotFoundException(selector)

/**
 * Will pick all occurrences of an Elements that are matching the CSS-DomSelector
 * and return it as Elements which is basically a List<Element>
 * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
 * @param selector that represents an CSS-DomSelector
 * @return Elements
 */
fun <T> Doc.elements(selector: String, init: Elements.() -> T) = elements(selector).init()

fun Doc.elements(selector: String): Elements {
    val elements = select(selector)
    if (elements.isEmpty()) throw ElementNotFoundException(selector)
    return elements
}

fun <T> Doc.selection(selector: String, init: DomSelector.() -> T) =
        DomSelector(rawCssSelector = selector, doc = this).init()
