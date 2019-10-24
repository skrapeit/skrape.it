package it.skrape.core

import it.skrape.selects.selection
import org.jsoup.nodes.Document

class Doc(
        val document: Document
) {

    fun text() = document.text()

    fun title() = document.title()

    fun selectFirst(cssQuery: String) = document.selectFirst(cssQuery)

    fun select(cssQuery: String) = document.select(cssQuery)

    override fun toString() = document.toString()

    operator fun String.invoke(init: DomSelector.() -> Unit) =
            this@Doc.selection(this, init)
}
