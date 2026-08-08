package com.example

import com.example.database.dao.EnergyDao
import com.example.database.dao.SkillDao
import com.example.database.model.tersylon.Energy
import com.example.database.tersylonSchema
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.Entity
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.lang.System
import java.util.UUID
import kotlin.collections.listOf
import kotlin.uuid.Uuid

data class EnergyWrapper(val name: String, val description: String, val slug: String)
data class SkillWrapper(val energyId: EntityID<Uuid>, val name: String, val description: String, val slug: String)
val energyById = mutableMapOf<String, EntityID<Uuid>>()
fun main() {
    val insertEnergies = System.getenv("INSERT_ENERGIES")?.toBoolean() ?: false
    val insertSkills = System.getenv("INSERT_SKILLS")?.toBoolean() ?: false
    setDatabase()

    if (insertEnergies) {
        insertEnergies()
        println("Inserted energies $energyById")
    }

    if (insertSkills) {
        insertSkills()
        println("Inserted skills")
    }

    println("Done")
}

fun setDatabase() {
    Database.connect(
        url = "jdbc:postgresql://aws-1-eu-central-1.pooler.supabase.com:5432/postgres",
        driver = "org.postgresql.Driver",
        user = System.getenv("SUPABASE_USER"),
        password = System.getenv("SUPABASE_PASSWORD")
    )
}

fun insertEnergies() {
    val energies = listOf(
        EnergyWrapper("Sarcos", "A powerful energy that devours and repairs at the same time", "sarcos"),
        EnergyWrapper("Essence", "The power of the outside world", "essence"),
        EnergyWrapper("Spirit", "The inner power of the self", "spirit"),
        EnergyWrapper("Stag", "The corruption of every other energy.", "stag"),
        EnergyWrapper("Innar", "The energy of the knowledge and sociability.", "innar"),
        EnergyWrapper("Nemya", "The energy of the living things.", "nemya"),
    )
    transaction {
        SchemaUtils.setSchema(tersylonSchema)
        energies.forEach { energy ->
            val record = EnergyDao.new {
                this.name = energy.name
                this.description = energy.description
                this.slug = "${energy.slug}-${this.id}"
                energyById[energy.name] = this.id
            }

        }
    }
}

fun insertSkills() {
    val essenceId = energyById["Essence"]!!
    val stagId = energyById["Stag"]!!
    val spiritId = energyById["Spirit"]!!
    val nemyaId = energyById["Nemya"]!!
    val innarId = energyById["Innar"]!!
    val sarcosId = energyById["Sarcos"]!!
    
    val skills = listOf(
        // Essence (019fdd2a-6629-74dd-8ab2-b763ea372270)
        SkillWrapper(
            energyId = essenceId,
            name = "Dominant",
            description = "The threads of essence are essential to influence the minds of living beings.",
            slug = "dominant"
        ),
        SkillWrapper(
            energyId = essenceId,
            name = "Shaper",
            description = "You can shape energy to create illusions or convert matter.",
            slug = "shaper"
        ),
        SkillWrapper(
            energyId = essenceId,
            name = "Essento",
            description = "The forces of essence hold no secrets; you attack with them like a true essento.",
            slug = "essento"
        ),
        SkillWrapper(
            energyId = essenceId,
            name = "Materializer",
            description = "Defines proficiency with immaterial weapons.",
            slug = "materializer"
        ),
        SkillWrapper(
            energyId = essenceId,
            name = "Nullifier",
            description = "Allows you to nullify essence in the environment.",
            slug = "nullifier"
        ),
        SkillWrapper(
            energyId = essenceId,
            name = "Connector",
            description = "Allows visualizing and identifying essence threads directly and acting upon them.",
            slug = "connector"
        ),

        // Spirit (019fdd2a-6629-74df-aa29-fafca2020cc8)
        SkillWrapper(
            energyId = spiritId,
            name = "Vigorous",
            description = "The ability to withstand weight and move heavy objects.",
            slug = "vigorous"
        ),
        SkillWrapper(
            energyId = spiritId,
            name = "Generator",
            description = "The ability to generate elements coming from within.",
            slug = "generator"
        ),
        SkillWrapper(
            energyId = spiritId,
            name = "Stoic",
            description = "You resist and defend, though it will not free you from status conditions.",
            slug = "stoic"
        ),
        SkillWrapper(
            energyId = spiritId,
            name = "Brawler",
            description = "Defines proficiency when attacking bare-handed or with improvised objects.",
            slug = "brawler"
        ),
        SkillWrapper(
            energyId = spiritId,
            name = "Armiger",
            description = "Defines proficiency when attacking with physical weapons.",
            slug = "armiger"
        ),
        SkillWrapper(
            energyId = spiritId,
            name = "Strategist",
            description = "You know where to be and how to position yourself. Defines success when attempting a reaction.",
            slug = "strategist"
        ),

        // Nemya (019fdd2a-662a-7373-947b-05270a1ad5af)
        SkillWrapper(
            energyId = nemyaId,
            name = "Nemyfic",
            description = "The ability to communicate with living beings.",
            slug = "nemyfic"
        ),
        SkillWrapper(
            energyId = nemyaId,
            name = "Sensitive",
            description = "The ability to perceive energies coming from living beings and identify their weak points.",
            slug = "sensitive"
        ),
        SkillWrapper(
            energyId = nemyaId,
            name = "Instinctive",
            description = "Defines your ability to dodge physical attacks.",
            slug = "instinctive"
        ),
        SkillWrapper(
            energyId = nemyaId,
            name = "Gatherer",
            description = "Allows identifying items in plants and animals.",
            slug = "gatherer"
        ),
        SkillWrapper(
            energyId = nemyaId,
            name = "Climatologist",
            description = "You master the natural forces of Nemya and act upon them.",
            slug = "climatologist"
        ),
        SkillWrapper(
            energyId = nemyaId,
            name = "Elastic",
            description = "Defines proficiency with flexible or string weapons.",
            slug = "elastic"
        ),

        // Stag (019fdd2a-6629-74e1-b8a9-358d2f05b3f0)
        SkillWrapper(
            energyId = stagId,
            name = "Ingenious",
            description = "The ability to use different types of items and their effects.",
            slug = "ingenious"
        ),
        SkillWrapper(
            energyId = stagId,
            name = "Trickster",
            description = "Defines proficiency in stealing or sleight of hand.",
            slug = "trickster"
        ),
        SkillWrapper(
            energyId = stagId,
            name = "Staggist",
            description = "The ability to control machines and use gadgets.",
            slug = "staggist"
        ),
        SkillWrapper(
            energyId = stagId,
            name = "Shadowy",
            description = "Your ability to go unnoticed.",
            slug = "shadowy"
        ),
        SkillWrapper(
            energyId = stagId,
            name = "Corruptor",
            description = "The ability to corrupt an energy.",
            slug = "corruptor"
        ),
        SkillWrapper(
            energyId = stagId,
            name = "Poisonous",
            description = "Defines proficiency with corrosion and poison.",
            slug = "poisonous"
        ),
        SkillWrapper(
            energyId = stagId,
            name = "Incandescent",
            description = "Defines proficiency when using incandescent or blazing weapons.",
            slug = "incandescent"
        ),

        // Innar (019fdd2a-662a-7371-a516-33f6f5c958fa)
        SkillWrapper(
            energyId = innarId,
            name = "Archaeologist",
            description = "Defines the capacity to find and identify extraordinary objects.",
            slug = "archaeologist"
        ),
        SkillWrapper(
            energyId = innarId,
            name = "Clairvoyant",
            description = "Detects energies that occurred in a location, providing additional information.",
            slug = "clairvoyant"
        ),
        SkillWrapper(
            energyId = innarId,
            name = "Sociable",
            description = "The ability to be diplomatic, converse, or be well-liked.",
            slug = "sociable"
        ),
        SkillWrapper(
            energyId = innarId,
            name = "Guardianologist",
            description = "Defines knowledge of guardians and additional pantheons.",
            slug = "guardianologist"
        ),
        SkillWrapper(
            energyId = innarId,
            name = "Artist",
            description = "Your knowledge of Tersylon cultures is infinite, allowing proficiency in any art.",
            slug = "artist"
        ),

        // Sarcos (019fdd2a-6571-7315-9a9f-a2d23b5b57c6)
        SkillWrapper(
            energyId = sarcosId,
            name = "Protector",
            description = "Sarcos always protects against corruption, decay, and violence.",
            slug = "protector"
        ),
        SkillWrapper(
            energyId = sarcosId,
            name = "Lucid",
            description = "Allows seeing beyond what is hidden.",
            slug = "lucid"
        ),
        SkillWrapper(
            energyId = sarcosId,
            name = "Devourer",
            description = "The ability to transmute energies.",
            slug = "devourer"
        ),
        SkillWrapper(
            energyId = sarcosId,
            name = "Thrower",
            description = "The devouring mark guides your weapons. Defines proficiency with thrown weapons, bows, and crossbows.",
            slug = "thrower"
        ),
        SkillWrapper(
            energyId = sarcosId,
            name = "Summoner",
            description = "The Devouring Light becomes a real threat when generating its beasts.",
            slug = "summoner"
        )
    )

    transaction {
        SchemaUtils.setSchema(tersylonSchema)
        skills.forEach { skill ->
            SkillDao.new {
                name = skill.name
                description = skill.description
                slug = "${skill.slug}-${this.id}"
                energyId = skill.energyId
            }
        }
    }
}