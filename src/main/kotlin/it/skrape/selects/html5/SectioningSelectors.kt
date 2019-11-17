package it.skrape.selects.html5

import it.skrape.SkrapeItElementPicker
import it.skrape.core.CssSelector
import it.skrape.core.Doc

/**
 * Will define a <body>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.body(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("body$cssSelector", init)

/**
 * Will define a <div>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.div(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("div$cssSelector", init)

/**
 * Will define a <section>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.section(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("section$cssSelector", init)

/**
 * Will define a <nav>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.nav(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("nav$cssSelector", init)

/**
 * Will define a <article>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.article(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("article$cssSelector", init)

/**
 * Will define a <aside>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.aside(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("aside$cssSelector", init)

/**
 * Will define a <h1>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.h1(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("h1$cssSelector", init)

/**
 * Will define a <h2>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.h2(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("h2$cssSelector", init)

/**
 * Will define a <h3>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.h3(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("h3$cssSelector", init)

/**
 * Will define a <h4>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.h4(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("h4$cssSelector", init)

/**
 * Will define a <h5>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.h5(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("h5$cssSelector", init)

/**
 * Will define a <h6>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.h6(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("h6$cssSelector", init)

/**
 * Will define a <header>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.header(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("header$cssSelector", init)

/**
 * Will define a <footer>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.footer(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("footer$cssSelector", init)

/**
 * Will define a <address>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.address(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("address$cssSelector", init)

/**
 * Will define a <main>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.main(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("main$cssSelector", init)
