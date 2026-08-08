package com.example.database.entities.tersylon


import com.example.database.entities.MetadataTable
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object Energy: MetadataTable("energy")

object RaceEnergy: IntIdTable("race_energy") {
    val race = reference("race", Race)
    val energy = reference("energy", Energy)
    val value = integer("value")

    init {
        uniqueIndex(race, energy)
    }
}