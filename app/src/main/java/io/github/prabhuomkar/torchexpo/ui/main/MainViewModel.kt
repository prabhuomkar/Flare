package io.github.prabhuomkar.torchexpo.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import io.github.prabhuomkar.torchexpo.data.db.TorchExpoDatabase
import io.github.prabhuomkar.torchexpo.data.db.model.Task
import io.github.prabhuomkar.torchexpo.data.network.APIClient
import io.github.prabhuomkar.torchexpo.data.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val taskRepository: TaskRepository

    init {
        val taskDao = TorchExpoDatabase.getInstance(application).taskDao()
        taskRepository = TaskRepository(taskDao)
    }

    val tasks: LiveData<List<Task>> = taskRepository.tasks

    val researchAreas: LiveData<List<String>> = taskRepository.researchAreas

    fun getTasksFromNetwork() = viewModelScope.launch(Dispatchers.IO) {
        val response = APIClient.instance.getTasks()
        if (response.isSuccessful()) {
            response.body()?.forEach { taskRepository.insert(it) }
        }
    }
}
