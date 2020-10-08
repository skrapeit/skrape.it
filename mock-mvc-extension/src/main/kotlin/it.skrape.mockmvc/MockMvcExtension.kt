package it.skrape.mockmvc

import it.skrape.core.htmlDocument
import it.skrape.selects.Doc
import org.springframework.test.web.servlet.ResultActions

/**
 * Will convert a MockMvc response body to a parsed Document.
 * Thereby it will give you the possibility to check HTML or XML from within a MockMvc Test.
 * @return this ResultActions to support MockMvc's fluent api.
 */
fun ResultActions.andExpectHtml(
    relaxed: Boolean = true,
    init: Doc.() -> Unit
): ResultActions {
    val responseBody = this.andReturn().response.contentAsString
    htmlDocument(responseBody).apply { this.relaxed = relaxed }.init()
    return this
}
