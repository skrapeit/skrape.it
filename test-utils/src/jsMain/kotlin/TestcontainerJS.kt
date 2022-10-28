@file:JsModule("testcontainers")
@file:JsNonModule

import kotlin.js.Promise

external class GenericContainer(image: String) {
    fun withExposedPorts(vararg ports: Int): GenericContainer
    fun withCmd(commands: Array<String>): GenericContainer
    fun withBindMount(source: String, target: String, bindMode: String): GenericContainer
    fun start(): Promise<StartedContainer>
}

external class StartedContainer {
    fun getMappedPort(port: Int): Int
    fun getHost(): String
}