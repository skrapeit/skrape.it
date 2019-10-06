package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import it.skrape.selects.element
import it.skrape.selects.elements
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

/**
 * Will pick all occurrences of <del> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.del(cssSelector: String ="", init: Elements.() -> Unit) = elements("del$cssSelector", init)

/**
 * Will pick all occurrences of <ins> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.ins(cssSelector: String ="", init: Elements.() -> Unit) = elements("ins$cssSelector", init)
