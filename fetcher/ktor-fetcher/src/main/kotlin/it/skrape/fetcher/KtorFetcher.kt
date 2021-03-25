package it.skrape.fetcher

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.HttpResponseValidator
import io.ktor.client.features.HttpTimeout
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.network.sockets.SocketTimeoutException
import kotlinx.coroutines.runBlocking

public object KtorFetcher : Fetcher<Request> {

    override val requestBuilder: Request get() = Request()

    override fun fetch(request: Request): Result = runBlocking { fetchAsync(request) }

    public suspend fun fetchAsync(request: Request): Result = configuredClient(request).toResult()

    @Suppress("MagicNumber")
    private suspend fun configuredClient(request: Request): HttpResponse {

        val client = HttpClient(Apache) {
            expectSuccess = false
            followRedirects = request.followRedirects
            install(HttpTimeout)
            request.authentication?.let { authentication: Authentication ->
                if (authentication is BasicAuth) {
                    installBasicAuth(authentication.username, authentication.password)
                }
            }
            HttpResponseValidator {
                validateResponse { response ->
                    if (response.status.value >= 300) {
                        response.toResult()
                    }
                }
                handleResponseException { cause: Throwable ->
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
        }
        return client.request(request.toHttpRequest())
    }

}


