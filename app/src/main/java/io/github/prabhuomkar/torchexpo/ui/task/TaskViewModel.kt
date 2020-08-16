package io.github.prabhuomkar.torchexpo.ui.task

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.github.prabhuomkar.torchexpo.data.db.TorchExpoDatabase
import io.github.prabhuomkar.torchexpo.data.db.model.Model
import io.github.prabhuomkar.torchexpo.data.db.model.Task
import io.github.prabhuomkar.torchexpo.data.network.APIClient
import io.github.prabhuomkar.torchexpo.data.repository.ModelRepository
import io.github.prabhuomkar.torchexpo.data.repository.TaskRepository
import io.github.prabhuomkar.torchexpo.util.NetworkUtil
import io.github.prabhuomkar.torchexpo.util.UIUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val taskRepository: TaskRepository
    private val modelRepository: ModelRepository

    init {
        val taskDao = TorchExpoDatabase.getInstance(application).taskDao()
        val modelDao = TorchExpoDatabase.getInstance(application).modelDao()
        taskRepository = TaskRepository(taskDao)
        modelRepository = ModelRepository(modelDao)
    }

    fun task(id: String): LiveData<Task> = taskRepository.task(id)

    fun modelsByTask(taskId: String): LiveData<List<Model>> = modelRepository.modelsByTask(taskId)

    fun getModelsFromNetwork(
        context: Context,
        refreshModelList: SwipeRefreshLayout?,
        taskId: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        if (NetworkUtil.isConnected(context)) {
            UIUtil.showSnackbar(context, "Loading...")
            val response = APIClient.instance.getModels(taskId)
            if (response.isSuccessful) {
                response.body()?.forEach { modelRepository.insert(it) }
            }
        } else {
            UIUtil.showSnackbar(context, "No connection", true)
        }
        if (refreshModelList != null) refreshModelList.isRefreshing = false
    }
}