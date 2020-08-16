package io.github.prabhuomkar.torchexpo.ui.main

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.github.prabhuomkar.torchexpo.data.db.TorchExpoDatabase
import io.github.prabhuomkar.torchexpo.data.db.model.Task
import io.github.prabhuomkar.torchexpo.data.network.APIClient
import io.github.prabhuomkar.torchexpo.data.repository.TaskRepository
import io.github.prabhuomkar.torchexpo.util.NetworkUtil
import io.github.prabhuomkar.torchexpo.util.UIUtil
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

    fun getTasksFromNetwork(context: Context, refreshTaskList: SwipeRefreshLayout?) =
        viewModelScope.launch(Dispatchers.IO) {
            if (NetworkUtil.isConnected(context)) {
                UIUtil.showSnackbar(context, "Loading...")
                val response = APIClient.instance.getTasks()
                if (response.isSuccessful) {
                    response.body()?.forEach { taskRepository.insert(it) }
                }
            } else {
                UIUtil.showSnackbar(context, "No connection", true)
            }
            if (refreshTaskList != null) refreshTaskList.isRefreshing = false
        }
}
