package it.skrape.fetcher

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import it.skrape.SkrapeItDsl
import it.skrape.fetcher.ContentType

typealias KtorRequestBuilder = HttpRequestBuilder

interface KtorClientPlatformConfig<T : HttpClientEngineConfig> {
    val engine: HttpClientEngineFactory<T>
    val config: HttpClientConfig<T>.()->Unit
}

expect val platformConfig: KtorClientPlatformConfig<*>

class KtorScraper(config: HttpClientConfig<*>.()->Unit = EMPTY_CONFIG) {

    companion object {
        val clientTemplate = HttpClient(platformConfig.engine) {
            followRedirects = false //We handle those ourselves
            install(KtorDynamicPlugin)
            install(HttpTimeout) {
                requestTimeoutMillis = 5000
                socketTimeoutMillis = 5000
                connectTimeoutMillis = 5000
            }

            @Suppress("UNCHECKED_CAST")
            (platformConfig.config as (HttpClientConfig<*>.()->Unit))()
        }

        internal val EMPTY_CONFIG: HttpClientConfig<*>.()->Unit = {}
    }

    private val client: HttpClient
    private val requestBuilder: KtorRequestBuilder = KtorRequestBuilder()

    init {
        client = if (config == EMPTY_CONFIG) {
            clientTemplate
        } else {
            clientTemplate.config(config)
        }
    }

    @SkrapeItDsl
    public fun request(block: KtorRequestBuilder.()->Unit): KtorScraper {
        requestBuilder.apply(block)
        return this
    }

    public suspend fun scrape(): Result = client.request(requestBuilder).toResult()

}

internal fun io.ktor.http.Cookie.toDomainCookie(origin: String): Cookie {
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

internal fun Int.toMaxAge(): Int? = when (this) {
    0 -> null
    else -> this
}

//TODO a lot of this could be done using KTOR components.
private suspend fun HttpResponse.toResult(): Result = Result(
    responseBody = bodyAsText(),
    responseStatus = Result.Status(status.value, status.description),
    contentType = contentType()?.contentType,
    headers = headers.flattenEntries().associateBy({ it.first }, { it.second}),
    baseUri = request.url.toString(),
    cookies = setCookie().map { cookie -> cookie.toDomainCookie(this.request.url.toString().urlOrigin) }
)

/*
 * Custom client plugin to handle redirects and user agent on a per-request basis
 * The code is mostly copied from the Ktor Plugins
 */
class KtorDynamicPlugin {

    companion object: HttpClientPlugin<Nothing, KtorDynamicPlugin> {
        internal val KEY_USERAGENT = AttributeKey<String>("it.skrape.fetcher.KtorScraper.UserAgent")
        internal const val defaultUserAgent = "Mozilla/5.0 skrape.it"
        internal val KEY_FOLLOW_REDIRECT = AttributeKey<Boolean>("it.skrape.fetcher.KtorScraper.FollowRedirect")
        internal val KEY_AUTHENTICATION = AttributeKey<Authentication>("it.skrape.fetcher.KtorScraper.Authentication")
        internal val KEY_SSL_RELAXED = AttributeKey<Boolean>("it.skrape.fetcher.KtorScraper.SslRelaxed")

        override val key: AttributeKey<KtorDynamicPlugin> = AttributeKey("it.skrape.fetcher.KtorDynamicPlugin")

        override fun prepare(block: Nothing.() -> Unit): KtorDynamicPlugin = KtorDynamicPlugin()

        override fun install(plugin: KtorDynamicPlugin, scope: HttpClient) {
            scope.plugin(HttpSend).intercept { context ->
                //If the request has a defined UserAgent set it
                val userAgent = context.attributes.getOrNull(KEY_USERAGENT) ?: defaultUserAgent
                context.headers.appendMissing(HttpHeaders.UserAgent, listOf(userAgent))
                //If we set authentication apply it
                if (context.attributes.contains(KEY_AUTHENTICATION)) {
                    context.headers.appendMissing(HttpHeaders.Authorization, listOf(context.attributes[KEY_AUTHENTICATION].toHeaderValue()))
                }
                val origin = execute(context)
                if (context.attributes.getOrNull(KEY_FOLLOW_REDIRECT) == true)
                    handleCall(
                        context,
                        origin,
                        allowHttpsDowngrade = context.attributes.getOrNull(KEY_SSL_RELAXED) == true,
                        client = scope)
                else
                    origin
            }
        }

        @OptIn(InternalAPI::class)
        private suspend fun Sender.handleCall(
            context: HttpRequestBuilder,
            origin: HttpClientCall,
            allowHttpsDowngrade: Boolean,
            client: HttpClient
        ): HttpClientCall {
            if (!origin.response.status.isRedirect()) return origin

            var call = origin
            var requestBuilder = context
            val originProtocol = origin.request.url.protocol
            val originAuthority = origin.request.url.authority

            while (true) {
                client.monitor.raise(HttpRedirect.HttpResponseRedirect, call.response)

                val location = call.response.headers[HttpHeaders.Location]

                requestBuilder = HttpRequestBuilder().apply {
                    takeFromWithExecutionContext(requestBuilder)
                    url.parameters.clear()

                    location?.let { url.takeFrom(it) }

                    /**
                     * Disallow redirect with a security downgrade.
                     */
                    if (!allowHttpsDowngrade && originProtocol.isSecure() && !url.protocol.isSecure()) {
                        return call
                    }

                    if (originAuthority != url.authority) {
                        headers.remove(HttpHeaders.Authorization)
                    }
                }

                call = execute(requestBuilder)
                if (!call.response.status.isRedirect()) return call
            }
        }

    }

}

private fun HttpStatusCode.isRedirect(): Boolean = when (value) {
    HttpStatusCode.MovedPermanently.value,
    HttpStatusCode.Found.value,
    HttpStatusCode.TemporaryRedirect.value,
    HttpStatusCode.PermanentRedirect.value,
    HttpStatusCode.SeeOther.value -> true
    else -> false
}

var KtorRequestBuilder.userAgent: String
    get() = this.attributes.getOrNull(KtorDynamicPlugin.KEY_USERAGENT) ?: KtorDynamicPlugin.defaultUserAgent
    set(value) = this.attributes.put(KtorDynamicPlugin.KEY_USERAGENT, value)

var KtorRequestBuilder.followRedirect: Boolean
    get() = attributes.getOrNull(KtorDynamicPlugin.KEY_FOLLOW_REDIRECT) ?: false
    set(value) = attributes.put(KtorDynamicPlugin.KEY_FOLLOW_REDIRECT, value)

var KtorRequestBuilder.authentication: Authentication?
    get() = attributes.getOrNull(KtorDynamicPlugin.KEY_AUTHENTICATION)
    set(value) = if (value == null) attributes.remove(KtorDynamicPlugin.KEY_AUTHENTICATION) else attributes.put(KtorDynamicPlugin.KEY_AUTHENTICATION, value)

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