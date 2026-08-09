package com.example.database

import ch.qos.logback.classic.Logger
import com.example.database.model.tersylon.CharacterSheet
import com.example.database.model.tersylon.CharacterSheetProdigy
import com.example.database.entities.tersylon.Race
import com.example.database.model.tersylon.Energy
import com.example.database.model.tersylon.Prodigy
import com.example.database.model.tersylon.ProdigyRace
import com.example.database.model.tersylon.ProdigySubrace
import com.example.database.model.tersylon.RaceEnergy
import com.example.database.model.tersylon.RaceSkill
import com.example.database.model.tersylon.Skill
import org.jetbrains.exposed.v1.core.Schema
import org.jetbrains.exposed.v1.core.Slf4jSqlDebugLogger
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.jetbrains.exposed.v1.migration.jdbc.MigrationUtils

val tersylonSchema = Schema("realms_of_tersylon")
val tables = arrayOf(Energy, RaceEnergy, Skill, RaceSkill, Race, Prodigy, ProdigySubrace, ProdigyRace,
    CharacterSheet, CharacterSheetProdigy)
fun tersylonConfig() {

    transaction {
        addLogger(Slf4jSqlDebugLogger)
        SchemaUtils.createSchema(tersylonSchema)
        SchemaUtils.setSchema(tersylonSchema)
        SchemaUtils.create(*tables)
        val statements = MigrationUtils.statementsRequiredForDatabaseMigration(*tables)
        statements.forEach { statement ->
            exec(statement)
        }
    }
}