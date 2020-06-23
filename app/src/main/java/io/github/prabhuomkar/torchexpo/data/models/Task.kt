package io.github.prabhuomkar.torchexpo.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Entity(tableName = "tasks")
@TypeConverters(ResearchAreaTypeConverter::class)
data class Task(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val imageLink: String,
    val researchArea: ResearchArea
)

enum class ResearchArea {
    Vision,
    Generative,
    Language,
    Audio,
    Unknown
}

class ResearchAreaTypeConverter {
    @TypeConverter
    fun fromResearchArea(researchArea: ResearchArea): String {
        return researchArea.toString()
    }

    @TypeConverter
    fun toResearchArea(researchArea: String): ResearchArea {
        return when (researchArea) {
            "Vision" -> ResearchArea.Vision
            "Generative" -> ResearchArea.Generative
            "Language" -> ResearchArea.Language
            "Audio" -> ResearchArea.Audio
            else -> ResearchArea.Unknown
        }
    }
}
