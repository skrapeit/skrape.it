package it.skrape.selects

import it.skrape.core.Doc
import it.skrape.core.Result
import it.skrape.exceptions.DivNotFoundException
import it.skrape.exceptions.ElementNotFoundException
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
infix fun Doc.element(selector: String): Element = this.selectFirst(selector) ?: throw ElementNotFoundException(selector)

/**
 * Will pick all occurrences of an Elements that are matching the CSS-Selector
 * and return it as Elements which is basically a List<Element>
 * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
 * @param selector that represents an CSS-Selector
 * @return Elements
 */
infix fun Doc.elements(selector: String): Elements = this.select(selector)


/**
 * Will pick the first occurrence of a Div-Element that
 * is matching the CSS-Selector from a parsed document.
 * @see <a href="https://developer.mozilla.org/de/docs/Web/HTML/Element/div">Div-tag explained for further information.</a>
 * @param selector that represents an CSS-Selector
 * @return Element
 */
fun Doc.div(selector: String = "", init: Element.() -> Unit) {
    val element = this.selectFirst("div$selector") ?: throw DivNotFoundException(selector)
    element.apply(init)
}

/**
 * Will pick all occurrences of a Div-Elements that
 * are matching the CSS-Selector from a parsed document.
 * @see <a href="https://developer.mozilla.org/de/docs/Web/HTML/Element/div">Div-tag explained for further information.</a>
 * @param selector that represents an CSS-Selector
 * @return Element
 */
fun Doc.divs(selector: String = "", init: Elements.() -> Unit) {
    val elements = this.select("div$selector")
    if (elements.size == 0) throw DivNotFoundException(selector)
    elements.apply(init)
}
