package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import it.skrape.selects.elements
import org.jsoup.select.Elements

@SkrapeItDslMarker
fun Result.base(cssSelector: String ="", init: Elements.() -> Unit) = elements("base$cssSelector", init)

@SkrapeItDslMarker
fun Result.head(cssSelector: String ="", init: Elements.() -> Unit) = elements("head$cssSelector", init)

@SkrapeItDslMarker
fun Result.link(cssSelector: String ="", init: Elements.() -> Unit) = elements("link$cssSelector", init)

@SkrapeItDslMarker
fun Result.meta(cssSelector: String ="", init: Elements.() -> Unit) = elements("meta$cssSelector", init)

@SkrapeItDslMarker
fun Result.style(cssSelector: String ="", init: Elements.() -> Unit) = elements("style$cssSelector", init)

@SkrapeItDslMarker
fun Result.title(cssSelector: String ="", init: Elements.() -> Unit) = elements("title$cssSelector", init)
