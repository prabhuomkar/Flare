package io.github.prabhuomkar.torchexpo.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import io.github.prabhuomkar.torchexpo.data.database.TorchExpoDatabase
import io.github.prabhuomkar.torchexpo.data.model.Task

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val torchExpoDatabase: TorchExpoDatabase = TorchExpoDatabase.getInstance(application)

    internal val tasks: LiveData<List<Task>> = torchExpoDatabase.taskDao().getTasks()
}
