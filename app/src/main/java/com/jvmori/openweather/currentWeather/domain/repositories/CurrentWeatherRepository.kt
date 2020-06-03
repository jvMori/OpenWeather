package com.jvmori.openweather.currentWeather.domain.repositories

import com.jvmori.openweather.common.data.Resource
import com.jvmori.openweather.currentWeather.domain.entities.CurrentWeatherEntity
import kotlinx.coroutines.flow.Flow

interface CurrentWeatherRepository {
    suspend fun fetchDefaultWeather(): Resource<String>
    suspend fun fetchNewWeather(city: String): Resource<String>
    fun fetchAllWeather(): Flow<Resource<List<CurrentWeatherEntity>>>
    suspend fun refreshCurrentWeatherList(): Resource<String>
}