<a href="https://docs.skrape.it/docs/"><img width="150px" height="150px" align="right" src="../skrape.png"/><a/>
<a href="https://ktor.io/"><img width="200px" align="right" src="assets/ktor.png"/><a/>

[![Documentation](https://img.shields.io/badge/skrape%7Bit%7D-docs-blue.svg)](https://docs.skrape.it)
[![maven central](https://img.shields.io/maven-central/v/it.skrape/skrapeit-ktor.svg?color=0)](https://search.maven.org/search?q=g:it.skrape%20AND%20a:skrapeit-ktor&skrapeit-ktor=gav)
[![License](https://img.shields.io/github/license/skrapeit/skrape.it.svg)](https://github.com/skrapeit/skrape.it/blob/master/LICENSE)
[![JDK](https://img.shields.io/badge/jdk-8-green.svg)](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

[![master build status](https://img.shields.io/travis/skrapeit/skrapeit-ktor-extension.svg?label=master)](https://travis-ci.org/skrapeit/skrapeit-ktor-extension)
[![Codecov](https://img.shields.io/codecov/c/github/skrapeit/skrapeit-ktor-extension.svg)](https://codecov.io/gh/skrapeit/skrapeit-ktor-extension)
[![Known Vulnerabilities](https://snyk.io/test/github/skrapeit/skrapeit-ktor-extension/badge.svg?targetFile=pom.xml)](https://snyk.io/test/github/skrapeit/skrapeit-ktor-extension?targetFile=pom.xml)

[![Donate](https://img.shields.io/badge/-donate-blue.svg?logo=paypal)](https://www.paypal.me/skrapeit)
[![Awesome Kotlin Badge](https://kotlin.link/awesome-kotlin.svg)](https://github.com/KotlinBy/awesome-kotlin)

[skrape{it} Ktor Extension](https://docs.skrape.it)
======================================================

An Extension to test [Ktor](https://ktor.io/) endpoints that produces HTML in a meaningful way.

_**[skrape{it}](http://www.skrape.it)**_ is a Kotlin-based HTML testing and web scraping library
that can be used seamlessly in Spring-Boot, Android or other JVM projects.
It places particular emphasis on ease of use, a high level of readability, 
attention to performance through the use of non-blocking operations and is not 
bound to a specific test runner.

### Example

![assets/example-usage](assets/example-usage.gif "Documentation by example")

### Setup
> Gradle (Kotlin DSL):
>```kotlin
>testCompile("it.skrape:skrapeit-core:+")
>testCompile("it.skrape:skrapeit-ktor:+")
>```

> Gradle (Groovy DSL):
>```groovy
>testCompile "it.skrape:skrapeit-core:+"
>testCompile "it.skrape:skrapeit-ktor:+"
>```

> Maven:
>```xml
><dependency>
>   <groupId>it.skrape</groupId>
>   <artifactId>skrapeit-core</artifactId>
>   <version>LATEST</version>
>   <scope>test</scope>
> </dependency>
><dependency>
>   <groupId>it.skrape</groupId>
>   <artifactId>skrapeit-ktor</artifactId>
>   <version>LATEST</version>
>   <scope>test</scope>
> </dependency>
>```

### Read the Docs

You'll always find documentation of the latest release at 
**[https://docs.skrape.it](https://docs.skrape.it)**
