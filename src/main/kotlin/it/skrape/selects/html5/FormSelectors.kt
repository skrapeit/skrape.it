package it.skrape.selects.html5

import it.skrape.SkrapeItElementPicker
import it.skrape.core.CssSelector
import it.skrape.core.Doc

/**
 * Will define a <button>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.button(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("button$cssSelector", init)

/**
 * Will define a <datalist>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.datalist(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("datalist$cssSelector", init)

/**
 * Will define a <fieldset>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.fieldset(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("fieldset$cssSelector", init)

/**
 * Will define a <form>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.form(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("form$cssSelector", init)

/**
 * Will define a <input>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.input(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("input$cssSelector", init)

/**
 * Will define a <label>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.label(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("label$cssSelector", init)

/**
 * Will define a <legend>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.legend(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("legend$cssSelector", init)

/**
 * Will define a <meter>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.meter(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("meter$cssSelector", init)

/**
 * Will define a <optgroup>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.optgroup(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("optgroup$cssSelector", init)

/**
 * Will define a <option>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.option(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("option$cssSelector", init)

/**
 * Will define a <output>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.output(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("output$cssSelector", init)

/**
 * Will define a <progress>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.progress(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("progress$cssSelector", init)

/**
 * Will define a <select>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.select(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("select$cssSelector", init)

/**
 * Will define a <textarea>-tags css query selector.
 * By default it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see it.skrape.core.CssSelector
 * @param cssSelector
 * @return T
 */
@SkrapeItElementPicker
fun <T> Doc.textarea(cssSelector: String = "", init: CssSelector.() -> T) =
        selection("textarea$cssSelector", init)
