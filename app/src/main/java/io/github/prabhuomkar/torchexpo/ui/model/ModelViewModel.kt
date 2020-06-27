package io.github.prabhuomkar.torchexpo.ui.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import io.github.prabhuomkar.torchexpo.data.database.TorchExpoDatabase
import io.github.prabhuomkar.torchexpo.data.model.Model

class ModelViewModel(application: Application) : AndroidViewModel(application) {

    private val torchExpoDatabase: TorchExpoDatabase = TorchExpoDatabase.getInstance(application)

    internal fun model(id: Int): LiveData<Model> {
        return torchExpoDatabase.modelDao().getModel(id)
    }
}