package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.core.Selector
import it.skrape.selects.selection

/**
 * Will define a <area>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.area(cssSelector: String = "", init: Selector.() -> T) = selection("area$cssSelector", init)

/**
 * Will define a <audio>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.audio(cssSelector: String = "", init: Selector.() -> T) = selection("audio$cssSelector", init)

/**
 * Will define a <img>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.img(cssSelector: String = "", init: Selector.() -> T) = selection("img$cssSelector", init)

/**
 * Will define a <map>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.map(cssSelector: String = "", init: Selector.() -> T) = selection("map$cssSelector", init)

/**
 * Will define a <track>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.track(cssSelector: String = "", init: Selector.() -> T) = selection("track$cssSelector", init)

/**
 * Will define a <video>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the Selector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via Selector fields, they will be merged.
 * @see it.skrape.core.Selector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.video(cssSelector: String = "", init: Selector.() -> T) = selection("video$cssSelector", init)
