package io.github.prabhuomkar.torchexpo.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import io.github.prabhuomkar.torchexpo.data.models.Model

@Dao
interface ModelsDao {

    @Query("SELECT * FROM models")
    fun getAllModels(): LiveData<List<Model>>
}
