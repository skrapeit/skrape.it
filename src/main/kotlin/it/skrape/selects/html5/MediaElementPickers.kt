package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import it.skrape.selects.elements
import org.jsoup.select.Elements

@SkrapeItDslMarker
fun Result.area(cssSelector: String ="", init: Elements.() -> Unit) = elements("area$cssSelector", init)

@SkrapeItDslMarker
fun Result.audio(cssSelector: String ="", init: Elements.() -> Unit) = elements("audio$cssSelector", init)

@SkrapeItDslMarker
fun Result.img(cssSelector: String ="", init: Elements.() -> Unit) = elements("img$cssSelector", init)

@SkrapeItDslMarker
fun Result.map(cssSelector: String ="", init: Elements.() -> Unit) = elements("map$cssSelector", init)

@SkrapeItDslMarker
fun Result.track(cssSelector: String ="", init: Elements.() -> Unit) = elements("track$cssSelector", init)

@SkrapeItDslMarker
fun Result.video(cssSelector: String ="", init: Elements.() -> Unit) = elements("video$cssSelector", init)
