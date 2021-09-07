package com.meetings.users.usecase

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.meetings.users.port.UsersRepository

data class GetUserRequest(val id: String)
class UserNotFound : Throwable()
typealias GetUserResponse = Result<UserDto, Throwable>

internal class GetUser(private val repository: UsersRepository) {
    fun execute(request: GetUserRequest): GetUserResponse {
        val user = repository.findOne(request.id) ?: return Err(UserNotFound())
        return Ok(user.toDto())
    }
}