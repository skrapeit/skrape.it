package it.skrape.selects.platform

actual typealias Attribute = org.jsoup.nodes.Attribute
actual typealias Document = org.jsoup.nodes.Document
actual typealias Element = org.jsoup.nodes.Element
@Suppress("ACTUAL_WITHOUT_EXPECT") //Elements inherits from ArrayList, which inherits from MutableList, so we are fine
actual typealias Elements = org.jsoup.select.Elements
@Suppress("ACTUAL_WITHOUT_EXPECT") //Without this kotlin currently produces a false error
actual typealias Attributes = org.jsoup.nodes.Attributes
actual typealias Node = org.jsoup.nodes.Node

internal actual fun emptyElements(): Elements = Elements()