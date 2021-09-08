package com.meetings.users.usecase

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.meetings.users.port.UsersRepository

data class GetUserRequest(val id: String)
data class UserNotFound(val id: String) : Throwable()
typealias GetUserResponse = Result<UserDto, Throwable>

internal class GetUser(private val repository: UsersRepository) {
    fun execute(request: GetUserRequest): GetUserResponse {
        val id = request.id
        val user = repository.findOne(id) ?: return Err(UserNotFound(id))
        return Ok(user.toDto())
    }
}