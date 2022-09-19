package it.skrape.selects.platform

expect abstract class Node {
    abstract fun attributes(): Attributes
    fun outerHtml(): String
    open fun attr(key: String, value: String): Node
}