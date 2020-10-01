<img width="150px" height="150px" align="right" src="skrape.png"/>

[![Documentation](https://img.shields.io/badge/skrape%7Bit%7D-docs-blue.svg)](https://docs.skrape.it)
[![maven central](https://img.shields.io/maven-central/v/it.skrape/skrapeit-core.svg?color=0)](https://search.maven.org/search?q=g:it.skrape%20AND%20a:skrapeit-core&skrapeit-core=gav)
[![License](https://img.shields.io/github/license/skrapeit/skrape.it.svg)](https://github.com/skrapeit/skrape.it/blob/master/LICENSE)

[![master build status](https://github.com/skrapeit/skrape.it/workflows/master%20build/badge.svg)](https://github.com/skrapeit/skrape.it/actions?query=workflow%3A"master+build")
![last commit](https://img.shields.io/github/last-commit/skrapeit/skrape.it)
[![Codecov](https://img.shields.io/codecov/c/github/skrapeit/skrape.it.svg)](https://codecov.io/gh/skrapeit/skrape.it)
[![JDK](https://img.shields.io/badge/jdk-8-green.svg)](#)

[![contributions welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat)](https://github.com/skrapeit/skrape.it/issues)
[![GitHub contributors](https://img.shields.io/github/contributors/skrapeit/skrape.it)](https://github.com/skrapeit/skrape.it/graphs/contributors)
[![Donate](https://img.shields.io/badge/-donate-blue.svg?logo=paypal)](https://www.paypal.me/skrapeit)
[![Awesome Kotlin Badge](https://kotlin.link/awesome-kotlin.svg)](https://kotlin.link/?q=Testing)

[skrape{it}](https://docs.skrape.it)
====================================

_**[skrape{it}](http://www.skrape.it)**_ is a Kotlin-based HTML/XML testing and web scraping library
that can be used seamlessly in Spring-Boot, Ktor, Android or other Kotlin-JVM projects.
The ability to analyze and extract HTML including client-side rendered DOM trees and all other XML-related markup specifications such as SVG, UML, RSS,... makes it unique.
It places particular emphasis on ease of use and a high level of readability by providing an intuitive DSL.
First and foremost skrape{it} aims to be a testing tool (not tied to a particular test runner), but it can also be used to scrape websites in a convenient fashion.

## Features
### Parsing
- [x] Deserialization of HTML/XML from websites, local html files and html as string to data classes / POJOs.
- [x] Designed to deserialize HTML but can handle any XML-related markup specifications such as SVG, UML, RSS or XML itself.
- [x] DSL to select html elements as well as supporting CSS query-selector syntax by string invocation.
### Http-Client
- [x] Http-Client without verbosity and ceremony to make requests and corresponding request options like headers, cookies etc in a fluent style interface.
- [x] Pre-configure client regarding auth and other request settings 
- [x] Can handle client side rendered web pages. Javascript execution results can optionally be considered in the response body.
### Idomatic
- [x] Easy to use, idiomatic and type-safe DSL to ensure a high level of readability.
- [x] Build-in matchers/assertions based on infix functions to archive a very high level of readability.
- [x] DSL is behaving like a Fluent-Api to make data extraction/scraping as comfortable as possible.
### Compatibility
- [x] Not bind to a specific test-runner, framework or whatever.
- [x] Open to use any other assertion library of your choice.
### Extensions
In addition, extensions for well-known testing libraries are provided to extend them with the mentioned skrape{it} functionality.
Currently available:
* **[skrape{it} MockMvc extension](https://github.com/skrapeit/skrapeit-mockmvc-extension)**
* **[skrape{it} Ktor extension](https://github.com/skrapeit/skrapeit-ktor-extension)**

---

## Quick Start
### Read the Docs
You'll always find the latest documentation, release notes and examples regarding official releases at **[https://docs.skrape.it](https://docs.skrape.it)**.
The README file you are reading right now provides example related to the latest master. Just use it [if you won't wait](#using-bleeding-edge-features-before-official-release) for latest changes to be released. 
If you don't want to read that much or just want to get a rough overview on how to use **skrape{it}**, you can have a look at the [Documentation by Example](#documentation-by-example) section.


### Installation
All our official/stable releases will be published to [mavens central repository](https://search.maven.org/search?q=g:it.skrape%20AND%20a:skrapeit-core&core=gav).

#### Add dependency

<details open><summary>Gradle</summary>

```kotlin
dependencies {
    implementation("it.skrape:skrapeit-core:1.0.0-alpha6")
}
```
</details>

<details><summary>Maven</summary>

```xml
<dependency>
    <groupId>it.skrape</groupId>
    <artifactId>skrapeit-core</artifactId>
    <version>1.0.0-alpha6</version>
</dependency>
```
</details>

#### using bleeding edge features before official release
We are offering snapshot releases via jitpack. Thereby you can install every commit and version you want.
But be careful, these are non official releases and may be unstable as well as breaking changes can occur at any time.

If you want be a bit more safe you can use a certain commit instead of referencing skrape{it}'s master branch as a dependency to avoid sudden breaking changes.
! Please make sure to go to https://jitpack.io/#skrapeit/skrape.it/ click the commits tab and the "Get it"-button to force a build of a certain commit.

##### Add experimental stuff

<details open><summary>Gradle</summary>

```kotlin
repositories {
    maven { url = uri("https://jitpack.io") }
}
dependencies {
    implementation("com.github.skrapeit:skrape.it:master-SNAPSHOT")
    // or use a certain commit to avoid sudden breaking changes
    implementation("com.github.skrapeit:skrape.it:<commit-hash-short>")
}
```
</details>

<details><summary>Maven</summary>

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

...

<dependency>
    <groupId>com.github.skrapeit</groupId>
    <artifactId>skrape.it</artifactId>
    <version>master-SNAPSHOT</version>
</dependency>
```
</details>

## Documentation by Example
### Parse and verify HTML from String
```kotlin
@Test
internal fun `can read and return html from String`() {
    htmlDocument("""
        <html>
            <body>
                <h1>welcome</h1>
                <div>
                    <p>first p-element</p>
                    <p class="foo">some p-element</p>
                    <p class="foo">last p-element</p>
                </div>
            </body>
        </html>""") {

        h1 {
            findFirst {
                text toBe "welcome"
            }
            p {
                withClass = "foo"
                findFirst {
                    text toBe "some p-element"
                    className  toBe "foo"
                }
            }
            p {
                findAll {
                    text toContain "p-element"
                }
                findLast {
                    text toBe "last p-element"
                }
            }
        }
    }
}
```

### Parse HTML and extract it
```kotlin
data class MyDataClass(
        var httpStatusCode: Int = 0,
        var httpStatusMessage: String = "",
        var paragraph: String = "",
        var allParagraphs: List<String> = emptyList(),
        var allLinks: List<String> = emptyList()
)

class HtmlExtractionService {

    fun extract() {
        val extracted = skrape(HttpFetcher) {
            request {
                url = "http://localhost:8080/"
            }           

            extractIt<MyDataClass> {
                it.httpStatusCode = statusCode
                it.httpStatusMessage = statusMessage.toString()
                htmlDocument {
                    it.allParagraphs = p { findAll { eachText }}
                    it.paragraph = p { findFirst { text }}
                    it.allLinks = a { findAll { eachHref }}
                }
            }
        }
        print(extracted)
        // will print:
        // MyDataClass(httpStatusCode=200, httpStatusMessage=OK, paragraph=i'm a paragraph, allParagraphs=[i'm a paragraph, i'm a second paragraph], allLinks=[http://some.url, http://some-other.url])
    }
}
```

### Testing HTML responses:
```kotlin
@Test
fun `dsl can skrape by url`() {
    skrape(HttpFetcher) {
        request {
            url = "http://localhost:8080/example"
        }       
        expect {
            htmlDocument {
                // all official html and html5 elements are supported by the DSL
                div {
                    withClass = "foo" and "bar" and "fizz" and "buzz"

                    findFirst {
                        text toBe "div with class foo"

                        // it's possible to search for elements from former search results
                        // ⚠️ this is only available in jitpack version for now!
                        // e.g. search all matching span elements within the above div with class foo etc...
                        span {
                            findAll {
                                // do something
                            }                       
                        }                   
                    }

                    findAll {
                        toBePresentExactlyTwice
                    }
                }
                // can handle custom tags as well
                "a-custom-tag" {
                    findFirst {
                        toBePresentExactlyOnce
                        text toBe "i'm a custom html5 tag"
                        text
                    }
                }
                // can handle custom tags written in css selctor query syntax
                "div.foo.bar.fizz.buzz" {
                    findFirst {
                        text toBe "div with class foo"
                    }
                }

                // can handle custom tags and add selector specificas via DSL
                "div.foo" {

                    withClass = "bar" and "fizz" and "buzz"

                    findFirst {
                        text toBe "div with class foo"
                    }
                }
            }
        }
    }
}
```

### Scrape a client side rendered page:
```kotlin
fun getDocumentByUrl(urlToScrape: String) = skrape(BrowserFetcher) { // <--- pass Browser fetcher to include rendered JS
    request { url = urlToScrape }
    extract { htmlDocument { this } }
}


fun main() {
    // do stuff with the document
    println(getDocumentByUrl("https://docs.skrape.it").eachLink)
}
```

### Configure HTTP-Client:
```kotlin
class ExampleTest {
    val myPreConfiguredClient = skrape(HttpFetcher) {
        // url can be a plain url as string or build by #urlBuilder
        request {
            url = urlBuilder {
                protocol = UrlBuilder.Protocol.HTTPS
                host = "skrape.it"
                port = 12345
                path = "/foo"
                queryParam = mapOf("foo" to "bar")
            }
            timeout = 5000 // optional -> defaults to 5000ms
            followRedirects = true // optional -> defaults to true
            userAgent = "some custom user agent" // optional -> defaults to "Mozilla/5.0 skrape.it"
            cookies = mapOf("some-cookie-name" to "some-value") // optional
            headers = mapOf("some-custom-header" to "some-value") // optional
        }
        preConfigured
    }
    
    @Test
    fun `can use preconfigured client`() {
    
        myPreConfiguredClient.expect {
            status { code toBe 200 }
            // do more stuff
        }
    
        // slightly modify preconfigured client
        myPreConfiguredClient.apply {
            request {
                followRedirects = false
            }
        }.expect {
            status { code toBe 301 }
            // do more stuff
        }
    }
}
```

## Get in touch
If you need help, have questions on how to use skrape{it} or want to discuss features or bugs please raise issues on [GitHub](https://github.com/skrapeit/skrape.it/issues).
If you want to discuss or ask more generel things or possible implementations as well as feature ideas you can join the [#skrape-it channel on the Kotlin Slack](https://kotlinlang.slack.com/archives/CSPDWD4R4).

* Issues: You can discuss and raise issues on [GitHub](https://github.com/skrapeit/skrape.it/issues).
* Slack: Join the [#skrape-it](https://kotlinlang.slack.com/archives/CSPDWD4R4) channel on the Kotlin Slack.
* Twitter: Follow [@skrape_it](https://twitter.com/skrape_it) on Twitter for updates and release notifications.
* Stasckoverflow: post or search issues on [Stackoverflow](https://stackoverflow.com/search?q=skrape)

## :sparkling_heart: Support the project
Skrape{it} is and always will be **free and open-source**. I try to reply to everyone needing help using these projects. Obviously,
the development, maintenance takes time.

However, if you are using this project and be happy with it or just want to encourage me to continue creating stuff or fund the caffeine and pizzas that fuel its development, there are few ways you can do it :-
- **Starring and sharing the project** :rocket: to help make it more popular
- Giving proper credit when you use skrape{it}, tell your friends and others about it :smiley:
- Sponsor Skrape{it} with a one-time donations via PayPal by just click this button → [![Donate](https://img.shields.io/badge/-donate-blue.svg?logo=paypal)](https://www.paypal.me/skrapeit) or use the GitHub sponsors programm to support on a monthly basis :sparkling_heart:
