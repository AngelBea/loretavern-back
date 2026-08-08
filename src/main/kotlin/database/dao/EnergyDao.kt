package com.example.database.dao

import com.example.database.entities.tersylon.Energy
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.UuidEntity
import org.jetbrains.exposed.v1.dao.UuidEntityClass
import org.jetbrains.exposed.v1.dao.java.UUIDEntity
import org.jetbrains.exposed.v1.dao.java.UUIDEntityClass
import kotlin.uuid.Uuid

class EnergyDao(id: EntityID<Uuid>): UuidEntity(id) {
    companion object: UuidEntityClass<EnergyDao>(Energy)
    var name by Energy.name
    var description by Energy.description
    var slug by Energy.slug

    fun toDTO() = EnergyDTO(name, description, slug)
}
@Serializable
data class EnergyDTO(val name: String, val description: String?, val slug: String)