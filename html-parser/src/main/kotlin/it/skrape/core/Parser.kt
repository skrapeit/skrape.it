package it.skrape.core

import it.skrape.SkrapeItDsl
import it.skrape.fetcher.BrowserFetcher
import it.skrape.fetcher.Request
import it.skrape.fetcher.Result
import it.skrape.selects.Doc
import org.intellij.lang.annotations.Language
import org.jsoup.nodes.Document
import java.io.File
import java.nio.charset.Charset
import java.util.*
import org.jsoup.parser.Parser.parse as jSoupParser

internal class Parser(
    var html: String,
    val charset: Charset,
    val jsExecution: Boolean,
    val baseUri: String
) {

    fun parse(): Doc {
        return if (jsExecution) {
            checkBrowserFetcherIsPresent()
            val mockRequest = Request(url = html.toUriScheme())
            BrowserFetcher.fetch(mockRequest).htmlDocument { this }
        } else jSoupParser(html, baseUri).toDocWrapper()
    }

    private fun checkBrowserFetcherIsPresent() {
        try {
            Class.forName("it.skrape.fetcher.BrowserFetcherKt")
        } catch (e: ClassNotFoundException) {
            throw MissingDependencyException("you need to add browser-fetcher dependency to exec JS")
        }
    }

    private fun Document.toDocWrapper() = Doc(this)

    private fun String.toUriScheme(): String {
        val dataUriMimeType = "data:text/html;charset=${charset.name()};"
        val base64encoded = Base64.getEncoder().encodeToString(toByteArray())
        return "${dataUriMimeType}base64,$base64encoded"
    }

    class MissingDependencyException(message: String = "") :
        Exception(message)
}

/**
 * Read and parse HTML from a String.
 * @param html represents a html snippet
 */
public fun <T> htmlDocument(
    @Language("HTML") html: String,
    charset: Charset = Charsets.UTF_8,
    jsExecution: Boolean = false,
    baseUri: String = "",
    init: Doc.() -> T
): T = htmlDocument(html, charset, jsExecution, baseUri).init()


/**
 * Read and parse a html file from local file-system.
 * @param file
 * @param charset defaults to UTF-8
 * @param jsExecution defaults to false
 * @param baseUri defaults to empty String
 */
public fun <T> htmlDocument(
    file: File,
    charset: Charset = Charsets.UTF_8,
    jsExecution: Boolean = false,
    baseUri: String = "",
    init: Doc.() -> T
): T = htmlDocument(file.readText(charset), charset, jsExecution, baseUri).init()

@SkrapeItDsl
public fun htmlDocument(
    @Language("HTML") html: String,
    charset: Charset = Charsets.UTF_8,
    jsExecution: Boolean = false,
    baseUri: String = ""
): Doc = Parser(html, charset, jsExecution, baseUri).parse()

public fun htmlDocument(
    file: File,
    charset: Charset = Charsets.UTF_8,
    jsExecution: Boolean = false,
    baseUri: String = ""
): Doc = htmlDocument(file.readText(charset), charset, jsExecution, baseUri)

public val Result.document: Doc
    get() = htmlDocument { this }

public fun <T> Result.htmlDocument(init: Doc.() -> T): T = htmlDocument(html = responseBody, baseUri = baseUri).init()
