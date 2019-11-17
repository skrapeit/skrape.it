package it.skrape.core

import it.skrape.exceptions.ElementNotFoundException
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

@Suppress("TooManyFunctions")
class Doc(
        val document: Document
) {

    val text = document.text().orEmpty()

    fun text() = document.text()

    fun title() = document.title()

    fun selectFirst(cssQuery: String) =
            selectFirstOrNull(cssQuery) ?: throw ElementNotFoundException(cssQuery)

    fun selectFirstOrNull(cssQuery: String): DocElement? {
        val selection: Element? = document.selectFirst(cssQuery)
        return selection?.let { DocElement(it) }
    }

    fun select(cssQuery: String) =
            selectOrNull(cssQuery) ?: throw ElementNotFoundException(cssQuery)

    fun selectOrNull(cssQuery: String): DocElements? {
        val selection: Elements = document.select(cssQuery)
        return if (selection.isEmpty()) null else DocElements(selection)
    }

    override fun toString() = document.toString()

    operator fun String.invoke(init: CssSelector.() -> Unit) =
            this@Doc.selection(this, init)

    /**
     * Will pick the first occurrence of an Element that
     * is matching the CSS-Selector from a parsed document.
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
     * @param selector that represents an CSS-Selector
     * @return Element
     */
    @Suppress("MaxLineLength")
    fun <T> element(selector: String, init: DocElement.() -> T) = selectFirst(selector).init()

    /**
     * Will pick all occurrences of an Elements that are matching the CSS-Selector
     * and return it as Elements which is basically a List<Element>
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
     * @param selector that represents an CSS-Selector
     * @return Elements
     */
    fun <T> elements(selector: String, init: DocElements.() -> T) = select(selector).init()

    fun <T> selection(selector: String, init: CssSelector.() -> T) =
            CssSelector(rawCssSelector = selector, doc = this).init()

}
