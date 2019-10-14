package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.selects.elements
import org.jsoup.select.Elements

/**
 * Will pick all occurrences of <blockquote> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.blockquote(cssSelector: String = "", init: Elements.() -> Unit) = elements("blockquote$cssSelector", init)

/**
 * Will pick all occurrences of <dd> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.dd(cssSelector: String = "", init: Elements.() -> Unit) = elements("dd$cssSelector", init)

/**
 * Will pick all occurrences of <dir> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.dir(cssSelector: String = "", init: Elements.() -> Unit) = elements("dir$cssSelector", init)

/**
 * Will pick all occurrences of <dl> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.dl(cssSelector: String = "", init: Elements.() -> Unit) = elements("dl$cssSelector", init)

/**
 * Will pick all occurrences of <dt> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.dt(cssSelector: String = "", init: Elements.() -> Unit) = elements("dt$cssSelector", init)

/**
 * Will pick all occurrences of <figcaption> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.figcaption(cssSelector: String = "", init: Elements.() -> Unit) = elements("figcaption$cssSelector", init)

/**
 * Will pick all occurrences of <figure> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.figure(cssSelector: String = "", init: Elements.() -> Unit) = elements("figure$cssSelector", init)

/**
 * Will pick all occurrences of <hr> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.hr(cssSelector: String = "", init: Elements.() -> Unit) = elements("hr$cssSelector", init)

/**
 * Will pick all occurrences of <li> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.li(cssSelector: String = "", init: Elements.() -> Unit) = elements("li$cssSelector", init)

/**
 * Will pick all occurrences of <ol> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.ol(cssSelector: String = "", init: Elements.() -> Unit) = elements("ol$cssSelector", init)

/**
 * Will pick all occurrences of <ul> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.ul(cssSelector: String = "", init: Elements.() -> Unit) = elements("ul$cssSelector", init)

/**
 * Will pick all occurrences of <p> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.p(cssSelector: String = "", init: Elements.() -> Unit) = elements("p$cssSelector", init)

/**
 * Will pick all occurrences of <pre> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.pre(cssSelector: String = "", init: Elements.() -> Unit) = elements("pre$cssSelector", init)
