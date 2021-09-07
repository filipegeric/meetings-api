package com.meetings.users.usecase

import com.meetings.users.port.UsersRepository

data class GetUserRequest(val id: String)

class GetUser(private val repository: UsersRepository) {
    fun execute(request: GetUserRequest): UserDto {
        val user = repository.findOne(request.id)
        return user?.toDto() ?: throw RuntimeException("Fail")
    }
}