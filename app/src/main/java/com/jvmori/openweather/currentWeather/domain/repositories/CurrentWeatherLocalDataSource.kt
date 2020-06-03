package com.jvmori.openweather.currentWeather.domain.repositories

import com.jvmori.openweather.currentWeather.data.local.CurrentWeatherData
import kotlinx.coroutines.flow.Flow

interface CurrentWeatherLocalDataSource {
    fun fetchAllWeather(): Flow<List<CurrentWeatherData>>
    suspend fun saveCurrentWeather(data: CurrentWeatherData)
}