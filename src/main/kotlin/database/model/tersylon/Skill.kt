package com.example.database.model.tersylon

import com.example.database.model.MetadataTable
import com.example.database.entities.tersylon.Race
import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.core.dao.id.CompositeIdTable

object Skill: MetadataTable("skill") {
    val energy = reference("energy", Energy)
}

object RaceSkill: CompositeIdTable("race_skill") {
    val race = reference("race", Race)
    val skill = reference("skill", Skill)
    val is_marked = bool("is_marked").default(false)
    val value = integer("value")

    override val primaryKey = PrimaryKey(race, skill)

    init {
        uniqueIndex(race, skill)
    }
}