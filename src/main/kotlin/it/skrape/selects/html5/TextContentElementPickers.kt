package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.selects.elements
import org.jsoup.select.Elements

/**
 * Will pick all occurrences of <blockquote> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.blockquote(cssSelector: String = "", init: Elements.() -> T) = elements("blockquote$cssSelector", init)

/**
 * Will pick all occurrences of <dd> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.dd(cssSelector: String = "", init: Elements.() -> T) = elements("dd$cssSelector", init)

/**
 * Will pick all occurrences of <dir> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.dir(cssSelector: String = "", init: Elements.() -> T) = elements("dir$cssSelector", init)

/**
 * Will pick all occurrences of <dl> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.dl(cssSelector: String = "", init: Elements.() -> T) = elements("dl$cssSelector", init)

/**
 * Will pick all occurrences of <dt> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.dt(cssSelector: String = "", init: Elements.() -> T) = elements("dt$cssSelector", init)

/**
 * Will pick all occurrences of <figcaption> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.figcaption(cssSelector: String = "", init: Elements.() -> T) = elements("figcaption$cssSelector", init)

/**
 * Will pick all occurrences of <figure> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.figure(cssSelector: String = "", init: Elements.() -> T) = elements("figure$cssSelector", init)

/**
 * Will pick all occurrences of <hr> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.hr(cssSelector: String = "", init: Elements.() -> T) = elements("hr$cssSelector", init)

/**
 * Will pick all occurrences of <li> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.li(cssSelector: String = "", init: Elements.() -> T) = elements("li$cssSelector", init)

/**
 * Will pick all occurrences of <ol> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.ol(cssSelector: String = "", init: Elements.() -> T) = elements("ol$cssSelector", init)

/**
 * Will pick all occurrences of <ul> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.ul(cssSelector: String = "", init: Elements.() -> T) = elements("ul$cssSelector", init)

/**
 * Will pick all occurrences of <p> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.p(cssSelector: String = "", init: Elements.() -> T) = elements("p$cssSelector", init)

/**
 * Will pick all occurrences of <p> elements from within Elements.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Elements.p(cssSelector: String = "", init: Elements.() -> T) = elements("p$cssSelector", init)

/**
 * Will pick all occurrences of <pre> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.pre(cssSelector: String = "", init: Elements.() -> T) = elements("pre$cssSelector", init)
