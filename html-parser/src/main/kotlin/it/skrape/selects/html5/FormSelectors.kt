@file:Suppress("TooManyFunctions")

package it.skrape.selects.html5

import it.skrape.selects.CssSelectable
import it.skrape.selects.CssSelector

/**
 * Will define a <button>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.button(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("button$cssSelector", init)

/**
 * Will define a <datalist>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.datalist(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("datalist$cssSelector", init)

/**
 * Will define a <fieldset>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.fieldset(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("fieldset$cssSelector", init)

/**
 * Will define a <form>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.form(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("form$cssSelector", init)

/**
 * Will define a <input>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.input(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("input$cssSelector", init)

/**
 * Will define a <label>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.label(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("label$cssSelector", init)

/**
 * Will define a <legend>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.legend(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("legend$cssSelector", init)

/**
 * Will define a <meter>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.meter(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("meter$cssSelector", init)

/**
 * Will define a <optgroup>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.optgroup(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("optgroup$cssSelector", init)

/**
 * Will define a <option>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.option(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("option$cssSelector", init)

/**
 * Will define a <output>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.output(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("output$cssSelector", init)

/**
 * Will define a <progress>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.progress(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("progress$cssSelector", init)

/**
 * Will define a <select>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.select(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("select$cssSelector", init)

/**
 * Will define a <textarea>-tags css query selector.
 * By default, it will just be the specific tag-name.
 * It is possible to define a more concrete selector by using the provided fields of the CssSelector object or
 * by passing a raw css query selector as parameter.
 * If a selector is passed as parameter as well as be defined via CssSelector fields, they will be merged.
 * @see CssSelector
 * @param cssSelector
 * @return T
 */
public fun <T> CssSelectable.textarea(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("textarea$cssSelector", init)
