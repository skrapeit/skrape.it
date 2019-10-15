package it.skrape.core.fetcher

import io.github.rybalkinsd.kohttp.client.defaultHttpClient
import io.github.rybalkinsd.kohttp.client.fork
import io.github.rybalkinsd.kohttp.dsl.*
import io.github.rybalkinsd.kohttp.dsl.context.HttpContext
import io.github.rybalkinsd.kohttp.ext.url
import it.skrape.core.Request
import it.skrape.core.Result
import it.skrape.exceptions.UnsupportedRequestOptionException
import org.jsoup.Connection
import java.lang.System.setProperty

class HttpFetcher(private val request: Request): Fetcher {

	// create a new client using our custom options
	private val client = defaultHttpClient.fork {
		followRedirects = request.followRedirects
		readTimeout = request.timeout.toLong()
	}

	override fun fetch(): Result {
		when(request.method) {
			Connection.Method.GET -> httpGet(client, getContext())
			Connection.Method.POST -> httpPost(client, getContext())
			Connection.Method.PUT -> httpPut(client, getContext())
			Connection.Method.DELETE -> httpDelete(client, getContext())
			Connection.Method.PATCH -> httpPatch(client, getContext())
			Connection.Method.HEAD -> httpHead(client, getContext())
			else -> throw UnsupportedRequestOptionException("Unsupported http verb.")
		}.use {
			// assemble the result from the response data
			return Result(
				responseBody = it.body()?.string() ?: "",
				statusCode = it.code(),
				statusMessage = it.message(),
				contentType = it.header("Content-Type"),
				headers = it.headers().names().associateBy({item -> item}, {item -> it.header(item, "")!!}),
				request = request
			)
		}
	}

	private fun getContext(): HttpContext.() -> Unit = {
		url(request.url)
		header {
			request.headers
			"User-Agent" to request.userAgent
			cookie {
				request.cookies
			}
		}
	}
}
