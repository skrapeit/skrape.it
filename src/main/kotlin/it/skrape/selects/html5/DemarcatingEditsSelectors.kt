package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.core.Selector
import it.skrape.selects.selection

/**
 * Will define a <del>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.del(cssSelector: String = "", init: Selector.() -> T) = selection("del$cssSelector", init)

/**
 * Will define a <ins>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.ins(cssSelector: String = "", init: Selector.() -> T) = selection("ins$cssSelector", init)
