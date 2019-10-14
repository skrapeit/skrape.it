package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.selects.elements
import org.jsoup.select.Elements

/**
 * Will pick all occurrences of <caption> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.caption(cssSelector: String = "", init: Elements.() -> Unit) = elements("caption$cssSelector", init)

/**
 * Will pick all occurrences of <col> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.col(cssSelector: String = "", init: Elements.() -> Unit) = elements("col$cssSelector", init)

/**
 * Will pick all occurrences of <colgroup> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.colgroup(cssSelector: String = "", init: Elements.() -> Unit) = elements("colgroup$cssSelector", init)

/**
 * Will pick all occurrences of <table> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.table(cssSelector: String = "", init: Elements.() -> Unit) = elements("table$cssSelector", init)

/**
 * Will pick all occurrences of <tbody> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.tbody(cssSelector: String = "", init: Elements.() -> Unit) = elements("tbody$cssSelector", init)

/**
 * Will pick all occurrences of <td> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.td(cssSelector: String = "", init: Elements.() -> Unit) = elements("td$cssSelector", init)

/**
 * Will pick all occurrences of <tfoot> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.tfoot(cssSelector: String = "", init: Elements.() -> Unit) = elements("tfoot$cssSelector", init)

/**
 * Will pick all occurrences of <th> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.th(cssSelector: String = "", init: Elements.() -> Unit) = elements("th$cssSelector", init)

/**
 * Will pick all occurrences of <thead> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.thead(cssSelector: String = "", init: Elements.() -> Unit) = elements("thead$cssSelector", init)

/**
 * Will pick all occurrences of <tr> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.tr(cssSelector: String = "", init: Elements.() -> Unit) = elements("tr$cssSelector", init)
