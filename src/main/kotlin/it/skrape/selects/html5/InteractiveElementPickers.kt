package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.selects.elements
import org.jsoup.select.Elements

/**
 * Will pick all occurrences of <details> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.details(cssSelector: String = "", init: Elements.() -> T) = elements("details$cssSelector", init)

/**
 * Will pick all occurrences of <dialog> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.dialog(cssSelector: String = "", init: Elements.() -> T) = elements("dialog$cssSelector", init)

/**
 * Will pick all occurrences of <menu> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.menu(cssSelector: String = "", init: Elements.() -> T) = elements("menu$cssSelector", init)

/**
 * Will pick all occurrences of <menuitem> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.menuitem(cssSelector: String = "", init: Elements.() -> T) = elements("menuitem$cssSelector", init)

/**
 * Will pick all occurrences of <summary> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.summary(cssSelector: String = "", init: Elements.() -> T) = elements("summary$cssSelector", init)
