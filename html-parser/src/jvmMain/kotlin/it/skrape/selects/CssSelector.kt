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
        get() = ("${doc.toCssSelector} $this").trim()

    override fun applySelector(rawCssSelector: String): List<DocElement> =
        doc.applySelector("$this $rawCssSelector".trim())

    override fun toString(): String = rawCssSelector.trim() + buildString {
        append(withId.toIdSelector())
        append(withClass.toClassesSelector())
        append(withAttributeKey.toAttributeKeySelector())
        append(withAttributeKeys.toAttributesKeysSelector())
        append(withAttribute.toAttributeSelector())
        append(withAttributes.toAttributesSelector())
    }

    private fun String?.toIdSelector() = this?.let { "#$it" }.orEmpty().withoutSpaces()

    private fun CssClassName?.toClassesSelector() = this?.let { ".$it" }.orEmpty().withoutSpaces()

    private fun String?.toAttributeKeySelector() = this?.let { "[$it]" }.orEmpty().withoutSpaces()

    private fun List<String>?.toAttributesKeysSelector() =
        this?.joinToString(prefix = "['", separator = "']['", postfix = "']").orEmpty().withoutSpaces()

    private fun Pair<String, String>?.toAttributeSelector() =
        this?.let { "[${it.first.withoutSpaces()}='${it.second}']" }.orEmpty()

    private fun List<Pair<String, String>>?.toAttributesSelector() =
        this?.joinToString(separator = "") { it.toAttributeSelector() }.orEmpty()

    private fun String.withoutSpaces() = replace("\\s".toRegex(), "")
}

public typealias CssClassName = String

public infix fun CssClassName.and(value: String): String = "$this.$value"

public infix fun Pair<String, String>.and(pair: Pair<String, String>): MutableList<Pair<String, String>> =
    mutableListOf(this).apply { add(pair) }
