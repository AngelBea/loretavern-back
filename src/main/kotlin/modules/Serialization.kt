package com.example.modules

import io.ktor.server.application.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(
            kotlinx.serialization.json.Json {
                encodeDefaults = true
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
    }
}