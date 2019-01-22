package it.skrape.core

import org.jsoup.Jsoup
import java.io.File

class Reader(
        var pathToFile: String = "",
        var charset: String = "UTF-8"
) {

    fun read(): Doc {
        val input = File(pathToFile)
        return Jsoup.parse(input, charset, "http://skrape.it/")
    }
}