package io.github.prabhuomkar.torchexpo.ui.playground

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import androidx.lifecycle.AndroidViewModel
import io.github.prabhuomkar.torchexpo.ui.playground.datasets.ImageNet
import io.github.prabhuomkar.torchexpo.util.FileUtil
import io.github.prabhuomkar.torchexpo.util.PlaygroundUtil
import kotlinx.android.synthetic.main.image_classification_fragment.view.*
import org.pytorch.IValue
import org.pytorch.Module
import org.pytorch.Tensor
import org.pytorch.torchvision.TensorImageUtils

class PlaygroundViewModel(application: Application) : AndroidViewModel(application) {

    private val context: Context = application.applicationContext

    private fun input(bitmap: Bitmap): Tensor = TensorImageUtils.bitmapToFloat32Tensor(
        bitmap,
        TensorImageUtils.TORCHVISION_NORM_MEAN_RGB, TensorImageUtils.TORCHVISION_NORM_STD_RGB
    )

    fun runImageClassification(view: View, modelName: String?, bitmapUri: Uri?) {
        if (!modelName.isNullOrEmpty() && bitmapUri != null) {
            val bitmap = FileUtil.getBitmap(context, bitmapUri)
            val module = Module.load(FileUtil.getModelAssetFilePath(context, modelName))
            val values = module.forward(IValue.from(input(bitmap))).toTensor().dataAsFloatArray
            val result = PlaygroundUtil.topK(values, 3)
            if (result.isNotEmpty()) {
                view.rootView.class1_text.text = ImageNet.TARGET_CLASSES[result[0].first]
                view.rootView.class1_score.text = "%.2f".format(result[0].second)
                view.rootView.class2_text.text = ImageNet.TARGET_CLASSES[result[1].first]
                view.rootView.class2_score.text = "%.2f".format(result[1].second)
                view.rootView.class3_text.text = ImageNet.TARGET_CLASSES[result[2].first]
                view.rootView.class3_score.text = "%.2f".format(result[2].second)
            }
        }
    }

}