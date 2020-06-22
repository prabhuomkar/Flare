package io.github.prabhuomkar.torchexpo.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import io.github.prabhuomkar.torchexpo.data.models.Model

@Dao
interface ModelsDao {
    @Query("SELECT * FROM models")
    fun getModels(): LiveData<List<Model>>

    @Query("SELECT * from models WHERE taskId = :taskId")
    fun getModelsByTaskId(taskId: Int): LiveData<List<Model>>
}
