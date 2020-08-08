package io.github.prabhuomkar.torchexpo.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "models", foreignKeys = [ForeignKey(
        entity = Task::class, parentColumns = ["_id"], childColumns = ["taskId"],
        onDelete = CASCADE
    )]
)
data class Model(
    @PrimaryKey
    val _id: String,
    @ColumnInfo(index = true)
    val taskId: String,
    val name: String,
    val description: String,
    val paperLink: String,
    val sourceLink: String,
    val downloadLink: String,
    val imageLink: String,
    val size: Int
)