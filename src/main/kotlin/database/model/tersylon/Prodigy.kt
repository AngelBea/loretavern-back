package com.example.database.entities.tersylon
import com.example.database.model.MetadataTable
import org.jetbrains.exposed.v1.core.Table

object Prodigy: MetadataTable("prodigy") {
    val damage = integer("damage")
}

object ProdigyRace: Table("prodigy_race") {
    val prodigy = reference("prodigy", Prodigy)
    val race = reference("race", Race)

    override val primaryKey = PrimaryKey(prodigy, race)

    init {
        uniqueIndex(prodigy, race)
    }
}

object ProdigySubrace: Table("prodigy_subrace") {
    val prodigy = reference("prodigy", Prodigy)
    val subrace = reference("subrace", Race)

    override val primaryKey = PrimaryKey(prodigy, subrace)

    init {
        uniqueIndex(prodigy, subrace)
    }
}
