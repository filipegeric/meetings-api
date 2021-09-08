package com.meetings.api

import com.meetings.database.DatabaseConfig
import com.sksamuel.hoplite.ConfigLoader

data class ServerConfig(val port: Int)
data class Config(val server: ServerConfig, val database: DatabaseConfig)

fun loadConfig(): Config {
    val env = System.getenv("ENV") ?: "dev"
    return ConfigLoader().loadConfigOrThrow(
        "/config/$env.yaml",
        "/config/default.yaml"
    )
}