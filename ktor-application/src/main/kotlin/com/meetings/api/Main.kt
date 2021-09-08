package com.meetings.api

import com.meetings.api.plugins.installPlugins
import com.meetings.api.routing.configureRouting
import com.meetings.database.Database
import com.meetings.database.DatabaseConfig
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    val database = setupDatabase()
    database.connect()

    embeddedServer(Netty, port = 3000) {
        installPlugins()
        configureRouting(database)
    }.start(wait = true)
}

private fun setupDatabase(): Database = Database(
    DatabaseConfig(
        url = "jdbc:postgresql://localhost:5432/meetings-db",
        driver = "org.postgresql.Driver",
        user = "dev",
        password = "dev",
        runMigrations = true
    )
)






