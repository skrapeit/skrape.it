package it.skrape.core.fetcher

/**
 * Object representing a web cookie
 */
data class Cookie(
    val name: String,
    val value: String,
    /**The maximum lifetime of the cookie as an HTTP-date timestamp. Defaults to [Expires.Session] if attribute is not specified in cookie, otherwise [Expires.Date].*/
    val expires: Expires,
    /**Number of seconds until the cookie expires. A zero or negative number will expire the cookie immediately. Takes precedence over [expires].*/
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
    STRICT, LAX, NONE
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