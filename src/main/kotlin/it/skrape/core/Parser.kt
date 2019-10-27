package it.skrape.core

import it.skrape.SkrapeItDslMarker
import it.skrape.core.fetcher.BrowserFetcher
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.util.Base64

class Parser(var html: String = "") {

    fun parse(): Doc = Jsoup.parse(html).toDocWrapper()

    fun parseDom(): Doc = BrowserFetcher(Request(url = html.toUriScheme())).fetch().document

    private fun Document.toDocWrapper() = Doc(this)

    private fun String.toUriScheme(): String {
        val dataUriMimeType = "data:text/html;charset=utf-8;"
        val base64encoded = Base64.getEncoder().encodeToString(toByteArray())
        return "${dataUriMimeType}base64,$base64encoded"
    }
}

/**
 * Read and parse html from a String.
 * @param html represents a html snippet
 */
@SkrapeItDslMarker
fun htmlDocument(html: String, init: Doc.() -> Unit): Doc {
    return Parser(html).parse().also(init)
}
