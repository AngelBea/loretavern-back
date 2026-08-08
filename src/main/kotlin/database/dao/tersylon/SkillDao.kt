package com.example.database.dao.tersylon

import com.example.database.model.tersylon.RaceSkill
import com.example.database.model.tersylon.Skill
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.v1.core.dao.id.CompositeID
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.CompositeEntity
import org.jetbrains.exposed.v1.dao.CompositeEntityClass
import org.jetbrains.exposed.v1.dao.UuidEntity
import org.jetbrains.exposed.v1.dao.UuidEntityClass
import kotlin.uuid.Uuid

class SkillDao(id: EntityID<Uuid>): UuidEntity(id) {
    companion object: UuidEntityClass<SkillDao>(Skill)
    var energyId by Skill.energy
    var energy by EnergyDao.Companion referencedOn Skill.energy
    var name by Skill.name
    var description by Skill.description
    var slug by Skill.slug

    fun toDTO(): SkillDTO = SkillDTO(energy.toDTO(), name, description, slug)
}

class RaceSkillDao(id: EntityID<CompositeID>): CompositeEntity(id){
    companion object: CompositeEntityClass<RaceSkillDao>(RaceSkill)

    var skill by SkillDao referencedOn RaceSkill.skill
    var isMarked by RaceSkill.is_marked
    var value by RaceSkill.value

    fun toDTO(): SkillDTO = SkillDTO(skill.energy.toDTO(), skill.name, skill.description, skill.slug, isMarked, value)
}

@Serializable
data class SkillDTO(val energy: EnergyDTO, val name: String, val description: String?, val slug: String, val isMarked: Boolean? = null, val value: Int? = null)