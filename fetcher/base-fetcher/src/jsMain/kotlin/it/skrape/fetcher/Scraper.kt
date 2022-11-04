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