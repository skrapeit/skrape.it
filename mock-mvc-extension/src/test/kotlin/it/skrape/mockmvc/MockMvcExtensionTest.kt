package it.skrape.mockmvc

import io.mockk.every
import io.mockk.mockk
import it.skrape.matchers.toBe
import org.junit.jupiter.api.Test
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.ResultActions

class MockMvcExtensionTest {

    private val aValidHtmlString = "<html><head><title>i'm the title</title></head></html>"

    private val httpServletResponse by lazy {
        mockk<MockHttpServletResponse> {
            every { contentAsString } returns aValidHtmlString
        }
    }

    private val mvcResult by lazy {
        mockk<MvcResult> {
            every { response } returns httpServletResponse
        }
    }

    private val resultActions by lazy {
        mockk<ResultActions> {
            every { andReturn() } returns mvcResult
        }
    }

    @Test
    fun `can parse and check a mockMvc response`() {
        resultActions.andExpectHtml {
            titleText toBe "i'm the title"
        }
    }

}