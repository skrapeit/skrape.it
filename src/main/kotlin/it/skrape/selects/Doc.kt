package it.skrape.selects

import it.skrape.SkrapeItElementPicker
import it.skrape.SkrapeItValuePicker
import it.skrape.exceptions.ElementNotFoundException
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

@Suppress("TooManyFunctions")
class Doc(
        val document: Document
) : Scrapable {

    @SkrapeItValuePicker
    override val text = document.text().orEmpty()

    @SkrapeItValuePicker
    override val html: String = document.html().orEmpty()

    @SkrapeItValuePicker
    override val outerHtml: String = document.outerHtml().orEmpty()

    override infix fun findAll(cssSelector: String) =
            findAllOrNull(cssSelector) ?: throw ElementNotFoundException(cssSelector)

    @SkrapeItElementPicker
    override fun <T> findAll(cssSelector: String, init: DocElements.() -> T) = findAll(cssSelector).init()

    override infix fun findFirst(cssSelector: String) =
            findFirstOrNull(cssSelector) ?: throw ElementNotFoundException(cssSelector)

    @SkrapeItElementPicker
    override fun <T> findFirst(cssSelector: String, init: DocElement.() -> T) = findFirst(cssSelector).init()

    fun findFirstOrNull(cssSelector: String): DocElement? {
        val element: Element? = document.selectFirst(cssSelector)
        return element?.let { DocElement(it) }
    }

    fun findAllOrNull(cssSelector: String): DocElements? {
        val elements: Elements = document.select(cssSelector)
        return if (elements.isEmpty()) null else DocElements(elements) // TODO: DocElements is empty after creation
    }

    fun title() = document.title()

    override fun toString() = document.toString()

    @SkrapeItElementPicker
    override operator fun String.invoke(init: CssSelector.() -> Unit) =
            this@Doc.selection(this, init)

    override fun <T> selection(cssSelector: String, init: CssSelector.() -> T) =
            CssSelector(rawCssSelector = cssSelector, doc = this).init()

}
