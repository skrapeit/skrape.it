package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import it.skrape.selects.elements
import org.jsoup.select.Elements

/**
 * Will pick all occurrences of <base> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.base(cssSelector: String ="", init: Elements.() -> Unit) = elements("base$cssSelector", init)

/**
 * Will pick all occurrences of <head> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.head(cssSelector: String ="", init: Elements.() -> Unit) = elements("head$cssSelector", init)

/**
 * Will pick all occurrences of <link> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.link(cssSelector: String ="", init: Elements.() -> Unit) = elements("link$cssSelector", init)

/**
 * Will pick all occurrences of <meta> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.meta(cssSelector: String ="", init: Elements.() -> Unit) = elements("meta$cssSelector", init)

/**
 * Will pick all occurrences of <style> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.style(cssSelector: String ="", init: Elements.() -> Unit) = elements("style$cssSelector", init)

/**
 * Will pick all occurrences of <title> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.title(cssSelector: String ="", init: Elements.() -> Unit) = elements("title$cssSelector", init)
