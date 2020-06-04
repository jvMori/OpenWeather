package com.jvmori.openweather.currentWeather.presentation.ui

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jvmori.openweather.R
import com.jvmori.openweather.currentWeather.domain.entities.CurrentWeatherEntity

class WeatherAdapter(
    private var items: List<CurrentWeatherEntity>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.weather_item -> WeatherViewHolder.create(parent)
            R.layout.add_weather_item -> AddWeatherViewHolder.create(parent)
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    fun submitList(items : List<CurrentWeatherEntity>){
        this.items = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)){
            R.layout.weather_item -> (holder as WeatherViewHolder).bind(items[position])
            R.layout.add_weather_item -> (holder as AddWeatherViewHolder).bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) {
            R.layout.add_weather_item
        } else {
            R.layout.weather_item
        }

    }

    override fun getItemCount(): Int {
        return items.size + 1
    }

    companion object{
        fun initGridAdapter(recyclerView: RecyclerView, context: Context, items : List<CurrentWeatherEntity>): WeatherAdapter {
            return WeatherAdapter(items).apply {
                recyclerView.adapter = this
                recyclerView.layoutManager = GridLayoutManager(context, 2)
            }
        }
    }

}