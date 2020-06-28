package io.github.prabhuomkar.torchexpo.ui.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import io.github.prabhuomkar.torchexpo.data.database.TorchExpoDatabase
import io.github.prabhuomkar.torchexpo.data.database.repository.ModelRepository
import io.github.prabhuomkar.torchexpo.data.model.Model

class ModelViewModel(application: Application) : AndroidViewModel(application) {

    private val modelRepository: ModelRepository

    init {
        val modelDao = TorchExpoDatabase.getInstance(application).modelDao()
        modelRepository = ModelRepository(modelDao)
    }

    fun model(id: Int): LiveData<Model> {
        return modelRepository.model(id)
    }
}