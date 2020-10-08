package it.skrape.ktor

import io.ktor.server.testing.*
import it.skrape.core.htmlDocument
import it.skrape.selects.Doc

/**
 * Will convert a TestApplicationResponse body to a parsed Document.
 * Thereby it will give you the possibility to check HTML or XML from within a Ktor-test.
 */
fun TestApplicationResponse.expectHtml(
    relaxed: Boolean = true,
    init: Doc.() -> Unit
): Doc {
    val response = this.content ?: throw IllegalArgumentException("can not parse document of content null")
    return htmlDocument(response).apply { this.relaxed = relaxed }.also(init)
}

/**
 * Helper function to run the `expectHtml` function with the TestApplicationCall response
 */
fun TestApplicationCall.expectHtml(
    relaxed: Boolean = true,
    init: Doc.() -> Unit
): Doc = response.expectHtml(relaxed, init)
