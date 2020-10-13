package io.github.prabhuomkar.torchexpo.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "publishers")
data class Publisher(
    @PrimaryKey
    val id: String
)