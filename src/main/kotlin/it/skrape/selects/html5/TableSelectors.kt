package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.core.CssSelector
import it.skrape.selects.selection

/**
 * Will define a <caption>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.caption(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("caption$cssSelector", init)

/**
 * Will define a <col>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.col(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("col$cssSelector", init)

/**
 * Will define a <colgroup>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.colgroup(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("colgroup$cssSelector", init)

/**
 * Will define a <table>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.table(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("table$cssSelector", init)

/**
 * Will define a <tbody>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.tbody(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("tbody$cssSelector", init)

/**
 * Will define a <td>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.td(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("td$cssSelector", init)

/**
 * Will define a <tfoot>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.tfoot(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("tfoot$cssSelector", init)

/**
 * Will define a <th>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.th(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("th$cssSelector", init)

/**
 * Will define a <thead>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.thead(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("thead$cssSelector", init)

/**
 * Will define a <tr>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.tr(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("tr$cssSelector", init)
