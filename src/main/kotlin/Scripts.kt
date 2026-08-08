package com.example

import com.example.database.dao.EnergyDao
import com.example.database.entities.tersylon.CharacterSheet.description
import com.example.database.entities.tersylon.CharacterSheet.name
import com.example.database.entities.tersylon.CharacterSheet.slug
import com.example.database.entities.tersylon.Energy
import com.example.database.tersylonSchema
import org.jetbrains.exposed.v1.core.Schema
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.batchInsert
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.jetbrains.exposed.v1.jdbc.update
import java.lang.System
import kotlin.collections.listOf

data class Energy(val name: String, val description: String, val slug: String)

fun main(){
    insertEnergies()
}


fun insertEnergies(){
    Database.connect(
        url = "jdbc:postgresql://aws-1-eu-central-1.pooler.supabase.com:5432/postgres",
        driver = "org.postgresql.Driver",
        user = System.getenv("SUPABASE_USER"),
        password = System.getenv("SUPABASE_PASSWORD")
    )
    val energies = listOf(
        Energy("Sarcos", "A powerful energy that devours and repairs at the same time", "sarcos"),
        Energy("Essence", "The power of the outside world", "essence"),
        Energy("Spirit", "The inner power of the self", "spirit"),
        Energy("Stag", "The corruption of every other energy.", "stag"),
        Energy("Innar", "The energy of the knowledge and sociability.", "innar"),
        Energy("Nemya", "The energy of the living things.", "nemya"),
    )
    transaction {
        SchemaUtils.setSchema(tersylonSchema)
        energies.forEach { energy ->
            val record = EnergyDao.new{
                this.name = energy.name
                this.description = energy.description
                this.slug = "${energy.slug}-${this.id}"
            }
        }
    }
}