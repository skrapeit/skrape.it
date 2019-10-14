package it.skrape.selects

import it.skrape.SkrapeItDslMarker
import it.skrape.exceptions.ElementNotFoundException
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

@SkrapeItDslMarker
fun <T> Element.element(cssSelector: String, init: Element.() -> T): T {
    val element = selectFirst(cssSelector) ?: throw ElementNotFoundException(cssSelector)
    return element.init()
}

@SkrapeItDslMarker
fun <T> Element.elements(cssSelector: String, init: Elements.() -> T): T {
    val elements = select(cssSelector)
    if (elements.isEmpty()) throw ElementNotFoundException(cssSelector)
    return elements.init()
}

@SkrapeItDslMarker
fun <T> Elements.element(cssSselector: String, init: Element.() -> T): T {
    val element = select(cssSselector).first() ?: throw ElementNotFoundException(cssSselector)
    return element.init()
}

@SkrapeItDslMarker
fun <T> Elements.elements(cssSelector: String, init: Elements.() -> T): T {
    val elements = select(cssSelector)
    if (elements.isEmpty()) throw ElementNotFoundException(cssSelector)
    return elements.init()
}

@SkrapeItDslMarker
fun <T> Elements.firstOccurrence(init: Element.() -> T) = get(0).init()

@SkrapeItDslMarker
fun <T> Elements.nthOccurrence(index: Int, init: Element.() -> T) = get(index).init()

@SkrapeItDslMarker
fun <T> Elements.secondOccurrence(init: Element.() -> T) = get(1).init()

@SkrapeItDslMarker
fun <T> Elements.thirdOccurrence(init: Element.() -> T) = get(2).init()

@SkrapeItDslMarker
fun <T> Elements.lastOccurrence(init: Element.() -> T) = get(size - 1).init()

@SkrapeItDslMarker
fun <T> Elements.secondLastOccurrence(init: Element.() -> T) = get(size - 2).init()
