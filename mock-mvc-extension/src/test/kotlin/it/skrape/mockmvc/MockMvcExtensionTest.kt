package it.skrape.mockmvc

import io.mockk.every
import io.mockk.mockk
import it.skrape.matchers.toBe
import it.skrape.selects.html5.head
import it.skrape.selects.html5.title
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.ResultActions

class MockMvcExtensionTest {

    @Language("HTML")
    private val aValidHtmlString = "<html><head><title>i'm the title</title></head></html>"

    private val httpServletResponse = mockk<MockHttpServletResponse> {
        every { contentAsString } returns aValidHtmlString
    }

    private val mvcResult = mockk<MvcResult> {
        every { response } returns httpServletResponse
    }

    private val resultActions = mockk<ResultActions> {
        every { andReturn() } returns mvcResult
    }

    @Test
    fun `can parse and check a mockMvc response`() {
        resultActions.andExpectHtml {
            titleText toBe "i'm the title"
            head { title { findFirst { text toBe "i'm the title" } } }
        }
    }
}
