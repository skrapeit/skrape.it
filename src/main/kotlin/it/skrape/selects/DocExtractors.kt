package it.skrape.selects

import it.skrape.core.Doc
import it.skrape.exceptions.DivElementNotFoundException
import it.skrape.exceptions.ElementNotFoundException
import it.skrape.exceptions.MetaElementNotFoundException
import it.skrape.exceptions.SpanElementNotFoundException
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
infix fun Doc.element(selector: String): Element = this.selectFirst(selector)
        ?: throw ElementNotFoundException(selector)

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
    val element = this.selectFirst("div$selector") ?: throw DivElementNotFoundException(selector)
    element.apply(init)
}

/**
 * Will pick all occurrences of Div-Elements that
 * are matching the CSS-Selector from a parsed document.
 * @see <a href="https://developer.mozilla.org/de/docs/Web/HTML/Element/div">Div-tag explained for further information.</a>
 * @param selector that represents an CSS-Selector
 * @return Elements
 */
fun Doc.divs(selector: String = "", init: Elements.() -> Unit) {
    val elements = this.select("div$selector")
    if (elements.size == 0) throw DivElementNotFoundException(selector)
    elements.apply(init)
}

/**
 * Will pick the first occurrence of a Span-Element that
 * is matching the CSS-Selector from a parsed document.
 * @see <a href="https://developer.mozilla.org/de/docs/Web/HTML/Element/span">Span-tag explained for further information.</a>
 * @param selector that represents an CSS-Selector
 * @return Element
 */
fun Doc.span(selector: String = "", init: Element.() -> Unit) {
    val element = this.selectFirst("span$selector") ?: throw SpanElementNotFoundException(selector)
    element.apply(init)
}

/**
 * Will pick all occurrences of Span-Elements that
 * are matching the CSS-Selector from a parsed document.
 * @see <a href="https://developer.mozilla.org/de/docs/Web/HTML/Element/span">Span-tag explained for further information.</a>
 * @param selector that represents an CSS-Selector
 * @return Elements
 */
fun Doc.spans(selector: String = "", init: Elements.() -> Unit) {
    val elements = this.select("span$selector")
    if (elements.size == 0) throw SpanElementNotFoundException(selector)
    elements.apply(init)
}

/**
 * Will pick the first occurrence of a Meta-Element that
 * is matching the CSS-Selector from a parsed document.
 * @see <a href="https://developer.mozilla.org/de/docs/Web/HTML/Element/div">Div-tag explained for further information.</a>
 * @param selector that represents an CSS-Selector
 * @return Element
 */
fun Doc.meta(selector: String = "", init: Element.() -> Unit) {
    val element = this.selectFirst("meta$selector") ?: throw MetaElementNotFoundException(selector)
    element.apply(init)
}

/**
 * Will pick all occurrences of Meta-Elements that
 * are matching the CSS-Selector from a parsed document.
 * @see <a href="https://developer.mozilla.org/de/docs/Web/HTML/Element/div">Div-tag explained for further information.</a>
 * @param selector that represents an CSS-Selector
 * @return Element
 */
fun Doc.metas(selector: String = "", init: Elements.() -> Unit) {
    val elements = this.select("meta$selector")
    if (elements.size == 0) throw MetaElementNotFoundException(selector)
    elements.apply(init)
}
