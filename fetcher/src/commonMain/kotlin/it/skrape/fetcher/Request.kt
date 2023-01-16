package it.skrape.fetcher

import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*
import it.skrape.fetcher.request.BodyBuilder

typealias KtorRequestBuilder = HttpRequestBuilder

var KtorRequestBuilder.userAgent: String
    get() = this.attributes.getOrNull(KtorDynamicPlugin.KEY_USERAGENT) ?: KtorDynamicPlugin.defaultUserAgent
    set(value) = this.attributes.put(KtorDynamicPlugin.KEY_USERAGENT, value)

var KtorRequestBuilder.followRedirects: Boolean
    get() = attributes.getOrNull(KtorDynamicPlugin.KEY_FOLLOW_REDIRECT) ?: false
    set(value) = attributes.put(KtorDynamicPlugin.KEY_FOLLOW_REDIRECT, value)

var KtorRequestBuilder.authentication: Authentication?
    get() = attributes.getOrNull(KtorDynamicPlugin.KEY_AUTHENTICATION)
    set(value) = if (value == null) attributes.remove(KtorDynamicPlugin.KEY_AUTHENTICATION) else attributes.put(
        KtorDynamicPlugin.KEY_AUTHENTICATION,
        value
    )

var KtorRequestBuilder.sslRelaxed: Boolean
    get() = attributes.getOrNull(KtorDynamicPlugin.KEY_SSL_RELAXED) ?: false
    set(value) = attributes.put(KtorDynamicPlugin.KEY_SSL_RELAXED, value)

var KtorRequestBuilder.timeout: Int
    get() = getCapabilityOrNull(HttpTimeout)?.requestTimeoutMillis?.toInt() ?: 5000
    set(value) = timeout {
        requestTimeoutMillis = value.toLong()
        socketTimeoutMillis = value.toLong()
        connectTimeoutMillis = value.toLong()
    }

var KtorRequestBuilder.jsExecution: Boolean
    get() = attributes.getOrNull(Scraper.KEY_JS_EXECUTION) ?: false
    set(value) = attributes.put(Scraper.KEY_JS_EXECUTION, value)


fun KtorRequestBuilder.copy(
    url: String = "",
    method: HttpMethod = this.method,
    headers: HeadersBuilder = HeadersBuilder(),
    body: Any = this.body,
    attributes: Attributes = Attributes(),
    userAgent: String = this.userAgent,
    followRedirects: Boolean = this.followRedirects,
    authentication: Authentication? = this.authentication,
    sslRelaxed: Boolean = this.sslRelaxed,
    timeout: Int = this.timeout
) = KtorRequestBuilder().takeFrom(this).also {
    it.url.takeFrom(url)
    it.method = method
    it.headers.appendAll(headers)
    it.setBody(body)
    it.attributes.putAll(attributes)
    it.userAgent = userAgent
    it.followRedirects = followRedirects
    it.authentication = authentication
    it.sslRelaxed = sslRelaxed
    it.timeout = timeout
}

fun KtorRequestBuilder.body(block: BodyBuilder.() -> Unit) {
    val builder = BodyBuilder().apply(block)
    contentType(io.ktor.http.ContentType.parse(builder.contentType))
    setBody(builder.data)
}

val HttpRequest.jsExecution: Boolean
    get() = attributes.getOrNull(Scraper.KEY_JS_EXECUTION)
        ?: call.client.jsExecution
