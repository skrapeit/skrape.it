package it.skrape.core

import it.skrape.SkrapeItDslMarker
import org.jsoup.nodes.Document

class DomSelector(
        var rawCssSelector: String = "",
        var withClass: CssClassName? = null,
        var withId: String? = null,
        var withAttributeKey: String? = null,
        var withAttributeKeys: List<String>? = null,
        var withAttribute: Pair<String, String>? = null,
        var withAttributes: List<Pair<String, String>>? = null,
        val doc: Doc = Doc(Document(""))
) {

    fun toCssSelector(): String {
        val calculatedSelector =
                rawCssSelector +
                withId.toIdSelector().orEmpty() +
                withClass.toClassesSelector().orEmpty() +
                withAttributeKey.toAttributeKeySelector().orEmpty() +
                withAttributeKeys.toCssAttributeKeysSelector().orEmpty() +
                withAttribute.toAttributeSelector().orEmpty() +
                withAttributes.toAttributesSelector().orEmpty()
        return calculatedSelector.withoutSpaces()
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

@SkrapeItDslMarker
infix fun CssClassName.and(value: String) = "$this.$value"

@SkrapeItDslMarker
infix fun Pair<String, String>.and(pair: Pair<String, String>): MutableList<Pair<String, String>> {
    val collectedValues = mutableListOf(this)
    collectedValues.add(pair)
    return collectedValues
}
