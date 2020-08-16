package io.github.prabhuomkar.torchexpo.binding

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Button
import androidx.navigation.findNavController
import io.github.prabhuomkar.torchexpo.ui.main.MainFragmentDirections
import io.github.prabhuomkar.torchexpo.ui.task.TaskFragmentDirections
import io.github.prabhuomkar.torchexpo.util.DownloadUtil
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

    fun navigateToPlayground(view: View, taskName: String, modelName: String) {
        val navDirection = PlaygroundUtil.getPlaygroundDestinationAction(taskName, modelName)
        if (navDirection != null) view.findNavController().navigate(navDirection)
    }

    fun navigateToTask(view: View, taskId: String) {
        view.findNavController()
            .navigate(MainFragmentDirections.actionMainFragmentToTaskFragment(taskId))
    }

    fun navigateToModel(view: View, modelId: String) {
        view.findNavController()
            .navigate(TaskFragmentDirections.actionTaskFragmentToModelFragment(modelId))
    }

    fun downloadModel(view: View, modelName: String?, downloadLink: String?) {
        if (!modelName.isNullOrEmpty() && !downloadLink.isNullOrEmpty()) {
            val button = view as Button
            button.text = "Downloading..."
            DownloadUtil.download(view.context, modelName, downloadLink)
        }
    }
}