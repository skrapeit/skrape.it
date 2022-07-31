package it.skrape.selects

import it.skrape.SkrapeItDsl
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

@Suppress("TooManyFunctions")
@SkrapeItDsl
public class Doc(public val document: Document, override var relaxed: Boolean = false) : DomTreeElement() {
    override val element: Element
        get() = this.document

    /**
     * Get the (unencoded) text of all children of this element, including any newlines and spaces present in the
     * original.
     *
     * @return unencoded, un-normalized text
     * @see text
     */
    public val wholeText: String by lazy { document.wholeText() }

    public val titleText: String by lazy { document.title() }

    override val toCssSelector: String = ""

    override fun makeDefaultElement(cssSelector: String): DocElement {
        return DocElement(Element(cssSelector), relaxed)
    }
}
