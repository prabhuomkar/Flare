package io.github.prabhuomkar.torchexpo.data.database.repository

import androidx.lifecycle.LiveData
import io.github.prabhuomkar.torchexpo.data.database.dao.TaskDao
import io.github.prabhuomkar.torchexpo.data.model.Task

class TaskRepository(private val taskDao: TaskDao) {
    val tasks: LiveData<List<Task>> = taskDao.getTasks()
    fun task(id: Int): LiveData<Task> = taskDao.getTask(id)
    fun tasksByResearchArea(researchArea: String): LiveData<List<Task>> =
        taskDao.getTasksByResearchArea(researchArea)
}