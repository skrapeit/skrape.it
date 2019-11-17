package it.skrape.selects.html5

import it.skrape.SkrapeItElementPicker
import it.skrape.selects.CssSelector
import it.skrape.selects.Doc

/**
 * Will define a <details>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.details(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("details$cssSelector", init)

/**
 * Will define a <dialog>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.dialog(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("dialog$cssSelector", init)

/**
 * Will define a <menu>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.menu(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("menu$cssSelector", init)

/**
 * Will define a <menuitem>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.menuitem(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("menuitem$cssSelector", init)

/**
 * Will define a <summary>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.summary(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("summary$cssSelector", init)
