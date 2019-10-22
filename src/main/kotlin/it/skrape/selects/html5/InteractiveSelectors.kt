package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.core.Selector
import it.skrape.selects.selection

/**
 * Will define a <details>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.details(cssSelector: String = "", init: Selector.() -> T) = selection("details$cssSelector", init)

/**
 * Will define a <dialog>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.dialog(cssSelector: String = "", init: Selector.() -> T) = selection("dialog$cssSelector", init)

/**
 * Will define a <menu>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.menu(cssSelector: String = "", init: Selector.() -> T) = selection("menu$cssSelector", init)

/**
 * Will define a <menuitem>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.menuitem(cssSelector: String = "", init: Selector.() -> T) = selection("menuitem$cssSelector", init)

/**
 * Will define a <summary>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.summary(cssSelector: String = "", init: Selector.() -> T) = selection("summary$cssSelector", init)
