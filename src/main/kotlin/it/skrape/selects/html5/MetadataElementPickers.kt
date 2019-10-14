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
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.base(cssSelector: String = "", init: Elements.() -> T) = elements("base$cssSelector", init)

/**
 * Will pick all occurrences of <head> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.head(cssSelector: String = "", init: Element.() -> T) = element("head$cssSelector", init)

/**
 * Will pick all occurrences of <link> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.link(cssSelector: String = "", init: Elements.() -> T) = elements("link$cssSelector", init)

/**
 * Will pick all occurrences of <meta> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.meta(cssSelector: String = "", init: Elements.() -> T) = elements("meta$cssSelector", init)

/**
 * Will pick all occurrences of <style> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.style(cssSelector: String = "", init: Elements.() -> T) = elements("style$cssSelector", init)

/**
 * Will pick all occurrences of <title> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.title(cssSelector: String = "", init: Elements.() -> T) = elements("title$cssSelector", init)
