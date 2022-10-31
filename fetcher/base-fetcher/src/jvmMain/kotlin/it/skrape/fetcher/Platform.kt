package it.skrape.fetcher

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import it.skrape.SkrapeItDsl
import kotlinx.coroutines.runBlocking
import kotlin.reflect.full.createInstance

actual val platformConfig: KtorClientPlatformConfig<*> = object : KtorClientPlatformConfig<HttpClientEngineConfig> {
    override val engine: HttpClientEngineFactory<HttpClientEngineConfig> = CIO
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
): T = runBlocking {
    Scraper(FetcherConverter(fetcher)).init()
}

/**
 * Blocking implementation of 'extract' as convenience function to call it outside of an coroutine.
 * @return T
 */
@SkrapeItDsl
fun <T> Scraper<*>.extractBlocking(result: Result.() -> T): T =
    runBlocking { response(result) }

/**
 * Execute http call with a given Fetcher implementation and invoke the fetching result as this and any given generic as it.
 * Attention: extract to class is only supported for classes where all parameters have default values
 * @return T
 */
@SkrapeItDsl
suspend inline fun <reified T : Any> Scraper<*>.extractIt(crossinline result: Result.(T) -> Unit): T =
    with(T::class) {
        response { createInstance().also { result(it) } }
    }

/**
 * Blocking implementation of 'extractIt' as convenience function to call it outside of an coroutine.
 * @return T
 */
@SkrapeItDsl
inline fun <reified T : Any> Scraper<*>.extractItBlocking(crossinline result: Result.(T) -> Unit): T =
    runBlocking { extractIt(result) }

/**
 * Blocking implementation of 'expect' as convenience function to call it outside of an coroutine.
 * @return T
 */
@SkrapeItDsl
fun Scraper<*>.expectBlocking(result: Result.() -> Unit) {
    runBlocking { response(result) }
}