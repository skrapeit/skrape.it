<img width="150px" height="150px" align="right" src="skrape.png"/>

[![Documentation](https://img.shields.io/badge/skrape%7Bit%7D-docs-blue.svg)](https://docs.skrape.it)
[![maven central](https://img.shields.io/maven-central/v/it.skrape/skrapeit.svg?color=0)](https://search.maven.org/search?q=g:it.skrape%20AND%20a:skrapeit&skrapeit=gav)
[![skrape-it @ kotlinlang.slack.com](https://img.shields.io/static/v1?label=kotlinlang&message=skrape-it&color=blue&logo=slack)](https://kotlinlang.slack.com/archives/CSPDWD4R4)
[![License](https://img.shields.io/github/license/skrapeit/skrape.it.svg)](https://github.com/skrapeit/skrape.it/blob/master/LICENSE)

[![master build status](https://github.com/skrapeit/skrape.it/actions/workflows/gradle.yml/badge.svg)](https://github.com/skrapeit/skrape.it/actions")
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
- [x] Open to implement your own fetcher
- [x] Supports non-blocking fetching / Coroutine support
### Extensions
In addition, extensions for well-known testing libraries are provided to extend them with the mentioned skrape{it} functionality.
Currently available:
* **[skrape{it} MockMvc extension](https://github.com/skrapeit/skrape.it/tree/master/mock-mvc-extension)**
* **[skrape{it} Ktor extension](https://github.com/skrapeit/skrape.it/tree/master/ktor-extension)**

---

## Quick Start
### Read the Docs
You'll always find the latest documentation, release notes and examples regarding official releases at **[https://docs.skrape.it](https://docs.skrape.it)**.
The README file you are reading right now provides example related to the latest master. Just use it [if you won't wait](#using-bleeding-edge-features-before-official-release) for latest changes to be released. 
If you don't want to read that much or just want to get a rough overview on how to use **skrape{it}**, you can have a look at the [Documentation by Example](#documentation-by-example) section which refers to the current master.


### Installation
All our official/stable releases will be published to [mavens central repository](https://search.maven.org/search?q=g:it.skrape%20AND%20a:skrapeit&skrapeit=gav).

#### Add dependency

<details open><summary>Gradle</summary>

```kotlin
dependencies {
    implementation("it.skrape:skrapeit:1.1.1")
}
```
</details>

<details><summary>Maven</summary>

```xml
<dependency>
    <groupId>it.skrape</groupId>
    <artifactId>skrapeit</artifactId>
    <version>1.1.1</version>
</dependency>
```
</details>

#### using bleeding edge features before official release
We are offering snapshot releases by publishing every successful build of a commit that has been pushed to master branch. Thereby you can just install the latest implementation of skrape{it}.
Be careful since these are non-official releases and may be unstable as well as breaking changes can occur at any time.

##### Add experimental stuff

<details open><summary>Gradle</summary>

```kotlin
repositories {
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
}
dependencies {
    implementation("it.skrape:skrapeit:0-SNAPSHOT") { isChanging = true } // version number will stay - implementation may change ...
}

// optional
configurations.all {
    resolutionStrategy {
        cacheChangingModulesFor(0, "seconds")
    }
}
```
</details>

<details><summary>Maven</summary>

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
    </repository>
</repositories>

...

<dependency>
    <groupId>it.skrape</groupId>
    <artifactId>skrapeit</artifactId>
    <version>0-SNAPSHOT</version>
</dependency>
```
</details>

## Documentation by Example
###### (referring to current master)

> You can find further examples in the [projects integration tests](https://github.com/skrapeit/skrape.it/blob/master/integrationtests/src/test/kotlin/DslTest.kt).

### Parse and verify HTML from String
```kotlin
@Test
fun `can read and return html from String`() {
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

### Parse HTML and extract
```kotlin
data class MySimpleDataClass(
    val httpStatusCode: Int,
    val httpStatusMessage: String,
    val paragraph: String,
    val allParagraphs: List<String>,
    val allLinks: List<String>
)

class HtmlExtractionService {

    fun extract() {
        val extracted = skrape(HttpFetcher) {
            request {
                url = "http://localhost:8080"
            }

            extract {
                MySimpleDataClass(
                    httpStatusCode = status { code },
                    httpStatusMessage = status { message },
                    allParagraphs = document.p { findAll { eachText } },
                    paragraph = document.p { findFirst { text } },
                    allLinks = document.a { findAll { eachHref } }
                )
            }
        }
        print(extracted)
        // will print:
        // MyDataClass(httpStatusCode=200, httpStatusMessage=OK, paragraph=i'm a paragraph, allParagraphs=[i'm a paragraph, i'm a second paragraph], allLinks=[http://some.url, http://some-other.url])
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
                url = "http://localhost:8080"
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
fun getDocumentByUrl(urlToScrape: String) = skrape(BrowserFetcher) { // <--- pass BrowserFetcher to include rendered JS
    request { url = urlToScrape }
    extract { htmlDocument { this } }
}


fun main() {
    // do stuff with the document
    println(getDocumentByUrl("https://docs.skrape.it").eachLink)
}
```

### Scrape async
#### skrape{it}'s `AsyncFetcher` provides coroutine support
```kotlin
suspend fun getAllLinks(): Map<String, String> = skrape(AsyncFetcher) {
    request {
        url = "https://my-fancy.website"
    }
    extract {
        htmlDocument { eachLink }
    }
}
```

### Configure HTTP-Client:
```kotlin
class ExampleTest {
    val myPreConfiguredClient = skrape(HttpFetcher) {
        // url can be a plain url as string or build by #urlBuilder
        request {
            method = Method.POST // defaults to GET
            
            url = "" // you can  either pass url as String (defaults to 'http://localhost:8080')
            url { // or build url (will respect value from url as String param)
                // thereby you can pass a url and just override or add parts
                protocol = UrlBuilder.Protocol.HTTPS // defaults to given scheme from url param (HTTP if not set)
                host = "skrape.it" // defaults to given host from url param (localhost if not set)
                port = 12345  // defaults to given port from url param (8080 if not set explicitly - none port if given url param value does noit have port) - set to -1 to remove port
                path = "/foo" // defaults to given path from url param (none path if not set)
                queryParam { // can handle adding query parameters of several types (defaults to none)
                    "foo" to "bar" // add query paramter foo=bar
                    "aaa" to false // add query paramter aaa=false
                    "bbb" to .4711 // add query paramter bbb=0.4711
                    "ccc" to 42    // add query paramter ccc=42
                    "ddd" to listOf("a", 1, null) // add query paramter ddd=a,1,null
                    +"xxx"         // add query paramter xxx (just key, no value)
                }
            }
            timeout = 5000 // optional -> defaults to 5000ms
            followRedirects = true // optional -> defaults to true
            userAgent = "some custom user agent" // optional -> defaults to "Mozilla/5.0 skrape.it"
            cookies = mapOf("some-cookie-name" to "some-value") // optional
            headers = mapOf("some-custom-header" to "some-value") // optional
        }
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

### send request body
#### 1) plain as string
##### most low level option, needs to set content-type header "by hand"
```kotlin
skrape(HttpFetcher) {
    request {
        url = "https://www.my-fancy.url"
        method = Method.GET
        headers = mapOf("Content-Type" to "application/json")
        body = """{"foo":"bar"}"""
    }
    extract {
        htmlDocument {
            ...
```

#### 2) plain text with auto added content-type header that can be optionally overwritten
```kotlin
skrape(HttpFetcher) {
    request {
        url = "https://www.my-fancy.url"
        method = Method.POST
        body {
            data = "just a plain text" // content-type header will automatically set to "text/plain"
            contentType = "your-custom/content" // can optionally override content-type
        }
    }
    extract {
        htmlDocument {
            ...
```

#### 3) with helper functions for json or xml bodies
##### supports json and xml autocompletion when using intellij
```kotlin
skrape(HttpFetcher) {
    request {
        url = "https://www.my-fancy.url"
        method = Method.POST
        body {
            json("""{"foo":"bar"}""") // will automatically set content-type header to "application/json" 
            // or
            xml("<foo>bar</foo>") // will automatically set content-type header to "text/xml" 
            // or
            form("foo=bar") // will automatically set content-type header to "application/x-www-form-urlencoded" 
        }
    }
    extract {
        htmlDocument {
            ...
```


#### 4 with on the fly created json via dsl
```kotlin
skrape(HttpFetcher) {
    request {
        url = "https://www.my-fancy.url"
        method = Method.POST
        body {
            // will automatically set content-type header to "application/json"
            // will create {"foo":"bar","xxx":{"a":"b","c":[1,"d"]}} as request body
            json {
                "foo" to "bar"
                "xxx" to json {
                    "a" to "b"
                    "c" to listOf(1, "d")
                }
            }
        }
    }
    extract {
        htmlDocument {
            ...
```

#### 5 with on the fly created form via dsl
```kotlin
skrape(HttpFetcher) {
    request {
        url = "https://www.my-fancy.url"
        method = Method.POST
        body {
            // will automatically set content-type header to "application/x-www-form-urlencoded"
            // will create foo=bar&xxx=1.5 as request body
            form {
                "foo" to "bar"
                "xxx" to 1.5
            }
        }
    }
    extract {
        htmlDocument {
            ...
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
