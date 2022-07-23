package buildsrc.convention

plugins {
    base
    id("com.adarshr.test-logger")
}

description =
    "Common build config that can be applied to all subprojects. This should typically be language-independent."

if (project != rootProject) {
    group = rootProject.group
    version = rootProject.version
}

testlogger {
    setTheme("mocha-parallel")
    slowThreshold = 1000
    showStandardStreams = false
}
