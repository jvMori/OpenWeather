package com.jvmori.openweather.currentWeather.domain.usecases

import com.jvmori.openweather.common.data.Actions
import com.jvmori.openweather.common.data.Resource

interface RefreshWeatherListUseCase {
    suspend fun refreshCurrentWeatherList(): Resource<Actions>
}