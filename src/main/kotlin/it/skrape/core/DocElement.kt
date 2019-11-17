package it.skrape.core

import org.jsoup.nodes.Element

class DocElement(private val element: Element) {

    fun html() = element.html().orEmpty()
    fun text() = element.text().orEmpty()
    fun className() = element.className().orEmpty()
    val text = element.text().orEmpty()
    val cssSelector = element.cssSelector().orEmpty()
    fun attribute(attributeKey: String): String = element.attr(attributeKey)

    fun attr(attributeKey: String): String = attribute(attributeKey)

    fun findAll() = DocElements(element.allElements)
    fun findAll(cssSelector: String) = DocElements(element.allElements).findAll(cssSelector)

    fun <T> selection(selector: String, init: CssSelector.() -> T) =
            CssSelector(rawCssSelector = selector).init()

    override fun toString() = element.toString()

}
