package it.skrape.selects.html5

import a3TimesNestedTag
import io.kotest.matchers.shouldBe
import it.skrape.core.htmlDocument
import it.skrape.selects.text
import kotlin.js.JsName
import kotlin.test.Test

class DemarcatingEditsSelectorsKtTest {

    @Test
    @JsName("CanParseDelTag")
	fun `can parse del-tag`() {
        htmlDocument(a3TimesNestedTag("del")) {
            del {
                findAll {
                    text.shouldBe("1 2 3 2 3 3")
                }
                findFirst {
                    text.shouldBe("1 2 3")
                }
                del {
                    findAll {
                        text.shouldBe("2 3 3")
                    }
                    findFirst {
                        text.shouldBe("2 3")
                        del {
                            findAll {
                                text.shouldBe("3")
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    @JsName("CanParseInsTag")
	fun `can parse ins-tag`() {
        htmlDocument(a3TimesNestedTag("ins")) {
            ins {
                findAll {
                    text.shouldBe("1 2 3 2 3 3")
                }
                findFirst {
                    text.shouldBe("1 2 3")
                }
                ins {
                    findAll {
                        text.shouldBe("2 3 3")
                    }
                    findFirst {
                        text.shouldBe("2 3")
                        ins {
                            findAll {
                                text.shouldBe("3")
                            }
                        }
                    }
                }
            }
        }
    }
}