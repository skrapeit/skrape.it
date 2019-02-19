package it.skrape.selects

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

/**
 * Will pick the first occurrence of an Element that
 * is matching the CSS-Selector.
 * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
 * @param selector that represents an CSS-Selector
 * @return Element
 * @see Element
 */
fun Result.el(selector: String): Element = document.selectFirst(selector)

/**
 * Will pick all occurrences of an Elements that are matching the CSS-Selector
 * and return it as Elements which is basically a List<Element>
 * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
 * @param selector that represents an CSS-Selector
 * @return Elements
 * @see Elements
 */
fun Result.`$`(selector: String): Elements = document.select(selector)