package io.github.prabhuomkar.torchexpo.ui.playground

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import io.github.prabhuomkar.torchexpo.util.FileUtil
import org.pytorch.Module

class PlaygroundViewModel(application: Application) : AndroidViewModel(application) {

    private val context: Context = application.applicationContext
    private lateinit var module: Module

    fun load(modelName: String) {
        module = Module.load(FileUtil.getModelAssetFilePath(context, modelName))
    }
}