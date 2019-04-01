package it.skrape.core

import org.jsoup.Jsoup
import java.io.File
import java.nio.charset.Charset

internal class Reader(
        var file: File,
        var charset: Charset = Charsets.UTF_8
) {

    internal fun read(): Doc = Jsoup.parse(file, charset.name(), "http://skrape.it/")

}
