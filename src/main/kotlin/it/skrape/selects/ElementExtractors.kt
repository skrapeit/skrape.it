package it.skrape.selects

import it.skrape.SkrapeItDslMarker
import it.skrape.core.DomSelector
import it.skrape.exceptions.ElementNotFoundException
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

@SkrapeItDslMarker
fun <T> Elements.findFirst(init: Element.() -> T) = first().init()

@SkrapeItDslMarker
fun <T> DomSelector.findFirst(init: Element.() -> T) = doc.elements(toCssSelector()).findFirst(init)

@SkrapeItDslMarker
fun <T> Elements.findByIndex(index: Int, init: Element.() -> T) = elementAt(index).init()

@SkrapeItDslMarker
fun <T> DomSelector.findByIndex(index: Int, init: Element.() -> T): T =
        doc.elements(toCssSelector()).findByIndex(index, init)

@SkrapeItDslMarker
fun <T> Elements.findSecond(init: Element.() -> T) = elementAt(1).init()

@SkrapeItDslMarker
fun <T> DomSelector.findSecond(init: Element.() -> T): T =
        doc.elements(toCssSelector()).findSecond(init)

@SkrapeItDslMarker
fun <T> Elements.findThird(init: Element.() -> T) = elementAt(2).init()

@SkrapeItDslMarker
fun <T> DomSelector.findThird(init: Element.() -> T): T =
        doc.elements(toCssSelector()).findThird(init)

@SkrapeItDslMarker
fun <T> Elements.findLast(init: Element.() -> T) = last().init()

@SkrapeItDslMarker
fun <T> DomSelector.findLast(init: Element.() -> T): T =
        doc.elements(toCssSelector()).findLast(init)

@SkrapeItDslMarker
fun <T> Elements.findSecondLast(init: Element.() -> T) = elementAt(size - 2).init()

@SkrapeItDslMarker
fun <T> DomSelector.findSecondLast(init: Element.() -> T): T =
        doc.elements(toCssSelector()).findSecondLast(init)

@SkrapeItDslMarker
fun <T> DomSelector.findAll(init: Elements.() -> T) = doc.elements(toCssSelector(), init)
