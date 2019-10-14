package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.selects.element
import it.skrape.selects.elements
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

/**
 * Will pick the first occurrences of an <body> element from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Element
 */
@SkrapeItDslMarker
fun <T> Doc.body(cssSelector: String = "", init: Element.() -> T) = element("body$cssSelector", init)

/**
 * Will pick all occurrences of <div> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.div(cssSelector: String = "", init: Elements.() -> T) = elements("div$cssSelector", init)

/**
 * Will pick all occurrences of <section> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.section(cssSelector: String = "", init: Elements.() -> T) = elements("section$cssSelector", init)

/**
 * Will pick all occurrences of <nav> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.nav(cssSelector: String = "", init: Elements.() -> T) = elements("nav$cssSelector", init)

/**
 * Will pick all occurrences of <article> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.article(cssSelector: String = "", init: Elements.() -> T) = elements("article$cssSelector", init)

/**
 * Will pick all occurrences of <aside> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.aside(cssSelector: String = "", init: Elements.() -> T) = elements("aside$cssSelector", init)

/**
 * Will pick all occurrences of <h1> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.h1(cssSelector: String = "", init: Elements.() -> T) = elements("h1$cssSelector", init)

/**
 * Will pick all occurrences of <h2> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.h2(cssSelector: String = "", init: Elements.() -> T) = elements("h2$cssSelector", init)

/**
 * Will pick all occurrences of <h3> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.h3(cssSelector: String = "", init: Elements.() -> T) = elements("h3$cssSelector", init)

/**
 * Will pick all occurrences of <h4> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.h4(cssSelector: String = "", init: Elements.() -> T) = elements("h4$cssSelector", init)

/**
 * Will pick all occurrences of <h5> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.h5(cssSelector: String = "", init: Elements.() -> T) = elements("h5$cssSelector", init)

/**
 * Will pick all occurrences of <h6> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.h6(cssSelector: String = "", init: Elements.() -> T) = elements("h6$cssSelector", init)

/**
 * Will pick all occurrences of <header> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.header(cssSelector: String = "", init: Elements.() -> T) = elements("header$cssSelector", init)

/**
 * Will pick all occurrences of <footer> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.footer(cssSelector: String = "", init: Elements.() -> T) = elements("footer$cssSelector", init)

/**
 * Will pick all occurrences of <address> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.address(cssSelector: String = "", init: Elements.() -> T) = elements("address$cssSelector", init)

/**
 * Will pick all occurrences of <main> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.main(cssSelector: String = "", init: Elements.() -> T) = elements("main$cssSelector", init)
