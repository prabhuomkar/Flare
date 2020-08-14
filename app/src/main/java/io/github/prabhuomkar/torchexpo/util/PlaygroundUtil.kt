package io.github.prabhuomkar.torchexpo.util

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import io.github.prabhuomkar.torchexpo.R
import io.github.prabhuomkar.torchexpo.ui.model.ModelFragmentDirections
import java.util.Locale
import kotlin.Comparator
import kotlin.collections.ArrayList

class PlaygroundUtil {

    companion object {
        fun getPlaygroundDestinationAction(taskName: String, modelName: String): NavDirections? {
            return when (taskName.toLowerCase(Locale.ROOT).replace(" ", "-")) {
                "image-classification" -> ModelFragmentDirections
                    .actionModelFragmentToImageClassificationFragment(modelName)
                "image-segmentation" -> ModelFragmentDirections
                    .actionModelFragmentToImageSegmentationFragment(modelName)
                "sentiment-analysis" -> ModelFragmentDirections
                    .actionModelFragmentToSentimentAnalysisFragment(modelName)
                else -> null
            }
        }

        fun chooseImage(fragment: Fragment) {
            val intent = Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT)
            fragment.startActivityForResult(
                Intent.createChooser(
                    intent,
                    fragment.context?.getString(R.string.choose_image)
                ), 1
            )
        }

        fun topK(values: FloatArray, k: Int): List<Pair<Int, Float>> {
            val indexWithScores = ArrayList<Pair<Int, Float>>()
            for (i in values.indices) indexWithScores.add(Pair(i, values[i]))
            indexWithScores.sortWith(Comparator { score1, score2 -> score2.second.compareTo(score1.second) })
            return indexWithScores.slice(IntRange(0, k))
        }
    }
}
