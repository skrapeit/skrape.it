package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import it.skrape.selects.elements
import org.jsoup.select.Elements

@SkrapeItDslMarker
fun Result.blockquote(cssSelector: String ="", init: Elements.() -> Unit) = elements("blockquote$cssSelector", init)

@SkrapeItDslMarker
fun Result.dd(cssSelector: String ="", init: Elements.() -> Unit) = elements("dd$cssSelector", init)

@SkrapeItDslMarker
fun Result.dir(cssSelector: String ="", init: Elements.() -> Unit) = elements("dir$cssSelector", init)

@SkrapeItDslMarker
fun Result.dl(cssSelector: String ="", init: Elements.() -> Unit) = elements("dl$cssSelector", init)

@SkrapeItDslMarker
fun Result.dt(cssSelector: String ="", init: Elements.() -> Unit) = elements("dt$cssSelector", init)

@SkrapeItDslMarker
fun Result.figcaption(cssSelector: String ="", init: Elements.() -> Unit) = elements("figcaption$cssSelector", init)

@SkrapeItDslMarker
fun Result.figure(cssSelector: String ="", init: Elements.() -> Unit) = elements("figure$cssSelector", init)

@SkrapeItDslMarker
fun Result.hr(cssSelector: String ="", init: Elements.() -> Unit) = elements("hr$cssSelector", init)

@SkrapeItDslMarker
fun Result.li(cssSelector: String ="", init: Elements.() -> Unit) = elements("li$cssSelector", init)

@SkrapeItDslMarker
fun Result.ol(cssSelector: String ="", init: Elements.() -> Unit) = elements("ol$cssSelector", init)

@SkrapeItDslMarker
fun Result.ul(cssSelector: String ="", init: Elements.() -> Unit) = elements("ul$cssSelector", init)

@SkrapeItDslMarker
fun Result.p(cssSelector: String ="", init: Elements.() -> Unit) = elements("p$cssSelector", init)

@SkrapeItDslMarker
fun Result.pre(cssSelector: String ="", init: Elements.() -> Unit) = elements("pre$cssSelector", init)
