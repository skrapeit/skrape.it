package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import it.skrape.selects.elements
import org.jsoup.select.Elements

/**
 * Will pick all occurrences of <area> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.area(cssSelector: String ="", init: Elements.() -> Unit) = elements("area$cssSelector", init)

/**
 * Will pick all occurrences of <audio> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.audio(cssSelector: String ="", init: Elements.() -> Unit) = elements("audio$cssSelector", init)

/**
 * Will pick all occurrences of <img> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.img(cssSelector: String ="", init: Elements.() -> Unit) = elements("img$cssSelector", init)

/**
 * Will pick all occurrences of <map> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.map(cssSelector: String ="", init: Elements.() -> Unit) = elements("map$cssSelector", init)

/**
 * Will pick all occurrences of <track> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.track(cssSelector: String ="", init: Elements.() -> Unit) = elements("track$cssSelector", init)

/**
 * Will pick all occurrences of <video> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.video(cssSelector: String ="", init: Elements.() -> Unit) = elements("video$cssSelector", init)
