package it.skrape.core.fetcher

import java.util.*

interface Authentication {
    fun toHeaderValue(): String
    fun String.base64Encoded() = Base64.getEncoder().encodeToString(toByteArray()).orEmpty()
}

class BasicAuth(
        var username: String = "",
        var password: String = ""
): Authentication {
    override fun toHeaderValue(): String = "Basic ${"$username:$password".base64Encoded()}"
}

class OAuth2(
        var clientId: String = "",
        var clientSecret: String = ""
): Authentication {
    override fun toHeaderValue(): String = "Bearer TODO"
}

fun basic(init: BasicAuth.() -> Unit) = BasicAuth().also(init) as Authentication
fun oauth2(init: OAuth2.() -> Unit) = OAuth2().also(init) as Authentication
