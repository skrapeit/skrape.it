package it.skrape.core

data class Result(
        private val response: Response,
        val document: Doc = response.parse(),
        val statusCode: Int = response.statusCode(),
        val statusMessage: String? = response.statusMessage(),
        val contentType: String? = response.contentType(),
        val headers: Map<String, String> = response.headers(),
        val request: Request
)
