package it.skrape.core

import com.gargoylesoftware.htmlunit.BrowserVersion
import com.gargoylesoftware.htmlunit.WebClient
import io.ktor.utils.io.charsets.*
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
            checkForHtmlUnit()
            val renderedHtml =
                WebClient(BrowserVersion.BEST_SUPPORTED).use { it.loadHtmlCodeIntoCurrentWindow(html).asXml() }
            jSoupParser(renderedHtml, baseUri).toDocWrapper()
        } else jSoupParser(html, baseUri).toDocWrapper()
    }

    private fun checkForHtmlUnit() {
        try {
            Class.forName("com.gargoylesoftware.htmlunit.WebClient")
        } catch (e: ClassNotFoundException) {
            throw MissingDependencyException(
                """
                ⚠️ You need to add HtmlUnit dependency to execute Javascript.
                💡 Please add 'net.sourceforge.htmlunit:htmlunit' dependency to your project.
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
fun <T> htmlDocument(
    file: File,
    charset: Charset = Charsets.UTF_8,
    jsExecution: Boolean = false,
    baseUri: String = "",
    init: Doc.() -> T
): T = htmlDocument(file, charset, jsExecution, baseUri).init()

fun htmlDocument(
    file: File,
    charset: Charset = Charsets.UTF_8,
    jsExecution: Boolean = false,
    baseUri: String = ""
): Doc = htmlDocument(file.readText(charset), charset, jsExecution, baseUri)