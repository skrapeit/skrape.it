@file:Suppress("TooManyFunctions")

package it.skrape.selects.html5

import it.skrape.selects.CssSelector
import it.skrape.selects.DomTreeElement

/**
 * Will define a <a>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.a(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("a$cssSelector", init)

fun <T> CssSelector.a(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector a$cssSelector", init)

/**
 * Will define a <abbr>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.abbr(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("abbr$cssSelector", init)

fun <T> CssSelector.abbr(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector abbr$cssSelector", init)

/**
 * Will define a <b>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.b(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("b$cssSelector", init)

fun <T> CssSelector.b(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector b$cssSelector", init)

/**
 * Will define a <bdi>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.bdi(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("bdi$cssSelector", init)

fun <T> CssSelector.bdi(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector bdi$cssSelector", init)

/**
 * Will define a <bdo>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.bdo(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("bdo$cssSelector", init)

fun <T> CssSelector.bdo(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector bdo$cssSelector", init)

/**
 * Will define a <br>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.br(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("br$cssSelector", init)

fun <T> CssSelector.br(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector br$cssSelector", init)

/**
 * Will define a <cite>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.cite(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("cite$cssSelector", init)

fun <T> CssSelector.cite(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector cite$cssSelector", init)

/**
 * Will define a <code>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.code(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("code$cssSelector", init)

fun <T> CssSelector.code(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector code$cssSelector", init)

/**
 * Will define a <data>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.data(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("data$cssSelector", init)

fun <T> CssSelector.data(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector data$cssSelector", init)

/**
 * Will define a <dfn>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.dfn(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("dfn$cssSelector", init)

fun <T> CssSelector.dfn(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector dfn$cssSelector", init)

/**
 * Will define a <em>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.em(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("em$cssSelector", init)

fun <T> CssSelector.em(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector em$cssSelector", init)

/**
 * Will define a <i>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.i(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("i$cssSelector", init)

fun <T> CssSelector.i(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector i$cssSelector", init)

/**
 * Will define a <kbd>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.kbd(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("kbd$cssSelector", init)

fun <T> CssSelector.kbd(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector kbd$cssSelector", init)

/**
 * Will define a <mark>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.mark(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("mark$cssSelector", init)

fun <T> CssSelector.mark(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector mark$cssSelector", init)

/**
 * Will define a <q>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.q(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("q$cssSelector", init)

fun <T> CssSelector.q(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector q$cssSelector", init)

/**
 * Will define a <rb>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.rb(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("rb$cssSelector", init)

fun <T> CssSelector.rb(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector rb$cssSelector", init)

/**
 * Will define a <rtc>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.rtc(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("rtc$cssSelector", init)

fun <T> CssSelector.rtc(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector rtc$cssSelector", init)

/**
 * Will define a <ruby>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.ruby(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("ruby$cssSelector", init)

fun <T> CssSelector.ruby(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector ruby$cssSelector", init)

/**
 * Will define a <s>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.s(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("s$cssSelector", init)

fun <T> CssSelector.s(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector s$cssSelector", init)

/**
 * Will define a <samp>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.samp(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("samp$cssSelector", init)

fun <T> CssSelector.samp(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector samp$cssSelector", init)

/**
 * Will define a <small>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.small(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("small$cssSelector", init)

fun <T> CssSelector.small(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector small$cssSelector", init)

/**
 * Will define a <span>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.span(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("span$cssSelector", init)

fun <T> CssSelector.span(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector span$cssSelector", init)

/**
 * Will define a <strong>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.strong(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("strong$cssSelector", init)

fun <T> CssSelector.strong(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector strong$cssSelector", init)

/**
 * Will define a <sub>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.sub(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("sub$cssSelector", init)

fun <T> CssSelector.sub(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector sub$cssSelector", init)

/**
 * Will define a <sup>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.sup(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("sup$cssSelector", init)

fun <T> CssSelector.sup(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector sup$cssSelector", init)

/**
 * Will define a <time>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.time(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("time$cssSelector", init)

fun <T> CssSelector.time(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector time$cssSelector", init)

/**
 * Will define a <tt>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.tt(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("tt$cssSelector", init)

fun <T> CssSelector.tt(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector tt$cssSelector", init)

/**
 * Will define a <u>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.u(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("u$cssSelector", init)

fun <T> CssSelector.u(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector u$cssSelector", init)

/**
 * Will define a <var>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.`var`(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("var$cssSelector", init)

fun <T> CssSelector.`var`(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector var$cssSelector", init)

/**
 * Will define a <wbr>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.wbr(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("wbr$cssSelector", init)

fun <T> CssSelector.wbr(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector wbr$cssSelector", init)
