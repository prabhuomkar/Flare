package io.github.prabhuomkar.torchexpo.ui.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.work.*
import io.github.prabhuomkar.torchexpo.data.db.TorchExpoDatabase
import io.github.prabhuomkar.torchexpo.data.db.model.Model
import io.github.prabhuomkar.torchexpo.data.repository.ModelRepository
import io.github.prabhuomkar.torchexpo.util.DownloadUtil
import io.github.prabhuomkar.torchexpo.util.WorkerManagerUtil

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

    suspend fun isDownloading(model: Model): Boolean {
        val workInfos = workManager.getWorkInfosForUniqueWork(
            DownloadUtil.getUniqueWorkName
                (model)
        ).await()
        if (workInfos.size == 1) {
            val workInfo = workInfos[0]
            if (workInfo.state == WorkInfo.State.BLOCKED || workInfo.state == WorkInfo.State.ENQUEUED
                || workInfo.state == WorkInfo.State.RUNNING
            ) {
                return true
            }
        }
        return false
    }

    fun startDownload(model: Model) {
        val downloadRequest = OneTimeWorkRequestBuilder<WorkerManagerUtil>()
            .setInputData(DownloadUtil.createDataFromModel(model))
            .build()

        workManager.enqueueUniqueWork(
            DownloadUtil.getUniqueWorkName(model),
            ExistingWorkPolicy.REPLACE,
            downloadRequest
        )
    }

    fun cancelDownload(model: Model) {
        workManager.cancelUniqueWork(DownloadUtil.getUniqueWorkName(model))
    }
}