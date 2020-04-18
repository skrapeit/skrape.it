package it.skrape.selects

import it.skrape.SkrapeItDsl
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.util.*

@Suppress("TooManyFunctions")
@SkrapeItDsl
class CssSelector(
        var rawCssSelector: String = "",
        var withClass: CssClassName? = null,
        var withId: String? = null,
        var withAttributeKey: String? = null,
        var withAttributeKeys: List<String>? = null,
        var withAttribute: Pair<String, String>? = null,
        var withAttributes: List<Pair<String, String>>? = null,
        val doc: DomTreeElement = Doc(Document(""))
) {

    fun <T> findByIndex(index: Int, init: DocElement.() -> T): T {
        val all = findAll { this }
        return if (all.isEmpty()) DocElement(Element("${UUID.randomUUID()}")).init() else all[index].init()
    }

    operator fun <T> Int.invoke(init: DocElement.() -> T) =
            findByIndex(this, init)

    fun <T> findFirst(init: DocElement.() -> T): T =
            findByIndex(0, init)

    fun <T> findSecond(init: DocElement.() -> T): T =
            findByIndex(1, init)

    fun <T> findThird(init: DocElement.() -> T): T =
            findByIndex(2, init)

    fun <T> findLast(init: DocElement.() -> T): T {
        val index = findAll { this }.size - 1
        return findByIndex(index, init)
    }

    fun <T> findSecondLast(init: DocElement.() -> T): T {
        val index = findAll { this }.size - 2
        return findByIndex(index, init)
    }

    fun <T> findAll(init: List<DocElement>.() -> T) = doc.findAll(toCssSelector, init)

    val toCssSelector: String
        get() {
            val calculatedSelector =
                    withId.toIdSelector().orEmpty() +
                            withClass.toClassesSelector().orEmpty() +
                            withAttributeKey.toAttributeKeySelector().orEmpty() +
                            withAttributeKeys.toCssAttributeKeysSelector().orEmpty() +
                            withAttribute.toAttributeSelector().orEmpty() +
                            withAttributes.toAttributesSelector().orEmpty()
            return rawCssSelector + calculatedSelector.withoutSpaces()
        }

    private fun String?.toIdSelector() = this?.let { "#$it" }

    private fun CssClassName?.toClassesSelector() = this?.let { ".$it" }

    private fun String?.toAttributeKeySelector() =
            this?.let { "[$it]" }

    private fun List<String>?.toCssAttributeKeysSelector() =
            this?.joinToString(prefix = "['", separator = "']['", postfix = "']")

    private fun Pair<String, String>?.toAttributeSelector() =
            this?.let { "[${it.first}='${it.second}']" }

    private fun List<Pair<String, String>>?.toAttributesSelector() =
            this?.joinToString(separator = "") { "[${it.first}='${it.second}']" }

    private fun String.withoutSpaces() = replace("\\s".toRegex(), "")

    operator fun <T> String.invoke(init: CssSelector.() -> T) =
            CssSelector(rawCssSelector = "${this@CssSelector.toCssSelector} $this").init()
}

typealias CssClassName = String

infix fun CssClassName.and(value: String) = "$this.$value"

infix fun Pair<String, String>.and(pair: Pair<String, String>) =
        mutableListOf(this).apply { add(pair) }
