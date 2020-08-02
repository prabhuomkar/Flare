package io.github.prabhuomkar.torchexpo.util

import androidx.navigation.NavDirections
import io.github.prabhuomkar.torchexpo.ui.model.ModelFragmentDirections
import java.util.*
import kotlin.collections.ArrayList

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

        fun topK(values: FloatArray, k: Int): List<Pair<Int, Float>> {
            val indexWithScores = ArrayList<Pair<Int, Float>>()
            for (i in values.indices) indexWithScores.add(Pair(i, values[i]))
            indexWithScores.sortWith(Comparator { score1, score2 -> score2.second.compareTo(score1.second) })
            return indexWithScores.slice(IntRange(0, k))
        }
    }

}
