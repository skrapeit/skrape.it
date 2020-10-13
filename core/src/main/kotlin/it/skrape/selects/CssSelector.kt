package it.skrape.selects

import it.skrape.SkrapeItDsl
import org.jsoup.nodes.Document

@Suppress("LongParameterList")
@SkrapeItDsl
public class CssSelector(
    public var rawCssSelector: String = "",
    public var withClass: CssClassName? = null,
    public var withId: String? = null,
    public var withAttributeKey: String? = null,
    public var withAttributeKeys: List<String>? = null,
    public var withAttribute: Pair<String, String>? = null,
    public var withAttributes: List<Pair<String, String>>? = null,
    public val doc: CssSelectable = Doc(Document(""))
) : CssSelectable() {
    override val toCssSelector: String
        get() = "${doc.toCssSelector} $ownCssSelector".trim()

    override fun applySelector(rawCssSelector: String): List<DocElement> {
        val combinedSelector = "$ownCssSelector $rawCssSelector".trim()
        return doc.applySelector(combinedSelector)
    }

    private val ownCssSelector: String
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

public typealias CssClassName = String

public infix fun CssClassName.and(value: String): String = "$this.$value"

public infix fun Pair<String, String>.and(pair: Pair<String, String>): MutableList<Pair<String, String>> =
        mutableListOf(this).apply { add(pair) }
