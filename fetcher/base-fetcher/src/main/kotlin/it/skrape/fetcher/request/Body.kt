package it.skrape.fetcher.request

import org.intellij.lang.annotations.Language

public class Body {
    public var contentType: String = "text/plain"
    public var data: String = ""

    public fun json(@Language("JSON") json: String) {
        contentType = JSON
        data = json
    }

    public fun xml(@Language("XML") xml: String) {
        contentType = XML
        data = xml
    }

    public fun json(init: Json.() -> Unit) {
        Json().also(init).toBody()
    }

    private fun Json.toBody() {
        contentType = JSON
        data = toString()

    }
}

public class Json {
    private val elements = mutableListOf<Pair<String, String>>()

    @Suppress("MemberNameEqualsClassName")
    public fun json(init: Json.() -> Unit): Json = Json().also(init)

    public infix fun String.to(list: List<*>) {
        val value = list.joinToString(separator = ",", prefix = "[", postfix = "]") {
            when (it) {
                null -> "null"
                is Number, is Json, is Boolean -> it.toString()
                else -> """"$it""""
            }
        }
        elements += Pair(this, value)
    }

    public infix fun String.to(string: String?) {
        elements += if (string == null) Pair(this, "null") else Pair(this, """"$string"""")
    }

    public infix fun String.to(number: Number?) {
        elements += Pair(this, number.toString())
    }

    public infix fun String.to(json: Json?) {
        elements += Pair(this, json.toString())
    }

    public infix fun String.to(boolean: Boolean?) {
        elements += Pair(this, boolean.toString())
    }

    override fun toString(): String =
        elements.joinToString(separator = ",", prefix = "{", postfix = "}") { (key, value) ->
            """"$key":$value"""
        }
}

private const val JSON = "application/json"
private const val XML = "text/xml"
