package it.skrape.core


class Selector(
        val withClass: String? = null,
        val withClasses: List<String>? = null,
        val withId: String? = null,
        val withAttributeKey: String? = null,
        val withAttributeKeys: List<String>? = null,
        val withAttribute: Pair<String, String>? = null,
        val withAttributes: List<Pair<String, String>>? = null
) {

    fun toCssSelector(rawCssSelector: String): String {
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
