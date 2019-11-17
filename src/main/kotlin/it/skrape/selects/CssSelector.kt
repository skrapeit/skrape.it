package it.skrape.selects

import it.skrape.SkrapeItElementPicker
import org.jsoup.nodes.Document

@Suppress("TooManyFunctions")
class CssSelector(
        var rawCssSelector: String = "",
        var withClass: CssClassName? = null,
        var withId: String? = null,
        var withAttributeKey: String? = null,
        var withAttributeKeys: List<String>? = null,
        var withAttribute: Pair<String, String>? = null,
        var withAttributes: List<Pair<String, String>>? = null,
        val doc: Doc = Doc(Document(""))
) {

    @SkrapeItElementPicker
    fun <T> findFirst(init: DocElement.() -> T): T =
            doc.findAll(toCssSelector()).findFirst(init)

    @SkrapeItElementPicker
    fun <T> findByIndex(index: Int, init: DocElement.() -> T): T =
            doc.findAll(toCssSelector()).findByIndex(index, init)

    @SkrapeItElementPicker
    fun <T> findSecond(init: DocElement.() -> T): T =
            doc.findAll(toCssSelector()).findSecond(init)

    @SkrapeItElementPicker
    fun <T> findThird(init: DocElement.() -> T): T =
            doc.findAll(toCssSelector()).findThird(init)

    @SkrapeItElementPicker
    fun <T> findLast(init: DocElement.() -> T): T =
            doc.findAll(toCssSelector()).findLast(init)

    @SkrapeItElementPicker
    fun <T> findSecondLast(init: DocElement.() -> T): T =
            doc.findAll(toCssSelector()).findSecondLast(init)

    @SkrapeItElementPicker
    fun <T> findAll(init: DocElements.() -> T) = doc.findAll(toCssSelector(), init)

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

@SkrapeItElementPicker
infix fun CssClassName.and(value: String) = "$this.$value"

@SkrapeItElementPicker
infix fun Pair<String, String>.and(pair: Pair<String, String>): MutableList<Pair<String, String>> {
    val collectedValues = mutableListOf(this)
    collectedValues.add(pair)
    return collectedValues
}
