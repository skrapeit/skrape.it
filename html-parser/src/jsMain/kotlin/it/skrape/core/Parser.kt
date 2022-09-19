package it.skrape.core

import io.ktor.utils.io.charsets.*
import it.skrape.selects.Doc
import it.skrape.selects.platform.Document
import org.w3c.dom.asList
import org.w3c.dom.parsing.DOMParser

internal actual class Parser actual constructor(
        actual var html: String,
        actual val charset: Charset,
        actual val jsExecution: Boolean,
        actual val baseUri: String
    ) {

    actual fun parse(): Doc {
        val doc = DOMParser().parseFromString(html, "text/html")
        return Doc(Document(doc))
    }

}