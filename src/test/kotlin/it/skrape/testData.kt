package it.skrape

import it.skrape.core.Request
import it.skrape.core.Result

fun aValidResult(body: String = "", statusCode: Int = 200) = Result(
        body = body,
        statusCode = statusCode,
        contentType = "",
        headers = mapOf(),
        statusMessage = "",
        request = Request()
)