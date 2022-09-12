package it.skrape.core

import it.skrape.selects.Doc
import org.jsoup.parser.Parser.parse as jSoupParser

public actual fun htmlParser(
    html: String,
    baseUri: String,
) : Doc {
    val document = jSoupParser(html, baseUri)
    return Doc(document)
}
