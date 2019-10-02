package it.skrape.core

import org.jsoup.select.Elements

data class ScrapebleElements(
        val className: String? = null,
        val id: String? = null,
        val attributeKey: String? = null,
        val attribute: Pair<String, String>? = null
): Elements()
