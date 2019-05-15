package it.skrape.core

import it.skrape.core.fetcher.BrowserFetcher
import org.jsoup.Jsoup
import java.util.*

class Parser(var html: String = "") {

    private val dataUriMimeType = "data:text/html;charset=utf-8;"

    fun parse(): Doc = Jsoup.parse(html)

    fun parseDom(): Doc = BrowserFetcher(Request(url = html.toUriScheme())).fetch().document

    private fun String.toUriScheme(): String {
        val base64encoded = Base64.getEncoder().encodeToString(toByteArray())
        return "${dataUriMimeType}base64,$base64encoded"
    }
}
