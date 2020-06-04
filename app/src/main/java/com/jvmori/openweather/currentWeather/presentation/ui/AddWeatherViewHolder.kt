package com.jvmori.openweather.currentWeather.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jvmori.openweather.R
import com.jvmori.openweather.databinding.AddWeatherItemBinding

class AddWeatherViewHolder(binding: AddWeatherItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(){}

    companion object {
        fun create(parent: ViewGroup): AddWeatherViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<AddWeatherItemBinding>(
                inflater,
                R.layout.add_weather_item,
                parent,
                false
            )
            return AddWeatherViewHolder(binding)
        }
    }
}