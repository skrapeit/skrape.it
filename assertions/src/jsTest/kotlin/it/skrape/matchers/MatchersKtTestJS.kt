package it.skrape.matchers

import it.skrape.selects.DocElement

interface Handler {
    val get: (obj: dynamic, prop: String) -> dynamic
    val set: (obj: dynamic, prop: String) -> dynamic
}

external class Proxy(obj: dynamic, handler: Handler)

actual fun MatchersKtTest.mockDocElementListOfSize(mockSize:Int): List<DocElement> = Proxy(Any(), object : Handler {
    override val get: (obj: dynamic, prop: String) -> dynamic = ::getFun
    override val set = { obj: dynamic, prop: String ->
        console.log("$obj <- $prop")
    }

    fun getFun(obj: dynamic, prop: String): dynamic {
        return when(prop) {
            "size" -> mockSize
            else -> obj[prop]
        }
    }
}).unsafeCast<List<DocElement>>()

actual fun MatchersKtTest.mockDocElement(mockIsPresent:Boolean, mockCssSelector:String): DocElement = Proxy(Any(), object : Handler {
    override val get: (obj: dynamic, prop: String) -> dynamic = ::getFun
    override val set = { obj: dynamic, prop: String ->
        console.log("$obj <- $prop")
    }

    fun getFun(obj: dynamic, prop: String): dynamic {
        return when(prop) {
            "isPresent" -> mockIsPresent
            "toCssSelector" -> mockCssSelector
            else -> obj[prop]
        }
    }
}).unsafeCast<DocElement>()