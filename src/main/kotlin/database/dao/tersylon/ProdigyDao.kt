package com.example.database.dao.tersylon

import com.example.database.model.tersylon.Prodigy
import com.example.database.model.tersylon.ProdigyRace
import com.example.database.model.tersylon.ProdigySubrace
import com.example.database.model.tersylon.ProdigyType
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.v1.core.dao.id.CompositeID
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.CompositeEntity
import org.jetbrains.exposed.v1.dao.CompositeEntityClass
import org.jetbrains.exposed.v1.dao.UuidEntity
import org.jetbrains.exposed.v1.dao.UuidEntityClass
import kotlin.uuid.Uuid

class ProdigyDao(id: EntityID<Uuid>): UuidEntity(id) {
    companion object : UuidEntityClass<ProdigyDao>(Prodigy)

    var name by Prodigy.name
    var description by Prodigy.description
    var slug by Prodigy.slug
    var energy by EnergyDao referencedOn Prodigy.energy
    var type by Prodigy.type
    var skill by SkillDao referencedOn Prodigy.skill
    var minimumSkillLevel by Prodigy.minimumSkillLevel
    val races by RaceDao via ProdigyRace
    val subraces by SubraceDao via ProdigySubrace

    fun toDTO() = ProdigyDTO(id.value, name, description, slug, energy.toDTO(), type, skill.toDTO(), minimumSkillLevel, races.map { it.slug }, subraces.map { it.slug })
}
@Serializable
data class ProdigyDTO(val id: Uuid, val name: String, val description: String?, val slug: String, val energy: EnergyDTO, val type: ProdigyType, val skill: SkillDTO, val minimumSkillLevel: Int, val races: List<String>, val subraces: List<String>)