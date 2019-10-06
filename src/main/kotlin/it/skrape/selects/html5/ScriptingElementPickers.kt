package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import it.skrape.selects.elements
import org.jsoup.select.Elements

/**
 * Will pick all occurrences of <script> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.script(cssSelector: String ="", init: Elements.() -> Unit) = elements("script$cssSelector", init)

/**
 * Will pick all occurrences of <canvas> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.canvas(cssSelector: String ="", init: Elements.() -> Unit) = elements("canvas$cssSelector", init)

/**
 * Will pick all occurrences of <noscript> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.noscript(cssSelector: String ="", init: Elements.() -> Unit) = elements("noscript$cssSelector", init)
