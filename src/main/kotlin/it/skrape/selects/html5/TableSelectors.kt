package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.core.DomSelector
import it.skrape.selects.selection

/**
 * Will define a <caption>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.caption(cssSelector: String = "", init: DomSelector.() -> T) =
        selection("caption$cssSelector", init)

/**
 * Will define a <col>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.col(cssSelector: String = "", init: DomSelector.() -> T) =
        selection("col$cssSelector", init)

/**
 * Will define a <colgroup>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.colgroup(cssSelector: String = "", init: DomSelector.() -> T) =
        selection("colgroup$cssSelector", init)

/**
 * Will define a <table>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.table(cssSelector: String = "", init: DomSelector.() -> T) =
        selection("table$cssSelector", init)

/**
 * Will define a <tbody>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.tbody(cssSelector: String = "", init: DomSelector.() -> T) =
        selection("tbody$cssSelector", init)

/**
 * Will define a <td>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.td(cssSelector: String = "", init: DomSelector.() -> T) =
        selection("td$cssSelector", init)

/**
 * Will define a <tfoot>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.tfoot(cssSelector: String = "", init: DomSelector.() -> T) =
        selection("tfoot$cssSelector", init)

/**
 * Will define a <th>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.th(cssSelector: String = "", init: DomSelector.() -> T) =
        selection("th$cssSelector", init)

/**
 * Will define a <thead>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.thead(cssSelector: String = "", init: DomSelector.() -> T) =
        selection("thead$cssSelector", init)

/**
 * Will define a <tr>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the DomSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via DomSelector fields, they will be merged.
 * @see it.skrape.core.DomSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.tr(cssSelector: String = "", init: DomSelector.() -> T) =
        selection("tr$cssSelector", init)
