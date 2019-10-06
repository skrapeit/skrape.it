package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import it.skrape.selects.elements
import org.jsoup.select.Elements

/**
 * Will pick all occurrences of <applet> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.applet(cssSelector: String ="", init: Elements.() -> Unit) = elements("applet$cssSelector", init)

/**
 * Will pick all occurrences of <embed> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.embed(cssSelector: String ="", init: Elements.() -> Unit) = elements("embed$cssSelector", init)

/**
 * Will pick all occurrences of <iframe> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.iframe(cssSelector: String ="", init: Elements.() -> Unit) = elements("iframe$cssSelector", init)

/**
 * Will pick all occurrences of <noembed> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.noembed(cssSelector: String ="", init: Elements.() -> Unit) = elements("noembed$cssSelector", init)

/**
 * Will pick all occurrences of <object> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.`object`(cssSelector: String ="", init: Elements.() -> Unit) = elements("object$cssSelector", init)

/**
 * Will pick all occurrences of <param> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.param(cssSelector: String ="", init: Elements.() -> Unit) = elements("param$cssSelector", init)

/**
 * Will pick all occurrences of <picture> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.picture(cssSelector: String ="", init: Elements.() -> Unit) = elements("picture$cssSelector", init)

/**
 * Will pick all occurrences of <source> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.source(cssSelector: String ="", init: Elements.() -> Unit) = elements("source$cssSelector", init)
