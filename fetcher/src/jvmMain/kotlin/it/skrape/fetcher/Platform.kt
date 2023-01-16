package it.skrape.fetcher

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import it.skrape.SkrapeItDsl
import kotlinx.coroutines.runBlocking
import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager
import kotlin.reflect.full.createInstance

actual val platformConfig: KtorClientPlatformConfig<*> = object : KtorClientPlatformConfig<CIOEngineConfig> {

    override val engine: HttpClientEngineFactory<CIOEngineConfig> = CIO
    override val config: HttpClientConfig<CIOEngineConfig>.() -> Unit = {}
    override val sslRelaxedConfig: HttpClientConfig<CIOEngineConfig>.() -> Unit = {
        engine {
            https.trustManager = object : X509TrustManager {
                override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {}
                override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {}
                override fun getAcceptedIssuers(): Array<X509Certificate> = emptyArray()
            }
        }
    }

}

/**
 * Create a scraper config with given parameters or defaults.
 * @return Result
 */
@SkrapeItDsl
fun <R, T> skrapeBlocking(
    config: HttpClientConfig<*>.()->Unit = Scraper.EMPTY_CONFIG,
    init: suspend Scraper.() -> T
): T = runBlocking { skrape(config, init) }

/**
 * Blocking implementation of 'extract' as convenience function to call it outside of an coroutine.
 * @return T
 */
@SkrapeItDsl
fun <T> Scraper.extractBlocking(scrapingResult: ScrapingResult.() -> T): T =
    runBlocking { response(scrapingResult) }

/**
 * Execute http call with a given Fetcher implementation and invoke the fetching result as this and any given generic as it.
 * Attention: extract to class is only supported for classes where all parameters have default values
 * @return T
 */
@SkrapeItDsl
suspend inline fun <reified T : Any> Scraper.extractIt(crossinline scrapingResult: ScrapingResult.(T) -> Unit): T =
    with(T::class) {
        response { createInstance().also { scrapingResult(it) } }
    }

/**
 * Blocking implementation of 'extractIt' as convenience function to call it outside of an coroutine.
 * @return T
 */
@SkrapeItDsl
inline fun <reified T : Any> Scraper.extractItBlocking(crossinline scrapingResult: ScrapingResult.(T) -> Unit): T =
    runBlocking { extractIt(scrapingResult) }

/**
 * Blocking implementation of 'expect' as convenience function to call it outside of an coroutine.
 * @return T
 */
@SkrapeItDsl
fun Scraper.expectBlocking(scrapingResult: ScrapingResult.() -> Unit) {
    runBlocking { response(scrapingResult) }
}