package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import it.skrape.selects.elements
import org.jsoup.select.Elements

/**
 * Will pick all occurrences of <caption> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.caption(cssSelector: String ="", init: Elements.() -> Unit) = elements("caption$cssSelector", init)

/**
 * Will pick all occurrences of <col> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.col(cssSelector: String ="", init: Elements.() -> Unit) = elements("col$cssSelector", init)

/**
 * Will pick all occurrences of <colgroup> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.colgroup(cssSelector: String ="", init: Elements.() -> Unit) = elements("colgroup$cssSelector", init)

/**
 * Will pick all occurrences of <table> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.table(cssSelector: String ="", init: Elements.() -> Unit) = elements("table$cssSelector", init)

/**
 * Will pick all occurrences of <tbody> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.tbody(cssSelector: String ="", init: Elements.() -> Unit) = elements("tbody$cssSelector", init)

/**
 * Will pick all occurrences of <td> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.td(cssSelector: String ="", init: Elements.() -> Unit) = elements("td$cssSelector", init)

/**
 * Will pick all occurrences of <tfoot> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.tfoot(cssSelector: String ="", init: Elements.() -> Unit) = elements("tfoot$cssSelector", init)

/**
 * Will pick all occurrences of <th> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.th(cssSelector: String ="", init: Elements.() -> Unit) = elements("th$cssSelector", init)

/**
 * Will pick all occurrences of <thead> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.thead(cssSelector: String ="", init: Elements.() -> Unit) = elements("thead$cssSelector", init)

/**
 * Will pick all occurrences of <tr> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.tr(cssSelector: String ="", init: Elements.() -> Unit) = elements("tr$cssSelector", init)
