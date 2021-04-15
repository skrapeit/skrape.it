package it.skrape.fetcher.request

import it.skrape.SkrapeItDsl
import org.intellij.lang.annotations.Language

@SkrapeItDsl
public class BodyBuilder {
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

    public fun form(form: String) {
        contentType = FORM
        data = form
    }

    public fun json(init: Json.() -> Unit) {
        Json().also(init).toBody()
    }

    public fun form(init: Form.() -> Unit) {
        Form().also(init).toBody()
    }

    private fun Json.toBody() {
        this@BodyBuilder.contentType = JSON
        this@BodyBuilder.data = toString()
    }

    private fun Form.toBody() {
        this@BodyBuilder.contentType = FORM
        this@BodyBuilder.data = toString()
    }
}

@SkrapeItDsl
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

@SkrapeItDsl
public class Form {
    private val elements = mutableListOf<Pair<String, String>>()

    public infix fun String.to(string: String?) {
        elements += if (string == null) Pair(this, "null") else Pair(this, """$string""")
    }

    public infix fun String.to(number: Number?) {
        elements += Pair(this, number.toString())
    }

    public infix fun String.to(boolean: Boolean?) {
        elements += Pair(this, boolean.toString())
    }

    override fun toString(): String =
        elements.joinToString(separator = "&") { (key, value) ->
            """$key=$value"""
        }
}

private const val JSON = "application/json"
private const val XML = "text/xml"
private const val FORM = "application/x-www-form-urlencoded"
