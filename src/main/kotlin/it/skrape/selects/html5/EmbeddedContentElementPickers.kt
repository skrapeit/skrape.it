package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.selects.elements
import org.jsoup.select.Elements

/**
 * Will pick all occurrences of <applet> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.applet(cssSelector: String = "", init: Elements.() -> T) = elements("applet$cssSelector", init)

/**
 * Will pick all occurrences of <embed> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.embed(cssSelector: String = "", init: Elements.() -> T) = elements("embed$cssSelector", init)

/**
 * Will pick all occurrences of <iframe> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.iframe(cssSelector: String = "", init: Elements.() -> T) = elements("iframe$cssSelector", init)

/**
 * Will pick all occurrences of <noembed> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.noembed(cssSelector: String = "", init: Elements.() -> T) = elements("noembed$cssSelector", init)

/**
 * Will pick all occurrences of <object> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.`object`(cssSelector: String = "", init: Elements.() -> T) = elements("object$cssSelector", init)

/**
 * Will pick all occurrences of <param> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.param(cssSelector: String = "", init: Elements.() -> T) = elements("param$cssSelector", init)

/**
 * Will pick all occurrences of <picture> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.picture(cssSelector: String = "", init: Elements.() -> T) = elements("picture$cssSelector", init)

/**
 * Will pick all occurrences of <source> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.source(cssSelector: String = "", init: Elements.() -> T) = elements("source$cssSelector", init)
