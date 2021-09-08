package com.meetings.api.routing

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*

suspend inline fun <reified T : Any> ApplicationCall.respond(statusCode: Int, data: T) =
    respond(HttpStatusCode.fromValue(statusCode), data)

suspend inline fun ApplicationCall.error(status: Int, message: String? = null) =
    respond(status, mapOf("message" to message))
