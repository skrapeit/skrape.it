package it.skrape.selects.html5

import a3TimesNestedTag
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
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
                    expect(text).toEqual("1 2 3 2 3 3")
                }
                findFirst {
                    expect(text).toEqual("1 2 3")
                }
                del {
                    findAll {
                        expect(text).toEqual("2 3 3")
                    }
                    findFirst {
                        expect(text).toEqual("2 3")
                        del {
                            findAll {
                                expect(text).toEqual("3")
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
                    expect(text).toEqual("1 2 3 2 3 3")
                }
                findFirst {
                    expect(text).toEqual("1 2 3")
                }
                ins {
                    findAll {
                        expect(text).toEqual("2 3 3")
                    }
                    findFirst {
                        expect(text).toEqual("2 3")
                        ins {
                            findAll {
                                expect(text).toEqual("3")
                            }
                        }
                    }
                }
            }
        }
    }
}