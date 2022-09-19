package it.skrape.fetcher

import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.network.sockets.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

public object AsyncFetcher : NonBlockingFetcher<Request> {

    override val requestBuilder: Request get() = Request()

    override suspend fun fetch(request: Request): Result = configuredClient(request).toResult()

    @Suppress("MagicNumber")
    private suspend fun configuredClient(request: Request): HttpResponse =
        HttpClient(Apache) {
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

                handleResponseExceptionWithRequest { cause: Throwable, _ ->
                    when (cause) {
                        is SocketTimeoutException -> {
                            throw cause
                        }
                    }
                }
            }
            engine {
                request.proxy?.let {
                    customizeClient {
                        setProxy(it.toHttpHost())
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
            it.request(request.toHttpRequest())
        }
}
