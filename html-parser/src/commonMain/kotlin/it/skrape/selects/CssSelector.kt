package it.skrape.selects

import it.skrape.SkrapeItDsl

@Suppress("LongParameterList")
@SkrapeItDsl
public expect class CssSelector : CssSelectable {
    public var rawCssSelector: String
    public var withClass: CssClassName?
    public var withId: String?
    public var withAttributeKey: String?
    public var withAttributeKeys: List<String>?
    public var withAttribute: Pair<String, String>?
    public var withAttributes: List<Pair<String, String>>?
    public val doc: CssSelectable
}

// TODO consider making this a value class (stronger type safety)
public typealias CssClassName = String

public infix fun CssClassName.and(value: String): String = "$this.$value"

public infix fun Pair<String, String>.and(pair: Pair<String, String>): MutableList<Pair<String, String>> =
    mutableListOf(this).apply { add(pair) }
