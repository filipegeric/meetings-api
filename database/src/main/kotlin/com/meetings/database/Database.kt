package com.meetings.database

import com.meetings.database.users.MemoryUsersRepository
import com.meetings.users.port.UsersRepository

data class DatabaseConfig(val port: Int)

class Database(private val config: DatabaseConfig) {
    val usersRepository: UsersRepository = MemoryUsersRepository()

    fun connect() {
        TODO()
    }
}