package io.github.prabhuomkar.torchexpo.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey
    val _id: String,
    val name: String,
    val description: String,
    val imageLink: String,
    val researchArea: String
)