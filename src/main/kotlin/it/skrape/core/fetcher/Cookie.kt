package it.skrape.core.fetcher

/**
 * Object representing a web cookie
 * @param name The name of the cookie
 * @param value The value of the cookie
 * @param expires The maximum lifetime of the cookie as an HTTP-date timestamp. Defaults to [Expires.Session] if attribute is not specified in cookie, otherwise [Expires.Date].
 * @param maxAge Number of seconds until the cookie expires. A zero or negative number will expire the cookie immediately. Takes precedence over [expires]
 * @param domain The domain that the cookie is associated with. Can apply either to only the base domain or to all subdomains based on [Domain.includesSubdomains]
 * @param path The path that the cookie is associated with
 * @param sameSite The [SameSite] value of the cookie
 * @param secure If true, the cookie is only sent to the server when a request is made using https, otherwise is available in all requests
 * @param httpOnly If true, the cookie is not accessible to javascript and can only be sent in an http request
 */
data class Cookie(
    val name: String,
    val value: String,
    val expires: Expires,
    val maxAge: Int?,
    val domain: Domain,
    val path: String = "/",
    val sameSite: SameSite = SameSite.LAX,
    val secure: Boolean = false,
    val httpOnly: Boolean = false
)

fun String.toCookie(origin: String): Cookie {
    val attributes = this.split(";").map { it.trim() }
    val (name, value) = attributes[0].split("=")

    val path = attributes.getAttribute("path") ?: "/"
    val expires = attributes.getAttribute("expires").toExpires()
    val maxAge = attributes.getAttribute("max-age")?.toInt()
    val domain = attributes.getDomain(origin)
    val sameSite = attributes.getAttribute("samesite").toSameSite()
    val secure = attributes.any { it.toLowerCase() == "secure" }
    val httpOnly = attributes.any { it.toLowerCase() == "httponly" }
    return Cookie(name, value, expires, maxAge, domain, path, sameSite, secure, httpOnly)
}

private fun List<String>.getAttribute(attributeName: String) =
    this.find { it.startsWith("${attributeName}=", ignoreCase = true) }?.takeLastWhile { it != '=' }

private fun List<String>.getDomain(origin: String): Domain {
    val domain = getAttribute("domain") ?: return Domain(origin, false)
    return Domain(domain, true)
}

enum class SameSite {
    /** Cookie is sent only to requests originating from the site that set it */
    STRICT,
    /** Cookie is withheld on cross-site requests (i.e. images), but sent on url navigation from external site. Default if no SameSite is specified */
    LAX,
    /** Cookie is send on both cross-site and same-site requests */
    NONE
}

fun String?.toSameSite(): SameSite {
    return when (this?.toLowerCase()) {
        "strict" -> SameSite.STRICT
        "lax" -> SameSite.LAX
        "none" -> SameSite.NONE
        else -> SameSite.LAX
    }
}

sealed class Expires {
    object Session : Expires()
    data class Date(val date: String) : Expires()
}

private fun String?.toExpires(): Expires {
    return when(this){
        null -> Expires.Session
        else -> Expires.Date(this)
    }
}

/** Remove http:// or https://, any subdirectories, and port if those exist */
internal fun String.urlOrigin() = this.substringAfter("://").substringBefore("/").substringBefore(":")

data class Domain(val domain: String, val includesSubdomains: Boolean)