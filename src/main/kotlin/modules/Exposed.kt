package com.example.modules

import com.example.database.tersylonConfig
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import org.jetbrains.exposed.v1.core.Schema
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils


suspend fun Application.configureExposed() {
    val database = HikariConfig().apply {
        jdbcUrl = "jdbc:postgresql://aws-1-eu-central-1.pooler.supabase.com:5432/postgres"
        driverClassName = "org.postgresql.Driver"
        username = System.getenv("SUPABASE_USER")
        password = System.getenv("SUPABASE_PASSWORD")

        maximumPoolSize = 5
        isAutoCommit = false
        transactionIsolation = "TRANSACTION_REPEATABLE_READ"

        connectionTimeout = 10000 // 10 seconds
        idleTimeout = 600000       // 10 minutes
        maxLifetime = 1800000      // 30 minutes
        validate()
    }

    val dataSource = HikariDataSource(database)
    Database.connect(dataSource)
    tersylonConfig()
}


