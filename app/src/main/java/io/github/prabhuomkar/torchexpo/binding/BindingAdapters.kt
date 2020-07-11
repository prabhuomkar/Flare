package io.github.prabhuomkar.torchexpo.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import io.github.prabhuomkar.torchexpo.R

@BindingAdapter("image")
fun loadImage(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view)
            .load(imageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

