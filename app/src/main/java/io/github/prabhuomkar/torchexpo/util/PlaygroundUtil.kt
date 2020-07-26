package io.github.prabhuomkar.torchexpo.util

import androidx.navigation.NavDirections
import io.github.prabhuomkar.torchexpo.ui.model.ModelFragmentDirections
import java.util.*

class PlaygroundUtil {

    companion object {
        fun getPlaygroundDestinationAction(taskName: String, modelId: String): NavDirections? {
            when (taskName.toLowerCase(Locale.ROOT).replace(" ", "-")) {
                "image-classification" -> return ModelFragmentDirections
                    .actionModelFragmentToImageClassificationFragment(modelId)
                "image-segmentation" -> return ModelFragmentDirections
                    .actionModelFragmentToImageSegmentationFragment(modelId)
                "object-detection" -> return ModelFragmentDirections
                    .actionModelFragmentToObjectDetectionFragment(modelId)
                "text-classification" -> return ModelFragmentDirections
                    .actionModelFragmentToTextClassificationFragment(modelId)
                "text-to-speech-synthesis" -> return ModelFragmentDirections
                    .actionModelFragmentToTextToSpeechSynthesisFragment(modelId)
                "image-generation" -> return ModelFragmentDirections
                    .actionModelFragmentToImageGenerationFragment(modelId)
                else -> return null
            }
        }
    }

}
