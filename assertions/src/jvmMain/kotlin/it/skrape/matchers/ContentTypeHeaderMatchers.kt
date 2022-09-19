package it.skrape.matchers

import it.skrape.SkrapeItDsl
import it.skrape.fetcher.ContentType

@SkrapeItDsl
public enum class ContentTypes(public val value: String) {
    APPLICATION_GZIP("application/gzip"),
    APPLICATION_JSON("application/json"),
    APPLICATION_JSON_UTF8("application/json;charset=utf-8"),
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

public infix fun ContentType.toBe(expected: ContentTypes): ContentType /* = kotlin.String? */ =
        this.apply { generalAssertion(raw() == expected.value, expected) }

public infix fun ContentType.toBeNot(expected: ContentTypes): ContentType /* = kotlin.String? */ =
        this.apply { generalAssertion(raw() != expected.value, expected) }

public infix fun ContentType.toContain(expected: ContentTypes): ContentType /* = kotlin.String? */ =
        this.apply { generalAssertion(raw().contains(expected.value), expected) }

private fun ContentType.raw() = (this as String)
        .lowercase()
        .replace("\\s".toRegex(), "")
