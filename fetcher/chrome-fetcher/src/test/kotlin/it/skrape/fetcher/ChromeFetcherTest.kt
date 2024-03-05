package it.skrape.fetcher

import Testcontainer
import com.gargoylesoftware.htmlunit.util.NameValuePair
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledOnOs
import org.junit.jupiter.api.condition.OS
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import setupCookiesStub
import setupPostStub
import setupRedirect
import setupStub
import strikt.api.expect
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.contains
import strikt.assertions.isEqualTo
import java.net.SocketTimeoutException

private val wiremock = Testcontainer.wiremock
private val httpBin = Testcontainer.httpBin

@Execution(ExecutionMode.SAME_THREAD)
@DisabledOnOs(OS.WINDOWS)
class ChromeFetcherTest {

    private val baseRequest by lazy { Request(url = wiremock.httpUrl) }

    @Test
    fun `will fetch localhost 8080 with defaults if no params`() {
        wiremock.setupStub()

        val fetched = ChromeFetcher.fetch(baseRequest)

        expect {
            that(fetched.status { code }).isEqualTo(200)
            that(fetched.responseBody).contains("i'm the title")
        }
    }

    @Test
    fun `can fetch url and use HTTP verb GET by default`() {
        wiremock.setupStub(path = "/example")

        val request = baseRequest.copy(
            url = "${wiremock.httpUrl}/example",
            sslRelaxed = true
        )

        val fetched = ChromeFetcher.fetch(request)

        expect {
            that(fetched.status { code }).isEqualTo(200)
            that(fetched.responseBody).contains("i'm the title")
        }
    }

    @Test
    fun `can parse js rendered elements`() {
        wiremock.setupStub(fileName = "js.html")

        val fetched = ChromeFetcher.fetch(baseRequest)

        expectThat(fetched.responseBody).contains("I have been dynamically added via Javascript")
    }

    @Test
    fun `can parse js rendered elements from https page`() {
        wiremock.setupStub(fileName = "js.html")
        val request = baseRequest.copy(
            url = wiremock.httpUrl,
            sslRelaxed = true
        )

        val fetched = ChromeFetcher.fetch(request)

        expectThat(fetched.responseBody).contains("I have been dynamically added via Javascript")
    }

    @Test
    fun `can parse es6 rendered elements from https page`() {
        wiremock.setupStub(fileName = "es6.html")

        val fetched = ChromeFetcher.fetch(baseRequest)
        expectThat(fetched.responseBody).contains("dynamically added")
    }

}
