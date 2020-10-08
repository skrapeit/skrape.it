<a href="https://docs.skrape.it/docs/"><img width="150px" height="150px" align="right" src="../skrape.png"/><a/>
<a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/testing.html#spring-mvc-test-framework"><img width="150px" height="150px" align="right" src="assets/spring.png"/><a/>

[![Documentation](https://img.shields.io/badge/skrape%7Bit%7D-docs-blue.svg)](https://docs.skrape.it)
[![maven central](https://img.shields.io/maven-central/v/it.skrape/skrapeit-mockmvc.svg?color=0)](https://search.maven.org/search?q=g:it.skrape%20AND%20a:skrapeit-mockmvc&skrapeit-mockmvc=gav)
[![License](https://img.shields.io/github/license/skrapeit/skrape.it.svg)](https://github.com/skrapeit/skrape.it/blob/master/LICENSE)

[![master build status](https://github.com/skrapeit/skrape.it/workflows/master%20build/badge.svg)](https://github.com/skrapeit/skrape.it/actions?query=workflow%3A"master+build")
![last commit](https://img.shields.io/github/last-commit/skrapeit/skrape.it)
[![Codecov](https://img.shields.io/codecov/c/github/skrapeit/skrape.it.svg)](https://codecov.io/gh/skrapeit/skrape.it)
[![JDK](https://img.shields.io/badge/jdk-8-green.svg)](#)

[![contributions welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat)](https://github.com/skrapeit/skrape.it/issues)
[![GitHub contributors](https://img.shields.io/github/contributors/skrapeit/skrape.it)](https://github.com/skrapeit/skrape.it/graphs/contributors)
[![Donate](https://img.shields.io/badge/-donate-blue.svg?logo=paypal)](https://www.paypal.me/skrapeit)
[![Awesome Kotlin Badge](https://kotlin.link/awesome-kotlin.svg)](https://kotlin.link/?q=Testing)

[skrape{it} MockMvc Extension](https://docs.skrape.it)
======================================================

An Extension for [Spring MockMvc](https://docs.spring.io/spring/docs/current/spring-framework-reference/testing.html#spring-mvc-test-framework) tests to enable meaningful testing of controllers that produces HTML.

_**[skrape{it}](http://www.skrape.it)**_ is a Kotlin-based HTML testing and web scraping library
that can be used seamlessly in Spring-Boot, Android or other JVM projects.
It places particular emphasis on ease of use, a high level of readability, 
attention to performance through the use of non-blocking operations and is not 
bound to a specific test runner.

### Example

![example-usage](example-usage.gif "Documentation by example")

### Setup
> Gradle (Kotlin DSL):
>```
>testCompile("it.skrape:skrapeit-core:+")
>testCompile("it.skrape:skrapeit-mockmvc:+")
>```

> Gradle (Groovy DSL):
>```
>testCompile "it.skrape:skrapeit-core:+"
>testCompile "it.skrape:skrapeit-mockmvc:+"
>```

> Maven:
>```
><dependency>
>   <groupId>it.skrape</groupId>
>   <artifactId>skrapeit-core</artifactId>
>   <version>LATEST</version>
>   <scope>test</scope>
> </dependency>
><dependency>
>   <groupId>it.skrape</groupId>
>   <artifactId>skrapeit-mockmvc</artifactId>
>   <version>LATEST</version>
>   <scope>test</scope>
> </dependency>
>```

### Read the Docs

You'll always find documentation of the latest release at 
**[https://docs.skrape.it](https://docs.skrape.it)**
