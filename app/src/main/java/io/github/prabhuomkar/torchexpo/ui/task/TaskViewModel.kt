package io.github.prabhuomkar.torchexpo.ui.task

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import io.github.prabhuomkar.torchexpo.data.database.TorchExpoDatabase
import io.github.prabhuomkar.torchexpo.data.database.repository.ModelRepository
import io.github.prabhuomkar.torchexpo.data.database.repository.TaskRepository
import io.github.prabhuomkar.torchexpo.data.model.Model
import io.github.prabhuomkar.torchexpo.data.model.Task

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val taskRepository: TaskRepository
    private val modelRepository: ModelRepository

    init {
        val taskDao = TorchExpoDatabase.getInstance(application).taskDao()
        val modelDao = TorchExpoDatabase.getInstance(application).modelDao()
        taskRepository = TaskRepository(taskDao)
        modelRepository = ModelRepository(modelDao)
    }

    fun task(id: Int): LiveData<Task> {
        return taskRepository.task(id)
    }

    fun modelsByTask(taskId: Int): LiveData<List<Model>> {
        return modelRepository.modelsByTask(taskId)
    }
}