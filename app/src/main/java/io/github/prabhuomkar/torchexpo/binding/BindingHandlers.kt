package io.github.prabhuomkar.torchexpo.binding

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.navigation.findNavController
import io.github.prabhuomkar.torchexpo.ui.main.MainFragmentDirections
import io.github.prabhuomkar.torchexpo.ui.task.TaskFragmentDirections
import io.github.prabhuomkar.torchexpo.util.PlaygroundUtil

class BindingHandlers {

    fun openLink(view: View, url: String?) {
        if (!url.isNullOrEmpty()) {
            view.context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(url)
                )
            )
        }
    }

    fun navigateToPlayground(view: View, taskId: Int, modelId: Int) {
        val navDirection = PlaygroundUtil.getPlaygroundDestinationAction(taskId, modelId)
        if (navDirection != null) view.findNavController().navigate(navDirection)
    }

    fun navigateToTask(view: View, taskId: Int) {
        view.findNavController()
            .navigate(MainFragmentDirections.actionMainFragmentToTaskFragment(taskId))
    }

    fun navigateToModel(view: View, modelId: Int) {
        view.findNavController()
            .navigate(TaskFragmentDirections.actionTaskFragmentToModelFragment(modelId))
    }
}