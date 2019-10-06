package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import it.skrape.selects.elements
import org.jsoup.select.Elements

/**
 * Will pick all occurrences of <button> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.button(cssSelector: String ="", init: Elements.() -> Unit) = elements("button$cssSelector", init)

/**
 * Will pick all occurrences of <datalist> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.datalist(cssSelector: String ="", init: Elements.() -> Unit) = elements("datalist$cssSelector", init)

/**
 * Will pick all occurrences of <fieldset> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.fieldset(cssSelector: String ="", init: Elements.() -> Unit) = elements("fieldset$cssSelector", init)

/**
 * Will pick all occurrences of <form> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.form(cssSelector: String ="", init: Elements.() -> Unit) = elements("form$cssSelector", init)

/**
 * Will pick all occurrences of <input> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.input(cssSelector: String ="", init: Elements.() -> Unit) = elements("input$cssSelector", init)

/**
 * Will pick all occurrences of <label> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.label(cssSelector: String ="", init: Elements.() -> Unit) = elements("label$cssSelector", init)

/**
 * Will pick all occurrences of <legend> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.legend(cssSelector: String ="", init: Elements.() -> Unit) = elements("legend$cssSelector", init)

/**
 * Will pick all occurrences of <meter> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.meter(cssSelector: String ="", init: Elements.() -> Unit) = elements("meter$cssSelector", init)

/**
 * Will pick all occurrences of <optgroup> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.optgroup(cssSelector: String ="", init: Elements.() -> Unit) = elements("optgroup$cssSelector", init)

/**
 * Will pick all occurrences of <option> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.option(cssSelector: String ="", init: Elements.() -> Unit) = elements("option$cssSelector", init)

/**
 * Will pick all occurrences of <output> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.output(cssSelector: String ="", init: Elements.() -> Unit) = elements("output$cssSelector", init)

/**
 * Will pick all occurrences of <progress> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.progress(cssSelector: String ="", init: Elements.() -> Unit) = elements("progress$cssSelector", init)

/**
 * Will pick all occurrences of <select> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.select(cssSelector: String ="", init: Elements.() -> Unit) = elements("select$cssSelector", init)

/**
 * Will pick all occurrences of <textarea> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.textarea(cssSelector: String ="", init: Elements.() -> Unit) = elements("textarea$cssSelector", init)
