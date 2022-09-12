package it.skrape.selects

import it.skrape.SkrapeItDsl


@Suppress("TooManyFunctions")
@SkrapeItDsl
public expect abstract class CssSelectable {
    public abstract val toCssSelector: String

    internal abstract fun applySelector(rawCssSelector: String): List<DocElement>

    public fun <T> selection(cssSelector: String, init: CssSelector.() -> T): T

    /**
     * Will create a CssSelector scope to calculate a css selector
     * @param init block for configuring the CSS-Selector that will be considered during calculation
     * @return T
     */
    public operator fun <T> String.invoke(init: CssSelector.() -> T): T

    public open fun makeDefault(cssSelector: String): DocElement

    /**
     * Will pick all occurrences of elements that are matching the CSS-Selector
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
     * @param cssSelector that represents an CSS-Selector
     * @return T
     */
    public infix fun findAll(cssSelector: String): List<DocElement>


    public operator fun Int.invoke(cssSelector: String = ""): DocElement


    public operator fun Regex.invoke(): List<DocElement>

    /**
     * Will pick the first occurrence of an element that
     * is matching the CSS-Selector from a parsed document and invoke it to a lambda function.
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
     * @param cssSelector that represents an CSS-Selector
     * @return T
     */
    public infix fun findFirst(cssSelector: String): DocElement

    public fun findSecond(cssSelector: String = ""): DocElement

    public fun findThird(cssSelector: String = ""): DocElement

    public fun findLast(cssSelector: String = ""): DocElement

    public fun findSecondLast(cssSelector: String = ""): DocElement

    /**
     * Will pick all occurrences of elements that are matching the CSS-Selector
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
     * @param cssSelector that represents an CSS-Selector
     * @return T
     */
    public fun <T> findAll(cssSelector: String = "", init: List<DocElement>.() -> T): T

    public fun <T> findByIndex(index: Int, cssSelector: String = "", init: DocElement.() -> T): T

    public operator fun <T> Int.invoke(cssSelector: String = "", init: DocElement.() -> T): T

    public fun <T> findBySelectorMatching(regex: Regex, init: List<DocElement>.() -> T): T

    public operator fun <T> Regex.invoke(init: List<DocElement>.() -> T): T

    /**
     * Will pick the first occurrence of an element that
     * is matching the CSS-Selector from a parsed document and invoke it to a lambda function.
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">Overview of CSS-Selectors for further information.</a>
     * @param cssSelector that represents an CSS-Selector
     * @return T
     */
    public fun <T> findFirst(cssSelector: String = "", init: DocElement.() -> T): T

    public fun <T> findSecond(cssSelector: String = "", init: DocElement.() -> T): T

    public fun <T> findThird(cssSelector: String = "", init: DocElement.() -> T): T

    public fun <T> findLast(cssSelector: String = "", init: DocElement.() -> T): T

    public fun <T> findSecondLast(cssSelector: String = "", init: DocElement.() -> T): T
}
