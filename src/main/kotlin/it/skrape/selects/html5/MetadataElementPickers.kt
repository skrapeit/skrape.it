package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.selects.element
import it.skrape.selects.elements
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

/**
 * Will pick all occurrences of <base> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.base(cssSelector: String = "", init: Elements.() -> Unit) = elements("base$cssSelector", init)

/**
 * Will pick all occurrences of <head> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.head(cssSelector: String = "", init: Element.() -> Unit) = element("head$cssSelector", init)

/**
 * Will pick all occurrences of <link> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.link(cssSelector: String = "", init: Elements.() -> Unit) = elements("link$cssSelector", init)

/**
 * Will pick all occurrences of <meta> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.meta(cssSelector: String = "", init: Elements.() -> Unit) = elements("meta$cssSelector", init)

/**
 * Will pick all occurrences of <style> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.style(cssSelector: String = "", init: Elements.() -> Unit) = elements("style$cssSelector", init)

/**
 * Will pick all occurrences of <title> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.title(cssSelector: String = "", init: Elements.() -> Unit) = elements("title$cssSelector", init)
