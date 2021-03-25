package it.skrape.fetcher

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.apache.ApacheEngineConfig
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.timeout
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.headers
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.client.statement.request
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.http.setCookie
import io.ktor.http.toHttpDate
import io.ktor.util.flattenEntries
import io.ktor.util.network.hostname
import io.ktor.util.network.port
import org.apache.http.HttpHost
import org.apache.http.conn.ssl.NoopHostnameVerifier
import org.apache.http.conn.ssl.TrustSelfSignedStrategy
import org.apache.http.ssl.SSLContextBuilder
import java.net.Proxy

internal fun Request.toHttpRequest(): HttpRequestBuilder {
    val request = this
    return HttpRequestBuilder().apply {
        method = request.method.toHttpMethod()
        url(request.url)
        headers {
            request.headers.forEach { (k, v) ->
                append(k, v)
            }
            append("User-Agent", request.userAgent)
            cookies = request.cookies
            if (request.authentication != null) {
                append("Authorization", request.authentication!!.toHeaderValue())
            }
        }
        timeout {
            socketTimeoutMillis = request.timeout.toLong()
        }
    }
}

internal fun HttpClientConfig<ApacheEngineConfig>.installBasicAuth() {
    engine {
        customizeRequest {
            setAuthenticationEnabled(true)
        }
    }
}

internal fun HttpClientConfig<ApacheEngineConfig>.installTokenAuth(token: String) {
    defaultRequest {
        headers {
            append("Authorization", "token $token")
        }
    }
}

internal fun Method.toHttpMethod(): HttpMethod = when (this) {
    Method.GET -> HttpMethod.Get
    Method.POST -> HttpMethod.Post
    Method.HEAD -> HttpMethod.Head
    Method.DELETE -> HttpMethod.Delete
    Method.PATCH -> HttpMethod.Patch
    Method.PUT -> HttpMethod.Put
}

internal fun io.ktor.http.Cookie.toDomainCookie(origin: String): Cookie {
    val path = this.path ?: "/"
    val expires = this.expires?.toHttpDate().toExpires()
    val domain = when (val domainUrl = this.domain) {
        null -> Domain(origin, false)
        else -> Domain(domainUrl.urlOrigin(), true)
    }
    val sameSite = this.extensions["SameSite"].toSameSite()
    val maxAge = this.maxAge.toMaxAge()

    return Cookie(this.name, this.value, expires, maxAge, domain, path, sameSite, this.secure, this.httpOnly)
}

internal fun Int.toMaxAge(): Int? = when (this) {
    0 -> null
    else -> this
}

/** Remove http:// or https://, any subdirectories, and port if those exist */
internal fun String.urlOrigin() = this.substringAfter("://").substringBefore("/").substringBefore(":")

internal fun String?.toExpires(): Expires {
    return when (this) {
        null -> Expires.Session
        else -> Expires.Date(this)
    }
}

internal fun String?.toSameSite(): SameSite = when (this?.toLowerCase()) {
    "strict" -> SameSite.STRICT
    "lax" -> SameSite.LAX
    "none" -> SameSite.NONE
    else -> SameSite.LAX
}

internal fun HttpClientConfig<ApacheEngineConfig>.trustSelfSignedClient() {
    engine {
        customizeClient {
            setSSLContext(
                SSLContextBuilder
                    .create()
                    .loadTrustMaterial(TrustSelfSignedStrategy())
                    .build()
            )
            setSSLHostnameVerifier(NoopHostnameVerifier())
        }
    }
}

internal suspend fun HttpResponse.toResult(): Result = Result(
    responseBody = this.readText(),
    responseStatus = this.toStatus(),
    contentType = this.contentType()?.toString()?.replace(" ", ""),
    headers = this.headers.flattenEntries()
        .associateBy({ item -> item.first }, { item -> this.headers[item.first]!! }),
    cookies = this.setCookie().map { cookie -> cookie.toDomainCookie(this.request.url.toString().urlOrigin()) },
    baseUri = this.request.url.toString()
)

internal fun HttpResponse.toStatus() = Result.Status(this.status.value, this.status.description)

internal fun Proxy.toHttpHost(): HttpHost? = when (this.type()) {
    Proxy.NO_PROXY -> null
    Proxy.Type.HTTP -> HttpHost(this.address().hostname, this.address().port)
    else -> null
}


