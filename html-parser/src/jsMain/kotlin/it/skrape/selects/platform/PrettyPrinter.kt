package it.skrape.selects.platform

import org.w3c.dom.*
import org.w3c.dom.Element
import org.w3c.dom.Node
import Node as NodeType

val Element.lowerTagName
    get() = this.tagName.lowercase()

data class Tag(
    val name: String,
    val block: Boolean = false,
    val inline: Boolean = false,
    val formatBlock: Boolean = true,
    val empty: Boolean = false,
    val preserveWhitespace: Boolean = false,
    val formListed: Boolean = false,
    val formSubmit: Boolean = false
) {
    companion object {
        private val blockTags = setOf(
            "html",
            "head",
            "body",
            "frameset",
            "script",
            "noscript",
            "style",
            "meta",
            "link",
            "title",
            "frame",
            "noframes",
            "section",
            "nav",
            "aside",
            "hgroup",
            "header",
            "footer",
            "p",
            "h1",
            "h2",
            "h3",
            "h4",
            "h5",
            "h6",
            "ul",
            "ol",
            "pre",
            "div",
            "blockquote",
            "hr",
            "address",
            "figure",
            "figcaption",
            "form",
            "fieldset",
            "ins",
            "del",
            "dl",
            "dt",
            "dd",
            "li",
            "table",
            "caption",
            "thead",
            "tfoot",
            "tbody",
            "colgroup",
            "col",
            "tr",
            "th",
            "td",
            "video",
            "audio",
            "canvas",
            "details",
            "menu",
            "plaintext",
            "template",
            "article",
            "main",
            "svg",
            "math",
            "center"
        )
        private val inlineTags = setOf(
            "object",
            "base",
            "font",
            "tt",
            "i",
            "b",
            "u",
            "big",
            "small",
            "em",
            "strong",
            "dfn",
            "code",
            "samp",
            "kbd",
            "var",
            "cite",
            "abbr",
            "time",
            "acronym",
            "mark",
            "ruby",
            "rt",
            "rp",
            "a",
            "img",
            "br",
            "wbr",
            "map",
            "q",
            "sub",
            "sup",
            "bdo",
            "iframe",
            "embed",
            "span",
            "input",
            "select",
            "textarea",
            "label",
            "button",
            "optgroup",
            "option",
            "legend",
            "datalist",
            "keygen",
            "output",
            "progress",
            "meter",
            "area",
            "param",
            "source",
            "track",
            "summary",
            "command",
            "device",
            "area",
            "basefont",
            "bgsound",
            "menuitem",
            "param",
            "source",
            "track",
            "data",
            "bdi",
            "s"
        )
        private val emptyTags = setOf(
            "meta", "link", "base", "frame", "img", "br", "wbr", "embed", "hr", "input", "keygen", "col", "command",
            "device", "area", "basefont", "bgsound", "menuitem", "param", "source", "track"
        )

        // todo - rework this to format contents as inline; and update html emitter in Element. Same output, just neater.
        private val formatAsInlineTags = setOf(
            "title",
            "a",
            "p",
            "h1",
            "h2",
            "h3",
            "h4",
            "h5",
            "h6",
            "pre",
            "address",
            "li",
            "th",
            "td",
            "script",
            "style",
            "ins",
            "del",
            "s"
        )
        private val preserveWhitespaceTags = setOf(
            "pre",
            "plaintext",
            "title",
            "textarea" // script is not here as it is a data node, which always preserve whitespace
        )

        // todo: I think we just need submit tags, and can scrub listed
        private val formListedTags = setOf(
            "button", "fieldset", "input", "keygen", "object", "output", "select", "textarea"
        )
        private val formSubmitTags = setOf(
            "input", "keygen", "object", "select", "textarea"
        )

        val tags =
            listOf(
                blockTags,
                inlineTags,
                emptyTags,
                formatAsInlineTags,
                preserveWhitespaceTags,
                formListedTags,
                formSubmitTags
            )
                .flatten()
                .distinct()
                .map {
                    Tag(
                        it,
                        block = blockTags.contains(it) && !inlineTags.contains(it),
                        formatBlock = blockTags.contains(it) && !formatAsInlineTags.contains(it) && !inlineTags.contains(it),
                        inline = inlineTags.contains(it) || formatAsInlineTags.contains(it),
                        empty = emptyTags.contains(it),
                        preserveWhitespace = preserveWhitespaceTags.contains(it),
                        formListed = formListedTags.contains(it),
                        formSubmit = formSubmitTags.contains(it)
                    )
                }.associateBy { it.name }
    }
}

val Element.tag
    get() = Tag.tags[this.tagName.lowercase()] ?: Tag(this.tagName.lowercase())

class PrettyPrinter {

    private val paddingChar = " "
    private val paddingWidth = 1
    private val lineTerminator = "\n"
    private val collapsibleAttributes = setOf(
        "allowfullscreen", "async", "autofocus", "checked", "compact", "declare", "default", "defer", "disabled",
        "formnovalidate", "hidden", "inert", "ismap", "itemscope", "multiple", "muted", "nohref", "noresize",
        "noshade", "novalidate", "nowrap", "open", "readonly", "required", "reversed", "seamless", "selected",
        "sortable", "truespeed", "typemustmatch"
    )

    fun print(nodes: NodeList, builder: StringBuilder = StringBuilder()) = builder
        .also { nodes.asList().forEach { printInternal(it, builder, 0) } }.toString()

    fun print(node: Node, builder: StringBuilder = StringBuilder()) = builder
        .also { printInternal(node, builder) }.toString()

    private fun printInternal(node: Node, builder: StringBuilder = StringBuilder(), level: Int = 0) {
        //println("Printing ${node.nodeType}")
        when (node.nodeType) {
            NodeType.ELEMENT_NODE -> {
                val el = node.unsafeCast<Element>()
                printTagStart(builder, el, level)
                node.childNodes.asList().forEach { printInternal(it, builder, level + 1) }
                printTagEnd(builder, el, level)
            }

            NodeType.TEXT_NODE -> {
                printText(builder, node.unsafeCast<Text>(), level)
            }

            else -> {
                builder.append(node.nodeValue)
            }
        }
    }

    private fun indent(builder: StringBuilder, level: Int) {
        //println("Identing width $level")
        builder.append(lineTerminator).append(paddingChar.repeat(paddingWidth * level))
    }

    private fun printTagStart(builder: StringBuilder, node: Element, level: Int) {
        val tag = node.tag
        val parentTag = node.parentElement?.tag
        if (parentTag != null && parentTag.formatBlock && !parentTag.inline && builder.isNotEmpty()) {
            indent(builder, level)
        }
        //println("Start tag $tag")
        builder.append("<${tag.name}")
        if (node.attributes.length > 0) printAttributes(builder, node)
        if (node.childNodes.length == 0 && tag.empty) {
            builder.append(" /")
        }
        builder.append(">")
    }

    private fun printTagEnd(builder: StringBuilder, node: Element, level: Int) {
        val tag = node.tag
        //Skip selfclosing empty tags
        //println("End tag $tag")
        if (node.childNodes.length == 0 && tag.empty) return
        if (node.childNodes.length > 0 && tag.formatBlock) indent(builder, level)
        builder.append("</${node.lowerTagName}>")
    }

    //FIXME: Order of attributes is implementation specific.
    // Linkedom inserts new attributes before old attributes, while other insert them after
    // Since the tests are written to conform to JSoup we might need to figure something out here
    private fun printAttributes(builder: StringBuilder, node: Element) {
        for (i in 0 until node.attributes.length) {
            val attributeName = node.attributes[i]?.name
            builder.append(" $attributeName")
            if (!node.attributes[i]?.value.isNullOrEmpty() || !collapsibleAttributes.contains(attributeName)) {
                builder.append("=\"${node.attributes[i]?.value ?: ""}\"")
            }
        }
    }

    private fun printText(builder: StringBuilder, node: Text, level: Int) {
        if (node.previousSibling == null
            && node.parentNode?.nodeType == NodeType.ELEMENT_NODE
            && node.parentNode!!.unsafeCast<Element>().tag.formatBlock
            && node.nodeValue!!.isNotBlank()
        ) {
            indent(builder, level)
        }

        val normalizeWhitespace = !preserveWhitespace(node.parentElement)
        val stripLeadingWhitespace = node.parentElement?.nodeType == NodeType.DOCUMENT_NODE
        val text = node.nodeValue!!
        //print("Printing text \"")
        if (normalizeWhitespace) {
            var lastWasWS = false
            var reachedText = false
            for (c in text) {
                if (c.isWhitespace() && !lastWasWS && (!stripLeadingWhitespace || reachedText))
                    builder.append(" ")//.also { print(" ") }
                lastWasWS = c.isWhitespace()
                reachedText = reachedText || !lastWasWS
                if (!lastWasWS) builder.append(c)//.also { print("$c(${c.code})") }
            }
        } else {
            builder.append(text)
        }
        //println("\" ($normalizeWhitespace, $stripLeadingWhitespace, \"${node}\")")

    }

    private fun preserveWhitespace(parent: Element?): Boolean {
        var el = parent
        while (el != null) {
            if (el.tag.preserveWhitespace) return true
            el = el.parentElement
        }
        return false
    }

}

class TextPrinter {

    fun print(node: Node, accum: StringBuilder = StringBuilder()) = accum
        .also { printInternal(node, accum)}.toString().trim()

    private fun printInternal(node: Node, accum: StringBuilder) {
        when (node.nodeType) {
            NodeType.ELEMENT_NODE -> {
                val el = node.unsafeCast<Element>()
                val tag = el.tag
                //println("Starting ${tag.name} (${tag.block}, ${accum.lastOrNull()?.isWhitespace() == false})")
                if (accum.lastOrNull()?.isWhitespace() == false && (tag.block || tag.name == "br"))
                    accum.append(" ")//.also { println("Starting WS") }
                el.childNodes.asList().forEach { print(it, accum) }
                //println("Closing ${tag.name} (${tag.block}, ${el.nextSibling}, ${accum.lastOrNull()?.isWhitespace() == false})")
                if (tag.block
                    && (el.nextSibling != null && el.nextSibling?.nodeType == NodeType.TEXT_NODE)
                    && accum.lastOrNull()?.isWhitespace() == false
                )
                    accum.append(" ")//.also { println("Closing WS") }
            }
            NodeType.TEXT_NODE -> {
                //println("Text ${node.textContent}")
                if (preserveWhitespace(node.parentElement))
                    accum.append(node.textContent)
                else if (!node.textContent.isNullOrEmpty()) {
                    val stripLeadingWhitespace = accum.lastOrNull()?.isWhitespace() == true
                    var lastWasWS = false
                    var reachedText = false
                    for (c in node.textContent!!) {
                        if (c.isWhitespace()) {
                            if ((stripLeadingWhitespace && !reachedText) || lastWasWS)
                                continue
                            lastWasWS = true
                            accum.append(" ")//.also { print("Normalized WS") }
                        } else {
                            lastWasWS = false
                            reachedText = true
                            accum.append(c)//.also { print("$c(${c.code})") }
                        }
                    }
                }
            }
        }
    }

    private fun preserveWhitespace(parent: Element?): Boolean {
        var el = parent
        while (el != null) {
            if (el.tag.preserveWhitespace) return true
            el = el.parentElement
        }
        return false
    }

}