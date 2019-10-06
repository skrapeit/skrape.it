package it.skrape.selects.html5

import assertk.assertThat
import assertk.assertions.isEqualTo
import it.skrape.aValidResult
import it.skrape.matchers.toBePresent
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SectioningElementPickersKtTest {

    @Test
    fun `can parse body-tag`() {
        val result = aValidResult()
        result.body {
            toBePresent()
        }
    }

    @Test
    fun `can parse div-tag`() {
        val result = aValidResult()
        result.div {
            assertThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse section-tag`() {
        val result = aValidResult()
        result.section {
            assertThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse nav-tag`() {
        val result = aValidResult()
        result.nav {
            assertThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse article-tag`() {
        val result = aValidResult()
        result.article {
            assertThat(text()).isEqualTo("i'm an article")
        }
    }

    @Test
    fun `can parse aside-tag`() {
        val result = aValidResult()
        result.aside {
            assertThat(text()).isEqualTo("i'm an aside")
        }
    }

    @Test
    fun `can parse h1-tag`() {
        val result = aValidResult()
        result.h1 {
            assertThat(text()).isEqualTo("i'm the headline")
        }
    }

    @Test
    fun `can parse h2-tag`() {
        val result = aValidResult()
        result.h2 {
            assertThat(text()).isEqualTo("i'm a h2")
        }
    }

    @Test
    fun `can parse h3-tag`() {
        val result = aValidResult()
        result.h3 {
            assertThat(text()).isEqualTo("i'm a h3")
        }
    }

    @Test
    fun `can parse h4-tag`() {
        val result = aValidResult()
        result.h4 {
            assertThat(text()).isEqualTo("i'm a h4")
        }
    }

    @Test
    fun `can parse h5-tag`() {
        val result = aValidResult()
        result.h5 {
            assertThat(text()).isEqualTo("i'm a h5")
        }
    }

    @Test
    fun `can parse h6-tag`() {
        val result = aValidResult()
        result.h6 {
            assertThat(text()).isEqualTo("i'm a h6")
        }
    }

    @Test
    fun `can parse header-tag`() {
        val result = aValidResult()
        result.header {
            assertThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse footer-tag`() {
        val result = aValidResult()
        result.footer {
            assertThat(size).isEqualTo(1)
        }
    }

    @Test
    fun `can parse address-tag`() {
        val result = aValidResult()
        result.address {
            assertThat(text()).isEqualTo("i'm an address")
        }
    }

    @Test
    fun `can parse main-tag`() {
        val result = aValidResult()
        result.main {
            assertThat(size).isEqualTo(1)
        }
    }
}