@file:Suppress("TooManyFunctions")

package it.skrape.selects.html5

import it.skrape.selects.CssSelector
import it.skrape.selects.Doc
import it.skrape.selects.DocElement

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
fun <T> Doc.a(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("a$cssSelector", init)

fun <T> CssSelector.a(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector a$cssSelector", init)

fun <T> DocElement.a(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("a$cssSelector", init)

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
fun <T> Doc.abbr(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("abbr$cssSelector", init)

fun <T> CssSelector.abbr(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector abbr$cssSelector", init)

fun <T> DocElement.abbr(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("abbr$cssSelector", init)

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
fun <T> Doc.b(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("b$cssSelector", init)

fun <T> CssSelector.b(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector b$cssSelector", init)

fun <T> DocElement.b(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("b$cssSelector", init)

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
fun <T> Doc.bdi(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("bdi$cssSelector", init)

fun <T> CssSelector.bdi(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector bdi$cssSelector", init)

fun <T> DocElement.bdi(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("bdi$cssSelector", init)

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
fun <T> Doc.bdo(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("bdo$cssSelector", init)

fun <T> CssSelector.bdo(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector bdo$cssSelector", init)

fun <T> DocElement.bdo(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("bdo$cssSelector", init)

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
fun <T> Doc.br(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("br$cssSelector", init)

fun <T> CssSelector.br(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector br$cssSelector", init)

fun <T> DocElement.br(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("br$cssSelector", init)

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
fun <T> Doc.cite(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("cite$cssSelector", init)

fun <T> CssSelector.cite(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector cite$cssSelector", init)

fun <T> DocElement.cite(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("cite$cssSelector", init)

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
fun <T> Doc.code(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("code$cssSelector", init)

fun <T> CssSelector.code(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector code$cssSelector", init)

fun <T> DocElement.code(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("code$cssSelector", init)

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
fun <T> Doc.data(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("data$cssSelector", init)

fun <T> CssSelector.data(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector data$cssSelector", init)

fun <T> DocElement.data(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("data$cssSelector", init)

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
fun <T> Doc.dfn(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("dfn$cssSelector", init)

fun <T> CssSelector.dfn(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector dfn$cssSelector", init)

fun <T> DocElement.dfn(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("dfn$cssSelector", init)

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
fun <T> Doc.em(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("em$cssSelector", init)

fun <T> CssSelector.em(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector em$cssSelector", init)

fun <T> DocElement.em(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("em$cssSelector", init)

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
fun <T> Doc.i(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("i$cssSelector", init)

fun <T> CssSelector.i(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector i$cssSelector", init)

fun <T> DocElement.i(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("i$cssSelector", init)

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
fun <T> Doc.kbd(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("kbd$cssSelector", init)

fun <T> CssSelector.kbd(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector kbd$cssSelector", init)

fun <T> DocElement.kbd(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("kbd$cssSelector", init)

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
fun <T> Doc.mark(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("mark$cssSelector", init)

fun <T> CssSelector.mark(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector mark$cssSelector", init)

fun <T> DocElement.mark(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("mark$cssSelector", init)

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
fun <T> Doc.q(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("q$cssSelector", init)

fun <T> CssSelector.q(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector q$cssSelector", init)

fun <T> DocElement.q(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("q$cssSelector", init)

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
fun <T> Doc.rb(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("rb$cssSelector", init)

fun <T> CssSelector.rb(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector rb$cssSelector", init)

fun <T> DocElement.rb(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("rb$cssSelector", init)

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
fun <T> Doc.rtc(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("rtc$cssSelector", init)

fun <T> CssSelector.rtc(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector rtc$cssSelector", init)

fun <T> DocElement.rtc(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("rtc$cssSelector", init)

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
fun <T> Doc.ruby(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("ruby$cssSelector", init)

fun <T> CssSelector.ruby(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector ruby$cssSelector", init)

fun <T> DocElement.ruby(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("ruby$cssSelector", init)

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
fun <T> Doc.s(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("s$cssSelector", init)

fun <T> CssSelector.s(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector s$cssSelector", init)

fun <T> DocElement.s(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("s$cssSelector", init)

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
fun <T> Doc.samp(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("samp$cssSelector", init)

fun <T> CssSelector.samp(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector samp$cssSelector", init)

fun <T> DocElement.samp(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("samp$cssSelector", init)

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
fun <T> Doc.small(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("small$cssSelector", init)

fun <T> CssSelector.small(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector small$cssSelector", init)

fun <T> DocElement.small(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("small$cssSelector", init)

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
fun <T> Doc.span(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("span$cssSelector", init)

fun <T> CssSelector.span(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector span$cssSelector", init)

fun <T> DocElement.span(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("span$cssSelector", init)

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
fun <T> Doc.strong(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("strong$cssSelector", init)

fun <T> CssSelector.strong(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector strong$cssSelector", init)

fun <T> DocElement.strong(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("strong$cssSelector", init)

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
fun <T> Doc.sub(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("sub$cssSelector", init)

fun <T> CssSelector.sub(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector sub$cssSelector", init)

fun <T> DocElement.sub(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("sub$cssSelector", init)

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
fun <T> Doc.sup(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("sup$cssSelector", init)

fun <T> CssSelector.sup(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector sup$cssSelector", init)

fun <T> DocElement.sup(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("sup$cssSelector", init)

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
fun <T> Doc.time(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("time$cssSelector", init)

fun <T> CssSelector.time(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector time$cssSelector", init)

fun <T> DocElement.time(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("time$cssSelector", init)

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
fun <T> Doc.tt(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("tt$cssSelector", init)

fun <T> CssSelector.tt(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector tt$cssSelector", init)

fun <T> DocElement.tt(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("tt$cssSelector", init)

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
fun <T> Doc.u(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("u$cssSelector", init)

fun <T> CssSelector.u(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector u$cssSelector", init)

fun <T> DocElement.u(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("u$cssSelector", init)

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
fun <T> Doc.`var`(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("var$cssSelector", init)

fun <T> CssSelector.`var`(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector var$cssSelector", init)

fun <T> DocElement.`var`(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("var$cssSelector", init)

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
fun <T> Doc.wbr(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("wbr$cssSelector", init)

fun <T> CssSelector.wbr(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector wbr$cssSelector", init)

fun <T> DocElement.wbr(cssSelector: String = "", init: CssSelector.() -> T) =
        toDoc.selection("wbr$cssSelector", init)
