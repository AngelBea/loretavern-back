package com.example.database.repositories

import com.example.database.dao.tersylon.SkillDTO
import com.example.database.dao.tersylon.SkillDao
import com.example.database.tersylonSchema
import org.jetbrains.exposed.v1.dao.with
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

fun getAllSkills():List<SkillDTO> {
    return transaction{
        SchemaUtils.setSchema(tersylonSchema)
        SkillDao.all().with(SkillDao::energy).map { it.toDTO() }
    }
}