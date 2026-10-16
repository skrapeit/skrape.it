import dev.detekt.gradle.Detekt
import org.gradle.language.base.plugins.LifecycleBasePlugin
import org.gradle.api.artifacts.VersionCatalogsExtension

plugins {
    id("dev.detekt")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

detekt {
    toolVersion = libs.findVersion("detekt").get().requiredVersion
    autoCorrect = false
    config.setFrom(file("${rootProject.projectDir}/config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
    parallel = true
}

tasks.withType<Detekt>().configureEach {
    reports {
        html.required.set(true)
        checkstyle.required.set(true)
        markdown.required.set(true)
        sarif.required.set(true)
    }
}

tasks.named(LifecycleBasePlugin.CHECK_TASK_NAME) {
    dependsOn(tasks.detekt)
}
