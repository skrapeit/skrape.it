package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.selects.elements
import org.jsoup.select.Elements

/**
 * Will pick all occurrences of <caption> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.caption(cssSelector: String = "", init: Elements.() -> T) = elements("caption$cssSelector", init)

/**
 * Will pick all occurrences of <col> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.col(cssSelector: String = "", init: Elements.() -> T) = elements("col$cssSelector", init)

/**
 * Will pick all occurrences of <colgroup> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.colgroup(cssSelector: String = "", init: Elements.() -> T) = elements("colgroup$cssSelector", init)

/**
 * Will pick all occurrences of <table> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.table(cssSelector: String = "", init: Elements.() -> T) = elements("table$cssSelector", init)

/**
 * Will pick all occurrences of <tbody> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.tbody(cssSelector: String = "", init: Elements.() -> T) = elements("tbody$cssSelector", init)

/**
 * Will pick all occurrences of <td> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.td(cssSelector: String = "", init: Elements.() -> T) = elements("td$cssSelector", init)

/**
 * Will pick all occurrences of <tfoot> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.tfoot(cssSelector: String = "", init: Elements.() -> T) = elements("tfoot$cssSelector", init)

/**
 * Will pick all occurrences of <th> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.th(cssSelector: String = "", init: Elements.() -> T) = elements("th$cssSelector", init)

/**
 * Will pick all occurrences of <thead> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.thead(cssSelector: String = "", init: Elements.() -> T) = elements("thead$cssSelector", init)

/**
 * Will pick all occurrences of <tr> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.tr(cssSelector: String = "", init: Elements.() -> T) = elements("tr$cssSelector", init)
