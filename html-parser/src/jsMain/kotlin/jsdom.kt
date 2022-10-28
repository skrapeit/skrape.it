@file:JsModule("jsdom")
@file:JsNonModule

import org.w3c.dom.DocumentFragment
import org.w3c.dom.Location
import org.w3c.dom.Node
import org.w3c.dom.Window
import kotlin.js.Promise

open external class JSDOM(
    html: Any /* String | Buffer | SharedArrayBuffer | ArrayBuffer | Uint8Array | Uint8ClampedArray | Uint16Array | Uint32Array | Int8Array | Int16Array | Int32Array | BigUint64Array | BigInt64Array | Float32Array | Float64Array | DataView */ = definedExternally,
    options: ConstructorOptions? = definedExternally
) {
    open var window: Window
    open var virtualConsole: dynamic
    open var cookieJar: dynamic
    open fun serialize(): String
    open fun nodeLocation(node: Node): Location?
    open fun getInternalVMContext(): dynamic
    open fun reconfigure(settings: dynamic)

    companion object {
        fun fromURL(url: String, options: BaseOptions = definedExternally): Promise<JSDOM>
        fun fragment(html: String): DocumentFragment
    }
}

external interface BaseOptions {
    var referrer: String?
        get() = definedExternally
        set(value) = definedExternally
    var userAgent: String?
        get() = definedExternally
        set(value) = definedExternally
    var includeNodeLocations: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var runScripts: String? /* "dangerously" | "outside-only" */
        get() = definedExternally
        set(value) = definedExternally
    var resources: dynamic /* "usable" | ResourceLoader? */
        get() = definedExternally
        set(value) = definedExternally
    var virtualConsole: dynamic
        get() = definedExternally
        set(value) = definedExternally
    var cookieJar: dynamic
        get() = definedExternally
        set(value) = definedExternally
    var pretendToBeVisual: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    val beforeParse: ((window: Window) -> Unit)?
}

external interface ConstructorOptions : BaseOptions {
    var url: String?
        get() = definedExternally
        set(value) = definedExternally
    var contentType: String? /* "text/html" | "application/xhtml+xml" | "application/xml" | "text/xml" | "image/svg+xml" */
        get() = definedExternally
        set(value) = definedExternally
    var storageQuota: Number?
        get() = definedExternally
        set(value) = definedExternally
}