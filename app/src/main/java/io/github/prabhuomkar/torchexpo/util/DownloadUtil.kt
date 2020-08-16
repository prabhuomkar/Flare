package io.github.prabhuomkar.torchexpo.util

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import io.github.prabhuomkar.torchexpo.R
import java.io.File

class DownloadUtil {
    companion object {
        fun download(context: Context, modelName: String, downloadLink: String) {
            val request = DownloadManager.Request(Uri.parse(downloadLink))
                .setTitle(context.getString(R.string.app_name))
                .setDescription("Downloading $modelName")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                .setDestinationUri(
                    Uri.fromFile(
                        File(
                            FileUtil.getModelAssetFilePath(
                                context,
                                modelName
                            )
                        )
                    )
                )
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(true)
            val downloadManager =
                context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val downloadId = downloadManager.enqueue(request)
            Log.v("DownloadID", downloadId.toString())
        }

        val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                UIUtil.showSnackbar(context, "Model downloaded")
            }
        }
    }
}