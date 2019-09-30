package it.skrape.selects

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import it.skrape.exceptions.DivElementNotFoundException
import it.skrape.exceptions.ElementNotFoundException
import it.skrape.exceptions.MetaElementNotFoundException
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
infix fun Result.elements(selector: String): Elements = document.elements(selector)

/**
 * shorthand for elements
 * @param selector that represents an CSS-Selector
 * @return Element
 * @see elements
 */
fun Result.`$`(selector: String): Elements = this.elements(selector)

/**
 * Will return a certain response headers value
 * @see <a href="https://developer.mozilla.org/en-US/docs/Glossary/Response_header">Explanation about response headers.</a>
 * @param name that represents the
 * @return String with value of a certain response header
 */
infix fun Result.header(name: String): String? = this.headers[name]

@SkrapeItDslMarker
fun Result.title(init: String.() -> Unit): String {
    val title = document.title()
    title.apply(init)
    return title
}

@SkrapeItDslMarker
fun Result.body(init: Element.() -> Unit): Element {
    val body = document.body()
    body.apply(init)
    return body
}

/**
 * Will pick the first occurrence of a Div-Element that
 * is matching the CSS-Selector from a result.
 * @see <a href="https://developer.mozilla.org/de/docs/Web/HTML/Element/div">Div-tag explained for further information.</a>
 * @param selector that represents an CSS-Selector
 * @return Element
 */
fun Result.div(selector: String = "", init: Element.() -> Unit): Element {
    val div = document.selectFirst("div$selector") ?: throw DivElementNotFoundException(selector)
    div.apply(init)
    return div
}

/**
 * Will pick all occurrences of Div-Elements that
 * are matching the CSS-Selector from a result.
 * @see <a href="https://developer.mozilla.org/de/docs/Web/HTML/Element/div">Div-tag explained for further information.</a>
 * @param selector that represents an CSS-Selector
 * @return Elements
 */
fun Result.divs(selector: String = "", init: Elements.() -> Unit): Elements {
    val divs = document.select("div$selector")
    if (divs.size == 0) throw DivElementNotFoundException(selector)
    divs.apply(init)
    return divs
}

/**
 * Will pick the first occurrence of a Meta-Element that
 * is matching the CSS-Selector from a result.
 * @see <a href="https://developer.mozilla.org/de/docs/Web/HTML/Element/meta">Meta-tag explained for further information.</a>
 * @param selector that represents an CSS-Selector
 * @return Element
 */
fun Result.meta(selector: String = "", init: Element.() -> Unit): Element {
    val meta = document.selectFirst("meta$selector") ?: throw MetaElementNotFoundException(selector)
    meta.apply(init)
    return meta
}

/**
 * Will pick all occurrences of Meta-Elements that
 * are matching the CSS-Selector from a result.
 * @see <a href="https://developer.mozilla.org/de/docs/Web/HTML/Element/meta">Meta-tag explained for further information.</a>
 * @param selector that represents an CSS-Selector
 * @return Elements
 */
fun Result.metas(selector: String = "", init: Elements.() -> Unit): Elements {
    val metas = document.select("meta$selector")
    if (metas.size == 0) throw MetaElementNotFoundException(selector)
    metas.apply(init)
    return metas
}

@SkrapeItDslMarker
fun Result.element(selector: String, init: Element.() -> Unit) {
    val element = document.selectFirst(selector) ?: throw ElementNotFoundException(selector)
    element.apply(init)
}

@SkrapeItDslMarker
fun Result.elements(selector: String, init: Elements.() -> Unit) {
    document.select(selector).apply(init)
}

@SkrapeItDslMarker
fun Result.headers(init: Map<String, String>.() -> Unit) : Map<String, String> {
    val headers = this.headers
    headers.apply(init)
    return headers
}

@SkrapeItDslMarker
fun Result.header(name: String, init: String?.() -> Unit) : String? {
    val header = this.headers[name]
    header.apply(init)
    return header
}
