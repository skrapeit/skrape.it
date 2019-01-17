package core

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File

class Skraper {

    fun fetch(): Document {
        return Fetcher().fetch().response.parse()
    }

    fun read(pathToFile: String): Document {
        val input = File(pathToFile)
        return Jsoup.parse(input, "UTF-8", "http://skrape.it/")

    }
}
