package io.github.prabhuomkar.torchexpo.util

import androidx.navigation.NavDirections
import io.github.prabhuomkar.torchexpo.ui.model.ModelFragmentDirections

class PlaygroundUtil {

    companion object {
        fun getPlaygroundDestinationAction(taskId: Int, modelId: Int): NavDirections? {
            when (taskId) {
                1 -> return ModelFragmentDirections
                    .actionModelFragmentToImageClassificationFragment(modelId)
                2 -> return ModelFragmentDirections
                    .actionModelFragmentToImageSegmentationFragment(modelId)
                3 -> return ModelFragmentDirections
                    .actionModelFragmentToObjectDetectionFragment(modelId)
                4 -> return ModelFragmentDirections
                    .actionModelFragmentToTextClassificationFragment(modelId)
                5 -> return ModelFragmentDirections
                    .actionModelFragmentToTextToSpeechSynthesisFragment(modelId)
                6 -> return ModelFragmentDirections
                    .actionModelFragmentToImageGenerationFragment(modelId)
                else -> return null
            }
        }
    }

}
