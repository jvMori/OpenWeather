package com.jvmori.openweather.currentWeather.domain.repositories

import com.jvmori.openweather.common.data.Actions
import com.jvmori.openweather.common.data.Resource
import com.jvmori.openweather.currentWeather.domain.entities.CurrentWeatherEntity
import kotlinx.coroutines.flow.Flow

interface CurrentWeatherRepository {
    suspend fun initDefaultWeather() : Resource<Actions>
    suspend fun fetchNewWeather(city: String): Resource<Actions>
    fun observeAllWeather(): Flow<Resource<List<CurrentWeatherEntity>>>
    suspend fun refreshCurrentWeatherList(): Resource<Actions>
}