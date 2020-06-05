package com.jvmori.openweather.forecast.domain.repositories

import com.jvmori.openweather.currentWeather.domain.entities.Coordinates
import com.jvmori.openweather.forecast.data.network.response.ForecastResponse

interface ForecastNetworkDataSource {
    suspend fun fetchForecast(coordinates: Coordinates): ForecastResponse?
}