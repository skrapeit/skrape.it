package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import it.skrape.selects.elements
import org.jsoup.select.Elements

/**
 * Will pick all occurrences of <details> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.details(cssSelector: String ="", init: Elements.() -> Unit) = elements("details$cssSelector", init)

/**
 * Will pick all occurrences of <dialog> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.dialog(cssSelector: String ="", init: Elements.() -> Unit) = elements("dialog$cssSelector", init)

/**
 * Will pick all occurrences of <menu> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.menu(cssSelector: String ="", init: Elements.() -> Unit) = elements("menu$cssSelector", init)

/**
 * Will pick all occurrences of <menuitem> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.menuitem(cssSelector: String ="", init: Elements.() -> Unit) = elements("menuitem$cssSelector", init)

/**
 * Will pick all occurrences of <summary> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.summary(cssSelector: String ="", init: Elements.() -> Unit) = elements("summary$cssSelector", init)
