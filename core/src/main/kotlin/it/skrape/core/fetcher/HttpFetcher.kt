package it.skrape.core.fetcher

import io.github.rybalkinsd.kohttp.client.client
import io.github.rybalkinsd.kohttp.client.defaultHttpClient
import io.github.rybalkinsd.kohttp.client.fork
import io.github.rybalkinsd.kohttp.dsl.*
import io.github.rybalkinsd.kohttp.dsl.context.HttpContext
import io.github.rybalkinsd.kohttp.ext.url
import it.skrape.core.fetcher.Method.*
import okhttp3.OkHttpClient
import okhttp3.Response
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

public object HttpFetcher : Fetcher<Request> {
    override val requestBuilder: Request get() = Request()

    override fun fetch(request: Request): Result {
        configuredClient(request).use {
            return Result(
                    responseBody = it.body()?.string() ?: "",
                    responseStatus = it.toStatus(),
                    contentType = it.header("Content-Type"),
                    headers = it.headers().names().associateBy({ item -> item }, { item -> it.header(item, "")!! }),
                    cookies = it.headers("Set-Cookie").map { item -> item.toCookie(request.url.urlOrigin()) },
                    baseUri = request.url
            )
        }
    }

    private fun Response.toStatus() = Result.Status(code(), message())

    private fun configuredClient(request: Request): Response {
        val configuredClient = client {
            defaultHttpClient
            proxy = request.proxy?.toProxy()
        }

        val client = configuredClient
                .withSslConfiguration(request)
                .fork {
                    followRedirects = request.followRedirects
                    readTimeout = request.timeout.toLong()
                }

        val context: HttpContext.() -> Unit = {
            url(request.url)
            header {
                request.headers.forEach { (k, v) ->
                    k to v
                }
                "User-Agent" to request.userAgent
                cookie {
                    request.cookies
                }

                if (request.authentication != null) {
                    "Authorization" to request.authentication!!.toHeaderValue()
                }
            }
        }

        return when (request.method) {
            GET -> httpGet(client, context)
            POST -> httpPost(client, context)
            PUT -> httpPut(client, context)
            DELETE -> httpDelete(client, context)
            PATCH -> httpPatch(client, context)
            HEAD -> httpHead(client, context)
        }
    }

    private fun OkHttpClient.withSslConfiguration(request: Request): OkHttpClient = when {
        request.sslRelaxed -> OkHttpClient.Builder()
                .sslSocketFactory(insecureSocketFactory(), naiveTrustManager())
                .hostnameVerifier { _, _ -> true }
                .build()
        else -> this
    }

    private fun naiveTrustManager() = object : X509TrustManager {
        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) = Unit
        override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) = Unit
    }

    private fun insecureSocketFactory() = SSLContext.getInstance("TLSv1.2").apply {
        val trustAllCerts = arrayOf<TrustManager>(naiveTrustManager())
        init(null, trustAllCerts, SecureRandom())
    }.socketFactory
}
