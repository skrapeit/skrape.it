package it.skrape.selects

import it.skrape.core.ScrapebleElements

fun calculatedSelector(parameterSelector: String, element: ScrapebleElements): String {
    if (parameterSelector.isBlank()) {
        val id = element.id?.let { "#$it" } ?: ""
        val className = element.className?.let { ".$it" } ?: ""
        val attributeKey = element.attributeKey?.let { "[$it]" } ?: ""
        val attribute = element.attribute?.let { "[${it.first}='${it.second}']" } ?: ""
        return "$id$className$attributeKey$attribute"
    }
    return parameterSelector
}
