package io.github.prabhuomkar.torchexpo.ui.playground.vision

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.view.View
import androidx.core.graphics.set
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import io.github.prabhuomkar.torchexpo.R
import io.github.prabhuomkar.torchexpo.torchexpo.TensorOperations
import io.github.prabhuomkar.torchexpo.ui.playground.common.ColorPalette
import io.github.prabhuomkar.torchexpo.ui.playground.common.ImageNet
import io.github.prabhuomkar.torchexpo.util.FileUtil
import io.github.prabhuomkar.torchexpo.util.PlaygroundUtil
import io.github.prabhuomkar.torchexpo.util.UIUtil
import kotlinx.android.synthetic.main.image_classification_fragment.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.pytorch.IValue
import org.pytorch.Module
import org.pytorch.Tensor
import org.pytorch.torchvision.TensorImageUtils

class VisionViewModel(application: Application) : AndroidViewModel(application) {

    private val context: Context = application.applicationContext
    private lateinit var module: Module

    private fun input(bitmap: Bitmap): Tensor = TensorImageUtils.bitmapToFloat32Tensor(
        bitmap,
        TensorImageUtils.TORCHVISION_NORM_MEAN_RGB, TensorImageUtils.TORCHVISION_NORM_STD_RGB
    )

    fun clearPlayground() = module.destroy()

    private fun loadModel(view: View, modelName: String) {
        try {
            module = Module.load(FileUtil.getModelAssetFilePath(context, modelName))
        } catch (e: Exception) {
            e.printStackTrace()
            UIUtil.showSnackbar(view.context, "Model cannot be loaded")
        }
    }

    fun runImageClassification(view: View, modelName: String?, bitmapUri: Uri?) = viewModelScope
        .launch(Dispatchers.IO) {
            UIUtil.showSnackbar(view.context, "Predicting...")
            if (!modelName.isNullOrEmpty() && bitmapUri != null) {
                val bitmap = FileUtil.getBitmap(context, bitmapUri, true)
                loadModel(view, modelName)
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

    fun runImageSegmentation(view: View, modelName: String?, bitmapUri: Uri?) = viewModelScope
        .launch(Dispatchers.IO) {
            UIUtil.showSnackbar(view.context, "Segmenting...")
            if (!modelName.isNullOrEmpty() && bitmapUri != null) {
                val bitmap = FileUtil.getBitmap(context, bitmapUri, false)
                loadModel(view, modelName)
                val values = module.forward(IValue.from(input(bitmap))).toTensor()
                val shape = values.shape()
                val output = TensorOperations.argmax(
                    values.dataAsFloatArray, shape[0].toInt(), shape[1].toInt(),
                    shape[2].toInt()
                )
                val segmentedBitmap = Bitmap.createBitmap(
                    output, bitmap.width, bitmap.height,
                    Bitmap.Config.ARGB_8888
                ).copy(Bitmap.Config.ARGB_8888, true)
                var idx = 0
                for (i in 0 until bitmap.height) {
                    for (j in 0 until bitmap.width) {
                        val color = ColorPalette.COLOURS[output[idx++]]
                        segmentedBitmap.set(j, i, Color.rgb(color[0], color[1], color[2]))
                    }
                }
                view.rootView.inputImage.setImageBitmap(segmentedBitmap)
            }
        }
}