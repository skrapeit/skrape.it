package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.core.DomSelector
import it.skrape.selects.selection

/**
 * Will define a <blockquote>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.blockquote(cssSelector: String = "", init: DomSelector.() -> T) =
        selection("blockquote$cssSelector", init)

/**
 * Will define a <dd>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.dd(cssSelector: String = "", init: DomSelector.() -> T) =
        selection("dd$cssSelector", init)

/**
 * Will define a <dir>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.dir(cssSelector: String = "", init: DomSelector.() -> T) =
        selection("dir$cssSelector", init)

/**
 * Will define a <dl>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.dl(cssSelector: String = "", init: DomSelector.() -> T) =
        selection("dl$cssSelector", init)

/**
 * Will define a <dt>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.dt(cssSelector: String = "", init: DomSelector.() -> T) =
        selection("dt$cssSelector", init)

/**
 * Will define a <figcaption>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.figcaption(cssSelector: String = "", init: DomSelector.() -> T) =
        selection("figcaption$cssSelector", init)

/**
 * Will define a <figure>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.figure(cssSelector: String = "", init: DomSelector.() -> T) =
        selection("figure$cssSelector", init)

/**
 * Will define a <hr>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.hr(cssSelector: String = "", init: DomSelector.() -> T) =
        selection("hr$cssSelector", init)

/**
 * Will define a <li>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.li(cssSelector: String = "", init: DomSelector.() -> T) =
        selection("li$cssSelector", init)

/**
 * Will define a <ol>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.ol(cssSelector: String = "", init: DomSelector.() -> T) =
        selection("ol$cssSelector", init)

/**
 * Will define a <ul>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.ul(cssSelector: String = "", init: DomSelector.() -> T) =
        selection("ul$cssSelector", init)

/**
 * Will define a <p>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.p(cssSelector: String = "", init: DomSelector.() -> T) =
        selection("p$cssSelector", init)

/**
 * Will define a <pre>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.pre(cssSelector: String = "", init: DomSelector.() -> T) =
        selection("pre$cssSelector", init)
