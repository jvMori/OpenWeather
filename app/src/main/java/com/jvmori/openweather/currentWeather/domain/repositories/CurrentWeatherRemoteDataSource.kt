package com.jvmori.openweather.currentWeather.domain.repositories

import com.jvmori.openweather.currentWeather.data.network.response.CurrentWeatherResponse

interface CurrentWeatherRemoteDataSource {
    suspend fun fetchCurrentWeather(city: String): CurrentWeatherResponse
}