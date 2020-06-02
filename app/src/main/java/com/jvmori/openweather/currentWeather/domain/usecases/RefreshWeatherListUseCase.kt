package com.jvmori.openweather.currentWeather.domain.usecases

import com.jvmori.openweather.currentWeather.domain.entities.CurrentWeatherEntity

interface RefreshWeatherListUseCase {
    suspend fun refreshCurrentWeatherList(): List<CurrentWeatherEntity>
}