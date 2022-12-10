import org.testcontainers.DockerClientFactory

actual val supportedPlatforms: List<Platform> by lazy {
    val supported = mutableListOf(Platform.JVM)
    if (System.getProperty("os.name").contains("Windows"))
        supported.add(Platform.WINDOWS)
    else if (System.getProperty("os.name").contains("Linux"))
        supported.add(Platform.LINUX)
    supported
}

actual suspend fun testDockerAvailable(): Boolean = DockerClientFactory.instance().isDockerAvailable