package com.example.routes

import com.example.database.repositories.getAllEnergies
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

fun configureRaceEndpoints(routing: Routing) {
    routing.route("/v1/races") {
        route("/new"){
            get("/template") {
                val energies = getAllEnergies().map { it.toDTO() }
                call.respondText(Json.encodeToString(energies))
            }
        }
        get("/{uuid}") {
            val raceName = call.parameters["uuid"] ?: "unknown"
            call.respondText("Hello, $raceName!")
        }
        post {
        }

        delete("/{uuid}") {
        }

        put("/{uuid}") {
        }

        route("/{race_slug}/subraces") {
            get("/{subrace_slug}") {
                val raceName = call.parameters["race_slug"] + " " + call.parameters["subrace_uuid"]
                call.respondText("Hello, $raceName!")
            }
            post {
            }
            delete("/{subrace_uuid}") {
            }
            put("/{subrace_uuid}") {
            }
        }
    }
}

@Serializable
data class Race(val name: String, val description: String)