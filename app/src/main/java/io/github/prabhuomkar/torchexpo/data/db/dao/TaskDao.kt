package io.github.prabhuomkar.torchexpo.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.prabhuomkar.torchexpo.data.db.model.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getTasks(): LiveData<List<Task>>

    @Query("SELECT * from tasks WHERE _id = :id")
    fun getTask(id: String): LiveData<Task>

    @Query("SELECT * from tasks WHERE researchArea = :researchArea")
    fun getTasksByResearchArea(researchArea: String): LiveData<List<Task>>

    @Query("SELECT DISTINCT researchArea from tasks")
    fun getResearchAreas(): LiveData<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Query("DELETE FROM tasks")
    suspend fun delete()
}