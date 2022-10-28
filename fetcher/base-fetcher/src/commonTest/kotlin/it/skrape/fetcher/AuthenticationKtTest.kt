package it.skrape.fetcher

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
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

        expect(authenticationHeader).toEqual("Basic dXNlcjpzZWNyZXQ=")
    }
}