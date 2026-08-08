package com.example.database.entities

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable
import org.jetbrains.exposed.v1.core.dao.id.UuidTable
import org.jetbrains.exposed.v1.datetime.CurrentTimestamp
import org.jetbrains.exposed.v1.datetime.datetime
import kotlin.time.Clock

abstract class MetadataTable(tableName: String): UuidTable(tableName, uuidVersion = UuidVersion.V7) {
    val name = varchar("name", 50)
    val description = text("description").nullable()
    val createdAt = datetime("created_at").clientDefault{
        Clock.System.now().toLocalDateTime(TimeZone.UTC)
    }
    val updatedAt = datetime("updated_at").clientDefault{
        Clock.System.now().toLocalDateTime(TimeZone.UTC)
    }
    val deletedAt = datetime("deleted_at").nullable()
    val isDeleted = bool("is_deleted").default(false)
    val slug = varchar("slug", 50).uniqueIndex()
    val thumbnail = varchar("thumbnail", 255).nullable()
    val isPublic = bool("is_public").default(true)
    val image = varchar("image", 255).nullable()
}