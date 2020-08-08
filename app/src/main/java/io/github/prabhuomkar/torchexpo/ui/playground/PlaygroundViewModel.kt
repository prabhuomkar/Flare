package io.github.prabhuomkar.torchexpo.ui.playground

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.view.View
import androidx.core.graphics.set
import androidx.lifecycle.AndroidViewModel
import io.github.prabhuomkar.torchexpo.R
import io.github.prabhuomkar.torchexpo.torchexpo.TensorOperations
import io.github.prabhuomkar.torchexpo.ui.playground.common.ImageNet
import io.github.prabhuomkar.torchexpo.util.FileUtil
import io.github.prabhuomkar.torchexpo.util.PlaygroundUtil
import kotlinx.android.synthetic.main.image_classification_fragment.view.*
import org.pytorch.IValue
import org.pytorch.Module
import org.pytorch.Tensor
import org.pytorch.torchvision.TensorImageUtils

class PlaygroundViewModel(application: Application) : AndroidViewModel(application) {

    private val context: Context = application.applicationContext
    private lateinit var module: Module

    private fun input(bitmap: Bitmap): Tensor = TensorImageUtils.bitmapToFloat32Tensor(
        bitmap,
        TensorImageUtils.TORCHVISION_NORM_MEAN_RGB, TensorImageUtils.TORCHVISION_NORM_STD_RGB
    )

    fun clearPlayground() = module.destroy()

    fun runImageClassification(view: View, modelName: String?, bitmapUri: Uri?) {
        if (!modelName.isNullOrEmpty() && bitmapUri != null) {
            val bitmap = FileUtil.getBitmap(context, bitmapUri, true)
            module = Module.load(FileUtil.getModelAssetFilePath(context, modelName))
            val values = module.forward(IValue.from(input(bitmap))).toTensor()
                .dataAsFloatArray
            val result = PlaygroundUtil.topK(values, 3)
            if (result.isNotEmpty()) {
                view.rootView.class1_text.text = ImageNet.TARGET_CLASSES[result[0].first]
                view.rootView.class1_score.text =
                    context.getString(R.string.score_precision).format(result[0].second)
                view.rootView.class2_text.text = ImageNet.TARGET_CLASSES[result[1].first]
                view.rootView.class2_score.text =
                    context.getString(R.string.score_precision).format(result[1].second)
                view.rootView.class3_text.text = ImageNet.TARGET_CLASSES[result[2].first]
                view.rootView.class3_score.text =
                    context.getString(R.string.score_precision).format(result[2].second)
            }
        }
    }

    fun runImageSegmentation(view: View, modelName: String?, bitmapUri: Uri?) {
        if (!modelName.isNullOrEmpty() && bitmapUri != null) {
            val bitmap = FileUtil.getBitmap(context, bitmapUri, false)
            module = Module.load(FileUtil.getModelAssetFilePath(context, modelName))
            val values = module.forward(IValue.from(input(bitmap))).toTensor()
            val shape = values.shape()
            val output = TensorOperations.argmax(
                values.dataAsFloatArray, shape[0].toInt(), shape[1].toInt(),
                shape[2].toInt()
            )
            val colors = arrayOf(
                intArrayOf(0, 0, 0),
                intArrayOf(1, 127, 31),
                intArrayOf(2, 254, 62),
                intArrayOf(3, 126, 93),
                intArrayOf(4, 253, 124),
                intArrayOf(5, 125, 155),
                intArrayOf(6, 252, 186),
                intArrayOf(7, 124, 217),
                intArrayOf(8, 251, 248),
                intArrayOf(9, 123, 24),
                intArrayOf(10, 250, 55),
                intArrayOf(11, 122, 86),
                intArrayOf(12, 249, 117),
                intArrayOf(13, 121, 148),
                intArrayOf(14, 248, 179),
                intArrayOf(15, 120, 210),
                intArrayOf(16, 247, 241),
                intArrayOf(17, 119, 17),
                intArrayOf(18, 246, 48),
                intArrayOf(19, 118, 79),
                intArrayOf(20, 245, 110)
            )
            val segmentedBitmap = Bitmap.createBitmap(
                output, bitmap.width, bitmap.height,
                Bitmap.Config.ARGB_8888
            ).copy(Bitmap.Config.ARGB_8888, true)
            var idx = 0
            for (i in 0 until bitmap.height) {
                for (j in 0 until bitmap.width) {
                    val color = colors[output[idx++]]
                    segmentedBitmap.set(j, i, Color.rgb(color[0], color[1], color[2]))
                }
            }
            view.rootView.inputImage.setImageBitmap(segmentedBitmap)
        }
    }
}