package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.core.DomSelector
import it.skrape.selects.selection

/**
 * Will define a <details>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.details(cssSelector: String = "", init: DomSelector.() -> T) = selection("details$cssSelector", init)

/**
 * Will define a <dialog>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.dialog(cssSelector: String = "", init: DomSelector.() -> T) = selection("dialog$cssSelector", init)

/**
 * Will define a <menu>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.menu(cssSelector: String = "", init: DomSelector.() -> T) = selection("menu$cssSelector", init)

/**
 * Will define a <menuitem>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.menuitem(cssSelector: String = "", init: DomSelector.() -> T) = selection("menuitem$cssSelector", init)

/**
 * Will define a <summary>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.summary(cssSelector: String = "", init: DomSelector.() -> T) = selection("summary$cssSelector", init)
