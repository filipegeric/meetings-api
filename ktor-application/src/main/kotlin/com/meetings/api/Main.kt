package com.meetings.api

import com.meetings.api.plugins.installPlugins
import com.meetings.api.routing.configureRouting
import com.meetings.database.Database
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    val (serverConfig, dbConfig) = loadConfig()

    val database = Database(dbConfig)
    database.connect()

    embeddedServer(Netty, port = serverConfig.port) {
        installPlugins()
        configureRouting(database)
    }.start(wait = true)
}





