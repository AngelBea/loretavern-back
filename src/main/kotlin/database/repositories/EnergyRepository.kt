package com.example.database.repositories

import com.example.database.dao.tersylon.EnergyDTO
import com.example.database.dao.tersylon.EnergyDao
import com.example.database.tersylonSchema
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

fun getAllEnergies(): List<EnergyDTO>{
    return transaction {
        SchemaUtils.setSchema(tersylonSchema)
        EnergyDao.all().map { it.toDTO() }
    }
}