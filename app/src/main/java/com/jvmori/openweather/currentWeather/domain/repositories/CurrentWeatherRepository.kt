package com.jvmori.openweather.currentWeather.domain.repositories

import com.jvmori.openweather.common.data.Resource
import com.jvmori.openweather.currentWeather.domain.entities.CurrentWeatherEntity
import kotlinx.coroutines.flow.Flow

interface CurrentWeatherRepository {
    suspend fun fetchCurrentWeather(city: String): Resource<CurrentWeatherEntity>
    fun fetchAllWeather(): Flow<Resource<CurrentWeatherEntity>>
    suspend fun refreshCurrentWeatherList(): List<CurrentWeatherEntity>
}