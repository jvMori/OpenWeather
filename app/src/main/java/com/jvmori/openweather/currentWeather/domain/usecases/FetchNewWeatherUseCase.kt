package com.jvmori.openweather.currentWeather.domain.usecases

import com.jvmori.openweather.currentWeather.domain.entities.CurrentWeatherEntity

interface FetchNewWeatherUseCase {
    suspend fun fetchCurrentWeather(city: String): CurrentWeatherEntity
}