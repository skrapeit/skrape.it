package it.skrape.selects

import it.skrape.SkrapeItDsl
import org.jsoup.nodes.Element

@Suppress("TooManyFunctions")
@SkrapeItDsl
class DocElement(
        private val element: Element
) : Scrapable {

    override val text = element.text().orEmpty()

    override val html = element.html().orEmpty()

    override val outerHtml: String = element.outerHtml().orEmpty()

    override fun <T> findAll(cssSelector: String, init: DocElements.() -> T): T =
            findAll(cssSelector).init()

    override infix fun findAll(cssSelector: String) = DocElements(element.allElements).findAll(cssSelector)

    override fun <T> findFirst(cssSelector: String, init: DocElement.() -> T): T = findFirst(cssSelector).init()

    override infix fun findFirst(cssSelector: String): DocElement = findAll(cssSelector).findFirst { this }

    val className = element.className().orEmpty()

    val cssSelector = element.cssSelector().orEmpty()

    infix fun attribute(attributeKey: String): String = element.attr(attributeKey)

    override fun <T> selection(cssSelector: String, init: CssSelector.() -> T) =
            CssSelector(rawCssSelector = cssSelector).init()

    override operator fun String.invoke(init: CssSelector.() -> Unit) =
            this@DocElement.selection(this, init)

    override fun toString() = element.toString()

    fun isPresent() = element.allElements.size > 0
}
