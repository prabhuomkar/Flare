package io.github.prabhuomkar.torchexpo.ui.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.github.prabhuomkar.torchexpo.data.database.TorchExpoDatabase

class ModelViewModel(application: Application) : AndroidViewModel(application) {

    private val torchExpoDatabase: TorchExpoDatabase = TorchExpoDatabase.getInstance(application)

    internal fun model(id: Int) {
        torchExpoDatabase.modelDao().getModel(id)
    }
}