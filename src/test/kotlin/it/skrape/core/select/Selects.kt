package it.skrape.core.select

import it.skrape.core.Result
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

infix fun Result.element(selector: String): Element = this.document().selectFirst(selector)

infix fun Result.`$`(selector: String): Elements = this.document().select(selector)

