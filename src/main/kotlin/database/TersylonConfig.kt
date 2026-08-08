package com.example.database

import com.example.database.model.tersylon.CharacterSheet
import com.example.database.model.tersylon.CharacterSheetProdigy
import com.example.database.entities.tersylon.Race
import com.example.database.model.tersylon.Energy
import com.example.database.entities.tersylon.Prodigy
import com.example.database.entities.tersylon.ProdigyRace
import com.example.database.entities.tersylon.ProdigySubrace
import com.example.database.model.tersylon.RaceEnergy
import com.example.database.model.tersylon.RaceSkill
import com.example.database.model.tersylon.Skill
import org.jetbrains.exposed.v1.core.Schema
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
val tersylonSchema = Schema("realms_of_tersylon")

fun tersylonConfig() {
    transaction {
        SchemaUtils.createSchema(tersylonSchema)
        SchemaUtils.setSchema(tersylonSchema)
        SchemaUtils.create(Energy, RaceEnergy, Skill, RaceSkill, Race, Prodigy, ProdigySubrace, ProdigyRace,
            CharacterSheet, CharacterSheetProdigy
        )
    }
}