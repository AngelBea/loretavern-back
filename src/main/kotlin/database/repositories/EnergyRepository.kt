package com.example.database.repositories

import com.example.database.dao.EnergyDao
import com.example.database.tersylonSchema
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

fun getAllEnergies(): List<EnergyDao>{
    return transaction {
        SchemaUtils.setSchema(tersylonSchema)
        EnergyDao.all().toList()
    }
}