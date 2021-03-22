package it.skrape.selects.html5

import it.skrape.a3TimesNestedTag
import it.skrape.core.htmlDocument
import it.skrape.selects.text
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class DemarcatingEditsSelectorsKtTest {

    @Test
    fun `can parse del-tag`() {
        htmlDocument(a3TimesNestedTag("del")) {
            del {
                findAll {
                    expectThat(text).isEqualTo("1 2 3 2 3 3")
                }
                findFirst {
                    expectThat(text).isEqualTo("1 2 3")
                }
                del {
                    findAll {
                        expectThat(text).isEqualTo("2 3 3")
                    }
                    findFirst {
                        expectThat(text).isEqualTo("2 3")
                        del {
                            findAll {
                                expectThat(text).isEqualTo("3")
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
                    expectThat(text).isEqualTo("1 2 3 2 3 3")
                }
                findFirst {
                    expectThat(text).isEqualTo("1 2 3")
                }
                ins {
                    findAll {
                        expectThat(text).isEqualTo("2 3 3")
                    }
                    findFirst {
                        expectThat(text).isEqualTo("2 3")
                        ins {
                            findAll {
                                expectThat(text).isEqualTo("3")
                            }
                        }
                    }
                }
            }
        }
    }
}