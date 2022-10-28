package it.skrape.selects.platform

expect class Elements: MutableList<Element> {
    fun select(query: String?): Elements
}

internal expect fun emptyElements(): Elements

expect class Attributes: Iterable<Attribute>