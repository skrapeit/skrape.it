package it.skrape.matchers

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import kotlin.js.JsName
import kotlin.test.Test

class ContentTypeHeaderMatchersKtTest {

    @JsName("CanMatchExactContentTypesFromString")
	@Test
	fun `can match exact content types from string`() {
        ContentTypes.values().forEach(::testExactMatch)
    }
    
    fun testExactMatch(contentType:ContentTypes) {
        val returnedContentTypeValue = contentType.value toBe contentType
        "${contentType.value}foo" toBeNot contentType
        expect(returnedContentTypeValue).toEqual(contentType.value)
    }

    @JsName("CanMatchPartialContentTypesFromString")
	@Test
	fun `can match partial content types from string`() {
        ContentTypes.values().forEach(::testPartialMatch)
    }
    
    fun testPartialMatch(contentType: ContentTypes) {
        "${contentType.value}foo" toContain contentType
        "${contentType.value}bar" toContain contentType
    }
}
