package io.github.prabhuomkar.torchexpo.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import io.github.prabhuomkar.torchexpo.data.model.Model

@Dao
interface ModelDao {
    @Query("SELECT * FROM models")
    fun getModels(): LiveData<List<Model>>

    @Query("SELECT * from models WHERE id = :id")
    fun getModel(id: Int): LiveData<Model>

    @Query("SELECT * from models WHERE taskId = :taskId")
    fun getModelsByTaskId(taskId: Int): LiveData<List<Model>>
}
