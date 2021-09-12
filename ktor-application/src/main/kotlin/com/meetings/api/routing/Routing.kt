package com.meetings.api.routing

import com.meetings.api.routing.meetings.configureMeetingsRouting
import com.meetings.api.routing.users.configureUsersRouting
import com.meetings.database.Database
import io.ktor.application.*
import io.ktor.routing.*

fun Application.configureRouting(database: Database) {
    routing {
        configureUsersRouting(database)
        configureMeetingsRouting(database)
    }
}
