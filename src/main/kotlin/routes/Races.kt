package com.example.routes

import com.example.database.dao.tersylon.RaceDTO
import com.example.database.repositories.getAllEnergies
import com.example.database.repositories.getAllSkills
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.uuid.Uuid

fun configureRaceEndpoints(routing: Routing) {
    routing.route("/api/v1/races") {
        route("/new"){
            get("/template") {
                try{
                    val energies = getAllEnergies()
                    val skills = getAllSkills()
                    val template = RaceDTO(null, "", "", energies, skills, "")
                    call.response.headers.append("Content-Type", "application/json")
                    call.respondText(Json.encodeToString(template))
                }catch (e: Exception){
                    call.respondText("Error: ${e.message} | ${e.stackTraceToString()}")
                }
            }
        }
        route("/edit"){

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