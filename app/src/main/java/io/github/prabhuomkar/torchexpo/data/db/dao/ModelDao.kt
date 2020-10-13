package io.github.prabhuomkar.torchexpo.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import io.github.prabhuomkar.torchexpo.data.db.model.Model

@Dao
interface ModelDao {
    @Query("SELECT * FROM models")
    fun getModels(): LiveData<List<Model>>
}