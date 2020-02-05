package it.skrape.core

import it.skrape.SkrapeItDsl
import it.skrape.core.fetcher.BrowserFetcher
import it.skrape.core.fetcher.Request
import it.skrape.selects.Doc
import org.jsoup.nodes.Document
import java.io.File
import java.nio.charset.Charset
import java.util.*

internal class Parser(
        var html: String,
        val charset: Charset,
        val jsExecution: Boolean,
        val baseUri: String
) {

    fun parse(): Doc {
        return if (jsExecution) {
            BrowserFetcher(Request(url = html.toUriScheme())).fetch().document
        } else org.jsoup.parser.Parser.parse(html, baseUri).toDocWrapper()
    }

    private fun Document.toDocWrapper() = Doc(this)

    private fun String.toUriScheme(): String {
        val dataUriMimeType = "data:text/html;charset=utf-8;"
        val base64encoded = Base64.getEncoder().encodeToString(toByteArray())
        return "${dataUriMimeType}base64,$base64encoded"
    }
}

/**
 * Read and parse HTML from a String.
 * @param html represents a html snippet
 */
@SkrapeItDsl
fun htmlDocument(
        html: String,
        charset: Charset = Charsets.UTF_8,
        jsExecution: Boolean = false,
        baseUri: String = "",
        init: Doc.() -> Unit
): Doc = htmlDocument(html, charset, jsExecution, baseUri).also(init)


/**
 * Read and parse a html file from local file-system.
 * @param file
 * @param charset defaults to UTF-8
 * @param jsExecution defaults to false
 * @param baseUri defaults to empty String
 */
@SkrapeItDsl
fun htmlDocument(
        file: File,
        charset: Charset = Charsets.UTF_8,
        jsExecution: Boolean = false,
        baseUri: String = "",
        init: Doc.() -> Unit
): Doc = htmlDocument(file.readText(charset), charset, jsExecution, baseUri).also(init)


fun htmlDocument(
        html: String,
        charset: Charset = Charsets.UTF_8,
        jsExecution: Boolean = false,
        baseUri: String = ""
): Doc = Parser(html, charset, jsExecution, baseUri).parse()

fun htmlDocument(
        file: File,
        charset: Charset = Charsets.UTF_8,
        jsExecution: Boolean = false,
        baseUri: String = ""
): Doc = htmlDocument(file.readText(charset), charset, jsExecution, baseUri)
