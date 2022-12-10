package it.skrape.selects.html5

import a3TimesNestedTag
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import it.skrape.core.htmlDocument
import it.skrape.selects.text

class DemarcatingEditsSelectorsKtTest: FunSpec({

    test("can parse del-tag") {
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

    test("can parse ins-tag") {
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
})