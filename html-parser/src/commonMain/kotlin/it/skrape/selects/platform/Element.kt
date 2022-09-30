package it.skrape.selects.platform

expect open class Element: Node {

    constructor(selector: String)

    override fun attributes(): Attributes
    override fun attr(key: String, value: String): Element
    fun tagName(): String

    fun parents(): Elements
    fun children(): Elements
    fun cssSelector(): String
    fun siblingElements(): Elements
    open fun getAllElements(): Elements

    fun text(): String?
    fun wholeText(): String
    fun ownText(): String

    // performance sensitive
    fun html(): String?
    fun html(html: String?): Element
    fun prependText(text: String): Element
    fun addClass(clazz: String): Element
    fun append(markup: String): Element

}

val Element.allElements
    get() = getAllElements()
