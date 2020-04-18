package it.skrape.selects.html5

import it.skrape.selects.CssSelector
import it.skrape.selects.DomTreeElement

/**
 * Will define a <applet>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.applet(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("applet$cssSelector", init)

fun <T> CssSelector.applet(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector applet$cssSelector", init)

/**
 * Will define a <embed>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.embed(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("embed$cssSelector", init)

fun <T> CssSelector.embed(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector embed$cssSelector", init)

/**
 * Will define a <iframe>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.iframe(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("iframe$cssSelector", init)

fun <T> CssSelector.iframe(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector iframe$cssSelector", init)

/**
 * Will define a <noembed>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.noembed(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("noembed$cssSelector", init)

fun <T> CssSelector.noembed(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector noembed$cssSelector", init)

/**
 * Will define a <object>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.`object`(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("object$cssSelector", init)

fun <T> CssSelector.`object`(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector object$cssSelector", init)

/**
 * Will define a <param>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.param(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("param$cssSelector", init)

fun <T> CssSelector.param(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector param$cssSelector", init)

/**
 * Will define a <picture>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.picture(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("picture$cssSelector", init)

fun <T> CssSelector.picture(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector picture$cssSelector", init)

/**
 * Will define a <source>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.source(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("source$cssSelector", init)

fun <T> CssSelector.source(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector source$cssSelector", init)
