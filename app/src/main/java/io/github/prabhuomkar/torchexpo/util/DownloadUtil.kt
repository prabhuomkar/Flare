package io.github.prabhuomkar.torchexpo.util

import androidx.work.Data
import io.github.prabhuomkar.torchexpo.data.db.model.Model


class DownloadUtil {

    companion object {

        fun createDataFromModel(model: Model): Data {
            val builder = Data.Builder()
            builder.putString("MODEL_NAME", model.name)
            builder.putString("MODEL_DOWNLOAD_LINK", model.downloadLink)
            return builder.build()
        }

        fun getUniqueWorkName(model: Model): String {
            return "DOWNLOAD_MODEL_".plus(model.id.toString())
        }
    }
}