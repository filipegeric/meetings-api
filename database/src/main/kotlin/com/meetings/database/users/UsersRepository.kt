package com.meetings.database.users

import com.meetings.users.domain.User
import com.meetings.users.port.UsersRepository

internal class MemoryUsersRepository : UsersRepository {
    private val users = mutableMapOf<String, User>()

    override fun save(user: User): User {
        users[user.id] = user
        return user
    }

    override fun findOne(id: String): User? {
        return users[id]
    }
}