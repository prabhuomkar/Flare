package io.github.prabhuomkar.torchexpo.data.database.repository

import androidx.lifecycle.LiveData
import io.github.prabhuomkar.torchexpo.data.database.dao.TaskDao
import io.github.prabhuomkar.torchexpo.data.models.ResearchArea
import io.github.prabhuomkar.torchexpo.data.models.Task

class TaskRepository(private val taskDao: TaskDao) {
    val tasks: LiveData<List<Task>> = taskDao.getTasks()
    fun tasksByResearchArea(researchArea: ResearchArea) =
        taskDao.getTasksByResearchArea(researchArea)
}