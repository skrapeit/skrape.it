package it.skrape.fetcher

import io.ktor.client.plugins.cookies.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*

typealias ScrapingResult = HttpResponse
typealias SkrapeItContentType = String

val RESPONSE_BODY_TEXT = AttributeKey<String>("it.skrape.response.body.text")

@Deprecated("Use Ktor Types", replaceWith = ReplaceWith("this.value"))
val HttpStatusCode.code: Int
    get() = this.value

@Deprecated("Use Ktor Types", replaceWith = ReplaceWith("this.description"))
val HttpStatusCode.message: String
    get() = this.description

fun <T> ScrapingResult.status(block: HttpStatusCode.()->T) : T = status.block()

infix fun ScrapingResult.httpHeader(name: String): String? = this.headers[name]

fun ScrapingResult.httpHeader(name: String, init: String?.() -> Unit): String? {
    val header = headers[name]
    header.apply(init)
    return header
}

fun ScrapingResult.httpHeaders(init: Headers.() -> Unit): Headers {
    headers.apply(init)
    return headers
}

internal suspend fun ScrapingResult.readBody(): ScrapingResult {
    val bodyText = bodyAsText()
    this.call.attributes.put(RESPONSE_BODY_TEXT, bodyText)
    return this
}

//setCookie() doesn't set the domain by itself. Ensure that it's set
val ScrapingResult.cookies
    get() = this.setCookie().map {
        when (it.domain) {
            null -> it.copy(domain = this.request.url.host)
            else -> it
        }
    }

@Deprecated("Use bodyAsText()", ReplaceWith(expression = "this.bodyAsText()", "io.ktor.client.statement.bodyAsText"))
val ScrapingResult.responseBody
    get() = this.call.attributes.getOrNull(RESPONSE_BODY_TEXT) ?: error("Response was not initialized!")

@Deprecated(message = "Use Ktor ContentType", ReplaceWith(expression = "this.contentType()"))
val ScrapingResult.contentType: SkrapeItContentType
    get() = this.contentType().toString()

val ScrapingResult.jsExecution: Boolean
    get() = request.jsExecution

@Deprecated("Use Ktor fields", ReplaceWith("this.request.url.toString()"))
val ScrapingResult.baseUri: String
    get() = request.url.toString()

@Deprecated("Use Ktor fields", ReplaceWith(expression = "this.status"))
val ScrapingResult.responseStatus
    get() = status

public enum class SameSite {
    STRICT,
    LAX,
    NONE
}

val Cookie.sameSite : SameSite
    get() = when(this.extensions["samesite"]?.lowercase()) {
        "lax" -> SameSite.LAX
        "strict" -> SameSite.STRICT
        "none" -> SameSite.NONE
        else -> SameSite.LAX
    }


