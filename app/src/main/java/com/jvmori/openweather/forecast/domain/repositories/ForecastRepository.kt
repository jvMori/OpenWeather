package com.jvmori.openweather.forecast.domain.repositories

import com.jvmori.openweather.common.data.Resource
import com.jvmori.openweather.currentWeather.domain.entities.Coordinates
import com.jvmori.openweather.forecast.domain.entities.WeatherDetailsEntity

interface ForecastRepository {
    suspend fun fetchForecast(coordinates: Coordinates): Resource<WeatherDetailsEntity>
}