package com.meetings.users

import com.meetings.users.port.UsersRepository
import com.meetings.users.usecase.*

class UsersFacade(repository: UsersRepository) {
    private val createUserUseCase = CreateUser(repository)
    private val getUserUseCase = GetUser(repository)

    fun createUser(request: CreateUserRequest): CreateUserResponse {
        return createUserUseCase.execute(request)
    }

    fun getUser(request: GetUserRequest): GetUserResponse {
        return getUserUseCase.execute(request)
    }
}