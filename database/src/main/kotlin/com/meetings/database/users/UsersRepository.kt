package com.meetings.database.users

import com.meetings.users.domain.User
import com.meetings.users.port.UsersRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

internal class ExposedUsersRepository : UsersRepository {
    override fun save(user: User): User = transaction {
        Users.insert(user)
        user
    }

    override fun findOne(id: String): User? = transaction {
        Users.select { Users.id eq id }.map { it.toUser() }.firstOrNull()
    }
}

private fun ResultRow.toUser() = User(this[Users.id], this[Users.email])