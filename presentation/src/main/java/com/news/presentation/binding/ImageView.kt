package com.news.presentation.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("picasso")
fun ImageView.bindLoadWithPicasso(image: String?) {
    if (!image.isNullOrEmpty()) Picasso.get()
        .load(image)
        .into(this)
}