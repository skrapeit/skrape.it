package it.skrape.matchers

import io.ktor.http.*
import it.skrape.SkrapeItDsl
import it.skrape.fetcher.SkrapeItContentType

@SkrapeItDsl
@Deprecated(message = "Use ContentType instead", ReplaceWith(expression = "ContentType", "io.ktor.http.ContentType"))
object ContentTypes {
    @Deprecated(
        message = "Use ContentType instead",
        ReplaceWith(expression = "Application.GZip", "io.ktor.http.ContentType")
    )
    val APPLICATION_GZIP = ContentType.Application.GZip

    @Deprecated(
        message = "Use ContentType instead",
        ReplaceWith(expression = "Application.Json", "io.ktor.http.ContentType")
    )
    val APPLICATION_JSON = ContentType.Application.Json

    @Deprecated(
        message = "Use ContentType instead",
        ReplaceWith(expression = "Application.Json.withParameter(\"charset\",\"utf-8\")", "io.ktor.http.ContentType")
    )
    val APPLICATION_JSON_UTF8 = ContentType.Application.Json.withParameter("charset", "utf-8")

    @Deprecated(
        message = "Use ContentType instead",
        ReplaceWith(expression = "ContentType(\"application\", \"x-tar\")", "io.ktor.http.ContentType")
    )
    val APPLICATION_TAR = ContentType("application", "x-tar")

    @Deprecated(
        message = "Use ContentType instead",
        ReplaceWith(expression = "ContentType(\"application\", \"xhtml+xml\")", "io.ktor.http.ContentType")
    )
    val APPLICATION_XHTML = ContentType("application", "xhtml+xml")

    @Deprecated(
        message = "Use ContentType instead",
        ReplaceWith(expression = "Application.Xml", "io.ktor.http.ContentType")
    )
    val APPLICATION_XML = ContentType.Application.Xml

    @Deprecated(
        message = "Use ContentType instead",
        ReplaceWith(expression = "ContentType(\"application\", \"vnd.mozilla.xul+xml\")", "io.ktor.http.ContentType")
    )
    val APPLICATION_XUL = ContentType("application", "vnd.mozilla.xul+xml")

    @Deprecated(
        message = "Use ContentType instead",
        ReplaceWith(expression = "Application.Zip", "io.ktor.http.ContentType")
    )
    val APPLICATION_ZIP = ContentType.Application.Zip

    @Deprecated(message = "Use ContentType instead", ReplaceWith(expression = "Text.CSS", "io.ktor.http.ContentType"))
    val TEXT_CSS = ContentType.Text.CSS

    @Deprecated(message = "Use ContentType instead", ReplaceWith(expression = "Text.CSV", "io.ktor.http.ContentType"))
    val TEXT_CSV = ContentType.Text.CSV

    @Deprecated(message = "Use ContentType instead", ReplaceWith(expression = "Text.Plain", "io.ktor.http.ContentType"))
    val TEXT_PLAIN = ContentType.Text.Plain

    @Deprecated(message = "Use ContentType instead", ReplaceWith(expression = "Text.Html", "io.ktor.http.ContentType"))
    val TEXT_HTML = ContentType.Text.Html

    @Deprecated(
        message = "Use ContentType instead",
        ReplaceWith(expression = "Text.Html.withParameter(\"charset\",\"utf-8\")", "io.ktor.http.ContentType")
    )
    val TEXT_HTML_UTF8 = ContentType.Text.Html.withParameter("charset", "utf-8")

    @Deprecated(
        message = "Use ContentType instead",
        ReplaceWith(expression = "Text.JavaScript", "io.ktor.http.ContentType")
    )
    val TEXT_JAVASCRIPT = ContentType.Text.JavaScript

    @Deprecated(message = "Use ContentType instead", ReplaceWith(expression = "Text.Xml", "io.ktor.http.ContentType"))
    val TEXT_XML = ContentType.Text.Xml

    @Deprecated(
        message = "Use ContentType instead",
        ReplaceWith(expression = "ContentType(\"image\",\"bmp\")", "io.ktor.http.ContentType")
    )
    val IMAGE_BMP = ContentType("image", "bmp")

    @Deprecated(message = "Use ContentType instead", ReplaceWith(expression = "Image.GIF", "io.ktor.http.ContentType"))
    val IMAGE_GIF = ContentType.Image.GIF

    @Deprecated(message = "Use ContentType instead", ReplaceWith(expression = "Image.JPEG", "io.ktor.http.ContentType"))
    val IMAGE_JPEG = ContentType.Image.JPEG

    @Deprecated(message = "Use ContentType instead", ReplaceWith(expression = "Image.PNG", "io.ktor.http.ContentType"))
    val IMAGE_PNG = ContentType.Image.PNG

    @Deprecated(message = "Use ContentType instead", ReplaceWith(expression = "Image.SVG", "io.ktor.http.ContentType"))
    val IMAGE_SVG = ContentType.Image.SVG

    @Deprecated(message = "Use ContentType instead", ReplaceWith(""))
    fun values() = listOf(
        APPLICATION_GZIP,
        APPLICATION_JSON,
        APPLICATION_JSON_UTF8,
        APPLICATION_TAR,
        APPLICATION_XHTML,
        APPLICATION_XML,
        APPLICATION_XUL,
        APPLICATION_ZIP,
        TEXT_CSS,
        TEXT_CSV,
        TEXT_PLAIN,
        TEXT_HTML,
        TEXT_HTML_UTF8,
        TEXT_JAVASCRIPT,
        TEXT_XML,
        IMAGE_BMP,
        IMAGE_GIF,
        IMAGE_JPEG,
        IMAGE_PNG,
        IMAGE_SVG
    )
}

@Deprecated("Use toString instead", ReplaceWith("toString()"))
val ContentType.value
    get() = toString()

//TODO decide if the ordering should be important (type <> * != * <> type)
public infix fun ContentType.toMatch(expected: ContentType): ContentType =
    this.apply { generalAssertion(expected.match(this), expected) }

public infix fun ContentType.toBeExactly(expected: ContentType): ContentType =
    this.apply { generalAssertion(this == expected, expected) }

public infix fun ContentType.notToMatch(expected: ContentType): ContentType =
    this.apply { generalAssertion(!expected.match(this), expected) }

public infix fun ContentType.notToBeExactly(expected: ContentType): ContentType =
    this.apply { generalAssertion(this == expected, expected) }

@Deprecated("Use ktor ContentTypes", ReplaceWith("toBeExactly"))
public infix fun SkrapeItContentType.toBe(expected: ContentType): SkrapeItContentType =
    this.apply { generalAssertion(raw() == expected.raw(), expected) }

@Deprecated("Use ktor ContentTypes", ReplaceWith("notToBeExactly"))
public infix fun SkrapeItContentType.toBeNot(expected: ContentType): SkrapeItContentType =
    this.apply { generalAssertion(raw() != expected.raw(), expected) }

@Deprecated("Use ktor ContentTypes", ReplaceWith("toMatch"))
public infix fun SkrapeItContentType.toContain(expected: ContentType): SkrapeItContentType =
    this.apply { generalAssertion(raw().contains(expected.raw()), expected) }

private fun ContentType.raw() = toString()
    .lowercase()
    .replace("\\s".toRegex(), "")

private fun SkrapeItContentType.raw() = (this as String)
    .lowercase()
    .replace("\\s".toRegex(), "")
