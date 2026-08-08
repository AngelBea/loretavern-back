package com.example.database.entities.tersylon

import com.example.database.entities.MetadataTable
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object Race: MetadataTable("races") {
    val parentRace = reference("parent_race", Race)
    val isPlayable = bool("is_playable")
}