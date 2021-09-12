package com.meetings.api.routing.meetings

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.meetings.api.plugins.actor
import com.meetings.api.routing.error
import com.meetings.api.routing.respond
import com.meetings.common.Actor
import com.meetings.common.BadRequest
import com.meetings.usecase.CreateMeetingRequest
import com.meetings.usecase.CreateMeetingResponse
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.routing.*

fun Route.createMeetingRoute(createMeeting: (CreateMeetingRequest, Actor) -> CreateMeetingResponse) =
    post {
        val request = call.receive<CreateMeetingRequest>()
        val actor = call.actor

        when (val response = createMeeting(request, actor)) {
            is Ok -> call.respond(201, response.value)
            is Err -> call.handleError(response.error)
        }
    }

private suspend fun ApplicationCall.handleError(error: Throwable) {
    when(error) {
        is BadRequest -> error(400, error.message)
        else -> error(500, "Something went wrong")
    }
}