package io.github.prabhuomkar.torchexpo.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import io.github.prabhuomkar.torchexpo.data.models.Task

@Dao
interface TasksDao {

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): LiveData<List<Task>>
}
