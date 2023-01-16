package it.skrape.fetcher

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import io.ktor.util.reflect.*
import it.skrape.SkrapeItDsl

interface KtorClientPlatformConfig<T : HttpClientEngineConfig> {
    val engine: HttpClientEngineFactory<T>
    val config: HttpClientConfig<T>.() -> Unit
    val sslRelaxedConfig: HttpClientConfig<T>.() -> Unit
}

expect val platformConfig: KtorClientPlatformConfig<*>

class Scraper(
    val requestBuilder: KtorRequestBuilder = KtorRequestBuilder(),
    config: HttpClientConfig<*>.() -> Unit = EMPTY_CONFIG
) {

    @Deprecated("Renamed", replaceWith = ReplaceWith("requestBuilder"))
    val preparedRequest
        get() = requestBuilder

    companion object {
        internal val KEY_JS_EXECUTION = AttributeKey<Boolean>("it.skrape.fetcher.jsExecution")

        val defaultClientConfig: HttpClientConfig<*>.() -> Unit = {
            followRedirects = false //We handle those ourselves
            install(KtorDynamicPlugin)
            install(HttpCookies)
            install(HttpTimeout) {
                requestTimeoutMillis = 5000
                socketTimeoutMillis = 5000
                connectTimeoutMillis = 5000
            }

            @Suppress("UNCHECKED_CAST")
            (platformConfig.config as (HttpClientConfig<*>.() -> Unit))()
        }

        internal val EMPTY_CONFIG: HttpClientConfig<*>.() -> Unit = {}
    }

    private val client: HttpClient by lazy {
        HttpClient(platformConfig.engine) {
            defaultClientConfig()
            config()
        }
    }

    @Suppress("UNCHECKED_CAST")
    private val relaxedClient by lazy {
        HttpClient(platformConfig.engine) {
            defaultClientConfig()
            config()
            (platformConfig.sslRelaxedConfig as HttpClientConfig<*>.() -> Unit)()
        }
    }

    @SkrapeItDsl
    fun request(block: KtorRequestBuilder.() -> Unit): Scraper {
        requestBuilder.apply(block)
        return this
    }

    suspend fun scrape(): ScrapingResult = (
            if (requestBuilder.sslRelaxed)
                relaxedClient.request(requestBuilder)
            else
                client.request(requestBuilder)
            ).readBody()

}

//Provide default configurations similar to the old fetchers
@Deprecated("")
val BrowserFetcher: HttpClientConfig<*>.()->Unit = {
    useBrowserUserAgent()
    withJsExecution()
}
@Deprecated("")
val HttpFetcher: HttpClientConfig<*>.()->Unit = Scraper.EMPTY_CONFIG
@Deprecated("")
val AsyncFetcher: HttpClientConfig<*>.()->Unit = {
    expectSuccess = false
}

/*
 * Custom client plugin to handle redirects and user agent on a per-request basis
 * The code is mostly copied from the Ktor Plugins
 */
class KtorDynamicPlugin {

    companion object : HttpClientPlugin<Nothing, KtorDynamicPlugin> {
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
                if (!context.headers.contains(HttpHeaders.UserAgent) && context.attributes.contains(KEY_USERAGENT)) {
                    context.headers.appendMissing(HttpHeaders.UserAgent, listOf(context.attributes[KEY_USERAGENT]))
                } else if (!context.headers.contains(HttpHeaders.UserAgent)) {
                    context.headers.appendMissing(HttpHeaders.UserAgent, listOf(defaultUserAgent))
                }
                //If we set authentication apply it
                if (context.attributes.contains(KEY_AUTHENTICATION)) {
                    context.headers.appendMissing(
                        HttpHeaders.Authorization,
                        listOf(context.attributes[KEY_AUTHENTICATION].toHeaderValue())
                    )
                }
                val origin = execute(context)
                if (context.attributes.getOrNull(KEY_FOLLOW_REDIRECT) != false)
                    handleCall(
                        context,
                        origin,
                        allowHttpsDowngrade = context.attributes.getOrNull(KEY_SSL_RELAXED) == true,
                        client = scope
                    )
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

val HttpClient.jsExecution: Boolean
    get() = attributes.getOrNull(Scraper.KEY_JS_EXECUTION) ?: false

fun HttpClientConfig<*>.withJsExecution() {
    this.install("jsExecution") {
        attributes.put(Scraper.KEY_JS_EXECUTION, true)
    }
}

fun URLBuilder.queryParam(block: SkrapeItQueryBuilder.() -> Unit) {
    SkrapeItQueryBuilder(parameters).block()
}

class SkrapeItQueryBuilder internal constructor(private val parameters: ParametersBuilder) {
    infix fun String.to(value: Any?) {
        when (value) {
            null -> parameters.append(this, "null")
            is List<*> -> parameters.appendAll(this, value.map { it.toString() })
            else -> parameters.append(this, "$value")
        }
    }

    operator fun String.unaryPlus() {
        if (this.isNotBlank()) parameters.appendAll(this, emptyList())
    }
}


fun HttpClientConfig<*>.useBrowserUserAgent() {
    install(UserAgent) {
        //Use a chrome userAgent
        agent =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.53 Safari/537.36"
    }
}