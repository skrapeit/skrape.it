package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import it.skrape.selects.elements
import org.jsoup.select.Elements

@SkrapeItDslMarker
fun Result.applet(cssSelector: String ="", init: Elements.() -> Unit) = elements("applet$cssSelector", init)

@SkrapeItDslMarker
fun Result.embed(cssSelector: String ="", init: Elements.() -> Unit) = elements("embed$cssSelector", init)

@SkrapeItDslMarker
fun Result.iframe(cssSelector: String ="", init: Elements.() -> Unit) = elements("iframe$cssSelector", init)

@SkrapeItDslMarker
fun Result.noembed(cssSelector: String ="", init: Elements.() -> Unit) = elements("noembed$cssSelector", init)

@SkrapeItDslMarker
fun Result.`object`(cssSelector: String ="", init: Elements.() -> Unit) = elements("object$cssSelector", init)

@SkrapeItDslMarker
fun Result.param(cssSelector: String ="", init: Elements.() -> Unit) = elements("param$cssSelector", init)

@SkrapeItDslMarker
fun Result.picture(cssSelector: String ="", init: Elements.() -> Unit) = elements("picture$cssSelector", init)

@SkrapeItDslMarker
fun Result.source(cssSelector: String ="", init: Elements.() -> Unit) = elements("source$cssSelector", init)
