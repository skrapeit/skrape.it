@file:Suppress("FunctionName", "EnumEntryName")

package it.skrape.matchers

import io.ktor.http.*
import kotlin.js.JsName


private val informationalResponse = HttpStatusCode(1, "Informational Response")
private val successful = HttpStatusCode(2, "Successful")
private val redirection = HttpStatusCode(3, "Redirection")
private val clientError = HttpStatusCode(4, "Client Error")
private val serverError = HttpStatusCode(5, "Server Error")
private val groupStatusCodes = listOf(informationalResponse, successful, redirection, clientError, serverError)

val HttpStatusCode.Companion.InformationalResponse
    get() = informationalResponse
val HttpStatusCode.Companion.Successful
    get() = successful
val HttpStatusCode.Companion.Redirection
    get() = redirection
val HttpStatusCode.Companion.ClientError
    get() = clientError
val HttpStatusCode.Companion.ServerError
    get() = serverError

val HttpStatusCode.Companion.allGroupStatusCodes
    get() = groupStatusCodes

public infix fun HttpStatusCode.toBe(expected: HttpStatusCode): HttpStatusCode {
    @Suppress("MagicNumber")
    if (expected.value <= 5) {
        return statusAssertion(this.value / 100 == expected.value, expected)
    }
    return statusAssertion(this == expected && this.description == expected.description, expected)
}

public infix fun HttpStatusCode.toBeExactly(expected: HttpStatusCode): HttpStatusCode {
    if (expected.value > 5) statusAssertion(description == expected.description, expected)
    return toBe(expected)
}

public infix fun HttpStatusCode.notToBe(expected: HttpStatusCode): HttpStatusCode {
    @Suppress("MagicNumber")
    if (expected.value <= 5) {
        return statusAssertion(this.value / 100 != expected.value, expected)
    }
    return statusAssertion(this != expected, expected)
}

public infix fun HttpStatusCode.notToBeExactly(expected: HttpStatusCode): HttpStatusCode {
    @Suppress("MagicNumber")
    if (expected.value <= 5) {
        return statusAssertion(this.value / 100 != expected.value || this.description != expected.description, expected)
    }
    return statusAssertion(this.value != expected.value || this.description != expected.description, expected)
}
