package com.meetings.database.users

import com.meetings.users.domain.User
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert

internal object Users : Table() {
    val id = varchar("id", 50)
    val email = varchar("email", 254)

    override val primaryKey
        get() = PrimaryKey(id)

    fun insert(user: User) = insert {
        it[id] = user.id
        it[email] = user.email
    }
}