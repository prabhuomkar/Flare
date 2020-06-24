package io.github.prabhuomkar.torchexpo.ui.task

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import io.github.prabhuomkar.torchexpo.data.database.TorchExpoDatabase
import io.github.prabhuomkar.torchexpo.data.models.Model

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val torchExpoDatabase: TorchExpoDatabase = TorchExpoDatabase.getInstance(application)

    internal val models: LiveData<List<Model>> = torchExpoDatabase.modelDao().getModels()

    internal fun modelsByTask(taskId: Int) {
        torchExpoDatabase.modelDao().getModelsByTaskId(taskId)
    }

    internal fun task(id: Int) {
        torchExpoDatabase.taskDao().getTask(id)
    }
}