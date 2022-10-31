package it.skrape.fetcher

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.js.*
import it.skrape.SkrapeItDsl
import kotlinx.coroutines.*

actual val platformConfig: KtorClientPlatformConfig<*> = object : KtorClientPlatformConfig<HttpClientEngineConfig> {
    override val engine: HttpClientEngineFactory<HttpClientEngineConfig> = Js
    override val config: HttpClientConfig<HttpClientEngineConfig>.() -> Unit = {}
}

/**
 * Create a scraper config with given parameters or defaults.
 * @return Result
 */
@SkrapeItDsl
actual fun <R, T> skrape(
    fetcher: BlockingFetcher<R>,
    init: suspend Scraper<R>.() -> T
): dynamic {
    return GlobalScope.promise { Scraper(FetcherConverter(fetcher)).init() }
}