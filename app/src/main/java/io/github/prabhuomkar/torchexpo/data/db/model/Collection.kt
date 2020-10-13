package io.github.prabhuomkar.torchexpo.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "collections")
data class Collection(
    @PrimaryKey
    val id: String
)