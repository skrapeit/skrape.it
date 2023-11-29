@file:Suppress("TooManyFunctions")

package it.skrape.selects.html5

import it.skrape.selects.CssSelectable
import it.skrape.selects.CssSelector

/**
 * Will define a <blockquote>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.blockquote(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("blockquote$cssSelector", init)

/**
 * Will define a <dd>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.dd(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("dd$cssSelector", init)

/**
 * Will define a <dir>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.dir(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("dir$cssSelector", init)

/**
 * Will define a <dl>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.dl(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("dl$cssSelector", init)

/**
 * Will define a <dt>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.dt(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("dt$cssSelector", init)

/**
 * Will define a <figcaption>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.figcaption(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("figcaption$cssSelector", init)

/**
 * Will define a <figure>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.figure(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("figure$cssSelector", init)

/**
 * Will define a <hr>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.hr(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("hr$cssSelector", init)

/**
 * Will define a <li>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.li(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("li$cssSelector", init)

/**
 * Will define a <ol>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.ol(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("ol$cssSelector", init)

/**
 * Will define a <ul>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.ul(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("ul$cssSelector", init)

/**
 * Will define a <p>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.p(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("p$cssSelector", init)

/**
 * Will define a <pre>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.pre(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("pre$cssSelector", init)
