package io.github.prabhuomkar.torchexpo.util

import io.github.prabhuomkar.torchexpo.R

class PlaygroundUtil {

    companion object {
        fun getPlaygroundDestinationAction(taskId: Int): Int {
            when (taskId) {
                1 -> return R.id.action_modelFragment_to_imageClassificationFragment
                2 -> return R.id.action_modelFragment_to_imageSegmentationFragment
                else -> return 0
            }
        }
    }

}
