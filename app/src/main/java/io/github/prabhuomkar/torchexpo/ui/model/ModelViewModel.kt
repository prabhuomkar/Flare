package io.github.prabhuomkar.torchexpo.ui.model

import android.app.Application
import android.content.Context
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.downloader.PRDownloaderConfig
import io.github.prabhuomkar.torchexpo.data.db.TorchExpoDatabase
import io.github.prabhuomkar.torchexpo.data.db.model.Model
import io.github.prabhuomkar.torchexpo.data.repository.ModelRepository
import io.github.prabhuomkar.torchexpo.util.DownloadUtil
import io.github.prabhuomkar.torchexpo.util.FileUtil


class ModelViewModel(application: Application) : AndroidViewModel(application) {

    private val modelRepository: ModelRepository
    private val context: Context

    init {
        val modelDao = TorchExpoDatabase.getInstance(application).modelDao()
        modelRepository = ModelRepository(modelDao)
        PRDownloader.initialize(application.applicationContext);
        context = application.applicationContext
    }

    fun model(id: Int): LiveData<Model> {
        return modelRepository.model(id)
    }

    val isDownloading: MutableLiveData<Boolean> = MutableLiveData(false)

    val isDownloaded: MutableLiveData<Boolean> = MutableLiveData(false)

    val progress: MutableLiveData<String> = MutableLiveData("...")

    fun handleDownload(view: View, model: Model) {
        if (isDownloading.value!!) {
            PRDownloader.cancel(DownloadUtil.getTag(model))
            isDownloading.value = false
        } else {
            val context = view.context
            PRDownloader.initialize(context, PRDownloaderConfig.newBuilder().build())
            val dirPath = FileUtil.getModelAssetDirPath(context)
            val fileName = FileUtil.getModelAssetFileName(model.name)
            val downloadId = PRDownloader.download(model.downloadLink, dirPath, fileName)
                .setTag(DownloadUtil.getTag(model))
                .build()
                .setOnStartOrResumeListener {
                    isDownloading.value = true
                    progress.value = "..."
                }
                .setOnProgressListener {
                    isDownloading.value = true
                    progress.value =
                        DownloadUtil.getProgressInPercent(it.currentBytes, it.totalBytes)
                }
                .start(object : OnDownloadListener {
                    override fun onDownloadComplete() {
                        isDownloading.value = false
                        isDownloaded.value = true
                    }

                    override fun onError(error: Error?) {
                        isDownloading.value = false
                    }
                })
        }
    }
}