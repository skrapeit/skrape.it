package it.skrape.select

import it.skrape.core.Result
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

fun Result.el(selector: String): Element = this.document().selectFirst(selector)

fun Result.`$`(selector: String): Elements = this.document().select(selector)

