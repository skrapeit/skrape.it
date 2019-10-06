package it.skrape.selects

import it.skrape.SkrapeItDslMarker
import it.skrape.exceptions.ElementNotFoundException
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

@SkrapeItDslMarker
fun Element.element(cssSelector: String, init: Element.() -> Unit) {
    val element = selectFirst(cssSelector) ?: throw ElementNotFoundException(cssSelector)
    element.apply(init)
}

@SkrapeItDslMarker
fun Element.elements(cssSelector: String, init: Elements.() -> Unit) {
    val elements = select(cssSelector)
    if (elements.isEmpty()) throw ElementNotFoundException(cssSelector)
    elements.apply(init)
}

@SkrapeItDslMarker
fun Elements.element(cssSselector: String, init: Element.() -> Unit) {
    val element = select(cssSselector).first() ?: throw ElementNotFoundException(cssSselector)
    element.apply(init)
}

@SkrapeItDslMarker
fun Elements.elements(cssSelector: String, init: Elements.() -> Unit) {
    val elements = select(cssSelector)
    if (elements.isEmpty()) throw ElementNotFoundException(cssSelector)
    elements.apply(init)
}

@SkrapeItDslMarker
fun Elements.firstOccurrence(init: Element.() -> Unit) {
    get(0).apply(init)
}

@SkrapeItDslMarker
fun Elements.secondOccurrence(init: Element.() -> Unit) {
    get(1).apply(init)
}

@SkrapeItDslMarker
fun Elements.thirdOccurrence(init: Element.() -> Unit) {
    get(2).apply(init)
}

@SkrapeItDslMarker
fun Elements.lastOccurrence(init: Element.() -> Unit) {
    get(size - 1).apply(init)
}


@SkrapeItDslMarker
fun Elements.secondLastOccurrence(init: Element.() -> Unit) {
    get(size - 2).apply(init)
}
