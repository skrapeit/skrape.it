package io.skrape.ktor

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.ktor.server.testing.*
import it.skrape.matchers.toBe
import it.skrape.matchers.toBeEmpty
import it.skrape.matchers.toBePresent
import it.skrape.selects.html5.body
import it.skrape.selects.html5.h1
import it.skrape.selects.html5.li
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

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
        val response = mock<TestApplicationResponse> {
            on { content } doReturn aValidHtmlString
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
        val response = mock<TestApplicationResponse> {
            on { content } doReturn aValidHtmlString
        }

        val doc = response.expectHtml {}

        assertEquals("i'm the title", doc.titleText)

        doc.h1 {
            findFirst { text toBe "headline" }
        }
    }

    @Test
    fun `can handle null response`() {
        val response = mock<TestApplicationResponse> {
            on { content } doReturn null
        }
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            response.expectHtml {}
        }
    }

}