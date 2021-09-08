package com.meetings.database

import com.meetings.database.users.MemoryUsersRepository
import com.meetings.users.port.UsersRepository

data class DatabaseConfig(
    val connectionString: String, val driver: String, val user: String, val password: String
)

class Database(private val config: DatabaseConfig) {
    val usersRepository: UsersRepository = MemoryUsersRepository()

    fun connect() {
        // TODO
    }
}