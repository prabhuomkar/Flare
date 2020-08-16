package io.github.prabhuomkar.torchexpo.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import io.github.prabhuomkar.torchexpo.R
import io.github.prabhuomkar.torchexpo.util.FileUtil

@BindingAdapter("image")
fun loadImage(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view)
            .load(imageUrl)
            .error(R.mipmap.ic_launcher)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("canPlay")
fun canPlay(view: View, modelName: String?) {
    if (!modelName.isNullOrEmpty()) {
        view.visibility = if (FileUtil.isModelAssetFileDownloaded(view.context, modelName))
            View.VISIBLE else View.INVISIBLE
    }
}

@BindingAdapter("canDownload")
fun canDownload(view: View, modelName: String?) {
    if (!modelName.isNullOrEmpty()) {
        view.visibility = if (FileUtil.isModelAssetFileDownloaded(view.context, modelName))
            View.INVISIBLE else View.VISIBLE
    }
}