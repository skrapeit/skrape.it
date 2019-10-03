package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import it.skrape.selects.elements
import org.jsoup.select.Elements

@SkrapeItDslMarker
fun Result.body(cssSelector: String ="", init: Elements.() -> Unit) = elements("body$cssSelector", init)

@SkrapeItDslMarker
fun Result.div(cssSelector: String ="", init: Elements.() -> Unit) = elements("div$cssSelector", init)

@SkrapeItDslMarker
fun Result.section(cssSelector: String ="", init: Elements.() -> Unit) = elements("section$cssSelector", init)

@SkrapeItDslMarker
fun Result.nav(cssSelector: String ="", init: Elements.() -> Unit) = elements("nav$cssSelector", init)

@SkrapeItDslMarker
fun Result.article(cssSelector: String ="", init: Elements.() -> Unit) = elements("article$cssSelector", init)

@SkrapeItDslMarker
fun Result.aside(cssSelector: String ="", init: Elements.() -> Unit) = elements("aside$cssSelector", init)

@SkrapeItDslMarker
fun Result.h1(cssSelector: String ="", init: Elements.() -> Unit) = elements("h1$cssSelector", init)

@SkrapeItDslMarker
fun Result.h2(cssSelector: String ="", init: Elements.() -> Unit) = elements("h2$cssSelector", init)

@SkrapeItDslMarker
fun Result.h3(cssSelector: String ="", init: Elements.() -> Unit) = elements("h3$cssSelector", init)

@SkrapeItDslMarker
fun Result.h4(cssSelector: String ="", init: Elements.() -> Unit) = elements("h4$cssSelector", init)

@SkrapeItDslMarker
fun Result.h5(cssSelector: String ="", init: Elements.() -> Unit) = elements("h5$cssSelector", init)

@SkrapeItDslMarker
fun Result.h6(cssSelector: String ="", init: Elements.() -> Unit) = elements("h6$cssSelector", init)

@SkrapeItDslMarker
fun Result.header(cssSelector: String ="", init: Elements.() -> Unit) = elements("header$cssSelector", init)

@SkrapeItDslMarker
fun Result.footer(cssSelector: String ="", init: Elements.() -> Unit) = elements("footer$cssSelector", init)

@SkrapeItDslMarker
fun Result.address(cssSelector: String ="", init: Elements.() -> Unit) = elements("address$cssSelector", init)

@SkrapeItDslMarker
fun Result.main(cssSelector: String ="", init: Elements.() -> Unit) = elements("main$cssSelector", init)
