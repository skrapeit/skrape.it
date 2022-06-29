package it.skrape.selects

import it.skrape.SkrapeItDsl
import org.jsoup.nodes.Element
import java.util.*

@Suppress("TooManyFunctions")
@SkrapeItDsl
public abstract class CssSelectable {
    public abstract val toCssSelector: String

    internal abstract fun applySelector(rawCssSelector: String): List<DocElement>

    public fun <T> selection(cssSelector: String, init: CssSelector.() -> T): T =
            CssSelector(rawCssSelector = cssSelector, doc = this).init()

    /**
     * Will create a CssSelector scope to calculate a css selector
     * @param init block for configuring the CSS-Selector that will be considered during calculation
     * @return T
     */
    public operator fun <T> String.invoke(init: CssSelector.() -> T): T =
            this@CssSelectable.selection(this, init)

    public open fun makeDefault(cssSelector: String): DocElement {
        return DocElement(Element("${UUID.randomUUID()}"))
    }

    /**
     * Will pick all occurrences of elements that are matching the CSS-Selector
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
     * @param cssSelector that represents an CSS-Selector
     * @return T
     */
    public infix fun findAll(cssSelector: String): List<DocElement> =
            this.applySelector(cssSelector)

    public fun findByIndex(index: Int, cssSelector: String = ""): DocElement =
            findAll(cssSelector).getOrElse(index) { makeDefault(cssSelector) }

    public operator fun Int.invoke(cssSelector: String = ""): DocElement =
            findByIndex(this, cssSelector)

    public fun findBySelectorMatching(regex: Regex): List<DocElement> =
            this@CssSelectable.findAll("*").filter { it.ownCssSelector.matches(regex) }

    public operator fun Regex.invoke(): List<DocElement> =
            findBySelectorMatching(this)

    /**
     * Will pick the first occurrence of an element that
     * is matching the CSS-Selector from a parsed document and invoke it to a lambda function.
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
     * @param cssSelector that represents an CSS-Selector
     * @return T
     */
    public infix fun findFirst(cssSelector: String): DocElement =
            findByIndex(0, cssSelector)

    public fun findSecond(cssSelector: String = ""): DocElement =
            findByIndex(1, cssSelector)

    public fun findThird(cssSelector: String = ""): DocElement =
            findByIndex(2, cssSelector)

    public fun findLast(cssSelector: String = ""): DocElement =
            findAll(cssSelector).last()

    public fun findSecondLast(cssSelector: String = ""): DocElement =
            findAll(cssSelector).let { it.getOrElse(it.lastIndex -1) { makeDefault(cssSelector) } }

    /**
     * Will pick all occurrences of elements that are matching the CSS-Selector
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
     * @param cssSelector that represents an CSS-Selector
     * @return T
     */
    public fun <T> findAll(cssSelector: String = "", init: List<DocElement>.() -> T): T =
            findAll(cssSelector).init()

    public fun <T> findByIndex(index: Int, cssSelector: String = "", init: DocElement.() -> T): T =
            findByIndex(index, cssSelector).init()

    public operator fun <T> Int.invoke(cssSelector: String = "", init: DocElement.() -> T): T =
            this(cssSelector).init()

    public fun <T> findBySelectorMatching(regex: Regex, init: List<DocElement>.() -> T): T =
            findBySelectorMatching(regex).init()

    public operator fun <T> Regex.invoke(init: List<DocElement>.() -> T): T =
            this().init()

    /**
     * Will pick the first occurrence of an element that
     * is matching the CSS-Selector from a parsed document and invoke it to a lambda function.
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
     * @param cssSelector that represents an CSS-Selector
     * @return T
     */
    public fun <T> findFirst(cssSelector: String = "", init: DocElement.() -> T): T =
            findFirst(cssSelector).init()

    public fun <T> findSecond(cssSelector: String = "", init: DocElement.() -> T): T =
            findSecond(cssSelector).init()

    public fun <T> findThird(cssSelector: String = "", init: DocElement.() -> T): T =
            findThird(cssSelector).init()

    public fun <T> findLast(cssSelector: String = "", init: DocElement.() -> T): T =
            findLast(cssSelector).init()

    public fun <T> findSecondLast(cssSelector: String = "", init: DocElement.() -> T): T =
            findSecondLast(cssSelector).init()
}
