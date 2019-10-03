package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import it.skrape.selects.elements
import org.jsoup.select.Elements

@SkrapeItDslMarker
fun Result.caption(cssSelector: String ="", init: Elements.() -> Unit) = elements("caption$cssSelector", init)

@SkrapeItDslMarker
fun Result.col(cssSelector: String ="", init: Elements.() -> Unit) = elements("col$cssSelector", init)

@SkrapeItDslMarker
fun Result.colgroup(cssSelector: String ="", init: Elements.() -> Unit) = elements("colgroup$cssSelector", init)

@SkrapeItDslMarker
fun Result.table(cssSelector: String ="", init: Elements.() -> Unit) = elements("table$cssSelector", init)


@SkrapeItDslMarker
fun Result.tbody(cssSelector: String ="", init: Elements.() -> Unit) = elements("tbody$cssSelector", init)


@SkrapeItDslMarker
fun Result.td(cssSelector: String ="", init: Elements.() -> Unit) = elements("td$cssSelector", init)


@SkrapeItDslMarker
fun Result.tfoot(cssSelector: String ="", init: Elements.() -> Unit) = elements("tfoot$cssSelector", init)


@SkrapeItDslMarker
fun Result.th(cssSelector: String ="", init: Elements.() -> Unit) = elements("th$cssSelector", init)


@SkrapeItDslMarker
fun Result.thead(cssSelector: String ="", init: Elements.() -> Unit) = elements("thead$cssSelector", init)


@SkrapeItDslMarker
fun Result.tr(cssSelector: String ="", init: Elements.() -> Unit) = elements("tr$cssSelector", init)
