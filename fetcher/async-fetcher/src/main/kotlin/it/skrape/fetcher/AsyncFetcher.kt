package it.skrape.fetcher

import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*

public object AsyncFetcher : NonBlockingFetcher<Request> {

    override val requestBuilder: Request get() = Request()

    override suspend fun fetch(request: Request): Result =
        configuredClient(request).use { client -> client.request(request.toHttpRequest()).toResult() }

    private fun configuredClient(request: Request): HttpClient =
        HttpClient(Apache) {
            expectSuccess = false
            followRedirects = request.followRedirects
            install(HttpTimeout)
            if (request.authentication is BasicAuth) {
                installBasicAuth()
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
        }
}
