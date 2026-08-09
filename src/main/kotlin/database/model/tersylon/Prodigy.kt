package com.example.database.model.tersylon

import com.example.database.entities.tersylon.Race
import com.example.database.model.MetadataTable
import org.jetbrains.exposed.v1.core.dao.id.CompositeIdTable

object Prodigy: MetadataTable("prodigy"){
    val energy = reference("energy", Energy)
    val skill = reference("skill", Skill)
    val minimumSkillLevel = integer("minimum_skill_level")
    val type = enumeration("type", ProdigyType::class)
}

object ProdigyRace: CompositeIdTable("prodigy_race") {
    val prodigy = reference("prodigy", Prodigy)
    val race = reference("race", Race)

    override val primaryKey = PrimaryKey(prodigy, race)

    init {
        uniqueIndex(prodigy, race)
    }
}

object ProdigySubrace: CompositeIdTable("prodigy_subrace") {
    val prodigy = reference("prodigy", Prodigy)
    val subrace = reference("subrace", Race)

    override val primaryKey = PrimaryKey(prodigy, subrace)

    init {
        uniqueIndex(prodigy, subrace)
    }
}

enum class ProdigyType {
    PASSIVE,
    ACTIVE
}