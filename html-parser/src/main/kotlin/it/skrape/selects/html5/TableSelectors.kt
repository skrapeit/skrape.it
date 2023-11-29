package it.skrape.selects.html5

import it.skrape.selects.CssSelectable
import it.skrape.selects.CssSelector

/**
 * Will define a <caption>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.caption(cssSelector: String = "", init: CssSelector.() -> T): T =
        selection("caption$cssSelector", init)

/**
 * Will define a <col>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.col(cssSelector: String = "", init: CssSelector.() -> T): T =
        selection("col$cssSelector", init)

/**
 * Will define a <colgroup>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.colgroup(cssSelector: String = "", init: CssSelector.() -> T): T =
        selection("colgroup$cssSelector", init)

/**
 * Will define a <table>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.table(cssSelector: String = "", init: CssSelector.() -> T): T =
        selection("table$cssSelector", init)

/**
 * Will define a <tbody>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.tbody(cssSelector: String = "", init: CssSelector.() -> T): T =
        selection("tbody$cssSelector", init)

/**
 * Will define a <td>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.td(cssSelector: String = "", init: CssSelector.() -> T): T =
        selection("td$cssSelector", init)

/**
 * Will define a <tfoot>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.tfoot(cssSelector: String = "", init: CssSelector.() -> T): T =
        selection("tfoot$cssSelector", init)

/**
 * Will define a <th>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.th(cssSelector: String = "", init: CssSelector.() -> T): T =
        selection("th$cssSelector", init)

/**
 * Will define a <thead>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.thead(cssSelector: String = "", init: CssSelector.() -> T): T =
        selection("thead$cssSelector", init)

/**
 * Will define a <tr>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.tr(cssSelector: String = "", init: CssSelector.() -> T): T =
        selection("tr$cssSelector", init)
