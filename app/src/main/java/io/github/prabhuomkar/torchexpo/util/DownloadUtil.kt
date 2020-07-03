package io.github.prabhuomkar.torchexpo.util

import io.github.prabhuomkar.torchexpo.data.db.model.Model


class DownloadUtil {

    companion object {

        fun getProgressInPercent(currentBytes: Long, totalBytes: Long): String {
            return String.format("%.2f%%", (currentBytes * 100.00 / totalBytes))
        }

        fun getTag(model: Model): String {
            return "DOWNLOAD_MODEL_".plus(model.id.toString())
        }
    }
}