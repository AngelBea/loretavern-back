package com.example.database.dao.tersylon

import com.example.database.entities.tersylon.Race
import com.example.database.model.tersylon.RaceEnergy
import com.example.database.model.tersylon.RaceSkill
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.UuidEntity
import org.jetbrains.exposed.v1.dao.UuidEntityClass
import kotlin.uuid.Uuid

open class RaceDao(id: EntityID<Uuid>): UuidEntity(id){
    companion object : UuidEntityClass<RaceDao>(Race)
    var name by Race.name
    var slug by Race.slug
    var description by Race.description
}

class RaceParentDao(id: EntityID<Uuid>): RaceDao(id){
    companion object : UuidEntityClass<RaceParentDao>(Race)
    val subraces by SubraceDao optionalReferrersOn Race.parentRace
    val energies by RaceEnergyDao referrersOn RaceEnergy.race
    val skills by RaceSkillDao referrersOn RaceSkill.race

    fun toDto(): RaceDTO = RaceDTO(id.value, name, slug, energies.map { it.toDTO() }, skills.map { it.toDTO() }, description, subraces.map { it.toDto() })
}

class SubraceDao(id: EntityID<Uuid>): RaceDao(id){
    companion object : UuidEntityClass<SubraceDao>(Race)
    val parentRace by RaceParentDao optionalReferencedOn Race.parentRace
    val skills by RaceSkillDao referrersOn RaceSkill.race

    fun toDto(): RaceDTO = RaceDTO(id.value, name, slug, parentRace?.energies?.map { it.toDTO() } ?: emptyList(), skills.map { it.toDTO() }, description)
}
@Serializable
data class RaceDTO (val id: Uuid?, val name: String, val slug: String, val energies: List<EnergyDTO>, val skills: List<SkillDTO>, val description: String? = null, val subraces: List<RaceDTO>? = null)
