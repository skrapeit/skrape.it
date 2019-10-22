package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.core.Selector
import it.skrape.selects.elements
import it.skrape.selects.selection
import org.jsoup.select.Elements

/**
 * Will define a <a>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.a(cssSelector: String = "", init: Selector.() -> T) = selection("a$cssSelector", init)

/**
 * Will define a <abbr>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.abbr(cssSelector: String = "", init: Selector.() -> T) = selection("abbr$cssSelector", init)

/**
 * Will define a <b>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.b(cssSelector: String = "", init: Selector.() -> T) = selection("b$cssSelector", init)

/**
 * Will define a <bdi>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.bdi(cssSelector: String = "", init: Selector.() -> T) = selection("bdi$cssSelector", init)

/**
 * Will define a <bdo>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.bdo(cssSelector: String = "", init: Selector.() -> T) = selection("bdo$cssSelector", init)

/**
 * Will define a <br>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.br(cssSelector: String = "", init: Selector.() -> T) = selection("br$cssSelector", init)

/**
 * Will define a <cite>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.cite(cssSelector: String = "", init: Selector.() -> T) = selection("cite$cssSelector", init)

/**
 * Will define a <code>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.code(cssSelector: String = "", init: Selector.() -> T) = selection("code$cssSelector", init)

/**
 * Will define a <data>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.data(cssSelector: String = "", init: Selector.() -> T) = selection("data$cssSelector", init)

/**
 * Will define a <dfn>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.dfn(cssSelector: String = "", init: Selector.() -> T) = selection("dfn$cssSelector", init)

/**
 * Will define a <em>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.em(cssSelector: String = "", init: Selector.() -> T) = selection("em$cssSelector", init)

/**
 * Will define a <i>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.i(cssSelector: String = "", init: Selector.() -> T) = selection("i$cssSelector", init)

/**
 * Will define a <kbd>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.kbd(cssSelector: String = "", init: Selector.() -> T) = selection("kbd$cssSelector", init)

/**
 * Will define a <mark>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.mark(cssSelector: String = "", init: Selector.() -> T) = selection("mark$cssSelector", init)

/**
 * Will define a <q>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.q(cssSelector: String = "", init: Selector.() -> T) = selection("q$cssSelector", init)

/**
 * Will define a <rb>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.rb(cssSelector: String = "", init: Selector.() -> T) = selection("rb$cssSelector", init)

/**
 * Will define a <rp>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.rp(cssSelector: String = "", init: Selector.() -> T) = selection("rp$cssSelector", init)

/**
 * Will define a <rt>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.rt(cssSelector: String = "", init: Selector.() -> T) = selection("rt$cssSelector", init)

/**
 * Will define a <rtc>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.rtc(cssSelector: String = "", init: Selector.() -> T) = selection("rtc$cssSelector", init)

/**
 * Will define a <ruby>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.ruby(cssSelector: String = "", init: Selector.() -> T) = selection("ruby$cssSelector", init)

/**
 * Will define a <s>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.s(cssSelector: String = "", init: Selector.() -> T) = selection("s$cssSelector", init)

/**
 * Will define a <samp>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.samp(cssSelector: String = "", init: Selector.() -> T) = selection("samp$cssSelector", init)

/**
 * Will define a <small>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.small(cssSelector: String = "", init: Selector.() -> T) = selection("small$cssSelector", init)

/**
 * Will define a <span>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.span(cssSelector: String = "", init: Selector.() -> T) = selection("span$cssSelector", init)

/**
 * Will define a <strong>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.strong(cssSelector: String = "", init: Selector.() -> T) = selection("strong$cssSelector", init)

/**
 * Will define a <sub>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.sub(cssSelector: String = "", init: Selector.() -> T) = selection("sub$cssSelector", init)

/**
 * Will define a <sup>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.sup(cssSelector: String = "", init: Selector.() -> T) = selection("sup$cssSelector", init)

/**
 * Will define a <time>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.time(cssSelector: String = "", init: Selector.() -> T) = selection("time$cssSelector", init)

/**
 * Will define a <tt>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.tt(cssSelector: String = "", init: Selector.() -> T) = selection("tt$cssSelector", init)

/**
 * Will define a <u>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.u(cssSelector: String = "", init: Selector.() -> T) = selection("u$cssSelector", init)

/**
 * Will define a <var>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.`var`(cssSelector: String = "", init: Selector.() -> T) = selection("var$cssSelector", init)

/**
 * Will define a <wbr>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.wbr(cssSelector: String = "", init: Selector.() -> T) = selection("wbr$cssSelector", init)
