package it.skrape.matchers

import io.ktor.http.*

infix fun ContentType?.toMatch(expected: ContentType?): ContentType? =
    this.apply {
        generalAssertion(
            this == expected
                    || (this != null && expected?.match(this.withWildcardParameter()) == true)
                    || (expected != null && this?.match(expected.withWildcardParameter()) == true)
                , expected)
    }

infix fun ContentType?.toMatch(expected: String): ContentType? = toMatch(ContentType.parse(expected))

infix fun ContentType?.toBeExactly(expected: ContentType?): ContentType? =
    this.apply { generalAssertion(this == expected, expected) }

infix fun ContentType?.toBeExactly(expected: String): ContentType? = toBeExactly(ContentType.parse(expected))

infix fun ContentType?.notToMatch(expected: ContentType?): ContentType? =
    this.apply {
        generalAssertion(
            (this != expected
                    && (this != null && expected?.match(this.withWildcardParameter()) == false)) && !this.match(expected.withWildcardParameter())
            , expected)
    }

infix fun ContentType?.notToMatch(expected: String): ContentType? = notToMatch(ContentType.parse(expected))

infix fun ContentType?.notToBe(expected: ContentType?): ContentType? =
    this.apply { generalAssertion(this != expected, expected) }

infix fun ContentType?.notToBe(expected: String): ContentType? = notToBe(ContentType.parse(expected))

fun ContentType.withWildcardParameter(ifEmpty: Boolean = true) =
    if (!ifEmpty || parameters.isEmpty()) {
        withParameter("*","*")
    } else {
        this
    }
