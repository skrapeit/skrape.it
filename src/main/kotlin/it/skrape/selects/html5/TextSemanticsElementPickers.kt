package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.selects.elements
import org.jsoup.select.Elements

/**
 * Will pick all occurrences of <a> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.a(cssSelector: String = "", init: Elements.() -> Unit) = elements("a$cssSelector", init)

/**
 * Will pick all occurrences of <abbr> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.abbr(cssSelector: String = "", init: Elements.() -> Unit) = elements("abbr$cssSelector", init)

/**
 * Will pick all occurrences of <b> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.b(cssSelector: String = "", init: Elements.() -> Unit) = elements("b$cssSelector", init)

/**
 * Will pick all occurrences of <bdi> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.bdi(cssSelector: String = "", init: Elements.() -> Unit) = elements("bdi$cssSelector", init)

/**
 * Will pick all occurrences of <bdo> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.bdo(cssSelector: String = "", init: Elements.() -> Unit) = elements("bdo$cssSelector", init)

/**
 * Will pick all occurrences of <br> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.br(cssSelector: String = "", init: Elements.() -> Unit) = elements("br$cssSelector", init)

/**
 * Will pick all occurrences of <cite> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.cite(cssSelector: String = "", init: Elements.() -> Unit) = elements("cite$cssSelector", init)

/**
 * Will pick all occurrences of <code> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.code(cssSelector: String = "", init: Elements.() -> Unit) = elements("code$cssSelector", init)

/**
 * Will pick all occurrences of <data> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.data(cssSelector: String = "", init: Elements.() -> Unit) = elements("data$cssSelector", init)

/**
 * Will pick all occurrences of <dfn> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.dfn(cssSelector: String = "", init: Elements.() -> Unit) = elements("dfn$cssSelector", init)

/**
 * Will pick all occurrences of <em> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.em(cssSelector: String = "", init: Elements.() -> Unit) = elements("em$cssSelector", init)

/**
 * Will pick all occurrences of <i> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.i(cssSelector: String = "", init: Elements.() -> Unit) = elements("i$cssSelector", init)

/**
 * Will pick all occurrences of <kbd> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.kbd(cssSelector: String = "", init: Elements.() -> Unit) = elements("kbd$cssSelector", init)

/**
 * Will pick all occurrences of <mark> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.mark(cssSelector: String = "", init: Elements.() -> Unit) = elements("mark$cssSelector", init)

/**
 * Will pick all occurrences of <q> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.q(cssSelector: String = "", init: Elements.() -> Unit) = elements("q$cssSelector", init)

/**
 * Will pick all occurrences of <rb> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.rb(cssSelector: String = "", init: Elements.() -> Unit) = elements("rb$cssSelector", init)

/**
 * Will pick all occurrences of <rp> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.rp(cssSelector: String = "", init: Elements.() -> Unit) = elements("rp$cssSelector", init)

/**
 * Will pick all occurrences of <rt> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.rt(cssSelector: String = "", init: Elements.() -> Unit) = elements("rt$cssSelector", init)

/**
 * Will pick all occurrences of <rtc> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.rtc(cssSelector: String = "", init: Elements.() -> Unit) = elements("rtc$cssSelector", init)

/**
 * Will pick all occurrences of <ruby> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.ruby(cssSelector: String = "", init: Elements.() -> Unit) = elements("ruby$cssSelector", init)

/**
 * Will pick all occurrences of <s> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.s(cssSelector: String = "", init: Elements.() -> Unit) = elements("s$cssSelector", init)

/**
 * Will pick all occurrences of <samp> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.samp(cssSelector: String = "", init: Elements.() -> Unit) = elements("samp$cssSelector", init)

/**
 * Will pick all occurrences of <small> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.small(cssSelector: String = "", init: Elements.() -> Unit) = elements("small$cssSelector", init)

/**
 * Will pick all occurrences of <span> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.span(cssSelector: String = "", init: Elements.() -> Unit) = elements("span$cssSelector", init)

/**
 * Will pick all occurrences of <strong> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.strong(cssSelector: String = "", init: Elements.() -> Unit) = elements("strong$cssSelector", init)

/**
 * Will pick all occurrences of <sub> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.sub(cssSelector: String = "", init: Elements.() -> Unit) = elements("sub$cssSelector", init)

/**
 * Will pick all occurrences of <sup> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.sup(cssSelector: String = "", init: Elements.() -> Unit) = elements("sup$cssSelector", init)

/**
 * Will pick all occurrences of <time> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.time(cssSelector: String = "", init: Elements.() -> Unit) = elements("time$cssSelector", init)

/**
 * Will pick all occurrences of <tt> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.tt(cssSelector: String = "", init: Elements.() -> Unit) = elements("tt$cssSelector", init)

/**
 * Will pick all occurrences of <u> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.u(cssSelector: String = "", init: Elements.() -> Unit) = elements("u$cssSelector", init)

/**
 * Will pick all occurrences of <var> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.`var`(cssSelector: String = "", init: Elements.() -> Unit) = elements("var$cssSelector", init)

/**
 * Will pick all occurrences of <wbr> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.wbr(cssSelector: String = "", init: Elements.() -> Unit) = elements("wbr$cssSelector", init)
