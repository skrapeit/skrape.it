package it.skrape.selects.html5

import it.skrape.a3TimesNestedTag
import it.skrape.core.htmlDocument
import it.skrape.matchers.toBe
import it.skrape.selects.text
import org.junit.jupiter.api.Test

internal class DemarcatingEditsSelectorsKtTest {

    @Test
    fun `can parse del-tag`() {
        htmlDocument(a3TimesNestedTag("del")) {
            del {
                findAll {
                    text toBe "1 2 3 2 3 3"
                }
                findFirst {
                    text toBe "1 2 3"
                }
                del {
                    findAll {
                        text toBe "2 3 3"
                    }
                    findFirst {
                        text toBe "2 3"
                        del {
                            findAll {
                                text toBe "3"
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    fun `can parse ins-tag`() {
        htmlDocument(a3TimesNestedTag("ins")) {
            ins {
                findAll {
                    text toBe "1 2 3 2 3 3"
                }
                findFirst {
                    text toBe "1 2 3"
                }
                ins {
                    findAll {
                        text toBe "2 3 3"
                    }
                    findFirst {
                        text toBe "2 3"
                        ins {
                            findAll {
                                text toBe "3"
                            }
                        }
                    }
                }
            }
        }
    }
}