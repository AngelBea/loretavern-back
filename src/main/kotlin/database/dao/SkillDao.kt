package com.example.database.dao

import com.example.database.model.tersylon.Skill
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.UuidEntity
import org.jetbrains.exposed.v1.dao.UuidEntityClass
import kotlin.uuid.Uuid

class SkillDao(id: EntityID<Uuid>): UuidEntity(id) {
    companion object: UuidEntityClass<SkillDao>(Skill)
    var energyId by Skill.energy
    var energy by EnergyDao referencedOn Skill.energy
    var name by Skill.name
    var description by Skill.description
    var slug by Skill.slug

    fun toDTO(): SkillDTO = SkillDTO(energy.toDTO(), name, description, slug)
}

@Serializable
data class SkillDTO(val energy: EnergyDTO, val name: String, val description: String?, val slug: String)