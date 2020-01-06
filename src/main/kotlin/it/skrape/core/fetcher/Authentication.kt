package it.skrape.core.fetcher

import java.util.*

data class Authentication(
        val type: Type = Type.NONE,
        var username: String = "",
        var password: String = ""
) {
    enum class Type {
        NONE,
        BASIC,
        OAUTH2
    }

    fun toHeader(): Map<String, String> {
        val encodedCredentials = "$username:$password".base64Encoded()
        return when (type) {
            Authentication.Type.BASIC -> mapOf("Authorization" to "Basic $encodedCredentials")
            else -> emptyMap()
        }
    }

    private fun String.base64Encoded() = Base64.getEncoder().encodeToString(toByteArray())
}

fun basic(init: Authentication.() -> Unit) = Authentication(Authentication.Type.BASIC).also(init)
