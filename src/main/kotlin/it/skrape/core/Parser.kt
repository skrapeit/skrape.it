package it.skrape.core

import org.jsoup.Jsoup

class Parser(var html: String = "") {

    fun parse(): Doc = Jsoup.parse(html)
}
