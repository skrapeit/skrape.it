package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.selects.elements
import org.jsoup.select.Elements

/**
 * Will pick all occurrences of <button> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.button(cssSelector: String = "", init: Elements.() -> T) = elements("button$cssSelector", init)

/**
 * Will pick all occurrences of <datalist> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.datalist(cssSelector: String = "", init: Elements.() -> T) = elements("datalist$cssSelector", init)

/**
 * Will pick all occurrences of <fieldset> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.fieldset(cssSelector: String = "", init: Elements.() -> T) = elements("fieldset$cssSelector", init)

/**
 * Will pick all occurrences of <form> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.form(cssSelector: String = "", init: Elements.() -> T) = elements("form$cssSelector", init)

/**
 * Will pick all occurrences of <input> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.input(cssSelector: String = "", init: Elements.() -> T) = elements("input$cssSelector", init)

/**
 * Will pick all occurrences of <label> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.label(cssSelector: String = "", init: Elements.() -> T) = elements("label$cssSelector", init)

/**
 * Will pick all occurrences of <legend> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.legend(cssSelector: String = "", init: Elements.() -> T) = elements("legend$cssSelector", init)

/**
 * Will pick all occurrences of <meter> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.meter(cssSelector: String = "", init: Elements.() -> T) = elements("meter$cssSelector", init)

/**
 * Will pick all occurrences of <optgroup> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.optgroup(cssSelector: String = "", init: Elements.() -> T) = elements("optgroup$cssSelector", init)

/**
 * Will pick all occurrences of <option> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.option(cssSelector: String = "", init: Elements.() -> T) = elements("option$cssSelector", init)

/**
 * Will pick all occurrences of <output> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.output(cssSelector: String = "", init: Elements.() -> T) = elements("output$cssSelector", init)

/**
 * Will pick all occurrences of <progress> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.progress(cssSelector: String = "", init: Elements.() -> T) = elements("progress$cssSelector", init)

/**
 * Will pick all occurrences of <select> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.select(cssSelector: String = "", init: Elements.() -> T) = elements("select$cssSelector", init)

/**
 * Will pick all occurrences of <textarea> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.textarea(cssSelector: String = "", init: Elements.() -> T) = elements("textarea$cssSelector", init)
