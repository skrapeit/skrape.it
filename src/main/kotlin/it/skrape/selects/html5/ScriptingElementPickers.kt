package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.selects.elements
import org.jsoup.select.Elements

/**
 * Will pick all occurrences of <script> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.script(cssSelector: String = "", init: Elements.() -> T) = elements("script$cssSelector", init)

/**
 * Will pick all occurrences of <canvas> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.canvas(cssSelector: String = "", init: Elements.() -> T) = elements("canvas$cssSelector", init)

/**
 * Will pick all occurrences of <noscript> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.noscript(cssSelector: String = "", init: Elements.() -> T) = elements("noscript$cssSelector", init)
