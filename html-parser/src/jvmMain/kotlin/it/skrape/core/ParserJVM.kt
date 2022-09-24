package it.skrape.core

import io.ktor.utils.io.charsets.*
import it.skrape.fetcher.BrowserFetcher
import it.skrape.selects.Doc
import it.skrape.selects.platform.Document
import java.io.File
import org.jsoup.parser.Parser.parse as jSoupParser

internal actual class Parser actual constructor(
    actual var html: String,
    actual val charset: Charset,
    actual val jsExecution: Boolean,
    actual val baseUri: String
) {

    actual fun parse(): Doc {
        return if (jsExecution) {
            checkBrowserFetcherIsPresent()
            jSoupParser(BrowserFetcher.render(html), baseUri).toDocWrapper()
        } else jSoupParser(html, baseUri).toDocWrapper()
    }

    private fun checkBrowserFetcherIsPresent() {
        try {
            Class.forName("it.skrape.fetcher.BrowserFetcherKt")
        } catch (e: ClassNotFoundException) {
            throw MissingDependencyException(
                """
                ⚠️ You need to add browser-fetcher dependency to execute Javascript.
                💡 Please add 'it.skrape:skrapeit-browser-fetcher' dependency to your project.
                🧐 Find overview of latest releases: https://search.maven.org/artifact/it.skrape/skrapeit-browser-fetcher
                """.trimIndent()
            )
        }
    }

    private fun Document.toDocWrapper() = Doc(this)

    class MissingDependencyException(message: String = "") :
        Exception(message)
}

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
): T = htmlDocument(file, charset, jsExecution, baseUri).init()

public fun htmlDocument(
    file: File,
    charset: Charset = Charsets.UTF_8,
    jsExecution: Boolean = false,
    baseUri: String = ""
): Doc = htmlDocument(file.readText(charset), charset, jsExecution, baseUri)