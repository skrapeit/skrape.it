package it.skrape.selects

import it.skrape.SkrapeItDsl
import it.skrape.exceptions.ElementNotFoundException
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

@Suppress("TooManyFunctions")
@SkrapeItDsl
class Doc(
        val document: Document
) {

    val text = document.text().orEmpty()

    val html: String = document.html().orEmpty()

    val outerHtml: String = document.outerHtml().orEmpty()

    infix fun findAll(cssSelector: String) = document.select(cssSelector)
            .map { DocElement(it) }
            .takeIf { it.isNotEmpty() } ?: throw ElementNotFoundException(cssSelector)

    fun <T> findAll(cssSelector: String, init: List<DocElement>.() -> T) = findAll(cssSelector).init()

    infix fun findFirst(cssSelector: String) =
            findFirstOrNull(cssSelector) ?: throw ElementNotFoundException(cssSelector)

    fun <T> findFirst(cssSelector: String, init: DocElement.() -> T) = findFirst(cssSelector).init()

    fun findFirstOrNull(cssSelector: String): DocElement? {
        val element: Element? = document.selectFirst(cssSelector)
        return element?.let { DocElement(it) }
    }

    val titleText = document.title().orEmpty()

    override fun toString() = document.toString()

    operator fun String.invoke(init: CssSelector.() -> Unit) =
            this@Doc.selection(this, init)

    fun <T> selection(cssSelector: String, init: CssSelector.() -> T) =
            CssSelector(rawCssSelector = cssSelector, doc = this).init()

}
