package com.example.database.model.tersylon

import com.example.database.model.MetadataTable
import com.example.database.entities.tersylon.Race
import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.core.dao.id.CompositeIdTable

object CharacterSheet: MetadataTable("character_sheet") {
    val race = reference("race", Race)
    val subrace = reference("subrace", Race)
    val isFoe = bool("is_foe")
    val type = enumeration("type", CharacterSheetType::class)
}

object CharacterSheetProdigy: CompositeIdTable("character_sheet_prodigy") {
    val prodigy = reference("prodigy", Prodigy)
    val character_sheet = reference("character_sheet", CharacterSheet)

    override val primaryKey = PrimaryKey(prodigy, character_sheet)

    init {
        uniqueIndex(prodigy, character_sheet)
    }
}

enum class CharacterSheetType {
    PLAYER,
    NPC,
    MONSTER
}