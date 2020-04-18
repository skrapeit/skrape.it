package it.skrape.selects.html5

import it.skrape.selects.CssSelector
import it.skrape.selects.Doc
import it.skrape.selects.DomTreeElement

/**
 * Will define a <caption>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> Doc.caption(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("caption$cssSelector", init)

/**
 * Will define a <col>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.col(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("col$cssSelector", init)

fun <T> CssSelector.col(cssSelector: String = "", init: CssSelector.() -> T) =
        CssSelector("$toCssSelector col$cssSelector", doc = this.doc).init()

/**
 * Will define a <colgroup>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.colgroup(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("colgroup$cssSelector", init)

fun <T> CssSelector.colgroup(cssSelector: String = "", init: CssSelector.() -> T) =
        CssSelector("$toCssSelector colgroup$cssSelector", doc = this.doc).init()

/**
 * Will define a <table>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.table(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("table$cssSelector", init)

fun <T> CssSelector.table(cssSelector: String = "", init: CssSelector.() -> T) =
        CssSelector("$toCssSelector table$cssSelector", doc = this.doc).init()

/**
 * Will define a <tbody>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.tbody(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("tbody$cssSelector", init)

fun <T> CssSelector.tbody(cssSelector: String = "", init: CssSelector.() -> T) =
        CssSelector("$toCssSelector tbody$cssSelector", doc = this.doc).init()

/**
 * Will define a <td>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.td(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("td$cssSelector", init)

fun <T> CssSelector.td(cssSelector: String = "", init: CssSelector.() -> T) =
        CssSelector("$toCssSelector td$cssSelector", doc = this.doc).init()

/**
 * Will define a <tfoot>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.tfoot(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("tfoot$cssSelector", init)

fun <T> CssSelector.tfoot(cssSelector: String = "", init: CssSelector.() -> T) =
        CssSelector("$toCssSelector tfoot$cssSelector", doc = this.doc).init()

/**
 * Will define a <th>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.th(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("th$cssSelector", init)

fun <T> CssSelector.th(cssSelector: String = "", init: CssSelector.() -> T) =
        CssSelector("$toCssSelector th$cssSelector", doc = this.doc).init()

/**
 * Will define a <thead>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.thead(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("thead$cssSelector", init)

fun <T> CssSelector.thead(cssSelector: String = "", init: CssSelector.() -> T) =
        CssSelector("$toCssSelector thead$cssSelector", doc = this.doc).init()

/**
 * Will define a <tr>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.tr(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("tr$cssSelector", init)

fun <T> CssSelector.tr(cssSelector: String = "", init: CssSelector.() -> T) =
        CssSelector("$toCssSelector tr$cssSelector", doc = this.doc).init()
