package it.skrape.selects

import org.jsoup.nodes.Element

@Suppress("TooManyFunctions")
class DocElement(
        private val element: Element
) : Scrapable {


    override fun text() = element.text().orEmpty()

    override fun <T> findAll(cssSelector: String, init: DocElements.() -> T): T =
            findAll(cssSelector).init()

    override fun findAll(cssSelector: String) = DocElements(element.allElements).findAll(cssSelector)

    override fun <T> findFirst(cssSelector: String, init: DocElement.() -> T): T = findFirst(cssSelector).init()

    override fun findFirst(cssSelector: String): DocElement = findAll(cssSelector).findFirst { this }


    fun html() = element.html().orEmpty()
    fun className() = element.className().orEmpty()
    val text = element.text().orEmpty()
    val cssSelector = element.cssSelector().orEmpty()
    fun attribute(attributeKey: String): String = element.attr(attributeKey)

    fun attr(attributeKey: String): String = attribute(attributeKey)


    override fun <T> selection(cssSelector: String, init: CssSelector.() -> T) =
            CssSelector(rawCssSelector = cssSelector).init()

    override operator fun String.invoke(init: CssSelector.() -> Unit) =
            this@DocElement.selection(this, init)

    override fun toString() = element.toString()

    fun isPresent() = element.allElements.size > 0
}
