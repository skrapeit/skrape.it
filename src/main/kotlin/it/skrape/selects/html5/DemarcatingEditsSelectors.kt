package it.skrape.selects.html5

import it.skrape.selects.CssSelector
import it.skrape.selects.DomTreeElement

/**
 * Will define a <del>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.del(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("del$cssSelector", init)

fun <T> CssSelector.del(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector del$cssSelector", init)

/**
 * Will define a <ins>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.ins(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("ins$cssSelector", init)

fun <T> CssSelector.ins(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector ins$cssSelector", init)
