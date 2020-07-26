package io.github.prabhuomkar.torchexpo.util

import androidx.navigation.NavDirections
import io.github.prabhuomkar.torchexpo.ui.model.ModelFragmentDirections
import java.util.*

class PlaygroundUtil {

    companion object {
        fun getPlaygroundDestinationAction(taskName: String, modelId: String): NavDirections? {
            when (taskName.toLowerCase(Locale.ROOT).replace(" ", "")) {
                "ImageClassification" -> return ModelFragmentDirections
                    .actionModelFragmentToImageClassificationFragment(modelId)
                "ImageSegmentation" -> return ModelFragmentDirections
                    .actionModelFragmentToImageSegmentationFragment(modelId)
                "ObjectDetection" -> return ModelFragmentDirections
                    .actionModelFragmentToObjectDetectionFragment(modelId)
                "TextClassification" -> return ModelFragmentDirections
                    .actionModelFragmentToTextClassificationFragment(modelId)
                "TextToSpeechSynthesis" -> return ModelFragmentDirections
                    .actionModelFragmentToTextToSpeechSynthesisFragment(modelId)
                "ImageGeneration" -> return ModelFragmentDirections
                    .actionModelFragmentToImageGenerationFragment(modelId)
                else -> return null
            }
        }
    }

}
