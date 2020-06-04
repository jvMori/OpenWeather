package com.jvmori.openweather.currentWeather.domain.repositories

import com.jvmori.openweather.currentWeather.data.local.CurrentWeatherData
import kotlinx.coroutines.flow.Flow

interface CurrentWeatherLocalDataSource {
    fun observeAllWeather(): Flow<List<CurrentWeatherData>>
    suspend fun getAllWeather() : List<CurrentWeatherData>
    suspend fun saveCurrentWeather(data: CurrentWeatherData)
}