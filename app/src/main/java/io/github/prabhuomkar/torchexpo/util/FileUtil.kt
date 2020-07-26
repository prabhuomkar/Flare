package io.github.prabhuomkar.torchexpo.util

import android.content.Context
import android.os.Environment
import java.io.File
import java.util.*

class FileUtil {

    companion object {
        fun getModelAssetFileName(modelName: String): String =
            modelName.toLowerCase(Locale.ROOT).replace("[ - ]", "_").plus(".pt")

        fun getModelAssetDirPath(context: Context): String =
            context.applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
                .toString()

        fun getModelAssetFilePath(context: Context, modelName: String): String =
            getModelAssetDirPath(context).plus("/").plus(getModelAssetFileName(modelName))

        fun isModelAssetFileDownloaded(context: Context, modelName: String) =
            File(getModelAssetFilePath(context, modelName)).exists()
    }

}