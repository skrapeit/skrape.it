import com.adarshr.gradle.testlogger.theme.ThemeType.MOCHA_PARALLEL

plugins {
    id("com.adarshr.test-logger")
}

testlogger {
    theme = MOCHA_PARALLEL
    showFullStackTraces = false
    slowThreshold = 1_000
}
