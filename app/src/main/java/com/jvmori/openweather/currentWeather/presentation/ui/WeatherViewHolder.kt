package com.jvmori.openweather.currentWeather.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jvmori.openweather.R
import com.jvmori.openweather.currentWeather.domain.entities.CurrentWeatherEntity
import com.jvmori.openweather.databinding.WeatherItemBinding

class WeatherViewHolder(
    private val binding: WeatherItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    var onClickListener: ((data: CurrentWeatherEntity) -> Unit)? = null

    fun bind(item: CurrentWeatherEntity) {
        binding.weather = item
        binding.executePendingBindings()
        binding.root.setOnClickListener {
            onClickListener?.invoke(item)
        }
    }

    companion object {
        fun create(parent: ViewGroup): WeatherViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<WeatherItemBinding>(
                inflater,
                R.layout.weather_item,
                parent,
                false
            )
            return WeatherViewHolder(binding)
        }
    }
}