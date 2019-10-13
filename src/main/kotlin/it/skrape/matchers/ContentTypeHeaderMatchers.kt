package it.skrape.matchers

import it.skrape.core.ContentType
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.isEqualTo
import strikt.assertions.isNotEqualTo

enum class ContentTypes(val value: String) {
    APPLICATION_GZIP("application/gzip"),
    APPLICATION_JSON("application/json"),
    APPLICATION_TAR("application/x-tar"),
    APPLICATION_XHTML("application/xhtml+xml"),
    APPLICATION_XML("application/xml"),
    APPLICATION_XUL("application/vnd.mozilla.xul+xml"),
    APPLICATION_ZIP("application/zip"),
    TEXT_CSS("text/css"),
    TEXT_CSV("text/csv"),
    TEXT_PLAIN("text/plain"),
    TEXT_HTML("text/html"),
    TEXT_HTML_UTF8("text/html;charset=utf-8"),
    TEXT_JAVASCRIPT("text/javascript"),
    TEXT_XML("text/xml"),
    IMAGE_BMP("image/bmp"),
    IMAGE_GIF("image/gif"),
    IMAGE_JPEG("image/jpeg"),
    IMAGE_PNG("image/svg"),
    IMAGE_SVG("image/svg");
}

infix fun ContentType.toBe(expected: ContentTypes) {
    expectThat(this.raw())
            .describedAs("content-type")
            .isEqualTo(expected.value)
}

infix fun ContentType.`to be`(expected: ContentTypes) {
    this toBe expected
}

infix fun ContentType.toBeNot(expected: ContentTypes) {
    expectThat(this.raw())
            .describedAs("content-type")
            .isNotEqualTo(expected.value)
}

infix fun ContentType.`to be not`(expected: ContentTypes) {
    this toBeNot expected
}

infix fun ContentType.toContain(expected: ContentTypes) {
    expectThat(this.raw())
            .describedAs("content-type")
            .contains(expected.value)
}

infix fun ContentType.`to contain`(expected: ContentTypes) {
    this toContain expected
}

private fun ContentType.raw() = (this as String)
        .toLowerCase()
        .replace("\\s".toRegex(), "")
