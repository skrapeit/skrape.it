package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import it.skrape.selects.elements
import org.jsoup.select.Elements

/**
 * Will pick all occurrences of <content> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.content(cssSelector: String ="", init: Elements.() -> Unit) = elements("content$cssSelector", init)

/**
 * Will pick all occurrences of <shadow> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.shadow(cssSelector: String ="", init: Elements.() -> Unit) = elements("shadow$cssSelector", init)

/**
 * Will pick all occurrences of <slot> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.slot(cssSelector: String ="", init: Elements.() -> Unit) = elements("slot$cssSelector", init)

/**
 * Will pick all occurrences of <template> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.template(cssSelector: String ="", init: Elements.() -> Unit) = elements("template$cssSelector", init)
