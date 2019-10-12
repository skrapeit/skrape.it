package it.skrape.core


data class Selector(
        val className: String? = null,
        val classNames: List<String>? = null,
        val id: String? = null,
        val attributeKey: String? = null,
        val attributeKeys: List<String>? = null,
        val attribute: Pair<String, String>? = null,
        val attributes: List<Pair<String, String>>? = null
) {

    fun toCssSelector(rawCssSelector: String): String {
        if (rawCssSelector.isBlank()) {
            val id = id?.let { "#$it" } ?: ""
            val className = className?.let { ".$it" } ?: ""
            val classNames = classNames?.joinToString(prefix = ".", separator = ".") ?: ""
            val attributeKey = attributeKey?.let { "[$it]" } ?: ""
            val attributeKeys = attributeKeys?.joinToString()
            val attribute = attribute?.let { "[${it.first}='${it.second}']" } ?: ""
            val attributes = attributes?.let {  }
            return "$id$className$classNames$attributeKey$attribute"
                    .replace("\\s".toRegex(), "")
        }
        return rawCssSelector
    }

}
