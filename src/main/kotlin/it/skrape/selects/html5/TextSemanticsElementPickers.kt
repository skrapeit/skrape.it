package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import it.skrape.selects.elements
import org.jsoup.select.Elements

/**
 * Will pick all occurrences of <a> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.a(cssSelector: String ="", init: Elements.() -> Unit) = elements("a$cssSelector", init)

/**
 * Will pick all occurrences of <abbr> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.abbr(cssSelector: String ="", init: Elements.() -> Unit) = elements("abbr$cssSelector", init)

/**
 * Will pick all occurrences of <b> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.b(cssSelector: String ="", init: Elements.() -> Unit) = elements("b$cssSelector", init)

/**
 * Will pick all occurrences of <bdi> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.bdi(cssSelector: String ="", init: Elements.() -> Unit) = elements("bdi$cssSelector", init)

/**
 * Will pick all occurrences of <bdo> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.bdo(cssSelector: String ="", init: Elements.() -> Unit) = elements("bdo$cssSelector", init)

/**
 * Will pick all occurrences of <br> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.br(cssSelector: String ="", init: Elements.() -> Unit) = elements("br$cssSelector", init)

/**
 * Will pick all occurrences of <cite> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.cite(cssSelector: String ="", init: Elements.() -> Unit) = elements("cite$cssSelector", init)

/**
 * Will pick all occurrences of <code> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.code(cssSelector: String ="", init: Elements.() -> Unit) = elements("code$cssSelector", init)

/**
 * Will pick all occurrences of <data> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.data(cssSelector: String ="", init: Elements.() -> Unit) = elements("data$cssSelector", init)

/**
 * Will pick all occurrences of <dfn> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.dfn(cssSelector: String ="", init: Elements.() -> Unit) = elements("dfn$cssSelector", init)

/**
 * Will pick all occurrences of <em> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.em(cssSelector: String ="", init: Elements.() -> Unit) = elements("em$cssSelector", init)

/**
 * Will pick all occurrences of <i> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.i(cssSelector: String ="", init: Elements.() -> Unit) = elements("i$cssSelector", init)

/**
 * Will pick all occurrences of <kbd> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.kbd(cssSelector: String ="", init: Elements.() -> Unit) = elements("kbd$cssSelector", init)

/**
 * Will pick all occurrences of <mark> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.mark(cssSelector: String ="", init: Elements.() -> Unit) = elements("mark$cssSelector", init)

/**
 * Will pick all occurrences of <q> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.q(cssSelector: String ="", init: Elements.() -> Unit) = elements("q$cssSelector", init)

/**
 * Will pick all occurrences of <rb> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.rb(cssSelector: String ="", init: Elements.() -> Unit) = elements("rb$cssSelector", init)

/**
 * Will pick all occurrences of <rp> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.rp(cssSelector: String ="", init: Elements.() -> Unit) = elements("rp$cssSelector", init)

/**
 * Will pick all occurrences of <rt> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.rt(cssSelector: String ="", init: Elements.() -> Unit) = elements("rt$cssSelector", init)

/**
 * Will pick all occurrences of <rtc> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.rtc(cssSelector: String ="", init: Elements.() -> Unit) = elements("rtc$cssSelector", init)

/**
 * Will pick all occurrences of <ruby> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.ruby(cssSelector: String ="", init: Elements.() -> Unit) = elements("ruby$cssSelector", init)

/**
 * Will pick all occurrences of <s> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.s(cssSelector: String ="", init: Elements.() -> Unit) = elements("s$cssSelector", init)

/**
 * Will pick all occurrences of <samp> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.samp(cssSelector: String ="", init: Elements.() -> Unit) = elements("samp$cssSelector", init)

/**
 * Will pick all occurrences of <small> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.small(cssSelector: String ="", init: Elements.() -> Unit) = elements("small$cssSelector", init)

/**
 * Will pick all occurrences of <span> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.span(cssSelector: String ="", init: Elements.() -> Unit) = elements("span$cssSelector", init)

/**
 * Will pick all occurrences of <strong> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.strong(cssSelector: String ="", init: Elements.() -> Unit) = elements("strong$cssSelector", init)

/**
 * Will pick all occurrences of <sub> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.sub(cssSelector: String ="", init: Elements.() -> Unit) = elements("sub$cssSelector", init)

/**
 * Will pick all occurrences of <sup> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.sup(cssSelector: String ="", init: Elements.() -> Unit) = elements("sup$cssSelector", init)

/**
 * Will pick all occurrences of <time> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.time(cssSelector: String ="", init: Elements.() -> Unit) = elements("time$cssSelector", init)

/**
 * Will pick all occurrences of <tt> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.tt(cssSelector: String ="", init: Elements.() -> Unit) = elements("tt$cssSelector", init)

/**
 * Will pick all occurrences of <u> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.u(cssSelector: String ="", init: Elements.() -> Unit) = elements("u$cssSelector", init)

/**
 * Will pick all occurrences of <var> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.`var`(cssSelector: String ="", init: Elements.() -> Unit) = elements("var$cssSelector", init)

/**
 * Will pick all occurrences of <wbr> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.wbr(cssSelector: String ="", init: Elements.() -> Unit) = elements("wbr$cssSelector", init)
