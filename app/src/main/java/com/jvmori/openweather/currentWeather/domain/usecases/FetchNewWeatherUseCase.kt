package com.jvmori.openweather.currentWeather.domain.usecases

import com.jvmori.openweather.common.data.Actions
import com.jvmori.openweather.common.data.Resource

interface FetchNewWeatherUseCase {
    suspend fun fetchCurrentWeather(city: String): Resource<Actions>
}