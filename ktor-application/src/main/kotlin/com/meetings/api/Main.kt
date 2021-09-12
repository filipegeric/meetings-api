package com.meetings.api

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.meetings.api.plugins.installPlugins
import com.meetings.api.routing.configureRouting
import com.meetings.database.Database
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    val (serverConfig, dbConfig) = loadConfig()
    initFirebase()

    val database = Database(dbConfig)
    database.connect()

    embeddedServer(Netty, port = serverConfig.port) {
        installPlugins()
        configureRouting(database)
    }.start(wait = true)
}

fun initFirebase() {
    val credentials = GoogleCredentials.getApplicationDefault()
    val options = FirebaseOptions.builder().setCredentials(credentials).build()
    FirebaseApp.initializeApp(options)
}





