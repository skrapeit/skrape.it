package it.skrape.core

data class DomSelector(
        var rawCssSelector: String = "",
        var withClass: String? = null,
        var withClasses: List<String>? = null,
        var withId: String? = null,
        var withAttributeKey: String? = null,
        var withAttributeKeys: List<String>? = null,
        var withAttribute: Pair<String, String>? = null,
        var withAttributes: List<Pair<String, String>>? = null,
        val doc: Doc = Doc("")
) {

    fun toCssSelector(): String {
        val calculatedSelector =
                rawCssSelector +
                withId.toIdSelector().orEmpty() +
                withClass.toClassSelector().orEmpty() +
                withClasses.toClassesSelector().orEmpty() +
                withAttributeKey.toAttributeKeySelector().orEmpty() +
                withAttributeKeys.toCssAttributeKeysSelector().orEmpty() +
                withAttribute.toAttributeSelector().orEmpty() +
                withAttributes.toAttributesSelector().orEmpty()
        return calculatedSelector.withoutSpaces()
    }

    private fun String?.toIdSelector() = this?.let { "#$it" }

    private fun String?.toClassSelector() =
            this?.let { ".$it" }

    private fun List<String>?.toClassesSelector() =
            this?.joinToString(prefix = ".", separator = ".")

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
