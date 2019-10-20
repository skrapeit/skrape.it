package it.skrape.core.fetcher

import io.github.rybalkinsd.kohttp.client.defaultHttpClient
import io.github.rybalkinsd.kohttp.client.fork
import io.github.rybalkinsd.kohttp.dsl.*
import io.github.rybalkinsd.kohttp.dsl.context.HttpContext
import io.github.rybalkinsd.kohttp.ext.url
import it.skrape.core.Method.*
import it.skrape.core.Request
import it.skrape.core.Result

class HttpFetcher(private val request: Request): Fetcher {

	// create a new client using our custom options
	private val client = defaultHttpClient.fork {
		followRedirects = request.followRedirects
		readTimeout = request.timeout.toLong()
	}

	override fun fetch(): Result {
		when(request.method) {
			GET -> httpGet(client, getContext())
			POST -> httpPost(client, getContext())
			PUT -> httpPut(client, getContext())
			DELETE -> httpDelete(client, getContext())
			PATCH -> httpPatch(client, getContext())
			HEAD -> httpHead(client, getContext())
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
