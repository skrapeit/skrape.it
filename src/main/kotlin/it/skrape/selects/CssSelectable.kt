package it.skrape.selects

import it.skrape.SkrapeItDsl
import org.jsoup.nodes.Element
import java.util.*

@Suppress("TooManyFunctions")
@SkrapeItDsl
abstract class CssSelectable {
    internal abstract fun applySelector(rawCssSelector: String): List<DocElement>

    fun <T> selection(cssSelector: String, init: CssSelector.() -> T) =
            CssSelector(rawCssSelector = cssSelector, doc = this).init()

    /**
     * Will create a CssSelector scope to calculate a css selector
     * @param cssSelector that represents an CSS-Selector that will be considered during calculation
     * @return T
     */
    operator fun <T> String.invoke(init: CssSelector.() -> T) =
            this@CssSelectable.selection(this, init)

    open fun makeDefault(cssSelector: String): DocElement {
        return DocElement(Element("${UUID.randomUUID()}"))
    }

    /**
     * Will pick all occurrences of elements that are matching the CSS-Selector
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
     * @param cssSelector that represents an CSS-Selector
     * @return T
     */
    infix fun findAll(cssSelector: String): List<DocElement> =
            this.applySelector(cssSelector)

    fun findByIndex(index: Int, cssSelector: String = ""): DocElement =
            findAll(cssSelector).getOrElse(index) { makeDefault(cssSelector) }

    operator fun Int.invoke(cssSelector: String = ""): DocElement =
            findByIndex(this, cssSelector)

    /**
     * Will pick the first occurrence of an element that
     * is matching the CSS-Selector from a parsed document and invoke it to a lambda function.
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
     * @param cssSelector that represents an CSS-Selector
     * @return T
     */
    infix fun findFirst(cssSelector: String): DocElement =
            findByIndex(0, cssSelector)

    fun findSecond(cssSelector: String = ""): DocElement =
            findByIndex(1, cssSelector)

    fun findThird(cssSelector: String = ""): DocElement =
            findByIndex(2, cssSelector)

    fun findLast(cssSelector: String = ""): DocElement {
        val index = findAll(cssSelector).lastIndex
        return findByIndex(index, cssSelector)
    }

    fun findSecondLast(cssSelector: String = ""): DocElement {
        val index = findAll(cssSelector).lastIndex - 1
        return findByIndex(index, cssSelector)
    }

    /**
     * Will pick all occurrences of elements that are matching the CSS-Selector
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
     * @param cssSelector that represents an CSS-Selector
     * @return T
     */
    fun <T> findAll(cssSelector: String = "", init: List<DocElement>.() -> T) =
            this.findAll(cssSelector).init()

    fun <T> findByIndex(index: Int, cssSelector: String = "", init: DocElement.() -> T) =
            findByIndex(index, cssSelector).init()

    operator fun <T> Int.invoke(cssSelector: String = "", init: DocElement.() -> T) =
            this(cssSelector).init()

    /**
     * Will pick the first occurrence of an element that
     * is matching the CSS-Selector from a parsed document and invoke it to a lambda function.
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
     * @param cssSelector that represents an CSS-Selector
     * @return T
     */
    fun <T> findFirst(cssSelector: String = "", init: DocElement.() -> T) =
            findFirst(cssSelector).init()

    fun <T> findSecond(cssSelector: String = "", init: DocElement.() -> T) =
            findSecond(cssSelector).init()

    fun <T> findThird(cssSelector: String = "", init: DocElement.() -> T) =
            findThird(cssSelector).init()

    fun <T> findLast(cssSelector: String = "", init: DocElement.() -> T) =
            findLast(cssSelector).init()

    fun <T> findSecondLast(cssSelector: String = "", init: DocElement.() -> T) =
            findSecondLast(cssSelector).init()
}
