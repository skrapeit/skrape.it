package it.skrape.selects

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.core.Headers
import it.skrape.core.Result


/**
 * Will return a certain response headers value
 * @see <a href="https://developer.mozilla.org/en-US/docs/Glossary/Response_header">Explanation about response headers.</a>
 * @param name that represents the
 * @return String with value of a certain response header
 */
infix fun Result.httpHeader(name: String): String? = this.headers[name]

@SkrapeItDslMarker
fun Result.httpHeaders(init: Headers.() -> Unit): Map<String, String> {
    headers.apply(init)
    return headers
}

@SkrapeItDslMarker
fun Result.httpHeader(name: String, init: String?.() -> Unit): String? {
    val header = headers[name]
    header.apply(init)
    return header
}

@SkrapeItDslMarker
fun <T> Result.htmlDocument(init: Doc.() -> T) = document.init()
