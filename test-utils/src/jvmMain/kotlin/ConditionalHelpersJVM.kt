import org.testcontainers.DockerClientFactory

actual enum class Platform(actual val value: Boolean) {
    JVM(true),
    WINDOWS(System.getProperty("os.name").contains("Windows")),
    LINUX(System.getProperty("os.name").contains("Linux")),
    JS(false),
    WEB(false),
    NODE(false);
}

actual suspend fun testDockerAvailable(): Boolean = DockerClientFactory.instance().isDockerAvailable