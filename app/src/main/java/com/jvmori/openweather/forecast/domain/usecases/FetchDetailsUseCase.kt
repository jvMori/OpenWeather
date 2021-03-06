package com.jvmori.openweather.forecast.domain.usecases

import com.jvmori.openweather.common.data.network.Resource
import com.jvmori.openweather.currentWeather.domain.entities.Coordinates
import com.jvmori.openweather.forecast.domain.entities.WeatherDetailsEntity

interface FetchDetailsUseCase {
    suspend fun fetchForecast(coordinates: Coordinates): Resource<WeatherDetailsEntity>
}