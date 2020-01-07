package it.skrape.core.fetcher

import java.util.*

data class Authentication(
        val type: Type = Type.NONE,
        var username: String = "",
        var password: String = ""
) {
    enum class Type(val classifier: String) {
        NONE(""),
        BASIC("Basic"),
        OAUTH2("Bearer")
    }

    fun toHeaderValue(): String {
        val encodedCredentials = "$username:$password".base64Encoded()
        return when (type) {
            Type.BASIC -> "${type.classifier} $encodedCredentials"
            else -> ""
        }
    }

    private fun String.base64Encoded() = Base64.getEncoder().encodeToString(toByteArray())
}

fun basic(init: Authentication.() -> Unit) = Authentication(Authentication.Type.BASIC).also(init)
