package it.skrape.ktor

import io.ktor.server.testing.*
import io.mockk.every
import io.mockk.mockk
import it.skrape.matchers.toBe
import it.skrape.matchers.toBeEmpty
import it.skrape.matchers.toBePresent
import it.skrape.selects.html5.body
import it.skrape.selects.html5.h1
import it.skrape.selects.html5.li
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.isEqualTo

internal class KtorExtensionTest {

    private val aValidHtmlString = """
        <expectHtml>
            <head>
                <title>i'm the title</title>
            </head>
            <body>
                <h1>headline</h1>
                <ul>
                    <li>item1</li>
                    <li>item2</li>
                    <li>item3</li>
                </ul>
            </body>
        </expectHtml>
    """.trimIndent()

    @Test
    fun `can parse and check a TestApplicationResponse's content`() {
        val response = mockk<TestApplicationResponse> {
            every { content } returns aValidHtmlString
        }

        response.expectHtml {
            titleText toBe "i'm the title"

            body {
                findAll { toBePresent }
            }

            h1 {
                findFirst { text toBe "headline" }
            }

            li {
                findAll { size toBe 3 }
            }

            ".not-existing" {
                findAll { toBeEmpty }
            }
        }
    }

    @Test
    fun `can store a parsed TestApplicationResponse's content`() {
        val response = mockk<TestApplicationResponse> {
            every { content } returns aValidHtmlString
        }

        val doc = response.expectHtml {}

        expectThat(doc.titleText).isEqualTo("i'm the title")

        doc.h1 {
            findFirst { text toBe "headline" }
        }
    }

    @Test
    fun `can handle null response`() {
        val response = mockk<TestApplicationResponse> {
            every { content } returns null
        }
        expectThrows<IllegalArgumentException> {
            response.expectHtml {}
        }
    }
}
