package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.selects.elements
import org.jsoup.select.Elements

/**
 * Will pick all occurrences of <content> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.content(cssSelector: String = "", init: Elements.() -> T) = elements("content$cssSelector", init)

/**
 * Will pick all occurrences of <shadow> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.shadow(cssSelector: String = "", init: Elements.() -> T) = elements("shadow$cssSelector", init)

/**
 * Will pick all occurrences of <slot> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.slot(cssSelector: String = "", init: Elements.() -> T) = elements("slot$cssSelector", init)

/**
 * Will pick all occurrences of <template> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.template(cssSelector: String = "", init: Elements.() -> T) = elements("template$cssSelector", init)
