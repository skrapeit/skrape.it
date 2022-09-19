import kotlin.js.Json

@JsModule("js-beautify")
@JsNonModule
external object Beautify {

    @JsName("html")
    fun beautifyHtml(data: String, options: Json = definedExternally): String
}