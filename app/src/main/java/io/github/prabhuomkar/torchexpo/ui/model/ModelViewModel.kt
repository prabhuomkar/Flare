package io.github.prabhuomkar.torchexpo.ui.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import io.github.prabhuomkar.torchexpo.data.db.TorchExpoDatabase
import io.github.prabhuomkar.torchexpo.data.db.model.Model
import io.github.prabhuomkar.torchexpo.data.repository.ModelRepository


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