package io.github.prabhuomkar.torchexpo.data.repository

import androidx.lifecycle.LiveData
import io.github.prabhuomkar.torchexpo.data.db.dao.TaskDao
import io.github.prabhuomkar.torchexpo.data.db.model.Task

class TaskRepository(private val taskDao: TaskDao) {
    val tasks: LiveData<List<Task>> = taskDao.getTasks()

    fun task(id: String): LiveData<Task> = taskDao.getTask(id)

    fun tasksByResearchArea(researchArea: String): LiveData<List<Task>> =
        taskDao.getTasksByResearchArea(researchArea)

    val researchAreas: LiveData<List<String>> = taskDao.getResearchAreas()

    suspend fun insert(task: Task) = taskDao.insert(task)
}