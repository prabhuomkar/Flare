package io.github.prabhuomkar.torchexpo.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import io.github.prabhuomkar.torchexpo.data.db.model.Collection

@Dao
interface CollectionDao {
    @Query("SELECT * FROM collections")
    fun getCollections(): LiveData<List<Collection>>
}