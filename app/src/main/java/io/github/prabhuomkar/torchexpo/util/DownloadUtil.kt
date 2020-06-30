package io.github.prabhuomkar.torchexpo.util

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class DownloadUtil(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        val appContext = applicationContext
        // TODO: Add notification about starting to download file
        return try {
            // TODO: Download File using PRDownloader
            // TODO: Clear notifications
            Result.success()
        } catch (throwable: Throwable) {
            Log.e(DownloadUtil::class.simpleName, throwable.message!!)
            Result.failure()
        }
    }

}