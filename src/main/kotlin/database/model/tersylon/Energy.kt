package com.example.database.model.tersylon


import com.example.database.model.MetadataTable
import com.example.database.entities.tersylon.Race
import org.jetbrains.exposed.v1.core.dao.id.CompositeIdTable

object Energy: MetadataTable("energy")

object RaceEnergy: CompositeIdTable("race_energy") {
    val race = reference("race", Race)
    val energy = reference("energy", Energy)
    val value = integer("value")

    override val primaryKey = PrimaryKey(race, energy)

    init {
        uniqueIndex(race, energy)
    }
}