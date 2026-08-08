package com.example.database.repositories

import com.example.database.dao.EnergyDao
import com.example.database.dao.SkillDao
import org.jetbrains.exposed.v1.dao.with

fun getAllSkills():List<SkillDao> {
    return SkillDao.all().with(SkillDao::energy).map { it }
}