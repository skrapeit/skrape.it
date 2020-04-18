package it.skrape.selects.html5

import it.skrape.selects.CssSelectable
import it.skrape.selects.CssSelector

/**
 * Will define a <base>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> CssSelectable.base(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("base$cssSelector", init)

/**
 * Will define a <head>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> CssSelectable.head(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("head$cssSelector", init)

/**
 * Will define a <link>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> CssSelectable.link(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("link$cssSelector", init)

/**
 * Will define a <meta>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> CssSelectable.meta(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("meta$cssSelector", init)

/**
 * Will define a <style>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> CssSelectable.style(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("style$cssSelector", init)

/**
 * Will define a <title>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> CssSelectable.title(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("title$cssSelector", init)
