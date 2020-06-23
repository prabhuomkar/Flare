package io.github.prabhuomkar.torchexpo.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import io.github.prabhuomkar.torchexpo.data.models.ResearchArea
import io.github.prabhuomkar.torchexpo.data.models.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getTasks(): LiveData<List<Task>>

    @Query("SELECT * from tasks WHERE researchArea = :researchArea")
    fun getTasksByResearchArea(researchArea: ResearchArea): LiveData<List<Task>>
}