package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import it.skrape.selects.elements
import org.jsoup.select.Elements

@SkrapeItDslMarker
fun Result.button(cssSelector: String ="", init: Elements.() -> Unit) = elements("button$cssSelector", init)

@SkrapeItDslMarker
fun Result.datalist(cssSelector: String ="", init: Elements.() -> Unit) = elements("datalist$cssSelector", init)

@SkrapeItDslMarker
fun Result.fieldset(cssSelector: String ="", init: Elements.() -> Unit) = elements("fieldset$cssSelector", init)

@SkrapeItDslMarker
fun Result.form(cssSelector: String ="", init: Elements.() -> Unit) = elements("form$cssSelector", init)

@SkrapeItDslMarker
fun Result.input(cssSelector: String ="", init: Elements.() -> Unit) = elements("input$cssSelector", init)

@SkrapeItDslMarker
fun Result.label(cssSelector: String ="", init: Elements.() -> Unit) = elements("label$cssSelector", init)

@SkrapeItDslMarker
fun Result.legend(cssSelector: String ="", init: Elements.() -> Unit) = elements("legend$cssSelector", init)

@SkrapeItDslMarker
fun Result.meter(cssSelector: String ="", init: Elements.() -> Unit) = elements("meter$cssSelector", init)

@SkrapeItDslMarker
fun Result.optgroup(cssSelector: String ="", init: Elements.() -> Unit) = elements("optgroup$cssSelector", init)

@SkrapeItDslMarker
fun Result.option(cssSelector: String ="", init: Elements.() -> Unit) = elements("option$cssSelector", init)

@SkrapeItDslMarker
fun Result.output(cssSelector: String ="", init: Elements.() -> Unit) = elements("output$cssSelector", init)

@SkrapeItDslMarker
fun Result.progress(cssSelector: String ="", init: Elements.() -> Unit) = elements("progress$cssSelector", init)

@SkrapeItDslMarker
fun Result.select(cssSelector: String ="", init: Elements.() -> Unit) = elements("select$cssSelector", init)

@SkrapeItDslMarker
fun Result.textarea(cssSelector: String ="", init: Elements.() -> Unit) = elements("textarea$cssSelector", init)
