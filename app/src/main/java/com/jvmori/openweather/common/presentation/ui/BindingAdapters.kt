package com.jvmori.openweather.common.presentation.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.api.load

@BindingAdapter("loadImage")
fun bindImage(imageView: ImageView, url: String) {
    imageView.load("http://openweathermap.org/img/wn/${url}@2x.png") {
        crossfade(true)
    }
}
