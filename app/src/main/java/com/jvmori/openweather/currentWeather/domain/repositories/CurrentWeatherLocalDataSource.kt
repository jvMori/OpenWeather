package com.jvmori.openweather.currentWeather.domain.repositories

import com.jvmori.openweather.currentWeather.data.local.CurrentWeatherData
import kotlinx.coroutines.flow.Flow

interface CurrentWeatherLocalDataSource {
    suspend fun fetchCurrentWeather(city: String): Flow<CurrentWeatherData>
    fun fetchAllWeather(): Flow<List<CurrentWeatherData>>
    suspend fun saveCurrentWeather(data: CurrentWeatherData)
    suspend fun saveAllWeather(data: List<CurrentWeatherData>)
}