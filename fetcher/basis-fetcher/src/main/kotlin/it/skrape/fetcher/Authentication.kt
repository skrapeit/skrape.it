package it.skrape.fetcher

import it.skrape.SkrapeItDsl
import java.util.*

@SkrapeItDsl
public interface Authentication {
    public fun toHeaderValue(): String
    public fun String.base64Encoded(): String = Base64.getEncoder().encodeToString(toByteArray()).orEmpty()
}

@SkrapeItDsl
public class BasicAuth(
    public var username: String = "",
    public var password: String = ""
): Authentication {
    override fun toHeaderValue(): String = "Basic ${"$username:$password".base64Encoded()}"
}

@SkrapeItDsl
public class OAuth2(
    public var clientId: String = "",
    public var clientSecret: String = ""
): Authentication {
    override fun toHeaderValue(): String = "Bearer TODO"
}

public fun basic(init: BasicAuth.() -> Unit): Authentication = BasicAuth().also(init)
public fun oauth2(init: OAuth2.() -> Unit): Authentication = OAuth2().also(init)
