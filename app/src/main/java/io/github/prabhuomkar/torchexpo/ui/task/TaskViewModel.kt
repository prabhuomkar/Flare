package io.github.prabhuomkar.torchexpo.ui.task

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import io.github.prabhuomkar.torchexpo.data.database.TorchExpoDatabase
import io.github.prabhuomkar.torchexpo.data.model.Model
import io.github.prabhuomkar.torchexpo.data.model.Task

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val torchExpoDatabase: TorchExpoDatabase = TorchExpoDatabase.getInstance(application)

    internal val models: LiveData<List<Model>> = torchExpoDatabase.modelDao().getModels()

    internal fun modelsByTask(taskId: Int): LiveData<List<Model>> {
        return torchExpoDatabase.modelDao().getModelsByTaskId(taskId)
    }

    internal fun task(id: Int): LiveData<Task> {
        return torchExpoDatabase.taskDao().getTask(id)
    }
}