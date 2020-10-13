package io.github.prabhuomkar.torchexpo.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "models")
data class Model(
    @PrimaryKey
    val id: String
)