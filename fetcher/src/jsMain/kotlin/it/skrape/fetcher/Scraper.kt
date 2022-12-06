package it.skrape.fetcher

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.js.*
import it.skrape.SkrapeItDsl
import kotlinx.coroutines.*

actual val platformConfig: KtorClientPlatformConfig<*> = object : KtorClientPlatformConfig<HttpClientEngineConfig> {

    val IS_BROWSER: Boolean = js(
        "typeof window !== 'undefined' && typeof window.document !== 'undefined' || typeof self !== 'undefined' && typeof self.location !== 'undefined'" // ktlint-disable max-line-length
    ) as Boolean

    val IS_NODE: Boolean = js(
        "typeof process !== 'undefined' && process.versions != null && process.versions.node != null"
    ) as Boolean

    override val engine: HttpClientEngineFactory<HttpClientEngineConfig> = Js
    override val config: HttpClientConfig<HttpClientEngineConfig>.() -> Unit = {
        //Js browser fetch kinda sucks, we can't manually handle 3xx response
        if (IS_BROWSER) followRedirects = true
    }
    override val sslRelaxedConfig: HttpClientConfig<HttpClientEngineConfig>.() -> Unit
        get() = TODO("Not yet implemented")

}