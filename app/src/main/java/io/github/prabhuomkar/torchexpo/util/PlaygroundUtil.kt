package io.github.prabhuomkar.torchexpo.util

import androidx.navigation.NavDirections
import io.github.prabhuomkar.torchexpo.ui.model.ModelFragmentDirections
import java.util.*

class PlaygroundUtil {

    companion object {
        fun getPlaygroundDestinationAction(taskName: String, modelName: String): NavDirections? {
            return when (taskName.toLowerCase(Locale.ROOT).replace(" ", "-")) {
                "image-classification" -> ModelFragmentDirections
                    .actionModelFragmentToImageClassificationFragment(modelName)
                "image-segmentation" -> ModelFragmentDirections
                    .actionModelFragmentToImageSegmentationFragment(modelName)
                "object-detection" -> ModelFragmentDirections
                    .actionModelFragmentToObjectDetectionFragment(modelName)
                "text-classification" -> ModelFragmentDirections
                    .actionModelFragmentToTextClassificationFragment(modelName)
                "text-to-speech-synthesis" -> ModelFragmentDirections
                    .actionModelFragmentToTextToSpeechSynthesisFragment(modelName)
                "image-generation" -> ModelFragmentDirections
                    .actionModelFragmentToImageGenerationFragment(modelName)
                else -> null
            }
        }
    }

}
