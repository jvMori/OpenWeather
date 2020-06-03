package com.jvmori.openweather.currentWeather.domain.repositories

import com.jvmori.openweather.currentWeather.data.network.CurrentWeatherResponse

interface CurrentWeatherRemoteDataSource {
    suspend fun fetchCurrentWeather(city: String): CurrentWeatherResponse
    suspend fun refreshCurrentWeatherList(): List<CurrentWeatherResponse>
}