package it.skrape.selects

import it.skrape.core.Result
import it.skrape.exceptions.ElementNotFoundException
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
infix fun Result.element(selector: String): Element = document.selectFirst(selector) ?: throw ElementNotFoundException(selector)

/**
 * shorthand for element
 * @param selector that represents an CSS-Selector
 * @return Element
 * @see element
 */
fun Result.el(selector: String): Element = this.element(selector)

/**
 * Will pick all occurrences of an Elements that are matching the CSS-Selector
 * and return it as Elements which is basically a List<Element>
 * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
 * @param selector that represents an CSS-Selector
 * @return Elements
 */
infix fun Result.elements(selector: String): Elements = document.select(selector)

/**
 * shorthand for elements
 * @param selector that represents an CSS-Selector
 * @return Element
 * @see elements
 */
fun Result.`$`(selector: String): Elements = document.select(selector)

infix fun Result.header(name: String): String? = this.headers[name]
