package it.skrape.selects

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Selector
import it.skrape.exceptions.ElementNotFoundException
import it.skrape.selects.html5.select
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
fun <T> Elements.findFirst(init: Element.() -> T) = get(0).init()

@SkrapeItDslMarker
fun <T> Selector.findFirst(init: Element.() -> T) = doc.element(toCssSelector(), init)

@SkrapeItDslMarker
fun <T> Elements.findByIndex(index: Int, init: Element.() -> T) = get(index).init()

@SkrapeItDslMarker
fun <T> Selector.findByIndex(index: Int, init: Element.() -> T): T =
        doc.elements(toCssSelector()).findByIndex(index, init)

@SkrapeItDslMarker
fun <T> Elements.findSecond(init: Element.() -> T) = get(1).init()

@SkrapeItDslMarker
fun <T> Selector.findSecond(init: Element.() -> T): T =
        doc.elements(toCssSelector()).findSecond(init)

@SkrapeItDslMarker
fun <T> Elements.findThird(init: Element.() -> T) = get(2).init()

@SkrapeItDslMarker
fun <T> Selector.findThird(init: Element.() -> T): T =
        doc.elements(toCssSelector()).findThird(init)

@SkrapeItDslMarker
fun <T> Elements.findLast(init: Element.() -> T) = get(size - 1).init()

@SkrapeItDslMarker
fun <T> Selector.findLast(init: Element.() -> T): T =
        doc.elements(toCssSelector()).findLast(init)

@SkrapeItDslMarker
fun <T> Elements.findSecondLast(init: Element.() -> T) = get(size - 2).init()

@SkrapeItDslMarker
fun <T> Selector.findSecondLast(init: Element.() -> T): T =
        doc.elements(toCssSelector()).findSecondLast(init)

@SkrapeItDslMarker
fun <T> Selector.findAll(init: Elements.() -> T) = doc.elements(toCssSelector(), init)
