package it.skrape.matchers

import it.skrape.selects.DocElement

external interface Handler {
    val get: (obj: dynamic, prop: String) -> dynamic
    val set: (obj: dynamic, prop: String) -> dynamic
}

external class Proxy(obj: dynamic, handler: Handler)

actual fun MatchersKtTest.mockDocElementListOfSize(mockSize: Int): List<DocElement> {
    val proxy = Proxy(emptyList<DocElement>(), object : Handler {
        override val get: (obj: dynamic, prop: String) -> dynamic = ::getFun
        override val set = { obj: dynamic, prop: String ->
            console.log("$obj <- $prop")
        }

        fun getFun(obj: dynamic, prop: String): dynamic {
            return if (prop.startsWith("get_size")) {
                { mockSize }
            } else {
                obj[prop]
            }
        }
    }).unsafeCast<List<DocElement>>()
    return proxy
}

actual fun MatchersKtTest.mockDocElement(mockIsPresent: Boolean, mockCssSelector: String): DocElement =
    Proxy(Any(), object : Handler {
        override val get: (obj: dynamic, prop: String) -> dynamic = ::getFun
        override val set = { obj: dynamic, prop: String ->
            console.log("$obj <- $prop")
        }

        fun getFun(obj: dynamic, prop: String): dynamic {
            return if (prop.startsWith("get_isPresent")) {
                { mockIsPresent }
            } else if (prop.startsWith("get_toCssSelector")) {
                { mockCssSelector }
            } else if (prop == "toString") {
                { obj.toString() }
            } else {
                obj[prop]
            }
        }
    }).unsafeCast<DocElement>()