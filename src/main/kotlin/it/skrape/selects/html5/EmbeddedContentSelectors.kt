package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.core.DomSelector
import it.skrape.selects.selection

/**
 * Will define a <applet>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.applet(cssSelector: String = "", init: DomSelector.() -> T) = selection("applet$cssSelector", init)

/**
 * Will define a <embed>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.embed(cssSelector: String = "", init: DomSelector.() -> T) = selection("embed$cssSelector", init)

/**
 * Will define a <iframe>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.iframe(cssSelector: String = "", init: DomSelector.() -> T) = selection("iframe$cssSelector", init)

/**
 * Will define a <noembed>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.noembed(cssSelector: String = "", init: DomSelector.() -> T) = selection("noembed$cssSelector", init)

/**
 * Will define a <object>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.`object`(cssSelector: String = "", init: DomSelector.() -> T) = selection("object$cssSelector", init)

/**
 * Will define a <param>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.param(cssSelector: String = "", init: DomSelector.() -> T) = selection("param$cssSelector", init)

/**
 * Will define a <picture>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.picture(cssSelector: String = "", init: DomSelector.() -> T) = selection("picture$cssSelector", init)

/**
 * Will define a <source>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.source(cssSelector: String = "", init: DomSelector.() -> T) = selection("source$cssSelector", init)
