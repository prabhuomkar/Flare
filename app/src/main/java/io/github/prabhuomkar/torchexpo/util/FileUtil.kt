package io.github.prabhuomkar.torchexpo.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.util.Locale

class FileUtil {

    companion object {
        private fun getModelAssetFileName(modelName: String): String =
            modelName.toLowerCase(Locale.ROOT)
                .replace(" ", "_")
                .replace("-", "")
                .plus(".pt")

        private fun getModelAssetDirPath(context: Context): String =
            context.applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
                .toString()

        fun getModelAssetFilePath(context: Context, modelName: String): String =
            getModelAssetDirPath(context).plus("/").plus(getModelAssetFileName(modelName))

        fun isModelAssetFileDownloaded(context: Context, modelName: String) =
            File(getModelAssetFilePath(context, modelName)).exists()

        fun getBitmap(context: Context?, imageUri: Uri?, square: Boolean): Bitmap {
            var bitmap = when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.P -> {
                    val source = ImageDecoder.createSource(
                        context?.contentResolver!!, imageUri!!
                    )
                    ImageDecoder.decodeBitmap(source).copy(Bitmap.Config.RGBA_F16, true)
                }
                else -> MediaStore.Images.Media.getBitmap(context?.contentResolver, imageUri)
            }
            var scale = 400F / bitmap.height
            if (bitmap.width >= bitmap.height) {
                scale = 400F / bitmap.width
            }
            val scaledBitmap = Bitmap.createScaledBitmap(
                bitmap,
                (bitmap.width * scale).toInt(), (bitmap.height * scale).toInt(), true
            )
            return if (square) {
                ThumbnailUtils.extractThumbnail(scaledBitmap, 400, 400)
            } else scaledBitmap
        }
    }
}