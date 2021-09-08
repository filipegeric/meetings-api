package com.meetings.database

import com.meetings.database.users.ExposedUsersRepository
import com.meetings.users.port.UsersRepository
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database

data class DatabaseConfig(
    val url: String,
    val driver: String,
    val user: String,
    val password: String,
    val runMigrations: Boolean
)

class Database(private val config: DatabaseConfig) {
    val usersRepository: UsersRepository = ExposedUsersRepository()

    fun connect() {
        val (url, driver, user, password, runMigrations) = config
        Database.connect(url, driver, user, password)
        if (runMigrations) migrate()
    }

    private fun migrate() {
        val (url, _, user, password) = config
        val flyway = Flyway.configure().dataSource(url, user, password).load()
        flyway.migrate()
    }
}