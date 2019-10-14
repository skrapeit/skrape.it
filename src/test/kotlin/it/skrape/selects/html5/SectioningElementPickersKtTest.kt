package it.skrape.selects.html5

import it.skrape.aValidDocument
import it.skrape.matchers.toBePresent
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class SectioningElementPickersKtTest {

    @Test
    fun `can parse body-tag`() {
        val result = aValidDocument()
        result.body {
            toBePresent()
        }
    }

    @Test
    fun `can parse div-tag`() {
        val result = aValidDocument()
        result.div {
            expectThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse section-tag`() {
        val result = aValidDocument()
        result.section {
            expectThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse nav-tag`() {
        val result = aValidDocument()
        result.nav {
            expectThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse article-tag`() {
        val result = aValidDocument()
        result.article {
            expectThat(text()).isEqualTo("i'm an article")
        }
    }

    @Test
    fun `can parse aside-tag`() {
        val result = aValidDocument()
        result.aside {
            expectThat(text()).isEqualTo("i'm an aside")
        }
    }

    @Test
    fun `can parse h1-tag`() {
        val result = aValidDocument()
        result.h1 {
            expectThat(text()).isEqualTo("i'm the headline")
        }
    }

    @Test
    fun `can parse h2-tag`() {
        val result = aValidDocument()
        result.h2 {
            expectThat(text()).isEqualTo("i'm a h2")
        }
    }

    @Test
    fun `can parse h3-tag`() {
        val result = aValidDocument()
        result.h3 {
            expectThat(text()).isEqualTo("i'm a h3")
        }
    }

    @Test
    fun `can parse h4-tag`() {
        val result = aValidDocument()
        result.h4 {
            expectThat(text()).isEqualTo("i'm a h4")
        }
    }

    @Test
    fun `can parse h5-tag`() {
        val result = aValidDocument()
        result.h5 {
            expectThat(text()).isEqualTo("i'm a h5")
        }
    }

    @Test
    fun `can parse h6-tag`() {
        val result = aValidDocument()
        result.h6 {
            expectThat(text()).isEqualTo("i'm a h6")
        }
    }

    @Test
    fun `can parse header-tag`() {
        val result = aValidDocument()
        result.header {
            expectThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse footer-tag`() {
        val result = aValidDocument()
        result.footer {
            expectThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse address-tag`() {
        val result = aValidDocument()
        result.address {
            expectThat(text()).isEqualTo("i'm an address")
        }
    }

    @Test
    fun `can parse main-tag`() {
        val result = aValidDocument()
        result.main {
            expectThat(size).isEqualTo(1)
        }
    }
}