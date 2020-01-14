package it.skrape.selects.html5

import it.skrape.selects.CssSelector
import it.skrape.selects.Doc

/**
 * Will define a <area>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> Doc.area(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("area$cssSelector", init)

/**
 * Will define a <audio>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> Doc.audio(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("audio$cssSelector", init)

/**
 * Will define a <img>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> Doc.img(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("img$cssSelector", init)

/**
 * Will define a <map>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> Doc.map(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("map$cssSelector", init)

/**
 * Will define a <track>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> Doc.track(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("track$cssSelector", init)

/**
 * Will define a <video>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> Doc.video(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("video$cssSelector", init)
