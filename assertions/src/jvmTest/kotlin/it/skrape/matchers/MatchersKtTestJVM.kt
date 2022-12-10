package it.skrape.matchers

import io.mockk.every
import io.mockk.mockk
import it.skrape.selects.DocElement

actual fun mockDocElementListOfSize(mockSize:Int): List<DocElement> = mockk {
    every { size } returns mockSize
}
actual fun mockDocElement(mockIsPresent:Boolean, mockCssSelector:String): DocElement = mockk {
    every { isPresent } returns mockIsPresent
    every { toCssSelector } returns mockCssSelector
}