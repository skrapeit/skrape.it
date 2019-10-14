package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.selects.elements
import org.jsoup.select.Elements

/**
 * Will pick all occurrences of <a> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.a(cssSelector: String = "", init: Elements.() -> T) = elements("a$cssSelector", init)

/**
 * Will pick all occurrences of <abbr> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.abbr(cssSelector: String = "", init: Elements.() -> T) = elements("abbr$cssSelector", init)

/**
 * Will pick all occurrences of <b> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.b(cssSelector: String = "", init: Elements.() -> T) = elements("b$cssSelector", init)

/**
 * Will pick all occurrences of <bdi> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.bdi(cssSelector: String = "", init: Elements.() -> T) = elements("bdi$cssSelector", init)

/**
 * Will pick all occurrences of <bdo> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.bdo(cssSelector: String = "", init: Elements.() -> T) = elements("bdo$cssSelector", init)

/**
 * Will pick all occurrences of <br> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.br(cssSelector: String = "", init: Elements.() -> T) = elements("br$cssSelector", init)

/**
 * Will pick all occurrences of <cite> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.cite(cssSelector: String = "", init: Elements.() -> T) = elements("cite$cssSelector", init)

/**
 * Will pick all occurrences of <code> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.code(cssSelector: String = "", init: Elements.() -> T) = elements("code$cssSelector", init)

/**
 * Will pick all occurrences of <data> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.data(cssSelector: String = "", init: Elements.() -> T) = elements("data$cssSelector", init)

/**
 * Will pick all occurrences of <dfn> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.dfn(cssSelector: String = "", init: Elements.() -> T) = elements("dfn$cssSelector", init)

/**
 * Will pick all occurrences of <em> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.em(cssSelector: String = "", init: Elements.() -> T) = elements("em$cssSelector", init)

/**
 * Will pick all occurrences of <i> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.i(cssSelector: String = "", init: Elements.() -> T) = elements("i$cssSelector", init)

/**
 * Will pick all occurrences of <kbd> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.kbd(cssSelector: String = "", init: Elements.() -> T) = elements("kbd$cssSelector", init)

/**
 * Will pick all occurrences of <mark> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.mark(cssSelector: String = "", init: Elements.() -> T) = elements("mark$cssSelector", init)

/**
 * Will pick all occurrences of <q> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.q(cssSelector: String = "", init: Elements.() -> T) = elements("q$cssSelector", init)

/**
 * Will pick all occurrences of <rb> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.rb(cssSelector: String = "", init: Elements.() -> T) = elements("rb$cssSelector", init)

/**
 * Will pick all occurrences of <rp> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.rp(cssSelector: String = "", init: Elements.() -> T) = elements("rp$cssSelector", init)

/**
 * Will pick all occurrences of <rt> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.rt(cssSelector: String = "", init: Elements.() -> T) = elements("rt$cssSelector", init)

/**
 * Will pick all occurrences of <rtc> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.rtc(cssSelector: String = "", init: Elements.() -> T) = elements("rtc$cssSelector", init)

/**
 * Will pick all occurrences of <ruby> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.ruby(cssSelector: String = "", init: Elements.() -> T) = elements("ruby$cssSelector", init)

/**
 * Will pick all occurrences of <s> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.s(cssSelector: String = "", init: Elements.() -> T) = elements("s$cssSelector", init)

/**
 * Will pick all occurrences of <samp> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.samp(cssSelector: String = "", init: Elements.() -> T) = elements("samp$cssSelector", init)

/**
 * Will pick all occurrences of <small> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.small(cssSelector: String = "", init: Elements.() -> T) = elements("small$cssSelector", init)

/**
 * Will pick all occurrences of <span> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.span(cssSelector: String = "", init: Elements.() -> T) = elements("span$cssSelector", init)

/**
 * Will pick all occurrences of <strong> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.strong(cssSelector: String = "", init: Elements.() -> T) = elements("strong$cssSelector", init)

/**
 * Will pick all occurrences of <sub> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.sub(cssSelector: String = "", init: Elements.() -> T) = elements("sub$cssSelector", init)

/**
 * Will pick all occurrences of <sup> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.sup(cssSelector: String = "", init: Elements.() -> T) = elements("sup$cssSelector", init)

/**
 * Will pick all occurrences of <time> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.time(cssSelector: String = "", init: Elements.() -> T) = elements("time$cssSelector", init)

/**
 * Will pick all occurrences of <tt> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.tt(cssSelector: String = "", init: Elements.() -> T) = elements("tt$cssSelector", init)

/**
 * Will pick all occurrences of <u> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.u(cssSelector: String = "", init: Elements.() -> T) = elements("u$cssSelector", init)

/**
 * Will pick all occurrences of <var> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.`var`(cssSelector: String = "", init: Elements.() -> T) = elements("var$cssSelector", init)

/**
 * Will pick all occurrences of <wbr> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.wbr(cssSelector: String = "", init: Elements.() -> T) = elements("wbr$cssSelector", init)
