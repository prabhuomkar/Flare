package io.github.prabhuomkar.torchexpo.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import io.github.prabhuomkar.torchexpo.data.db.model.Publisher

@Dao
interface PublisherDao {
    @Query("SELECT * FROM publishers")
    fun getPublishers(): LiveData<List<Publisher>>
}