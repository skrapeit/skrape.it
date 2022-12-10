import io.ktor.utils.io.core.*

expect suspend fun getInputFromFile(location: String): Input
expect suspend fun getMarkupFromFile(location: String): String