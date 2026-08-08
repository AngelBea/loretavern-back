package com.example.routes

import com.example.database.dao.tersylon.EnergyDTO
import com.example.database.dao.tersylon.SkillDTO

data class ResponseDTO(val energies: List<EnergyDTO>, val skills: List<SkillDTO>, val name: String = "", val description: String = "")