package com.example.database.entities.tersylon

import com.example.database.model.MetadataTable

object Race: MetadataTable("races") {
    val parentRace = reference("parent_race", Race).nullable()
    val isPlayable = bool("is_playable")
}