package com.example.modules

import com.example.MySession
import com.example.UserSession
import com.example.routes.configureRaceEndpoints
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import io.ktor.server.auth.*
import io.ktor.server.sessions.*
import io.ktor.server.sessions.get

fun Application.configureRouting() {
    routing {
        configureRaceEndpoints(this)
    }
}