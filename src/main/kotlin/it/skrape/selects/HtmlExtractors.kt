package it.skrape.selects

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import org.jsoup.nodes.Element

@SkrapeItDslMarker
fun Result.html(init: Element.() -> Unit) = element("html", init)

@SkrapeItDslMarker
fun Result.head(init: Element.() -> Unit) = element("head", init)

@SkrapeItDslMarker
fun Result.p(init: Element.() -> Unit) = element("p", init)

@SkrapeItDslMarker
fun Result.li(init: Element.() -> Unit) = element("li", init)

@SkrapeItDslMarker
fun Result.ol(init: Element.() -> Unit) = element("ol", init)
