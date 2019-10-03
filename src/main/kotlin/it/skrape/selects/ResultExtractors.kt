package it.skrape.selects

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import it.skrape.exceptions.*
import org.jsoup.nodes.Element
import org.jsoup.select.Elements


/**
 * Will pick the first occurrence of an Element that
 * is matching the CSS-Selector.
 * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
 * @param selector that represents an CSS-Selector
 * @return Element
 */
@Suppress("MaxLineLength")
infix fun Result.element(selector: String): Element = document.element(selector)

/**
 * Will pick all occurrences of an Elements that are matching the CSS-Selector
 * and return it as Elements which is basically a List<Element>
 * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
 * @param selector that represents an CSS-Selector
 * @return Elements
 */
infix fun Result.elements(selector: String): Elements = document.elements(selector)

/**
 * Will return a certain response headers value
 * @see <a href="https://developer.mozilla.org/en-US/docs/Glossary/Response_header">Explanation about response headers.</a>
 * @param name that represents the
 * @return String with value of a certain response header
 */
infix fun Result.responseHeader(name: String): String? = this.headers[name]

@SkrapeItDslMarker
fun Result.element(selector: String, init: Element.() -> Unit): Element =
        document.element(selector, init)

@SkrapeItDslMarker
fun Result.elements(selector: String, init: Elements.() -> Unit): Elements =
        document.elements(selector, init)

@SkrapeItDslMarker
fun Result.headers(init: Map<String, String>.() -> Unit): Map<String, String> {
    headers.apply(init)
    return headers
}

@SkrapeItDslMarker
fun Result.responseHeader(name: String, init: String?.() -> Unit): String? {
    val header = headers[name]
    header.apply(init)
    return header
}
