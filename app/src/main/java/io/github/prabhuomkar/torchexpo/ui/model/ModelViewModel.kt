package io.github.prabhuomkar.torchexpo.ui.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import io.github.prabhuomkar.torchexpo.data.db.TorchExpoDatabase
import io.github.prabhuomkar.torchexpo.data.db.model.Model
import io.github.prabhuomkar.torchexpo.data.db.model.Task
import io.github.prabhuomkar.torchexpo.data.repository.ModelRepository
import io.github.prabhuomkar.torchexpo.data.repository.TaskRepository

class ModelViewModel(application: Application) : AndroidViewModel(application) {

    private val taskRepository: TaskRepository
    private val modelRepository: ModelRepository

    init {
        val taskDao = TorchExpoDatabase.getInstance(application).taskDao()
        val modelDao = TorchExpoDatabase.getInstance(application).modelDao()
        taskRepository = TaskRepository(taskDao)
        modelRepository = ModelRepository(modelDao)
    }

    fun task(id: String): LiveData<Task> = taskRepository.task(id)

    fun model(id: String): LiveData<Model> = modelRepository.model(id)
}