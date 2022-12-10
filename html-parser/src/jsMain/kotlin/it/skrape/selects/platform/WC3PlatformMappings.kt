package it.skrape.selects.platform

import it.skrape.core.Parser
import kotlinx.browser.document
import kotlinx.dom.addClass
import org.w3c.dom.*
import parseHTML
import Node as LinkedomNode
import org.w3c.dom.Attr as WC3Attribute
import org.w3c.dom.Document as WC3Document
import org.w3c.dom.Element as WC3Element
import org.w3c.dom.Node as WC3Node

val printer = PrettyPrinter()

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
    constructor(collection: List<WC3Element>) : this(collection.map { Element(it) })

    actual fun select(query: String?): Elements {
        val res = flatMap { (it.wc3Element?.querySelectorAll(query!!)?.asList() ?: emptyList()) }
            .plus(this.filter { it.wc3Element?.matches(query!!) ?: false }.map { it.wc3Element })
            .distinct()
            .map { Element(it.unsafeCast<WC3Element>()) }
        return Elements(res)
    }


}

actual abstract class Node(val wc3Node: WC3Node?) {
    actual abstract fun attributes(): Attributes
    actual fun outerHtml(): String = if (wc3Node != null) printer.print(wc3Node) else ""
    actual open fun attr(key: String, value: String): Node = this
}

actual open class Element(val wc3Element: WC3Element?) : Node(wc3Element) {

    companion object ElementFactory {
        private val factoryDocument = if (Parser.IS_BROWSER) document else parseHTML("").window.document
        private val textPrinter = TextPrinter()
        private fun createElement(selector: String): WC3Element = factoryDocument.createElement(selector)
    }

    actual constructor(selector: String) : this(
        try {
            createElement(selector)
        } catch (ex: Throwable) {
            println(ex)
            null
        }
    )

    actual override fun attributes(): Attributes = Attributes(wc3Element?.attributes)

    actual override fun attr(key: String, value: String): Element =
        this.also {
            it.wc3Element?.setAttribute(key, value)
        }

    actual open fun tagName(): String = wc3Element?.tagName?.lowercase() ?: ""

    actual fun parents(): Elements = Elements(collectParents(wc3Element))
    private fun collectParents(elem: WC3Element?): List<WC3Element> {
        val parents = elem?.parentElement?.let { collectParents(it) } ?: emptyList()
        return (if (elem?.parentElement == null) emptyList() else listOf(elem.parentElement!!)) + parents
    }

    actual open fun children(): Elements = Elements(wc3Element?.children?.asList() ?: emptyList())

    actual fun cssSelector(): String = wc3Element?.let(this::getCssSelector) ?: ""

    private fun getCssSelector(elem: WC3Element): String {
        if (elem.id.isNotBlank()) return "#${elem.id}"
        // Translate HTML namespace ns:tag to CSS namespace syntax ns|tag
        val tagName = elem.tagName.lowercase().replace(':', '|')
        val selector = StringBuilder(tagName)
        val classes = elem.classList.value.replace(" ", ".")
        if (classes.isNotEmpty()) selector.append(".$classes")
        if (elem.parentElement == null
            || elem.parentElement!!.nodeType == LinkedomNode.DOCUMENT_TYPE_NODE
            || elem.parentElement!!.nodeType == LinkedomNode.DOCUMENT_FRAGMENT_NODE
            || elem.parentElement!!.nodeType == LinkedomNode.DOCUMENT_NODE
        ) // don't add Document to selector, as will always have a html node
            return selector.toString()

        if ((elem.parentElement?.querySelectorAll(selector.toString())?.length ?: 0) > 1) selector.append(
            ":nth-child(${
                ((elem.parentElement?.children?.asList() ?: emptyList()) + elem).indexOf(
                    elem
                )
            }"
        )
        elem.parentElement?.let { selector.insert(0, " > ") }

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

    actual open fun getAllElements(): Elements {
        val toVisit = mutableListOf(this)
        val toAdd = mutableListOf<Element>()
        while (toVisit.isNotEmpty()) {
            val cElem = toVisit.removeAt(0)
            toVisit.addAll(0, cElem.children())
            toAdd.add(cElem)
        }
        return Elements(toAdd)
    }

    private fun getAllNodes(): List<WC3Node> {
        if (wc3Node == null) return emptyList()
        val toVisit = mutableListOf(wc3Node)
        val retList = mutableListOf<WC3Node>()
        while (toVisit.isNotEmpty()) {
            val cNode = toVisit.removeAt(0)
            toVisit.addAll(0, cNode.childNodes.asList())
            retList.add(cNode)
        }
        return retList
    }

    actual fun text(): String? = wc3Node?.let { textPrinter.print(it) }

    actual fun wholeText(): String =
        getAllNodes()
            .filter { it.nodeType == LinkedomNode.TEXT_NODE }
            .joinToString("", transform = { it.textContent ?: "" })

    actual fun ownText(): String {
        return wc3Node?.childNodes?.asList()?.filter { it.nodeType == LinkedomNode.TEXT_NODE }
            ?.joinToString("", transform = { it.textContent!!.trim() }) ?: ""
    }

    //Template elements treat their child nodes differently to others.
    // However, we always want the content to correctly parse and print the nodes
    private fun getChildNodes() =
        if (wc3Element?.lowerTagName == "template") { // template gets treated differently
            wc3Element.unsafeCast<HTMLTemplateElement>().content.childNodes
        } else {
            wc3Node!!.childNodes
        }

    // performance sensitive
    actual open fun html(): String? = printer.print(getChildNodes())
    actual fun html(html: String?): Element {
        wc3Element?.innerHTML = html ?: ""
        return this
    }

    actual fun prependText(text: String): Element = this.also { wc3Element?.prepend(text) }

    actual fun addClass(clazz: String): Element = this.also { wc3Element?.addClass(clazz) }
    actual fun append(markup: String): Element = this.also { wc3Element?.innerHTML += "\n$markup" }

    override fun toString(): String = outerHtml()
}

actual class Document(private val myDoc: WC3Document) : Element((myDoc.rootElement ?: myDoc.documentElement)) {

    actual constructor(baseUri: String) : this(parseHTML("").window.document) {
        //URI is ignored for now while figuring out what to do
    }

    actual fun title(): String {
        val titles = myDoc.documentElement?.querySelectorAll("title")
        return titles?.get(0)?.textContent ?: ""
    }

    override fun children(): Elements = Elements(myDoc.children)

    override fun html(): String = printer.print(wc3Node!!)

    override fun tagName() = "#root"
}

internal actual fun emptyElements(): Elements = Elements(emptyList<Element>())