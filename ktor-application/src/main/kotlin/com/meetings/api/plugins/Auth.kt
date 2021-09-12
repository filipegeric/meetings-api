package com.meetings.api.plugins

import com.google.firebase.auth.FirebaseAuth
import com.meetings.common.Actor
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.util.*
import io.ktor.util.pipeline.*

fun Application.configureAuth() {
    install(AuthMiddleware) {
        verifyToken = { token ->
            val decodedToken = FirebaseAuth.getInstance().verifyIdToken(token)
            Actor(decodedToken.uid, decodedToken.email)
        }
    }
}

private val actorKey = AttributeKey<Actor>("actor")
val ApplicationCall.actor
    get() = attributes[actorKey]

private class AuthMiddleware(configuration: Configuration) {
    val verifyToken = configuration.verifyToken

    class Configuration {
        var verifyToken: (token: String) -> Actor = { TODO() }
    }

    suspend fun intercept(context: PipelineContext<Unit, ApplicationCall>) {
        val call = context.call
        try {
            val authHeader = call.request.header("Authorization")
            val token = authHeader?.replace("Bearer ", "")
            if (token == null || token.isEmpty()) {
                throw RuntimeException("Token is missing")
            }

            val actor = verifyToken(token)

            call.attributes.put(actorKey, actor)
        } catch (e: Exception) {
            e.printStackTrace()
            call.respond(HttpStatusCode.Unauthorized, e.message ?: "Something went wrong")
            context.finish()
        }
    }

    companion object Feature :
        ApplicationFeature<ApplicationCallPipeline, Configuration, AuthMiddleware> {
        override val key = AttributeKey<AuthMiddleware>("AuthMiddleware")

        override fun install(
            pipeline: ApplicationCallPipeline, configure: Configuration.() -> Unit
        ): AuthMiddleware {
            val configuration = Configuration().apply(configure)
            val middleware = AuthMiddleware(configuration)

            pipeline.intercept(ApplicationCallPipeline.Call) {
                middleware.intercept(this)
            }

            return middleware
        }
    }
}