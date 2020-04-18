@file:Suppress("TooManyFunctions")

package it.skrape.selects.html5

import it.skrape.selects.CssSelector
import it.skrape.selects.Doc
import it.skrape.selects.DomTreeElement

/**
 * Will define a <body>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> Doc.body(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("body$cssSelector", init)

/**
 * Will define a <div>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.div(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("div$cssSelector", init)

fun <T> CssSelector.div(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector div$cssSelector", init)

/**
 * Will define a <section>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.section(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("section$cssSelector", init)

fun <T> CssSelector.section(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector section$cssSelector", init)

/**
 * Will define a <nav>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.nav(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("nav$cssSelector", init)

fun <T> CssSelector.nav(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector nav$cssSelector", init)

/**
 * Will define a <article>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.article(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("article$cssSelector", init)

fun <T> CssSelector.article(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector article$cssSelector", init)

/**
 * Will define a <aside>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.aside(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("aside$cssSelector", init)

fun <T> CssSelector.aside(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector aside$cssSelector", init)

/**
 * Will define a <h1>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.h1(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("h1$cssSelector", init)

fun <T> CssSelector.h1(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector h1$cssSelector", init)

/**
 * Will define a <h2>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.h2(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("h2$cssSelector", init)

fun <T> CssSelector.h2(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector h2$cssSelector", init)

/**
 * Will define a <h3>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.h3(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("h3$cssSelector", init)

fun <T> CssSelector.h3(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector h3$cssSelector", init)

/**
 * Will define a <h4>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.h4(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("h4$cssSelector", init)

fun <T> CssSelector.h4(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector h4$cssSelector", init)

/**
 * Will define a <h5>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.h5(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("h5$cssSelector", init)

fun <T> CssSelector.h5(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector h5$cssSelector", init)

/**
 * Will define a <h6>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.h6(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("h6$cssSelector", init)

fun <T> CssSelector.h6(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector h6$cssSelector", init)

/**
 * Will define a <header>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.header(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("header$cssSelector", init)

fun <T> CssSelector.header(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector header$cssSelector", init)

/**
 * Will define a <footer>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.footer(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("footer$cssSelector", init)

fun <T> CssSelector.footer(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector footer$cssSelector", init)

/**
 * Will define a <address>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.address(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("address$cssSelector", init)

fun <T> CssSelector.address(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector address$cssSelector", init)

/**
 * Will define a <main>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
fun <T> DomTreeElement.main(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("main$cssSelector", init)

fun <T> CssSelector.main(cssSelector: String = "", init: CssSelector.() -> T) =
        doc.selection("$toCssSelector main$cssSelector", init)
