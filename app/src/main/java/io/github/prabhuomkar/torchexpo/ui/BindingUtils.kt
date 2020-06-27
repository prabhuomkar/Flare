package io.github.prabhuomkar.torchexpo.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import io.github.prabhuomkar.torchexpo.R

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view)
        .load(url)
        .placeholder(R.mipmap.ic_launcher)
        .into(view)
}