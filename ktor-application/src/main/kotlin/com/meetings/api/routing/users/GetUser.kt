package com.meetings.api.routing.users

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.meetings.api.routing.error
import com.meetings.users.usecase.GetUserRequest
import com.meetings.users.usecase.GetUserResponse
import com.meetings.users.usecase.UserNotFound
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getUserRoute(getUser: (GetUserRequest) -> GetUserResponse) = get("/{id}") {
    val id = call.parameters["id"]!!

    when (val result = getUser(GetUserRequest(id))) {
        is Ok -> call.respond(result.value)
        is Err -> call.handleError(result.error)
    }
}

private suspend fun ApplicationCall.handleError(error: Throwable) = when (error) {
    is UserNotFound -> error(404, "User with id ${error.id} not found")
    else -> error(500)
}


