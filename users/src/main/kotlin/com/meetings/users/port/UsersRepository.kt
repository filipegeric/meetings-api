package com.meetings.users.port

import com.meetings.users.domain.User

interface UsersRepository {
    fun save(user: User): User
    fun findOne(id: String): User?
}