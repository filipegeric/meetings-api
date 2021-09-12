package com.meetings.api.plugins

import io.ktor.application.*

fun Application.installPlugins() {
    configureHTTP()
    configureSerialization()
    configureAuth()
}