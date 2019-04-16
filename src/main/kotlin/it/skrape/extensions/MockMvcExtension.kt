package it.skrape.extensions

import it.skrape.core.Doc
import it.skrape.core.Parser
import org.springframework.test.web.servlet.ResultActions

/**
 * Will convert a MockMvc response body to a parsed Document.
 * Thereby it will give you the possibility to check HTML or XML from within a MockMvc Test.
 * @return this ResultActions to support MockMvc's fluent api.
 */
fun ResultActions.andExpectHtml(init: Doc.() -> Unit): ResultActions {
    val responseBody = this.andReturn().response.contentAsString
    Parser(responseBody).parse().init()
    return this
}
