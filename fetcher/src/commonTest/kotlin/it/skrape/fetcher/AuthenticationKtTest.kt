package it.skrape.fetcher

import io.kotest.matchers.shouldBe
import kotlin.js.JsName
import kotlin.test.Test

class AuthenticationKtTest {

    @Test
    @JsName("CanCreateAuthorizationHeaderForBasicAuthViaDSL")
    fun `can create Authorization header for Basic-Auth via DSL`() {
        val authenticationHeader = basic {
            username = "user"
            password = "secret"
        }.toHeaderValue()

        authenticationHeader.shouldBe("Basic dXNlcjpzZWNyZXQ=")
    }
}