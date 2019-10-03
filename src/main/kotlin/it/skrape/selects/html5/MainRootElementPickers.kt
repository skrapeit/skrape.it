package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import it.skrape.selects.elements
import org.jsoup.select.Elements

@SkrapeItDslMarker
fun Result.html(cssSelector: String = "", init: Elements.() -> Unit) = elements("html$cssSelector", init)
