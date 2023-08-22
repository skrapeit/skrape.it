package it.skrape.fetcher

import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.network.sockets.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import java.net.URL

public object HttpFetcher : BlockingFetcher<Request> {

    override val requestBuilder: Request get() = Request()

    override fun fetch(request: Request): Result = configuredClient(request).toResult()

    @Suppress("MagicNumber")
    private fun configuredClient(request: Request): HttpResponse =
        HttpClient(Apache) {
            val resource: URL? =
                HttpFetcher::class.java.classLoader.getResource("org/apache/http/message/BasicLineFormatter.class")
            println("resource: $resource")
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

                handleResponseExceptionWithRequest { cause: Throwable, _: HttpRequest ->
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
        }.use {
            runBlocking { it.request(request.toHttpRequest()) }
        }
}
