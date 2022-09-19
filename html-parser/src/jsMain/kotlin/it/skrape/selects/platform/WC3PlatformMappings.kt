package it.skrape.selects.platform

import Beautify
import kotlinx.browser.document
import kotlinx.dom.addClass
import org.w3c.dom.*
import kotlin.js.json
import org.w3c.dom.Attr as WC3Attribute
import org.w3c.dom.Document as WC3Document
import org.w3c.dom.Element as WC3Element
import org.w3c.dom.Node as WC3Node

actual class Attribute(private val attribute: WC3Attribute) : MutableMap.MutableEntry<String, String> {

    override val key: String = attribute.name
    override val value: String = attribute.value

    override fun setValue(newValue: String): String {
        val oldV = attribute.value
        attribute.value = newValue
        return oldV
    }

}

actual class Attributes(private val attributes: NamedNodeMap?) : Iterable<Attribute> {
    override fun iterator(): Iterator<Attribute> =
        attributes?.asList()?.map { Attribute(it) }?.iterator() ?: emptyList<Attribute>().iterator()
}

@Suppress("ACTUAL_WITHOUT_EXPECT") //Elements inherits from ArrayList, which inherits from MutableList, so we are fine
actual class Elements(elements: List<Element>) : ArrayList<Element>(elements) {
    constructor(collection: HTMLCollection) : this(collection.asList().map { Element(it) })
    constructor(collection: NodeList) : this(collection.asList().map { Element(it as WC3Element) })
    constructor(collection: List<WC3Element>) : this(collection.map { Element(it) })

    actual fun select(query: String?): Elements {
        val res = flatMap { (it.wc3Element?.querySelectorAll(query!!)?.asList() ?: emptyList()) }
            .plus(this.filter { it.wc3Element?.matches(query!!) ?: false }.map { it.wc3Element })
            .distinct()
            .map { Element(it as WC3Element) }
        return Elements(res)
    }


}

actual abstract class Node(val wc3Node: WC3Node?) {
    actual abstract fun attributes(): Attributes
    actual fun outerHtml(): String = if (wc3Node is WC3Element) wc3Node.outerHTML else ""
    actual open fun attr(key: String, value: String): Node = this
}

fun WC3Element?.isRootElement(): Boolean = this?.tagName?.lowercase() == "root" && id == "root"

fun WC3Element?.jsoupTagName(): String = if (isRootElement()) "#root" else this?.tagName?.lowercase() ?: ""

actual open class Element(val wc3Element: WC3Element?) : Node(wc3Element) {

    actual constructor(selector: String) : this( try { document.createElement(selector) } catch (ex: Throwable) { null })


    actual override fun attributes(): Attributes = Attributes(wc3Element?.attributes)
    actual override fun attr(key: String, value: String): Element =
        this.also { it.wc3Element?.setAttribute(key, value) }

    actual fun tagName(): String = wc3Element.jsoupTagName()

    actual fun parents(): Elements = Elements(collectParents(wc3Element))
    private fun collectParents(elem: WC3Element?): List<WC3Element> {
        val parents = elem?.parentElement?.let { collectParents(it) } ?: emptyList()
        //HACK: We dismiss root elements, since they are the root of our doc
        //TODO Better way to determine root element. Check tag, id and parents maybe?
        return (if (elem?.parentElement == null || elem.parentElement.isRootElement()) emptyList() else listOf(elem.parentElement!!)) + parents
        //return (parents ?: mutableListOf()).apply { elem?.parentElement?.also(this::add) }
    }

    actual fun children(): Elements = Elements(wc3Element?.children?.asList() ?: emptyList())
    actual fun cssSelector(): String = wc3Element?.let(this::getCssSelector) ?: ""

    private fun getCssSelector(elem: WC3Element): String {
        if (elem.id.isNotBlank()) return "#${elem.id}"
        // Translate HTML namespace ns:tag to CSS namespace syntax ns|tag
        val tagName = elem.jsoupTagName().replace(':', '|')
        val selector = StringBuilder(tagName)
        val classes = elem.classList.asList().joinToString(".")
        if (classes.isNotEmpty()) selector.append(".$classes")

        if (elem.parentElement == null || elem.parentNode is WC3Document || elem.parentElement.isRootElement()) // don't add Document to selector, as will always have a html node
            return selector.toString()

        if ((elem.parentElement?.querySelectorAll(selector.toString())?.length ?: 0) > 1) selector.append(
            ":nth-child(${
                ((elem.parentElement?.children?.asList() ?: emptyList()) + elem).indexOf(
                    elem
                )
            }"
        )
        elem.parentElement?.let {selector.insert(0, " > ") }

        return getCssSelector(elem.parentElement!!) + selector.toString()
    }

    actual fun siblingElements(): Elements {
        val siblings = mutableListOf<WC3Element>()
        var prev = wc3Element?.previousElementSibling
        var next = wc3Element?.nextElementSibling
        while (prev != null) {
            siblings += prev
            prev = prev.previousElementSibling
        }
        while (next != null) {
            siblings += next
            next = next.nextElementSibling
        }
        return Elements(siblings)
    }

    actual fun getAllElements(): Elements = Elements(listOf(this) + children().flatMap { it.getAllElements() })

    actual fun text(): String? = (wc3Element as HTMLElement?)?.innerText?.replace("\\s+".toRegex(), " ")?.trim()
    actual fun wholeText(): String? = wc3Element?.textContent
    actual fun ownText(): String? =
        (wc3Node?.childNodes?.asList()?.firstOrNull { it is Text } as Text?)?.wholeText?.trim()

    // performance sensitive
    actual fun html(): String? = wc3Element?.innerHTML?.let { Beautify.beautifyHtml(it, json("indent_size" to 1, "extra_liners" to emptyArray<String>(), "indent_inner_html" to true)) }
    actual fun html(html: String?): Element {
        wc3Element?.innerHTML = html ?: ""
        return this
    }

    actual fun prependText(text: String): Element = this.also { wc3Element?.prepend(Text(text)) }

    actual fun addClass(clazz: String): Element = this.also { wc3Element?.addClass(clazz) }
    actual fun append(markup: String): Element = this.also { wc3Element?.innerHTML += markup }

    override fun toString(): String = wc3Element?.outerHTML ?: ""
}

actual class Document(private val myDoc: WC3Document) : Element((myDoc.rootElement ?: myDoc.documentElement)?.run {
    document.createElement("root").apply {
        id = "root"
        appendChild(this@run)
        myDoc.appendChild(this)
    }
}) {

    actual constructor(baseUri: String) : this(WC3Document()) {
        //URI is ignored for now while figuring out what to do
    }

    actual fun title(): String? = myDoc.title

}