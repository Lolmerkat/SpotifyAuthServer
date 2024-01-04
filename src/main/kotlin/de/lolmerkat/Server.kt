package de.lolmerkat

import de.lolmerkat.plugins.configureRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import java.util.*
import kotlin.concurrent.timerTask

var server: ApplicationEngine? = null

fun initServer(): ApplicationEngine? {
    server =
        if (server == null) embeddedServer(Netty, port = 8080, host = "127.0.0.1", module = Application::module)
        else server

    return server
}

fun start(wait: Boolean) = server?.start(wait)

fun stop() = server?.stop()

fun destroy() { server = null }

fun scheduleShutdown(timeInSeconds: Int) = Timer().schedule(timerTask {
    stop()
    destroy()
}, (timeInSeconds * 1000).toLong())

fun Application.module() {
    configureRouting()
}