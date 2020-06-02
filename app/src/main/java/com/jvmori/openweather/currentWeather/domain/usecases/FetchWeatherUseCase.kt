package com.jvmori.openweather.currentWeather.domain.usecases

import com.jvmori.openweather.currentWeather.domain.entities.CurrentWeatherEntity
import kotlinx.coroutines.flow.Flow

interface FetchWeatherUseCase {
    fun fetchCurrentWeather(city : String) : Flow<CurrentWeatherEntity>
}