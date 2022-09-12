package it.skrape.selects

public expect class Doc {

    /**
     * Get the (unencoded) text of all children of this element, including any newlines and spaces present in the
     * original.
     *
     * @return unencoded, un-normalized text
     * @see text
     */
    public val wholeText: String

    public val titleText: String
}
