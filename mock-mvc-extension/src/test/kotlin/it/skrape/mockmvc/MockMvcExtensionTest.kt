package it.skrape.mockmvc

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import it.skrape.matchers.toBe
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.ResultActions

class MockMvcExtensionTest {

    private val aValidHtmlString = "<html><head><title>i'm the title</title></head></html>"

    private val httpServletResponse = mock<MockHttpServletResponse> {
        on { contentAsString } doReturn aValidHtmlString
    }

    private val mvcResult = mock<MvcResult> {
        on { response } doReturn httpServletResponse
    }

    private val resultActions = mock<ResultActions> {
        on { andReturn() } doReturn mvcResult
    }

    @Test
    fun `can parse and check a mockMvc response`() {
        resultActions.andExpectHtml {
            titleText toBe "i'm the title"
        }
    }

}