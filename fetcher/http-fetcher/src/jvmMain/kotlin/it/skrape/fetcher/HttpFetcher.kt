package it.skrape.fetcher

import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.network.sockets.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import io.ktor.util.network.*
import kotlinx.coroutines.runBlocking
import org.apache.http.HttpHost
import org.apache.http.conn.ssl.NoopHostnameVerifier
import org.apache.http.conn.ssl.TrustSelfSignedStrategy
import org.apache.http.ssl.SSLContextBuilder
import java.net.Proxy
import java.net.URL

public actual object HttpFetcher : BlockingFetcher<Request> {

    override val requestBuilder: Request get() = Request()

    override fun fetch(request: Request): Result = configuredClient(request).toResult()

    @Suppress("MagicNumber")
    private fun configuredClient(request: Request): HttpResponse =
        HttpClient(Apache) {
            val resource: URL? =
                HttpFetcher::class.java.classLoader.getResource("org/apache/http/message/BasicLineFormatter.class")
            println("resource: $resource")
            expectSuccess = false
            followRedirects = request.followRedirects
            install(HttpTimeout)
            install(Logging) {
                level = LogLevel.NONE
            }
            request.authentication?.let { authentication: Authentication ->
                if (authentication is BasicAuth) {
                    installBasicAuth()
                }
            }
            HttpResponseValidator {

                handleResponseExceptionWithRequest { cause: Throwable, _: HttpRequest ->
                    when (cause) {
                        is SocketTimeoutException -> {
                            throw cause
                        }
                    }
                }
            }
            engine {
                request.proxy?.toProxy()?.toHttpHost()?.let {
                    customizeClient {
                        setProxy(it)
                    }
                }
                connectionRequestTimeout = request.timeout
                socketTimeout = request.timeout
                followRedirects = request.followRedirects
            }
            if (request.sslRelaxed) {
                trustSelfSignedClient()
            }
        }.use {
            runBlocking { it.request(request.toHttpRequest()) }
        }
}

private fun Request.toHttpRequest(): HttpRequestBuilder {
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
            request.authentication?.run {
                append("Authorization", toHeaderValue())
            }
        }
        request.body?.run {
            setBody(this)
        }
        timeout {
            socketTimeoutMillis = request.timeout.toLong()
        }
    }
}

private fun HttpClientConfig<ApacheEngineConfig>.installBasicAuth() {
    engine {
        customizeRequest {
            setAuthenticationEnabled(true)
        }
    }
}

private fun HttpClientConfig<ApacheEngineConfig>.installTokenAuth(token: String) {
    defaultRequest {
        headers {
            append("Authorization", "token $token")
        }
    }
}

private fun Method.toHttpMethod(): HttpMethod = when (this) {
    Method.GET    -> HttpMethod.Get
    Method.POST   -> HttpMethod.Post
    Method.HEAD   -> HttpMethod.Head
    Method.DELETE -> HttpMethod.Delete
    Method.PATCH  -> HttpMethod.Patch
    Method.PUT    -> HttpMethod.Put
}

private fun io.ktor.http.Cookie.toDomainCookie(origin: String): Cookie {
    val path = this.path ?: "/"
    val expires = this.expires?.toHttpDate().toExpires()
    val domain = when (val domainUrl = this.domain) {
        null -> Domain(origin, false)
        else -> Domain(domainUrl.urlOrigin, true)
    }
    val sameSite = this.extensions["SameSite"].toSameSite()
    val maxAge = this.maxAge.toMaxAge()

    return Cookie(this.name, this.value, expires, maxAge, domain, path, sameSite, this.secure, this.httpOnly)
}

private fun Int.toMaxAge(): Int? = when (this) {
    0 -> null
    else -> this
}

private fun String?.toExpires(): Expires {
    return when (this) {
        null -> Expires.Session
        else -> Expires.Date(this)
    }
}

private fun String?.toSameSite(): SameSite = when (this?.lowercase()) {
    "strict" -> SameSite.STRICT
    "lax" -> SameSite.LAX
    "none" -> SameSite.NONE
    else -> SameSite.LAX
}

private fun HttpClientConfig<ApacheEngineConfig>.trustSelfSignedClient() {
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

private fun HttpResponse.toResult(): Result =
    Result(
        responseBody = runBlocking { bodyAsText() },
        responseStatus = toStatus(),
        contentType = contentType()?.toString()?.replace(" ", ""),
        headers = headers.flattenEntries()
            .associateBy({ item -> item.first }, { item -> headers[item.first]!! }),
        cookies = setCookie().map { cookie -> cookie.toDomainCookie(request.url.toString().urlOrigin) },
        baseUri = request.url.toString()
    )

private fun HttpResponse.toStatus() = Result.Status(this.status.value, this.status.description)

private fun Proxy.toHttpHost(): HttpHost? = when (this.type()) {
    Proxy.NO_PROXY -> null
    Proxy.Type.HTTP -> HttpHost(this.address().hostname, this.address().port)
    else -> null
}
