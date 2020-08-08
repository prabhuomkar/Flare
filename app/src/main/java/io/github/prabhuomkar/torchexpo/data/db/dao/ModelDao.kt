package io.github.prabhuomkar.torchexpo.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.prabhuomkar.torchexpo.data.db.model.Model

@Dao
interface ModelDao {
    @Query("SELECT * FROM models")
    fun getModels(): LiveData<List<Model>>

    @Query("SELECT * from models WHERE _id = :id")
    fun getModel(id: String): LiveData<Model>

    @Query("SELECT * from models WHERE taskId = :taskId")
    fun getModelsByTaskId(taskId: String): LiveData<List<Model>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: Model)

    @Query("DELETE FROM models")
    suspend fun delete()
}