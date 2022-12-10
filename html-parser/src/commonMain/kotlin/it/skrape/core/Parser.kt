package it.skrape.core

import io.ktor.utils.io.charsets.*
import io.ktor.utils.io.core.*
import it.skrape.SkrapeItDsl
import it.skrape.fetcher.Result
import it.skrape.selects.Doc

internal expect class Parser {

    constructor(html: String, charset: Charset, jsExecution: Boolean, baseUri: String)

    var html: String
    val charset: Charset
    val jsExecution: Boolean
    val baseUri: String

    fun parse(): Doc

}

class MissingDependencyException(message: String = "") :
    Exception(message)

/**
 * Read and parse HTML from a String.
 * @param html represents a html snippet
 * @param charset defaults to UTF-8
 * @param jsExecution defaults to false
 * @param baseUri defaults to empty String
 */
public fun <T> htmlDocument(
    html: String,
    charset: Charset = Charsets.UTF_8,
    jsExecution: Boolean = false,
    baseUri: String = "",
    init: Doc.() -> T
): T = htmlDocument(html, charset, jsExecution, baseUri).init()

/**
 * Read and parse a html file from Input.
 * @param input
 * @param charset defaults to UTF-8
 * @param jsExecution defaults to false
 * @param baseUri defaults to empty String
 */
public fun <T> htmlDocument(
    input: Input,
    charset: Charset = Charsets.UTF_8,
    jsExecution: Boolean = false,
    baseUri: String = "",
    init: Doc.() -> T
): T = htmlDocument(input, charset, jsExecution, baseUri).init()

@SkrapeItDsl
public fun htmlDocument(
    html: String,
    charset: Charset = Charsets.UTF_8,
    jsExecution: Boolean = false,
    baseUri: String = ""
): Doc = Parser(html, charset, jsExecution, baseUri).parse()

public fun htmlDocument(
    file: Input,
    charset: Charset = Charsets.UTF_8,
    jsExecution: Boolean = false,
    baseUri: String = ""
): Doc = htmlDocument(file.readText(charset = charset), charset, jsExecution, baseUri)

public val Result.document: Doc
    get() = htmlDocument { this }

@SkrapeItDsl
public fun <T> Result.htmlDocument(init: Doc.() -> T): T = htmlDocument(html = responseBody, baseUri = baseUri, jsExecution = jsExecution).init()
