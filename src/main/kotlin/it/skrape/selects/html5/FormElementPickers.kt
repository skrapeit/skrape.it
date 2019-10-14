package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.selects.elements
import org.jsoup.select.Elements

/**
 * Will pick all occurrences of <button> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.button(cssSelector: String = "", init: Elements.() -> Unit) = elements("button$cssSelector", init)

/**
 * Will pick all occurrences of <datalist> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.datalist(cssSelector: String = "", init: Elements.() -> Unit) = elements("datalist$cssSelector", init)

/**
 * Will pick all occurrences of <fieldset> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.fieldset(cssSelector: String = "", init: Elements.() -> Unit) = elements("fieldset$cssSelector", init)

/**
 * Will pick all occurrences of <form> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.form(cssSelector: String = "", init: Elements.() -> Unit) = elements("form$cssSelector", init)

/**
 * Will pick all occurrences of <input> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.input(cssSelector: String = "", init: Elements.() -> Unit) = elements("input$cssSelector", init)

/**
 * Will pick all occurrences of <label> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.label(cssSelector: String = "", init: Elements.() -> Unit) = elements("label$cssSelector", init)

/**
 * Will pick all occurrences of <legend> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.legend(cssSelector: String = "", init: Elements.() -> Unit) = elements("legend$cssSelector", init)

/**
 * Will pick all occurrences of <meter> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.meter(cssSelector: String = "", init: Elements.() -> Unit) = elements("meter$cssSelector", init)

/**
 * Will pick all occurrences of <optgroup> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.optgroup(cssSelector: String = "", init: Elements.() -> Unit) = elements("optgroup$cssSelector", init)

/**
 * Will pick all occurrences of <option> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.option(cssSelector: String = "", init: Elements.() -> Unit) = elements("option$cssSelector", init)

/**
 * Will pick all occurrences of <output> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.output(cssSelector: String = "", init: Elements.() -> Unit) = elements("output$cssSelector", init)

/**
 * Will pick all occurrences of <progress> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.progress(cssSelector: String = "", init: Elements.() -> Unit) = elements("progress$cssSelector", init)

/**
 * Will pick all occurrences of <select> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.select(cssSelector: String = "", init: Elements.() -> Unit) = elements("select$cssSelector", init)

/**
 * Will pick all occurrences of <textarea> elements from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Doc.textarea(cssSelector: String = "", init: Elements.() -> Unit) = elements("textarea$cssSelector", init)
