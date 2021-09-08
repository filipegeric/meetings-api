package com.meetings.api.routing.users

import com.meetings.api.routing.respond
import com.meetings.users.usecase.CreateUserRequest
import com.meetings.users.usecase.CreateUserResponse
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.routing.*

fun Route.createUserRoute(createUser: (CreateUserRequest) -> CreateUserResponse) = post {
    val request = call.receive<CreateUserRequest>()
    val response = createUser(request)
    call.respond(201, response.userDto)
}
