package com.meetings.api.routing.users

import com.meetings.database.Database
import com.meetings.users.UsersFacade
import io.ktor.routing.*

fun Routing.configureUsersRouting(database: Database) = route("/users") {
    val usersFacade = UsersFacade(database.usersRepository)

    createUserRoute(usersFacade::createUser)
    getUserRoute(usersFacade::getUser)
}


