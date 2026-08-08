package com.example.database.entities.tersylon

import com.example.database.entities.MetadataTable
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object Prodigy: MetadataTable("prodigy") {
    val damage = integer("damage")
}

object ProdigyRace: IntIdTable("prodigy_race") {
    val prodigy = reference("prodigy", Prodigy)
    val race = reference("race", Race)
    init {
        uniqueIndex(prodigy, race)
    }
}

object ProdigySubrace: IntIdTable("prodigy_subrace") {
    val prodigy = reference("prodigy", Prodigy)
    val subrace = reference("subrace", Race)

    init {
        uniqueIndex(prodigy, subrace)
    }
}
