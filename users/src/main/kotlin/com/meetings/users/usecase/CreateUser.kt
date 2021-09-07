package com.meetings.users.usecase

import com.meetings.users.domain.User
import com.meetings.users.port.UsersRepository

data class CreateUserRequest(val id: String, val email: String)
data class CreateUserResponse(val userDto: UserDto)
data class UserDto(val id: String, val email: String)

fun User.toDto(): UserDto = UserDto(id, email)

class CreateUser(private val repository: UsersRepository) {
    fun execute(request: CreateUserRequest): CreateUserResponse {
        val (id, email) = request
        val user = repository.save(User(id, email))
        return CreateUserResponse(user.toDto())
    }
}