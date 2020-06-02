package com.jvmori.openweather.currentWeather.domain.repositories

import com.jvmori.openweather.currentWeather.domain.entities.CurrentWeatherEntity
import kotlinx.coroutines.flow.Flow

interface CurrentWeatherRepository {
    fun fetchCurrentWeather(city : String) : Flow<CurrentWeatherEntity>
    fun fetchAllWeather(): Flow<List<CurrentWeatherEntity>>
    suspend fun refreshCurrentWeatherList(): List<CurrentWeatherEntity>
}