package it.skrape.selects

import it.skrape.SkrapeItDsl
import org.jsoup.nodes.Document

@SkrapeItDsl
class CssSelector(
        var rawCssSelector: String = "",
        var withClass: CssClassName? = null,
        var withId: String? = null,
        var withAttributeKey: String? = null,
        var withAttributeKeys: List<String>? = null,
        var withAttribute: Pair<String, String>? = null,
        var withAttributes: List<Pair<String, String>>? = null,
        val doc: CssSelectable = Doc(Document(""))
) : CssSelectable() {
    override val toCssSelector: String
        get() = "${doc.toCssSelector} $ownCssSelector".trim()

    override fun applySelector(rawCssSelector: String): List<DocElement> {
        val combinedSelector = "$ownCssSelector $rawCssSelector".trim()
        return doc.applySelector(combinedSelector)
    }

    val ownCssSelector: String
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
}

typealias CssClassName = String

infix fun CssClassName.and(value: String) = "$this.$value"

infix fun Pair<String, String>.and(pair: Pair<String, String>) =
        mutableListOf(this).apply { add(pair) }
