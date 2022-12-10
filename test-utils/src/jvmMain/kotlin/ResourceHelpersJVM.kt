import io.ktor.utils.io.core.*
import io.ktor.utils.io.streams.*

actual suspend fun getInputFromFile(location: String): Input = DockerExtension::class.java.getResourceAsStream("/__files/$location").asInput()
actual suspend fun getMarkupFromFile(location: String): String = getInputFromFile(location).readText()