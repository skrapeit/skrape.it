package it.skrape.selects.html5

import a3TimesNestedTag
import it.skrape.core.htmlDocument
import it.skrape.selects.text
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class EmbeddedContentSelectorsKtTest {

    @Test
    fun `can parse applet-tag`() {
        htmlDocument(a3TimesNestedTag("applet")) {
            applet {
                findAll {
                    expectThat(text).isEqualTo("123 23 3")
                }
                findFirst {
                    expectThat(text).isEqualTo("123")
                }
                applet {
                    findAll {
                        expectThat(text).isEqualTo("23 3")
                    }
                    findFirst {
                        expectThat(text).isEqualTo("23")
                        applet {
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
    fun `can parse embed-tag`() {
        htmlDocument("<div><embed src='helloworld.swf'></div>") {
            embed {
                findFirst {
                    expectThat(attribute("src")).isEqualTo("helloworld.swf")
                }
            }
            div {
                findFirst {
                    embed {
                        findFirst {
                            expectThat(attribute("src")).isEqualTo("helloworld.swf")
                        }
                    }
                }
                embed {
                    findFirst {
                        expectThat(attribute("src")).isEqualTo("helloworld.swf")
                    }
                }
            }
        }
    }

    @Test
    fun `can parse iframe-tag`() {
        htmlDocument("<div><iframe>hello</iframe></div>") {
            iframe {
                findAll {
                    expectThat(text).isEqualTo("hello")
                }
            }
            div {
                findFirst {
                    iframe {
                        findFirst {
                            expectThat(text).isEqualTo("hello")
                        }
                    }
                }
                iframe {
                    findFirst {
                        expectThat(text).isEqualTo("hello")
                    }
                }
            }
        }
    }

    @Test
    fun `can parse noembed-tag`() {
        htmlDocument("<div><noembed src='helloworld.swf'></div>") {
            noembed {
                findFirst {
                    expectThat(attribute("src")).isEqualTo("helloworld.swf")
                }
            }
            div {
                findFirst {
                    noembed {
                        findFirst {
                            expectThat(attribute("src")).isEqualTo("helloworld.swf")
                        }
                    }
                }
                noembed {
                    findFirst {
                        expectThat(attribute("src")).isEqualTo("helloworld.swf")
                    }
                }
            }
        }
    }

    @Test
    fun `can parse object-tag`() {
        htmlDocument(a3TimesNestedTag("object")) {
            `object` {
                findAll {
                    expectThat(text).isEqualTo("123 23 3")
                }
                findFirst {
                    expectThat(text).isEqualTo("123")
                }
                `object` {
                    findAll {
                        expectThat(text).isEqualTo("23 3")
                    }
                    findFirst {
                        expectThat(text).isEqualTo("23")
                        `object` {
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
    fun `can parse param-tag`() {
        htmlDocument("<div><param name='autoplay' value='true'></div>") {
            param {
                findFirst {
                    expectThat(attribute("name")).isEqualTo("autoplay")
                }
            }
            div {
                findFirst {
                    param {
                        findFirst {
                            expectThat(attribute("name")).isEqualTo("autoplay")
                        }
                    }
                }
                param {
                    findFirst {
                        expectThat(attribute("name")).isEqualTo("autoplay")
                    }
                }
            }
        }
    }

    @Test
    fun `can parse picture-tag`() {
        htmlDocument(a3TimesNestedTag("picture")) {
            picture {
                findAll {
                    expectThat(text).isEqualTo("123 23 3")
                }
                findFirst {
                    expectThat(text).isEqualTo("123")
                }
                picture {
                    findAll {
                        expectThat(text).isEqualTo("23 3")
                    }
                    findFirst {
                        expectThat(text).isEqualTo("23")
                        picture {
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
    fun `can parse source-tag`() {
        htmlDocument("<div><source src='horse.ogg' type='audio/ogg'></div>") {
            source {
                findFirst {
                    expectThat(attribute("src")).isEqualTo("horse.ogg")
                }
            }
            div {
                findFirst {
                    source {
                        findFirst {
                            expectThat(attribute("src")).isEqualTo("horse.ogg")
                        }
                    }
                }
                source {
                    findFirst {
                        expectThat(attribute("src")).isEqualTo("horse.ogg")
                    }
                }
            }
        }
    }
}