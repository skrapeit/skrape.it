package it.skrape.fetcher

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class AuthenticationKtTest: FunSpec({

    test("can create Authorization header for Basic-Auth via DSL") {
        val authenticationHeader = basic {
            username = "user"
            password = "secret"
        }.toHeaderValue()

        authenticationHeader.shouldBe("Basic dXNlcjpzZWNyZXQ=")
    }
})