package it.skrape.fetcher.logging

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.PatternLayout
import ch.qos.logback.classic.spi.Configurator
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.encoder.LayoutWrappingEncoder
import ch.qos.logback.core.spi.ContextAwareBase

public class LoggingConfigurator : Configurator, ContextAwareBase() {
    override fun configure(loggerContext: LoggerContext) {
        val consoleAppender = ConsoleAppender<ILoggingEvent>()
        consoleAppender.context = loggerContext
        consoleAppender.name = "console"
        consoleAppender.isWithJansi = false

        val encoder = LayoutWrappingEncoder<ILoggingEvent>()
        encoder.context = loggerContext

        val patternLayout = PatternLayout()
        patternLayout.pattern = "%highlight(%.-1level) %date{HH:mm:ss.SSS} [%30.30logger] %msg%n"
        patternLayout.context = loggerContext
        patternLayout.start()
        encoder.layout = patternLayout

        consoleAppender.encoder = encoder
        consoleAppender.start()

        val htmlUnit = loggerContext.getLogger("com.gargoylesoftware.htmlunit")
        htmlUnit.level = Level.OFF

        val apacheHttp = loggerContext.getLogger("org.apache.http")
        apacheHttp.level = Level.ERROR

        val rootLogger = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME)
        rootLogger.level = Level.INFO
        rootLogger.addAppender(consoleAppender)
    }
}