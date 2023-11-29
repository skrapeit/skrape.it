@file:Suppress("TooManyFunctions")

package it.skrape.selects.html5

import it.skrape.selects.CssSelectable
import it.skrape.selects.CssSelector

/**
 * Will define a <a>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.a(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("a$cssSelector", init)

/**
 * Will define a <abbr>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.abbr(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("abbr$cssSelector", init)

/**
 * Will define a <b>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.b(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("b$cssSelector", init)

/**
 * Will define a <bdi>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.bdi(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("bdi$cssSelector", init)

/**
 * Will define a <bdo>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.bdo(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("bdo$cssSelector", init)

/**
 * Will define a <br>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.br(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("br$cssSelector", init)

/**
 * Will define a <cite>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.cite(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("cite$cssSelector", init)

/**
 * Will define a <code>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.code(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("code$cssSelector", init)

/**
 * Will define a <data>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.data(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("data$cssSelector", init)

/**
 * Will define a <dfn>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.dfn(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("dfn$cssSelector", init)

/**
 * Will define a <em>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.em(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("em$cssSelector", init)

/**
 * Will define a <i>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.i(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("i$cssSelector", init)

/**
 * Will define a <kbd>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.kbd(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("kbd$cssSelector", init)

/**
 * Will define a <mark>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.mark(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("mark$cssSelector", init)

/**
 * Will define a <q>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.q(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("q$cssSelector", init)

/**
 * Will define a <rb>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.rb(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("rb$cssSelector", init)

/**
 * Will define a <rtc>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.rtc(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("rtc$cssSelector", init)

/**
 * Will define a <ruby>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.ruby(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("ruby$cssSelector", init)

/**
 * Will define a <s>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.s(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("s$cssSelector", init)

/**
 * Will define a <samp>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.samp(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("samp$cssSelector", init)

/**
 * Will define a <small>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.small(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("small$cssSelector", init)

/**
 * Will define a <span>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.span(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("span$cssSelector", init)

/**
 * Will define a <strong>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.strong(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("strong$cssSelector", init)

/**
 * Will define a <sub>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.sub(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("sub$cssSelector", init)

/**
 * Will define a <sup>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.sup(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("sup$cssSelector", init)

/**
 * Will define a <time>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.time(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("time$cssSelector", init)

/**
 * Will define a <tt>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.tt(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("tt$cssSelector", init)

/**
 * Will define a <u>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.u(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("u$cssSelector", init)

/**
 * Will define a <var>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
@Suppress("FunctionNaming")
public fun <T> CssSelectable.`var`(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("var$cssSelector", init)

/**
 * Will define a <wbr>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.wbr(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("wbr$cssSelector", init)
