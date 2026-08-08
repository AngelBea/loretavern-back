package com.example.database.entities.tersylon

import com.example.database.entities.MetadataTable
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object CharacterSheet: MetadataTable("character_sheet") {
    val race = reference("race", Race)
    val subrace = reference("subrace", Race)
    val isFoe = bool("is_foe")
    val type = enumeration("type", CharacterSheetType::class)
}

object CharacterSheetProdigy: IntIdTable("character_sheet_prodigy") {
    val prodigy = reference("prodigy", Prodigy)
    val character_sheet = reference("character_sheet", CharacterSheet)

    init {
        uniqueIndex(prodigy, character_sheet)
    }
}

enum class CharacterSheetType {
    PLAYER,
    NPC,
    MONSTER
}