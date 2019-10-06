package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import it.skrape.selects.element
import it.skrape.selects.elements
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

/**
 * Will pick the first occurrences of an <body> element from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Element
 */
@SkrapeItDslMarker
fun Result.body(cssSelector: String ="", init: Element.() -> Unit) = element("body$cssSelector", init)

/**
 * Will pick all occurrences of <div> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.div(cssSelector: String ="", init: Elements.() -> Unit) = elements("div$cssSelector", init)

/**
 * Will pick all occurrences of <section> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.section(cssSelector: String ="", init: Elements.() -> Unit) = elements("section$cssSelector", init)

/**
 * Will pick all occurrences of <nav> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.nav(cssSelector: String ="", init: Elements.() -> Unit) = elements("nav$cssSelector", init)

/**
 * Will pick all occurrences of <article> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.article(cssSelector: String ="", init: Elements.() -> Unit) = elements("article$cssSelector", init)

/**
 * Will pick all occurrences of <aside> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.aside(cssSelector: String ="", init: Elements.() -> Unit) = elements("aside$cssSelector", init)

/**
 * Will pick all occurrences of <h1> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.h1(cssSelector: String ="", init: Elements.() -> Unit) = elements("h1$cssSelector", init)

/**
 * Will pick all occurrences of <h2> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.h2(cssSelector: String ="", init: Elements.() -> Unit) = elements("h2$cssSelector", init)

/**
 * Will pick all occurrences of <h3> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.h3(cssSelector: String ="", init: Elements.() -> Unit) = elements("h3$cssSelector", init)

/**
 * Will pick all occurrences of <h4> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.h4(cssSelector: String ="", init: Elements.() -> Unit) = elements("h4$cssSelector", init)

/**
 * Will pick all occurrences of <h5> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.h5(cssSelector: String ="", init: Elements.() -> Unit) = elements("h5$cssSelector", init)

/**
 * Will pick all occurrences of <h6> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.h6(cssSelector: String ="", init: Elements.() -> Unit) = elements("h6$cssSelector", init)

/**
 * Will pick all occurrences of <header> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.header(cssSelector: String ="", init: Elements.() -> Unit) = elements("header$cssSelector", init)

/**
 * Will pick all occurrences of <footer> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.footer(cssSelector: String ="", init: Elements.() -> Unit) = elements("footer$cssSelector", init)

/**
 * Will pick all occurrences of <address> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.address(cssSelector: String ="", init: Elements.() -> Unit) = elements("address$cssSelector", init)

/**
 * Will pick all occurrences of <main> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.main(cssSelector: String ="", init: Elements.() -> Unit) = elements("main$cssSelector", init)
