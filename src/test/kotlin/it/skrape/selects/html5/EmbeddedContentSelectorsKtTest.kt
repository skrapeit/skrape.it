package it.skrape.selects.html5

import it.skrape.a3TimesNestedTag
import it.skrape.core.htmlDocument
import it.skrape.matchers.toBe
import it.skrape.selects.text
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT

@Execution(CONCURRENT)
internal class EmbeddedContentSelectorsKtTest {

    @Test
    fun `can parse applet-tag`() {
        htmlDocument(a3TimesNestedTag("applet")) {
            applet {
                findAll {
                    text toBe "123 23 3"
                }
                findFirst {
                    text toBe "123"
                }
                applet {
                    findAll {
                        text toBe "23 3"
                    }
                    findFirst {
                        text toBe "23"
                        applet {
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
    fun `can parse embed-tag`() {
        htmlDocument("<div><embed src='helloworld.swf'></div>") {
            embed {
                findFirst {
                    attribute("src") toBe "helloworld.swf"
                }
            }
            div {
                findFirst {
                    embed { findFirst { attribute("src") toBe "helloworld.swf" } }
                }
                embed {
                    findFirst { attribute("src") toBe "helloworld.swf" }
                }
            }
        }
    }

    @Test
    fun `can parse iframe-tag`() {
        htmlDocument("<div><iframe>hello</iframe></div>") {
            iframe {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    iframe { findFirst { text toBe "hello" } }
                }
                iframe {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse noembed-tag`() {
        htmlDocument("<div><noembed src='helloworld.swf'></div>") {
            noembed {
                findFirst {
                    attribute("src") toBe "helloworld.swf"
                }
            }
            div {
                findFirst {
                    noembed { findFirst { attribute("src") toBe "helloworld.swf" } }
                }
                noembed {
                    findFirst { attribute("src") toBe "helloworld.swf" }
                }
            }
        }
    }

    @Test
    fun `can parse object-tag`() {
        htmlDocument(a3TimesNestedTag("object")) {
            `object` {
                findAll {
                    text toBe "123 23 3"
                }
                findFirst {
                    text toBe "123"
                }
                `object` {
                    findAll {
                        text toBe "23 3"
                    }
                    findFirst {
                        text toBe "23"
                        `object` {
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
    fun `can parse param-tag`() {
        htmlDocument("<div><param name='autoplay' value='true'></div>") {
            param {
                findFirst {
                    attribute("name") toBe "autoplay"
                }
            }
            div {
                findFirst {
                    param { findFirst { attribute("name") toBe "autoplay" } }
                }
                param {
                    findFirst { attribute("name") toBe "autoplay" }
                }
            }
        }
    }

    @Test
    fun `can parse picture-tag`() {
        htmlDocument(a3TimesNestedTag("picture")) {
            picture {
                findAll {
                    text toBe "123 23 3"
                }
                findFirst {
                    text toBe "123"
                }
                picture {
                    findAll {
                        text toBe "23 3"
                    }
                    findFirst {
                        text toBe "23"
                        picture {
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
    fun `can parse source-tag`() {
        htmlDocument("<div><source src='horse.ogg' type='audio/ogg'></div>") {
            source {
                findFirst {
                    attribute("src") toBe "horse.ogg"
                }
            }
            div {
                findFirst {
                    source { findFirst { attribute("src") toBe "horse.ogg" } }
                }
                source {
                    findFirst { attribute("src") toBe "horse.ogg" }
                }
            }
        }
    }
}