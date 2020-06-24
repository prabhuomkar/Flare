package io.github.prabhuomkar.torchexpo.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val imageLink: String,
    val researchArea: String
)
