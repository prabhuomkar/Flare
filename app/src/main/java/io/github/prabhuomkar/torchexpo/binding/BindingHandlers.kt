package io.github.prabhuomkar.torchexpo.binding

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.navigation.findNavController
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

    fun navigateToCollection(view: View, taskSlug: String) {
        // TODO(omkar): setup click from collection list to collection detail
    }

    fun navigateToModel(view: View, modelSlug: String) {
        // TODO(omkar): setup click from model list to model detail
    }

    fun navigateToPublisher(view: View, publisherSlug: String) {
        // TODO(omkar): setup click from publisher list to publisher detail
    }

}