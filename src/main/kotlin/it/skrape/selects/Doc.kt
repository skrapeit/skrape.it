package it.skrape.selects

import it.skrape.SkrapeItElementPicker
import it.skrape.exceptions.ElementNotFoundException
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

@Suppress("TooManyFunctions")
class Doc(
        val document: Document
) : Scrapable {

    override fun text() = document.text().orEmpty()

    override fun findAll(cssSelector: String) =
            findAllOrNull(cssSelector) ?: throw ElementNotFoundException(cssSelector)

    @SkrapeItElementPicker
    override fun <T> findAll(cssSelector: String, init: DocElements.() -> T) = findAll(cssSelector).init()

    override fun findFirst(cssSelector: String) =
            findFirstOrNull(cssSelector) ?: throw ElementNotFoundException(cssSelector)

    @SkrapeItElementPicker
    override fun <T> findFirst(cssSelector: String, init: DocElement.() -> T) = findFirst(cssSelector).init()

    fun findFirstOrNull(cssQuery: String): DocElement? {
        val selection: Element? = document.selectFirst(cssQuery)
        return selection?.let { DocElement(it) }
    }

    fun findAllOrNull(cssSelector: String): DocElements? {
        val selection: Elements = document.select(cssSelector)
        return if (selection.isEmpty()) null else DocElements(selection)
    }

    fun title() = document.title()

    override fun toString() = document.toString()

    @SkrapeItElementPicker
    override operator fun String.invoke(init: CssSelector.() -> Unit) =
            this@Doc.selection(this, init)

    override fun <T> selection(cssSelector: String, init: CssSelector.() -> T) =
            CssSelector(rawCssSelector = cssSelector, doc = this).init()

}
