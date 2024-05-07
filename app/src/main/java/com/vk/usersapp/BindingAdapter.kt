package com.vk.usersapp

import android.widget.ImageView
import com.bumptech.glide.Glide
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(imageView: ImageView, imageUrl: String?) {
        imageUrl?.let {
            Glide.with(imageView.context)
                .load(it)
                .into(imageView)
        }
    }

}