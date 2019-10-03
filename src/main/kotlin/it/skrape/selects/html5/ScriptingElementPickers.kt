package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import it.skrape.selects.elements
import org.jsoup.select.Elements

@SkrapeItDslMarker
fun Result.script(cssSelector: String ="", init: Elements.() -> Unit) = elements("script$cssSelector", init)

@SkrapeItDslMarker
fun Result.canvas(cssSelector: String ="", init: Elements.() -> Unit) = elements("canvas$cssSelector", init)

@SkrapeItDslMarker
fun Result.noscript(cssSelector: String ="", init: Elements.() -> Unit) = elements("noscript$cssSelector", init)
