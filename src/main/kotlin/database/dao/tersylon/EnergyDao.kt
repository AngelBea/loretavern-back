package com.example.database.dao.tersylon

import com.example.database.model.tersylon.Energy
import com.example.database.model.tersylon.RaceEnergy
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.v1.core.dao.id.CompositeID
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.CompositeEntity
import org.jetbrains.exposed.v1.dao.CompositeEntityClass
import org.jetbrains.exposed.v1.dao.UuidEntity
import org.jetbrains.exposed.v1.dao.UuidEntityClass
import kotlin.uuid.Uuid

class EnergyDao(id: EntityID<Uuid>): UuidEntity(id) {
    companion object: UuidEntityClass<EnergyDao>(Energy)

    var name by Energy.name
    var description by Energy.description
    var slug by Energy.slug

    fun toDTO() = EnergyDTO(id.value, name, description, slug)
}

class RaceEnergyDao(id: EntityID<CompositeID>): CompositeEntity(id){
    companion object: CompositeEntityClass<RaceEnergyDao>(RaceEnergy)
    var energy by EnergyDao referencedOn RaceEnergy.energy
    var value by RaceEnergy.value

    fun toDTO() = EnergyDTO(energy.id.value, energy.name, energy.description, energy.slug, value)
}

@Serializable
data class EnergyDTO(val id: Uuid, val name: String, val description: String?, val slug: String, val value: Int? = null)