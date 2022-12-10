
import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.test.Enabled
import io.kotest.core.test.EnabledIf
import io.kotest.core.test.EnabledOrReasonIf

expect enum class Platform {
    JVM,
    WINDOWS,
    LINUX,
    JS,
    WEB,
    NODE;

    val value: Boolean
}

fun runOnPlatform(vararg platforms: Platform): EnabledIf = { platforms.any { it.value } }

//@AutoScan
object DockerProjectConfig: AbstractProjectConfig() {

    var enabledOrReasonIf: EnabledOrReasonIf = {
        println("Test enabled $dockerRunning")
        if (dockerRunning)
            Enabled.enabled
        else
            Enabled.disabled("No docker client found")
    }

    lateinit var wiremock: Testcontainer.Wiremock
    lateinit var httpBin: String

    var dockerRunning:Boolean = true
        private set

    override suspend fun beforeProject() {
        dockerRunning = testDockerAvailable()
        println("DockerRunning=$dockerRunning")
        if (dockerRunning) {
            println("Creating containers")
            wiremock = Testcontainer.getWiremock()
            httpBin = Testcontainer.getHttpBin()
        }
    }
}


expect suspend fun testDockerAvailable(): Boolean