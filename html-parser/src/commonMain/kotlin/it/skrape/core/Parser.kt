package it.skrape.core

import it.skrape.SkrapeItDsl
import it.skrape.fetcher.BrowserFetcher
import it.skrape.fetcher.Result
import it.skrape.selects.Doc
import org.intellij.lang.annotations.Language
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.nio.charset.Charset

internal class Parser(
    var html: String,
    val charset: Charset,
    val jsExecution: Boolean,
    val baseUri: String
) {

    fun parse(): Doc {
        return if (jsExecution) {
            checkBrowserFetcherIsPresent()
            htmlParser(BrowserFetcher.render(html), baseUri)
        } else htmlParser(html, baseUri)
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

    class MissingDependencyException(message: String = "") :
        Exception(message)
}

/**
 * Read and parse HTML from a String.
 * @param html represents a html snippet
 * @param charset defaults to UTF-8
 * @param jsExecution defaults to false
 * @param baseUri defaults to empty String
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
): T = htmlDocument(file, charset, jsExecution, baseUri).init()

/**
 * Read and parse a html file from InputStream.
 * @param bytes
 * @param charset defaults to UTF-8
 * @param jsExecution defaults to false
 * @param baseUri defaults to empty String
 */
public fun <T> htmlDocument(
    bytes: InputStream,
    charset: Charset = Charsets.UTF_8,
    jsExecution: Boolean = false,
    baseUri: String = "",
    init: Doc.() -> T
): T = htmlDocument(bytes, charset, jsExecution, baseUri).init()

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

public fun htmlDocument(
    bytes: InputStream,
    charset: Charset = Charsets.UTF_8,
    jsExecution: Boolean = false,
    baseUri: String = ""
): Doc = htmlDocument(bytes.bufferedReader().use(BufferedReader::readText), charset, jsExecution, baseUri)

public val Result.document: Doc
    get() = htmlDocument { this }

@SkrapeItDsl
public fun <T> Result.htmlDocument(init: Doc.() -> T): T = htmlDocument(html = responseBody, baseUri = baseUri).init()
