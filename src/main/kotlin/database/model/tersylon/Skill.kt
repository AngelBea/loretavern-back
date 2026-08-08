package com.example.database.entities.tersylon

import com.example.database.entities.MetadataTable
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object Skill: MetadataTable("skill") {
    val energy = reference("energy", Energy)
}

object RaceSkill: IntIdTable("race_skill") {
    val race = reference("race", Race)
    val skill = reference("skill", Skill)
    val is_marked = bool("is_marked").default(false)
    val value = integer("value")

    init {
        uniqueIndex(race, skill)
    }
}