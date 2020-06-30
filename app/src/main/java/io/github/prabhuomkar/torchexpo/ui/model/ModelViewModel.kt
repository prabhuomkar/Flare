package io.github.prabhuomkar.torchexpo.ui.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import io.github.prabhuomkar.torchexpo.data.db.TorchExpoDatabase
import io.github.prabhuomkar.torchexpo.data.db.model.Model
import io.github.prabhuomkar.torchexpo.data.repository.ModelRepository
import io.github.prabhuomkar.torchexpo.util.DownloadUtil

class ModelViewModel(application: Application) : AndroidViewModel(application) {

    private val modelRepository: ModelRepository
    private val workManager: WorkManager

    init {
        val modelDao = TorchExpoDatabase.getInstance(application).modelDao()
        modelRepository = ModelRepository(modelDao)
        workManager = WorkManager.getInstance(application)
    }

    fun model(id: Int): LiveData<Model> {
        return modelRepository.model(id)
    }

    internal fun startDownload(model: Model) {
        val downloadRequest = OneTimeWorkRequestBuilder<DownloadUtil>()
            .setInputData(createDataFromModel(model))
            .build()

        workManager.enqueueUniqueWork(
            getUniqueWorkName(model),
            ExistingWorkPolicy.REPLACE,
            downloadRequest
        )
    }

    internal fun cancelDownload(model: Model) {
        workManager.cancelUniqueWork(getUniqueWorkName(model))
    }

    private fun createDataFromModel(model: Model): Data {
        val builder = Data.Builder()
        builder.putInt("MODEL_ID", model.id)
        builder.putString("MODEL_DOWNLOAD_LINK", model.downloadLink)
        return builder.build()
    }

    private fun getUniqueWorkName(model: Model): String {
        return "DOWNLOAD_MODEL_".plus(model.id.toString())
    }

}